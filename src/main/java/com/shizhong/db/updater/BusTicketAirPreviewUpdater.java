package com.shizhong.db.updater;

import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.shizhong.db.dao.BusTicketAirPreview;
import com.shizhong.db.util.HibernateUtil;
import com.shizhong.web.meta.Parameter;

public class BusTicketAirPreviewUpdater implements Updater<BusTicketAirPreview> {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public BusTicketAirPreview update(Map<String, Parameter> parameters) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();

		Criteria criteria = session.createCriteria(BusTicketAirPreview.class);
		Parameter<String> line = parameters.get("line");
		criteria.add(Restrictions.eq(line.getName(), line.getValue()));
		BusTicketAirPreview busTicketPreview = (BusTicketAirPreview) criteria
				.uniqueResult();

		if (null == busTicketPreview) {
			return busTicketPreview;
		}

		Parameter<String> type = parameters.get("bustype");
		Parameter<String> airPrice = parameters.get("airPrice");
		Parameter<String> pricetype = parameters.get("pricetype");
		Parameter<String> autoSale = parameters.get("autoSale");
		Parameter<String> opunit = parameters.get("opunit");

		busTicketPreview.setType(type.getValue());
		double air = 2.0;
		try {
			air = Double.parseDouble(airPrice.getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		busTicketPreview.setAirPrice(air);
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
