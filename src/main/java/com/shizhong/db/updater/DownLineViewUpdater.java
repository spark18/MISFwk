package com.shizhong.db.updater;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.google.gson.Gson;
import com.shizhong.db.dao.DownLine;
import com.shizhong.db.dao.STATUS;
import com.shizhong.db.dao.SZTransaction;
import com.shizhong.db.util.HibernateUtil;
import com.shizhong.web.meta.Parameter;
import com.shizhong.web.session.UserPool;

public class DownLineViewUpdater implements Updater<DownLine> {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DownLine update(Map<String, Parameter> parameters) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();

		Criteria criteria = session.createCriteria(DownLine.class);
		Parameter<Long> id = parameters.get("id");
		criteria.add(Restrictions.eq(id.getName(), id.getValue()));
		DownLine downline = (DownLine) criteria.uniqueResult();

		if (null == downline) {
			return null;
		}

		Parameter<String> name = parameters.get("name");
        Parameter<String> alias = parameters.get("alias");
        Parameter<String> stopaddress = parameters.get("stopaddress");
        Parameter<String> startend = parameters.get("startend");

        Map<String, String[]> modifiedFields = new HashMap<String, String[]>();
        String newName = UpdaterRepo.recordMofied(downline, name, modifiedFields);
        String newAlias = UpdaterRepo.recordMofied(downline, alias, modifiedFields);
        String newStopaddress = UpdaterRepo.recordMofied(downline, stopaddress, modifiedFields);
        String newStartend = UpdaterRepo.recordMofied(downline, startend, modifiedFields);
        
        DownLine newDownline = null;
        if (!modifiedFields.isEmpty()) {
            SZTransaction szTransaction = new SZTransaction();
            Parameter<String> user = parameters.get("username");
            szTransaction.setUser(UserPool.getUser(user.getValue()));

            downline.setStatus(STATUS.archive.name());
            session.save(downline);

            newDownline = new DownLine();
            newDownline.setName(newName);
            newDownline.setAlias(newAlias);
            newDownline.setStopaddress(newStopaddress);
            newDownline.setStartend(newStartend);
            newDownline.setLine(downline.getLine());
            newDownline.setLineindex(downline.getLineindex());
            newDownline.setStatus(STATUS.normal.name());
            newDownline.setModify(true);

            szTransaction.setTimeStamp(new Date());
            Gson convertor = new Gson();
            Map<String, Object> modifiedFieldsMeta = new HashMap<String, Object>();
            modifiedFieldsMeta.put("entity", "upline");
            modifiedFieldsMeta.put("fields", modifiedFields.keySet());
            modifiedFieldsMeta.put("id", downline.getId());
            
            Parameter<String> username = parameters.get("username");
            modifiedFieldsMeta.put("modifier", username.getValue());
            szTransaction.setComments(convertor.toJson(modifiedFieldsMeta));
            szTransaction.setDownline(newDownline);
            session.save(szTransaction);
        }
        transaction.commit();
        session.close();

        return newDownline;
	}

}
