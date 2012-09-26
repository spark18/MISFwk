package com.shizhong.db.querier;

import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.shizhong.db.dao.YangZhaoDian;
import com.shizhong.db.util.HibernateUtil;
import com.shizhong.web.meta.Parameter;

class YangZhaoDianQuerier implements Querier<YangZhaoDian> {

	@SuppressWarnings("rawtypes")
	@Override
	public YangZhaoDian retrieve(Map<String, Parameter> restrictions) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();

		Criteria criteria = session.createCriteria(YangZhaoDian.class);
		for(Parameter restriction: restrictions.values()) {
			criteria.add(Restrictions.eq(restriction.getName(), restriction.getValue()));
		}
		YangZhaoDian yangzhaodian = (YangZhaoDian) criteria.uniqueResult();

		transaction.commit();
		session.close();

		return yangzhaodian;
	}

}
