package com.shizhong.db.updater;

import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.shizhong.db.dao.BusTicketPreview;
import com.shizhong.db.util.HibernateUtil;
import com.shizhong.web.meta.Parameter;

public class BusTicketPreviewUpdater implements Updater<BusTicketPreview> {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public BusTicketPreview update(Map<String, Parameter> parameters) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();

		Criteria criteria = session.createCriteria(BusTicketPreview.class);
		Parameter<String> line = parameters.get("line");
		criteria.add(Restrictions.eq(line.getName(), line.getValue()));
		BusTicketPreview busTicketPreview = (BusTicketPreview) criteria
				.uniqueResult();

		if (null == busTicketPreview) {
			return busTicketPreview;
		}

		Parameter<String> type = parameters.get("type");
		Parameter<String> normPrice = parameters.get("normPrice");
		Parameter<String> pricetype = parameters.get("pricetype");
		Parameter<String> autoSale = parameters.get("autoSale");
		Parameter<String> opunit = parameters.get("opunit");

		busTicketPreview.setType(type.getValue());
		double norm = 1.0;
		try {
			norm = Double.parseDouble(normPrice.getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		busTicketPreview.setNormPrice(norm);
		busTicketPreview.setPricetype(pricetype.getValue());
		if(!"multi".equals(pricetype.getValue())) {
			busTicketPreview.setImage(null);
		}
		busTicketPreview.setAutoSale(autoSale.getValue());
		busTicketPreview.setOpunit(opunit.getValue());
		
		session.save(busTicketPreview);
		transaction.commit();
		session.close();
		return busTicketPreview;
	}

}
