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
import com.shizhong.db.dao.LiGan;
import com.shizhong.db.dao.STATUS;
import com.shizhong.db.dao.SZTransaction;
import com.shizhong.db.util.HibernateUtil;
import com.shizhong.db.util.SolrUtil;
import com.shizhong.web.meta.Parameter;
import com.shizhong.web.session.UserPool;

public class LiGanUpdater implements Updater<LiGan> {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public LiGan update(Map<String, Parameter> parameters) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();

		Criteria criteria = session.createCriteria(LiGan.class);
		Parameter<Long> id = parameters.get("id");
		criteria.add(Restrictions.eq(id.getName(), id.getValue()));
		LiGan ligan = (LiGan) criteria.uniqueResult();

		if (null == ligan) {
			return ligan;
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
		Parameter<String> type = parameters.get("type");
		Parameter<String> comments = parameters.get("comments");

		Map<String, String[]> modifiedFields = new HashMap<String, String[]>();
		String newLine = UpdaterRepo.recordMofied(ligan, line, modifiedFields);

		String newArea = UpdaterRepo.recordMofied(ligan, area, modifiedFields);

		String newNumber = UpdaterRepo.recordMofied(ligan, number, modifiedFields);

		String newRoad = UpdaterRepo.recordMofied(ligan, road, modifiedFields);

		String newStop = UpdaterRepo.recordMofied(ligan, stop, modifiedFields);

		String newFinalStop = UpdaterRepo.recordMofied(ligan, finalstop, modifiedFields);

		String newNextstop = UpdaterRepo.recordMofied(ligan, nextstop, modifiedFields);

		String newAddress = UpdaterRepo.recordMofied(ligan, address, modifiedFields);

		String newDirection = UpdaterRepo.recordMofied(ligan, direction, modifiedFields);

		String newType = UpdaterRepo.recordMofied(ligan, type, modifiedFields);

		String newComments = UpdaterRepo.recordMofied(ligan, comments, modifiedFields);

		System.out.println(modifiedFields);

		SimpleDateFormat formatter_zh_CN = new SimpleDateFormat("MMM dd,yyyy",
				Locale.CHINA);
		Date newDigdate = ligan.getDigdate();
		Date newFinishdate = ligan.getFinishdate();
		LiGan newLiGan = null;
		try {
			newDigdate = formatter_zh_CN.parse(digtime.getValue());
			if (ligan.getDigdate().compareTo(newDigdate) != 0) {
				modifiedFields.put("digdate",
						new String[] { newDigdate.toString(),
								ligan.getDigdate().toString(), });
			}
			newFinishdate = formatter_zh_CN.parse(finishdate.getValue());
			if (ligan.getFinishdate().compareTo(newFinishdate) != 0) {
				modifiedFields.put("finishdate",
						new String[] { newFinishdate.toString(),
								ligan.getFinishdate().toString(), });
			}

		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			if (!modifiedFields.isEmpty()) {
				SZTransaction szTransaction = new SZTransaction();
				Parameter<String> user = parameters.get("username");
				szTransaction.setUser(UserPool.getUser(user.getValue()));
//				szTransaction.setLigan(ligan);

				ligan.setStatus(STATUS.archive.name());
				session.save(ligan);

				newLiGan = new LiGan();
				newLiGan.setAddress(newAddress);
				newLiGan.setArea(newArea);
				newLiGan.setComments(newComments);
				newLiGan.setDigdate(newDigdate);
				newLiGan.setDirection(newDirection);
				newLiGan.setFinalstop(newFinalStop);
				newLiGan.setFinishdate(newFinishdate);
				newLiGan.setLine(newLine);
				newLiGan.setNextstop(newNextstop);
				newLiGan.setNumber(newNumber);
				newLiGan.setRoad(newRoad);
				newLiGan.setStop(newStop);
				newLiGan.setType(newType);
				newLiGan.setEntityindex(ligan.getEntityindex());
				newLiGan.setModify(true);
				newLiGan.setStatus(STATUS.normal.name());

				szTransaction.setTimeStamp(new Date());
				Gson convertor = new Gson();
				Map<String, Object> modifiedFieldsMeta = new HashMap<String, Object>();
				modifiedFieldsMeta.put("entity", "ligan");
				modifiedFieldsMeta.put("fields", modifiedFields.keySet());
				modifiedFieldsMeta.put("id", ligan.getId());
				
				Parameter<String> username = parameters.get("username");
				modifiedFieldsMeta.put("modifier", username.getValue());
				szTransaction.setComments(convertor.toJson(modifiedFieldsMeta));
				szTransaction.setLigan(newLiGan);
				session.save(szTransaction);
			}
			transaction.commit();
			session.close();
		}

		if (modifiedFields.isEmpty()) {
			return ligan;
		}

		SolrQuery query = new SolrQuery();
		String queryStr = "id:LiGan\\:" + ligan.getId();
		query.setQuery(queryStr);
		SimpleDateFormat formatter_en_US = new SimpleDateFormat(
				"EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.ENGLISH);
		try {
			CommonsHttpSolrServer solrServer = SolrUtil.solrServer;
			QueryResponse response = solrServer.query(query);
			SolrDocumentList solrResults = response.getResults();
			solrServer.deleteByQuery(queryStr);

			SolrInputDocument solrDocument = new SolrInputDocument();
			solrDocument.addField("id", "LiGan:" + newLiGan.getId());
			solrDocument.addField("liganaddress", newLiGan.getAddress());
			solrDocument.addField("liganarea", newLiGan.getArea());
			solrDocument.addField("ligancomments", newLiGan.getComments());
			solrDocument.addField("ligandigtime",
					formatter_en_US.format(newLiGan.getDigdate()));
			solrDocument.addField("ligandirection", newLiGan.getDirection());
			solrDocument.addField("liganfinalstop", newLiGan.getFinalstop());
			solrDocument.addField("liganfinishdate",
					formatter_en_US.format(newLiGan.getFinishdate()));
			solrDocument.addField("liganline", newLiGan.getLine());
			solrDocument.addField("ligannextstop", newLiGan.getNextstop());
			solrDocument.addField("ligannumber", newLiGan.getNumber());
			solrDocument.addField("liganroad", newLiGan.getRoad());
			solrDocument.addField("liganstop", newLiGan.getStop());
			solrDocument.addField("ligantype", newLiGan.getType());
			solrDocument.addField("modify", newLiGan.getModify());
			solrDocument.addField("entityindex", newLiGan.getEntityindex());

			solrServer.add(solrDocument);
			solrServer.commit();
			System.out.println(solrResults);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newLiGan;
	}

}
