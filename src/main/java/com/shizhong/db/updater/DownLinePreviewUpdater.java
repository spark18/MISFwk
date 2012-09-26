package com.shizhong.db.updater;

import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.shizhong.db.dao.DownLinePreview;
import com.shizhong.db.util.HibernateUtil;
import com.shizhong.web.meta.Parameter;

public class DownLinePreviewUpdater implements Updater<DownLinePreview> {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DownLinePreview update(Map<String, Parameter> parameters) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();

		Criteria criteria = session.createCriteria(DownLinePreview.class);
		Parameter<Long> id = parameters.get("id");
		criteria.add(Restrictions.eq(id.getName(), id.getValue()));
		DownLinePreview downlinePreview = (DownLinePreview) criteria.uniqueResult();

		if (null == downlinePreview) {
			return downlinePreview;
		}

		Parameter<String> name = parameters.get("name");
		Parameter<String> alias = parameters.get("alias");
		Parameter<String> stopaddress = parameters.get("stopaddress");
		Parameter<String> startend = parameters.get("startend");

		downlinePreview.setName(name.getValue());
		downlinePreview.setAlias(alias.getValue());
		downlinePreview.setStopAddress(stopaddress.getValue());
		downlinePreview.setStartend(startend.getValue());

		session.save(downlinePreview);
		transaction.commit();
		session.close();
		return downlinePreview;
	}

}
