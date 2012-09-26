package com.shizhong.db.updater;

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
import com.shizhong.db.dao.STATUS;
import com.shizhong.db.dao.SZTransaction;
import com.shizhong.db.dao.YangZhaoDian;
import com.shizhong.db.util.HibernateUtil;
import com.shizhong.db.util.SolrUtil;
import com.shizhong.web.meta.Parameter;
import com.shizhong.web.session.UserPool;

public class YangZhaoDianUpdater implements Updater<YangZhaoDian> {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public YangZhaoDian update(Map<String, Parameter> parameters) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();

        Criteria criteria = session.createCriteria(YangZhaoDian.class);
        Parameter<Long> id = parameters.get("id");
        criteria.add(Restrictions.eq(id.getName(), id.getValue()));
        YangZhaoDian yangzhaodian = (YangZhaoDian) criteria.uniqueResult();

        if (null == yangzhaodian) {
            return yangzhaodian;
        }

        Parameter<String> area = parameters.get("area");
        Parameter<String> stopnum = parameters.get("stopnum");
        Parameter<String> entitynum = parameters.get("entitynum");
        Parameter<String> yangzhaodiantype = parameters.get("yangzhaodianType");
        Parameter<String> picnumber = parameters.get("picnumber");
        Parameter<String> road = parameters.get("road");
        Parameter<String> address = parameters.get("address");
        Parameter<String> direction = parameters.get("direction");
        Parameter<String> lastcaredate = parameters.get("lastCareDate");
        Parameter<String> type = parameters.get("type");
        Parameter<String> comments = parameters.get("comments");
        Parameter<String> adop = parameters.get("adop");
        Parameter<String> adstart = parameters.get("adstart");
        Parameter<String> adend = parameters.get("adend");

        Map<String, String[]> modifiedFields = new HashMap<String, String[]>();

        String newArea = UpdaterRepo.recordMofied(yangzhaodian, area, modifiedFields);
        String newStopnum = UpdaterRepo.recordMofied(yangzhaodian, stopnum, modifiedFields);
        String newEntitynum = UpdaterRepo.recordMofied(yangzhaodian, entitynum, modifiedFields);
        String newYangzhaodianType = UpdaterRepo.recordMofied(yangzhaodian, yangzhaodiantype, modifiedFields);

        String newPicNumber = UpdaterRepo.recordMofied(yangzhaodian, picnumber, modifiedFields);

        String newRoad = UpdaterRepo.recordMofied(yangzhaodian, road, modifiedFields);

        String newAddress = UpdaterRepo.recordMofied(yangzhaodian, address, modifiedFields);

        String newDirection = UpdaterRepo.recordMofied(yangzhaodian, direction, modifiedFields);
        
        String newType = UpdaterRepo.recordMofied(yangzhaodian, type, modifiedFields);

        String newComments = UpdaterRepo.recordMofied(yangzhaodian, comments, modifiedFields);

        String newAdOp = UpdaterRepo.recordMofied(yangzhaodian, adop, modifiedFields);

        // SimpleDateFormat formatter_zh_CN = new
        // SimpleDateFormat("MMM dd,yyyy", Locale.CHINA);
        // Date newDigdate = yangzhaodian.getDigdate();
        Date newAdstart = UpdaterRepo.recordDateModified(yangzhaodian, adstart, modifiedFields);
        Date newAdend = UpdaterRepo.recordDateModified(yangzhaodian, adend, modifiedFields);
        Date newLastCareDate = UpdaterRepo.recordDateModified(yangzhaodian, lastcaredate, modifiedFields);
        System.out.println(modifiedFields);
        YangZhaoDian newYangzhaodian = null;

        if (!modifiedFields.isEmpty()) {
            SZTransaction szTransaction = new SZTransaction();
            Parameter<String> user = parameters.get("username");
            szTransaction.setUser(UserPool.getUser(user.getValue()));

            yangzhaodian.setStatus(STATUS.archive.name());
            session.save(yangzhaodian);

            newYangzhaodian = new YangZhaoDian();
            newYangzhaodian.setAddress(newAddress);
            newYangzhaodian.setArea(newArea);
            newYangzhaodian.setEntitynum(newEntitynum);
            newYangzhaodian.setStopnum(newStopnum);
            newYangzhaodian.setYangzhaodianType(newYangzhaodianType);
            newYangzhaodian.setComments(newComments);
            newYangzhaodian.setDirection(newDirection);
            newYangzhaodian.setPicnumber(newPicNumber);
            newYangzhaodian.setRoad(newRoad);
            newYangzhaodian.setType(newType);
            newYangzhaodian.setEntityindex(yangzhaodian.getEntityindex());
            newYangzhaodian.setAdop(newAdOp);
            newYangzhaodian.setAdstart(newAdstart);
            newYangzhaodian.setAdend(newAdend);
            newYangzhaodian.setLastCareDate(newLastCareDate);
            newYangzhaodian.setModify(true);
            newYangzhaodian.setStatus(STATUS.normal.name());

            szTransaction.setTimeStamp(new Date());
            Gson convertor = new Gson();
            Map<String, Object> modifiedFieldsMeta = new HashMap<String, Object>();
            modifiedFieldsMeta.put("entity", "yangzhaodian");
            modifiedFieldsMeta.put("fields", modifiedFields.keySet());
            modifiedFieldsMeta.put("id", yangzhaodian.getId());

            Parameter<String> username = parameters.get("username");
            modifiedFieldsMeta.put("modifier", username.getValue());
            szTransaction.setComments(convertor.toJson(modifiedFieldsMeta));
            szTransaction.setYangzhaodian(newYangzhaodian);
            session.save(szTransaction);
        }
        transaction.commit();
        session.close();

        if (modifiedFields.isEmpty()) {
            return yangzhaodian;
        }

        SolrQuery query = new SolrQuery();
        String queryStr = "id:YangZhaoDian\\:" + yangzhaodian.getId();
        query.setQuery(queryStr);
        SimpleDateFormat formatter_en_US = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.ENGLISH);
        try {
            CommonsHttpSolrServer solrServer = SolrUtil.solrServer;
            QueryResponse response = solrServer.query(query);
            SolrDocumentList solrResults = response.getResults();
            solrServer.deleteByQuery(queryStr);
            solrServer.commit();

            SolrInputDocument solrDocument = new SolrInputDocument();
            solrDocument.addField("id", "YangZhaoDian:" + newYangzhaodian.getId());
            solrDocument.addField("yangzhaodianaddress", newYangzhaodian.getAddress());
            solrDocument.addField("yangzhaodianarea", newYangzhaodian.getArea());
            solrDocument.addField("yangzhaodianstopnum", newYangzhaodian.getStopnum());
            solrDocument.addField("yangzhaodianentitynum", newYangzhaodian.getEntitynum());
            solrDocument.addField("yangzhaodiantype", newYangzhaodian.getYangzhaodianType());
            solrDocument.addField("yangzhaodiancomments", newYangzhaodian.getComments());
            solrDocument.addField("yangzhaodiandirection", newYangzhaodian.getDirection());
            solrDocument.addField("yangzhaodianpicnumber", newYangzhaodian.getPicnumber());
            solrDocument.addField("yangzhaodianroad", newYangzhaodian.getRoad());
            solrDocument.addField("yangzhaodianmodifytype", newYangzhaodian.getType());
            solrDocument.addField("yangzhaodianstop", newYangzhaodian.getStop());
            solrDocument.addField("yangzhaodianadop", newYangzhaodian.getAdop());
            Date adstartDate = newYangzhaodian.getAdstart();
            solrDocument.addField("yangzhaodianadstart", adstartDate == null ? "" : formatter_en_US.format(adstartDate));
            Date adendDate = newYangzhaodian.getAdend();
            solrDocument.addField("yangzhaodianadend", adendDate == null ? "" : formatter_en_US.format(adendDate));
            Date lastCareDateDate = newYangzhaodian.getLastCareDate();
            solrDocument.addField("yangzhaodianlastcaredate",
                    lastCareDateDate == null ? "" : formatter_en_US.format(lastCareDateDate));
            solrDocument.addField("modify", newYangzhaodian.getModify());
            solrDocument.addField("entityindex", newYangzhaodian.getEntityindex());

            solrServer.add(solrDocument);
            solrServer.commit();
            System.out.println(solrResults);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newYangzhaodian;
    }

}
