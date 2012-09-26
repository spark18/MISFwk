package com.shizhong.db.updater;

import java.text.ParseException;
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
import com.shizhong.db.dao.TingZi;
import com.shizhong.db.dao.STATUS;
import com.shizhong.db.dao.SZTransaction;
import com.shizhong.db.util.HibernateUtil;
import com.shizhong.db.util.SolrUtil;
import com.shizhong.web.meta.Parameter;
import com.shizhong.web.session.UserPool;

public class TingZiUpdater implements Updater<TingZi> {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public TingZi update(Map<String, Parameter> parameters) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();

		Criteria criteria = session.createCriteria(TingZi.class);
		Parameter<Long> id = parameters.get("id");
		criteria.add(Restrictions.eq(id.getName(), id.getValue()));
		TingZi tingzi = (TingZi) criteria.uniqueResult();

		if (null == tingzi) {
			return tingzi;
		}

		Parameter<String> line = parameters.get("line");
		Parameter<String> area = parameters.get("area");
		Parameter<String> number = parameters.get("number");
		Parameter<String> factory = parameters.get("factory");
		Parameter<String> equiptype = parameters.get("equiptype");
		Parameter<String> road = parameters.get("road");
		Parameter<String> stop = parameters.get("stop");
		Parameter<String> finalstop = parameters.get("finalstop");
		Parameter<String> nextstop = parameters.get("nextstop");
		Parameter<String> address = parameters.get("address");
		Parameter<String> direction = parameters.get("direction");
		Parameter<String> digtime = parameters.get("digtime");
		Parameter<String> finishdate = parameters.get("finishdate");
		Parameter<String> type = parameters.get("type");
		Parameter<String> comments = parameters.get("comments");

		Map<String, String[]> modifiedFields = new HashMap<String, String[]>();
		String newLine = UpdaterRepo.recordMofied(tingzi, line, modifiedFields);

		String newArea = UpdaterRepo.recordMofied(tingzi, area, modifiedFields);

		String newNumber = UpdaterRepo.recordMofied(tingzi, number, modifiedFields);
		String newFactory = UpdaterRepo.recordMofied(tingzi, factory, modifiedFields);
		String newEquiptype = UpdaterRepo.recordMofied(tingzi, equiptype, modifiedFields);

		String newRoad = UpdaterRepo.recordMofied(tingzi, road, modifiedFields);

		String newStop = UpdaterRepo.recordMofied(tingzi, stop, modifiedFields);

		String newFinalStop = UpdaterRepo.recordMofied(tingzi, finalstop, modifiedFields);

		String newNextstop = UpdaterRepo.recordMofied(tingzi, nextstop, modifiedFields);

		String newAddress = UpdaterRepo.recordMofied(tingzi, address, modifiedFields);

		String newDirection = UpdaterRepo.recordMofied(tingzi, direction, modifiedFields);

		String newType = UpdaterRepo.recordMofied(tingzi, type, modifiedFields);

		String newComments = UpdaterRepo.recordMofied(tingzi, comments, modifiedFields);

		System.out.println(modifiedFields);

		SimpleDateFormat formatter_zh_CN = new SimpleDateFormat("MMM dd,yyyy",
				Locale.CHINA);
		Date newDigdate = tingzi.getDigdate();
		Date newFinishdate = tingzi.getFinishdate();
		TingZi newTingzi = null;
		try {
			newDigdate = formatter_zh_CN.parse(digtime.getValue());
			if (tingzi.getDigdate().compareTo(newDigdate) != 0) {
				modifiedFields.put("digdate",
						new String[] { newDigdate.toString(),
								tingzi.getDigdate().toString(), });
			}
			newFinishdate = formatter_zh_CN.parse(finishdate.getValue());
			if (tingzi.getFinishdate().compareTo(newFinishdate) != 0) {
				modifiedFields.put("finishdate",
						new String[] { newFinishdate.toString(),
								tingzi.getFinishdate().toString(), });
			}

		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			if (!modifiedFields.isEmpty()) {
				SZTransaction szTransaction = new SZTransaction();
				Parameter<String> user = parameters.get("username");
				szTransaction.setUser(UserPool.getUser(user.getValue()));

				tingzi.setStatus(STATUS.archive.name());
				session.save(tingzi);

				newTingzi = new TingZi();
				newTingzi.setAddress(newAddress);
				newTingzi.setArea(newArea);
				newTingzi.setComments(newComments);
				newTingzi.setDigdate(newDigdate);
				newTingzi.setDirection(newDirection);
				newTingzi.setFinalstop(newFinalStop);
				newTingzi.setFinishdate(newFinishdate);
				newTingzi.setLine(newLine);
				newTingzi.setNextstop(newNextstop);
				newTingzi.setNumber(newNumber);
				newTingzi.setFactory(newFactory);
				newTingzi.setEquiptype(newEquiptype);
				newTingzi.setRoad(newRoad);
				newTingzi.setStop(newStop);
				newTingzi.setType(newType);
				newTingzi.setEntityindex(tingzi.getEntityindex());
				newTingzi.setModify(true);
				newTingzi.setStatus(STATUS.normal.name());

				szTransaction.setTimeStamp(new Date());
				Gson convertor = new Gson();
				Map<String, Object> modifiedFieldsMeta = new HashMap<String, Object>();
				modifiedFieldsMeta.put("entity", "tingzi");
				modifiedFieldsMeta.put("fields", modifiedFields.keySet());
				modifiedFieldsMeta.put("id", tingzi.getId());
				
				Parameter<String> username = parameters.get("username");
				modifiedFieldsMeta.put("modifier", username.getValue());
				szTransaction.setComments(convertor.toJson(modifiedFieldsMeta));
				szTransaction.setTingzi(newTingzi);
				session.save(szTransaction);
			}
			transaction.commit();
			session.close();
		}

		if (modifiedFields.isEmpty()) {
			return tingzi;
		}

		SolrQuery query = new SolrQuery();
		String queryStr = "id:TingZi\\:" + tingzi.getId();
		query.setQuery(queryStr);
		SimpleDateFormat formatter_en_US = new SimpleDateFormat(
				"EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.ENGLISH);
		try {
			CommonsHttpSolrServer solrServer = SolrUtil.solrServer;
			QueryResponse response = solrServer.query(query);
			SolrDocumentList solrResults = response.getResults();
			solrServer.deleteByQuery(queryStr);

			SolrInputDocument solrDocument = new SolrInputDocument();
			solrDocument.addField("id", "TingZi:" + newTingzi.getId());
			solrDocument.addField("tingziaddress", newTingzi.getAddress());
			solrDocument.addField("tingziarea", newTingzi.getArea());
			solrDocument.addField("tingzicomments", newTingzi.getComments());
			solrDocument.addField("tingzidigtime",
					formatter_en_US.format(newTingzi.getDigdate()));
			solrDocument.addField("tingzidirection", newTingzi.getDirection());
			solrDocument.addField("tingzifinalstop", newTingzi.getFinalstop());
			solrDocument.addField("tingzifinishdate",
					formatter_en_US.format(newTingzi.getFinishdate()));
			solrDocument.addField("tingziline", newTingzi.getLine());
			solrDocument.addField("tingzinextstop", newTingzi.getNextstop());
			solrDocument.addField("tingzinumber", newTingzi.getNumber());
			solrDocument.addField("tingziroad", newTingzi.getRoad());
			solrDocument.addField("tingzistop", newTingzi.getStop());
			solrDocument.addField("tingzitype", newTingzi.getType());
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
