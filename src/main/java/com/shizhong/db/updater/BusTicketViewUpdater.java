package com.shizhong.db.updater;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.google.gson.Gson;
import com.shizhong.db.dao.BusTicket;
import com.shizhong.db.dao.BusTicketAir;
import com.shizhong.db.dao.DownLine;
import com.shizhong.db.dao.IBusTicket;
import com.shizhong.db.dao.STATUS;
import com.shizhong.db.dao.SZTransaction;
import com.shizhong.db.dao.UpLine;
import com.shizhong.db.util.HibernateUtil;
import com.shizhong.web.meta.Parameter;
import com.shizhong.web.session.UserPool;

public class BusTicketViewUpdater implements Updater<IBusTicket> {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public IBusTicket update(Map<String, Parameter> parameters) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();

        Criteria criteria = session.createCriteria(BusTicket.class);
        Parameter<String> line = parameters.get("line");
        criteria.add(Restrictions.eq(line.getName(), line.getValue()));
        criteria.add(Restrictions.eq("status", STATUS.normal.name()));

        BusTicket busTicket = (BusTicket) criteria.uniqueResult();

        if (null == busTicket) {
            return busTicket;
        }

        Parameter<String> type = parameters.get("type");
        Parameter<String> normPrice = parameters.get("normPrice");
        Parameter<String> pricetype = parameters.get("pricetype");
        Parameter<String> autoSale = parameters.get("autoSale");
        Parameter<String> opunit = parameters.get("opunit");

        Map<String, String[]> modifiedFields = new HashMap<String, String[]>();
        String newType = UpdaterRepo.recordMofied(busTicket, type, modifiedFields);
        String newNormPrice = UpdaterRepo.recordMofied(busTicket, normPrice, modifiedFields);
        String newPricetype = UpdaterRepo.recordMofied(busTicket, pricetype, modifiedFields);
        String newAutoSale = UpdaterRepo.recordMofied(busTicket, autoSale, modifiedFields);
        String newOpunit = UpdaterRepo.recordMofied(busTicket, opunit, modifiedFields);

        IBusTicket newticket = null;
        if ("air".equals(newType)) {
            newticket = new BusTicketAir();
        } else {
            newticket = new BusTicket();
        }
        if (!modifiedFields.isEmpty()) {
            newticket.setLine(busTicket.getLine());
            newticket.setType(newType);
            double norm = 1.0;
            try {
                norm = Double.parseDouble(newNormPrice);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if ("norm".equals(newType)) {
                newticket.setNormPrice(norm);
            } else {
                newticket.setAirPrice(norm);
            }
            newticket.setPricetype(newPricetype);
            if ("multi".equals(pricetype.getValue()) && !modifiedFields.containsKey("type")) {
                newticket.setImage(busTicket.getImage());
            }
            newticket.setAutoSale(newAutoSale);
            newticket.setOpunit(newOpunit);
            newticket.setStatus(STATUS.normal.name());
            session.save(newticket);

            busTicket.setStatus(STATUS.archive.name());
            session.save(busTicket);

            criteria = session.createCriteria(UpLine.class);
            criteria.add(Restrictions.eq("line", busTicket.getLine()));
            List uplines = (List) criteria.list();
            for (Object uplineObj : uplines) {
                UpLine upline = (UpLine) uplineObj;
                if (newType.equals("norm")) {
                    upline.setBusTicket((BusTicket) newticket);
                    upline.setBusTicketAir(null);
                } else {
                    upline.setBusTicket(null);
                    upline.setBusTicketAir((BusTicketAir) newticket);
                }
                session.save(upline);
            }

            criteria = session.createCriteria(DownLine.class);
            criteria.add(Restrictions.eq("line", busTicket.getLine()));
            List downlines = (List) criteria.list();
            for (Object downlineObj : downlines) {
                DownLine downline = (DownLine) downlineObj;
                if (newType.equals("norm")) {
                    downline.setBusTicket((BusTicket) newticket);
                    downline.setBusTicketAir(null);
                } else {
                    downline.setBusTicket(null);
                    downline.setBusTicketAir((BusTicketAir) newticket);
                }
                session.save(downline);
            }

            SZTransaction szTransaction = new SZTransaction();
            Parameter<String> user = parameters.get("username");
            szTransaction.setUser(UserPool.getUser(user.getValue()));
            szTransaction.setTimeStamp(new Date());
            Gson convertor = new Gson();
            Map<String, Object> modifiedFieldsMeta = new HashMap<String, Object>();
            modifiedFieldsMeta.put("entity", "busticket");

            if(modifiedFields.containsKey("airPrice")) {
                modifiedFields.put("normPrice", modifiedFields.remove("airPrice"));
            }
            modifiedFieldsMeta.put("fields", modifiedFields.keySet());
            modifiedFieldsMeta.put("id", busTicket.getId());

            Parameter<String> username = parameters.get("username");
            modifiedFieldsMeta.put("modifier", username.getValue());
            szTransaction.setComments(convertor.toJson(modifiedFieldsMeta));

            if (newType.equals("norm")) {
                szTransaction.setBusticket((BusTicket) newticket);
            } else {
                szTransaction.setBusticketair((BusTicketAir) newticket);
            }
            session.save(szTransaction);

            transaction.commit();
            session.close();
        }
        return newticket;
    }

}
