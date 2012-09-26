package com.shizhong.db.updater;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.shizhong.db.dao.TingZiPreview;
import com.shizhong.db.util.HibernateUtil;
import com.shizhong.web.meta.Parameter;

public class TingZiPreviewUpdater implements Updater<TingZiPreview> {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public TingZiPreview update(Map<String, Parameter> parameters) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();

		Criteria criteria = session.createCriteria(TingZiPreview.class);
		Parameter<Long> id = parameters.get("id");
		criteria.add(Restrictions.eq(id.getName(), id.getValue()));
		TingZiPreview tingziPreview = (TingZiPreview) criteria.uniqueResult();

		if (null == tingziPreview) {
			return tingziPreview;
		}

		Parameter<String> line = parameters.get("line");
		Parameter<String> area = parameters.get("area");
		Parameter<String> number = parameters.get("number");
		Parameter<String> road = parameters.get("road");
		Parameter<String> stop = parameters.get("stop");
		Parameter<String> finalstop = parameters.get("finalstop");
		Parameter<String> nextstop = parameters.get("nextstop");
		Parameter<String> address = parameters.get("address");
		Parameter<String> direction = parameters.get("direction");
		Parameter<String> digtime = parameters.get("digtime");
		Parameter<String> finishdate = parameters.get("finishdate");

		tingziPreview.setLine(line.getValue());
		tingziPreview.setArea(area.getValue());
		tingziPreview.setNumber(number.getValue());
		tingziPreview.setRoad(road.getValue());
		tingziPreview.setStop(stop.getValue());
		tingziPreview.setFinalstop(finalstop.getValue());
		tingziPreview.setNextstop(nextstop.getValue());
		tingziPreview.setAddr(address.getValue());
		tingziPreview.setDirection(direction.getValue());

		SimpleDateFormat formatter = new SimpleDateFormat("MMM dd,yyyy",
				Locale.CHINA);
		try {
			tingziPreview.setDigdate(formatter.parse(digtime.getValue()));
			tingziPreview.setFinishdate(formatter.parse(finishdate.getValue()));
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			session.save(tingziPreview);
			transaction.commit();
			session.close();
		}
		return tingziPreview;
	}

}
