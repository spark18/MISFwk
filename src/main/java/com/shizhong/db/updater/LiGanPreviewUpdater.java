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

import com.shizhong.db.dao.LiGanPreview;
import com.shizhong.db.util.HibernateUtil;
import com.shizhong.web.meta.Parameter;

public class LiGanPreviewUpdater implements Updater<LiGanPreview> {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public LiGanPreview update(Map<String, Parameter> parameters) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();

		Criteria criteria = session.createCriteria(LiGanPreview.class);
		Parameter<Long> id = parameters.get("id");
		criteria.add(Restrictions.eq(id.getName(), id.getValue()));
		LiGanPreview liganPreview = (LiGanPreview) criteria.uniqueResult();

		if (null == liganPreview) {
			return liganPreview;
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

		liganPreview.setLine(line.getValue());
		liganPreview.setArea(area.getValue());
		liganPreview.setNumber(number.getValue());
		liganPreview.setRoad(road.getValue());
		liganPreview.setStop(stop.getValue());
		liganPreview.setFinalstop(finalstop.getValue());
		liganPreview.setNextstop(nextstop.getValue());
		liganPreview.setAddr(address.getValue());
		liganPreview.setDirection(direction.getValue());

		SimpleDateFormat formatter = new SimpleDateFormat("MMM dd,yyyy",
				Locale.CHINA);
		try {
			liganPreview.setDigdate(formatter.parse(digtime.getValue()));
			liganPreview.setFinishdate(formatter.parse(finishdate.getValue()));
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			session.save(liganPreview);
			transaction.commit();
			session.close();
		}
		return liganPreview;
	}

}
