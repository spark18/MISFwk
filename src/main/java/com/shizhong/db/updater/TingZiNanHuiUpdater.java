package com.shizhong.db.updater;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.google.gson.Gson;
import com.shizhong.db.dao.STATUS;
import com.shizhong.db.dao.SZTransaction;
import com.shizhong.db.dao.TingZiNanHui;
import com.shizhong.db.util.HibernateUtil;
import com.shizhong.db.util.SolrUtil;
import com.shizhong.web.meta.Parameter;
import com.shizhong.web.session.UserPool;

public class TingZiNanHuiUpdater implements Updater<TingZiNanHui> {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public TingZiNanHui update(Map<String, Parameter> parameters) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();

        Criteria criteria = session.createCriteria(TingZiNanHui.class);
        Parameter<Long> id = parameters.get("id");
        criteria.add(Restrictions.eq(id.getName(), id.getValue()));
        TingZiNanHui tingzi = (TingZiNanHui) criteria.uniqueResult();

        if (null == tingzi) {
            return tingzi;
        }

        Parameter<String> line = parameters.get("line");
        Parameter<String> area = parameters.get("area");
        Parameter<String> stopnum = parameters.get("stopnum");
        Parameter<String> entitynum = parameters.get("entitynum");
        Parameter<String> nanhuitingtype = parameters.get("nanhuitingType");
        Parameter<String> picnumber = parameters.get("picnumber");
        Parameter<String> road = parameters.get("road");
        Parameter<String> stop = parameters.get("stop");
        Parameter<String> finalstop = parameters.get("finalstop");
        Parameter<String> nextstop = parameters.get("nextstop");
        Parameter<String> address = parameters.get("address");
        Parameter<String> direction = parameters.get("direction");
        Parameter<String> digtime = parameters.get("digtime");
        Parameter<String> finishdate = parameters.get("finishdate");
        Parameter<String> lastcaredate = parameters.get("lastCareDate");
        Parameter<String> type = parameters.get("type");
        Parameter<String> comments = parameters.get("comments");
        Parameter<String> adop = parameters.get("adop");
        Parameter<String> adstart = parameters.get("adstart");
        Parameter<String> adend = parameters.get("adend");

        Map<String, String[]> modifiedFields = new HashMap<String, String[]>();
        String newLine = UpdaterRepo.recordMofied(tingzi, line, modifiedFields);

        String newArea = UpdaterRepo.recordMofied(tingzi, area, modifiedFields);
        String newStopnum = UpdaterRepo.recordMofied(tingzi, stopnum, modifiedFields);
        String newEntitynum = UpdaterRepo.recordMofied(tingzi, entitynum, modifiedFields);
        String newNanhuitingType = UpdaterRepo.recordMofied(tingzi, nanhuitingtype, modifiedFields);

        String newPicNumber = UpdaterRepo.recordMofied(tingzi, picnumber, modifiedFields);

        String newRoad = UpdaterRepo.recordMofied(tingzi, road, modifiedFields);

        String newStop = UpdaterRepo.recordMofied(tingzi, stop, modifiedFields);

        String newFinalStop = UpdaterRepo.recordMofied(tingzi, finalstop, modifiedFields);

        String newNextstop = UpdaterRepo.recordMofied(tingzi, nextstop, modifiedFields);

        String newAddress = UpdaterRepo.recordMofied(tingzi, address, modifiedFields);

        String newDirection = UpdaterRepo.recordMofied(tingzi, direction, modifiedFields);

        String newType = UpdaterRepo.recordMofied(tingzi, type, modifiedFields);

        String newComments = UpdaterRepo.recordMofied(tingzi, comments, modifiedFields);

        String newAdOp = UpdaterRepo.recordMofied(tingzi, adop, modifiedFields);

        // SimpleDateFormat formatter_zh_CN = new
        // SimpleDateFormat("MMM dd,yyyy", Locale.CHINA);
        // Date newDigdate = tingzi.getDigdate();
        Date newDigdate = UpdaterRepo.recordDateModified(tingzi, digtime, modifiedFields);
        Date newFinishdate = UpdaterRepo.recordDateModified(tingzi, finishdate, modifiedFields);
        Date newAdstart = UpdaterRepo.recordDateModified(tingzi, adstart, modifiedFields);
        Date newAdend = UpdaterRepo.recordDateModified(tingzi, adend, modifiedFields);
        Date newLastCareDate = UpdaterRepo.recordDateModified(tingzi, lastcaredate, modifiedFields);
        System.out.println(modifiedFields);

        TingZiNanHui newTingzi = null;
        // try {
        // newDigdate = formatter_zh_CN.parse(digtime.getValue());
        // if (tingzi.getDigdate().compareTo(newDigdate) != 0) {
        // modifiedFields.put("digdate", new String[] { newDigdate.toString(),
        // tingzi.getDigdate().toString(), });
        // }
        // newFinishdate = formatter_zh_CN.parse(finishdate.getValue());
        // if (tingzi.getFinishdate().compareTo(newFinishdate) != 0) {
        // modifiedFields.put("finishdate", new String[] {
        // newFinishdate.toString(),
        // tingzi.getFinishdate().toString(), });
        // }
        // newAdstart = formatter_zh_CN.parse(adstart.getValue());
        // if (tingzi.getAdstart().compareTo(newAdstart) != 0) {
        // modifiedFields.put("adstart", new String[] { newAdstart.toString(),
        // tingzi.getAdstart().toString(), });
        // }
        // newAdend = formatter_zh_CN.parse(adend.getValue());
        // if (tingzi.getAdend().compareTo(newAdend) != 0) {
        // modifiedFields.put("adend", new String[] { newAdend.toString(),
        // tingzi.getAdend().toString(), });
        // }
        // newLastCareDate = formatter_zh_CN.parse(lastcaredate.getValue());
        // if (tingzi.getLastCareDate().compareTo(newLastCareDate) != 0) {
        // modifiedFields.put("lastcaredate", new String[] {
        // newLastCareDate.toString(),
        // tingzi.getLastCareDate().toString(), });
        // }
        //
        // } catch (ParseException e) {
        // e.printStackTrace();
        // }
        if (!modifiedFields.isEmpty()) {
            SZTransaction szTransaction = new SZTransaction();
            Parameter<String> user = parameters.get("username");
            szTransaction.setUser(UserPool.getUser(user.getValue()));

            tingzi.setStatus(STATUS.archive.name());
            session.save(tingzi);

            newTingzi = new TingZiNanHui();
            newTingzi.setAddress(newAddress);
            newTingzi.setArea(newArea);
            newTingzi.setEntitynum(newEntitynum);
            newTingzi.setStopnum(newStopnum);
            newTingzi.setNanhuitingType(newNanhuitingType);
            newTingzi.setComments(newComments);
            newTingzi.setDigtime(newDigdate);
            newTingzi.setDirection(newDirection);
            newTingzi.setFinalstop(newFinalStop);
            newTingzi.setFinishdate(newFinishdate);
            newTingzi.setLine(newLine);
            newTingzi.setNextstop(newNextstop);
            newTingzi.setPicnumber(newPicNumber);
            newTingzi.setRoad(newRoad);
            newTingzi.setStop(newStop);
            newTingzi.setType(newType);
            newTingzi.setEntityindex(tingzi.getEntityindex());
            newTingzi.setAdop(newAdOp);
            newTingzi.setAdstart(newAdstart);
            newTingzi.setAdend(newAdend);
            newTingzi.setLastCareDate(newLastCareDate);
            newTingzi.setModify(true);
            newTingzi.setStatus(STATUS.normal.name());

            szTransaction.setTimeStamp(new Date());
            Gson convertor = new Gson();
            Map<String, Object> modifiedFieldsMeta = new HashMap<String, Object>();
            modifiedFieldsMeta.put("entity", "tingzinanhui");
            modifiedFieldsMeta.put("fields", modifiedFields.keySet());
            modifiedFieldsMeta.put("id", tingzi.getId());

            Parameter<String> username = parameters.get("username");
            modifiedFieldsMeta.put("modifier", username.getValue());
            szTransaction.setComments(convertor.toJson(modifiedFieldsMeta));
            szTransaction.setTingzinanhui(newTingzi);
            session.save(szTransaction);
        }
        transaction.commit();
        session.close();

        if (modifiedFields.isEmpty()) {
            return tingzi;
        }

        SolrQuery query = new SolrQuery();
        String queryStr = "id:TingZiNanHui\\:" + tingzi.getId();
        query.setQuery(queryStr);
        SimpleDateFormat formatter_en_US = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.ENGLISH);
        try {
            CommonsHttpSolrServer solrServer = SolrUtil.solrServer;
            QueryResponse response = solrServer.query(query);
            SolrDocumentList solrResults = response.getResults();
            solrServer.deleteByQuery(queryStr);
            solrServer.commit();

            SolrInputDocument solrDocument = new SolrInputDocument();
            solrDocument.addField("id", "TingZiNanHui:" + newTingzi.getId());
            solrDocument.addField("tingzinhaddress", newTingzi.getAddress());
            solrDocument.addField("tingzinharea", newTingzi.getArea());
            solrDocument.addField("tingzinhstopnum", newTingzi.getStopnum());
            solrDocument.addField("tingzinhentitynum", newTingzi.getEntitynum());
            solrDocument.addField("tingzinhtype", newTingzi.getNanhuitingType());
            solrDocument.addField("tingzinhcomments", newTingzi.getComments());
            Date digtimeDate = newTingzi.getDigtime();
            solrDocument.addField("tingzinhdigtime", digtimeDate == null ? "" : formatter_en_US.format(digtimeDate));
            solrDocument.addField("tingzinhdirection", newTingzi.getDirection());
            solrDocument.addField("tingzinhfinalstop", newTingzi.getFinalstop());
            Date finishdateDate = newTingzi.getFinishdate();
            solrDocument.addField("tingzinhfinishdate",
                    finishdateDate == null ? "" : formatter_en_US.format(finishdateDate));
            solrDocument.addField("tingzinhline", newTingzi.getLine());
            solrDocument.addField("tingzinhnextstop", newTingzi.getNextstop());
            solrDocument.addField("tingzinhpicnumber", newTingzi.getPicnumber());
            solrDocument.addField("tingzinhroad", newTingzi.getRoad());
            solrDocument.addField("tingzinhmodifytype", newTingzi.getType());
            solrDocument.addField("tingzinhstop", newTingzi.getStop());
            solrDocument.addField("tingzinhadop", newTingzi.getAdop());
            Date adstartDate = newTingzi.getAdstart();
            solrDocument.addField("tingzinhadstart", adstartDate == null ? "" : formatter_en_US.format(adstartDate));
            Date adendDate = newTingzi.getAdend();
            solrDocument.addField("tingzinhadend", adendDate == null ? "" : formatter_en_US.format(adendDate));
            Date lastCareDateDate = newTingzi.getLastCareDate();
            solrDocument.addField("tingzinhlastcare",
                    lastCareDateDate == null ? "" : formatter_en_US.format(lastCareDateDate));
            solrDocument.addField("modify", newTingzi.getModify());
            solrDocument.addField("entityindex", newTingzi.getEntityindex());

            solrServer.add(solrDocument);
            solrServer.commit();
            System.out.println(solrResults);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newTingzi;
    }

}
