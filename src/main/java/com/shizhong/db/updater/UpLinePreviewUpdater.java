package com.shizhong.db.updater;

import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.shizhong.db.dao.UpLinePreview;
import com.shizhong.db.util.HibernateUtil;
import com.shizhong.web.meta.Parameter;

public class UpLinePreviewUpdater implements Updater<UpLinePreview> {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public UpLinePreview update(Map<String, Parameter> parameters) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();

		Criteria criteria = session.createCriteria(UpLinePreview.class);
		Parameter<Long> id = parameters.get("id");
		criteria.add(Restrictions.eq(id.getName(), id.getValue()));
		UpLinePreview uplinePreview = (UpLinePreview) criteria.uniqueResult();

		if (null == uplinePreview) {
			return uplinePreview;
		}

		Parameter<String> name = parameters.get("name");
		Parameter<String> alias = parameters.get("alias");
		Parameter<String> stopaddress = parameters.get("stopaddress");
		Parameter<String> startend = parameters.get("startend");

		uplinePreview.setName(name.getValue());
		uplinePreview.setAlias(alias.getValue());
		uplinePreview.setStopAddress(stopaddress.getValue());
		uplinePreview.setStartend(startend.getValue());

		session.save(uplinePreview);
		transaction.commit();
		session.close();
		return uplinePreview;
	}

}
