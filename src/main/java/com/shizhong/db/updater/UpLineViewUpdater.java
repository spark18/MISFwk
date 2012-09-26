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
import com.shizhong.db.dao.STATUS;
import com.shizhong.db.dao.SZTransaction;
import com.shizhong.db.dao.UpLine;
import com.shizhong.db.util.HibernateUtil;
import com.shizhong.web.meta.Parameter;
import com.shizhong.web.session.UserPool;

public class UpLineViewUpdater implements Updater<UpLine> {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public UpLine update(Map<String, Parameter> parameters) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();

		Criteria criteria = session.createCriteria(UpLine.class);
		Parameter<Long> id = parameters.get("id");
		criteria.add(Restrictions.eq(id.getName(), id.getValue()));
		UpLine upline = (UpLine) criteria.uniqueResult();

		if (null == upline) {
			return upline;
		}

		Parameter<String> name = parameters.get("name");
		Parameter<String> alias = parameters.get("alias");
		Parameter<String> stopaddress = parameters.get("stopaddress");
		Parameter<String> startend = parameters.get("startend");

		Map<String, String[]> modifiedFields = new HashMap<String, String[]>();
        String newName = UpdaterRepo.recordMofied(upline, name, modifiedFields);
        String newAlias = UpdaterRepo.recordMofied(upline, alias, modifiedFields);
        String newStopaddress = UpdaterRepo.recordMofied(upline, stopaddress, modifiedFields);
        String newStartend = UpdaterRepo.recordMofied(upline, startend, modifiedFields);
		
        UpLine newUpline = null;
		if (!modifiedFields.isEmpty()) {
            SZTransaction szTransaction = new SZTransaction();
            Parameter<String> user = parameters.get("username");
            szTransaction.setUser(UserPool.getUser(user.getValue()));

            upline.setStatus(STATUS.archive.name());
            session.save(upline);

            newUpline = new UpLine();
            newUpline.setName(newName);
            newUpline.setAlias(newAlias);
            newUpline.setStopaddress(newStopaddress);
            newUpline.setStartend(newStartend);
            newUpline.setLine(upline.getLine());
            newUpline.setLineindex(upline.getLineindex());
            newUpline.setStatus(STATUS.normal.name());
            newUpline.setModify(true);

            szTransaction.setTimeStamp(new Date());
            Gson convertor = new Gson();
            Map<String, Object> modifiedFieldsMeta = new HashMap<String, Object>();
            modifiedFieldsMeta.put("entity", "upline");
            modifiedFieldsMeta.put("fields", modifiedFields.keySet());
            modifiedFieldsMeta.put("id", upline.getId());
            
            Parameter<String> username = parameters.get("username");
            modifiedFieldsMeta.put("modifier", username.getValue());
            szTransaction.setComments(convertor.toJson(modifiedFieldsMeta));
            szTransaction.setUpline(newUpline);
            session.save(szTransaction);
        }
        transaction.commit();
        session.close();

		return newUpline;
	}

}
