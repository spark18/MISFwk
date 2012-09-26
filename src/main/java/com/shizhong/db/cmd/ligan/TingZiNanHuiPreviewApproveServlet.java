package com.shizhong.db.cmd.ligan;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.google.gson.JsonObject;
import com.shizhong.db.cmd.BaseAjaxServlet;
import com.shizhong.db.dao.EntityPreviewStatus;
import com.shizhong.db.dao.TingZiNanHui;
import com.shizhong.db.dao.TingZiNanHuiPreview;
import com.shizhong.db.dao.PreTransaction;
import com.shizhong.db.dao.STATUS;
import com.shizhong.db.dao.SZTransaction;
import com.shizhong.db.dao.User;
import com.shizhong.db.util.HibernateUtil;
import com.shizhong.db.util.SolrUtil;
import com.shizhong.web.session.UserPool;

public class TingZiNanHuiPreviewApproveServlet extends BaseAjaxServlet {

    private static final long serialVersionUID = 430195510740094785L;

    @SuppressWarnings("rawtypes")
    @Override
    public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String approverName = req.getParameter("approver");
        String approverPasswd = req.getParameter("approverpasswd");

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();

        Query query = session.createQuery("from User user where user.name='" + approverName + "' and user.passwd='"
                + approverPasswd + "'");
        List users = query.list();

        transaction.commit();
        session.close();

        prepareResponse(resp);

        JsonObject returnStatus = new JsonObject();
        String retCode = SUCCESS;
        if (users.isEmpty()) {
            retCode = FAILURE;
        } else {
            User approver = (User) users.get(0);
            String role = approver.getRole();

            if (!User.Role.MANAGER.name().equals(role)) {
                retCode = FAILURE;
            }
        }

        if (FAILURE.equals(retCode)) {
            returnStatus.addProperty("retCode", retCode);
            resp.getWriter().write(convertor.toJson(returnStatus));
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
        }
        String username = req.getParameter("username");
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();
        query = session
                .createQuery("from TingZiNanHuiPreview tingziPreview where tingziPreview.transaction.user.name='"
                        + username + "' and tingziPreview.status='" + EntityPreviewStatus.PREVIEW.name() + "'");
        List tingziList = query.list();

        for (Object tingziObj : tingziList) {
            TingZiNanHuiPreview tingziPreview = (TingZiNanHuiPreview) tingziObj;
            tingziPreview.setStatus(EntityPreviewStatus.COMMITTED.name());
            session.save(tingziPreview);
        }

        transaction.commit();
        session.close();

        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();

        CommonsHttpSolrServer solrServer = SolrUtil.solrServer;
        SimpleDateFormat formatter_en_US = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.ENGLISH);
        List<TingZiNanHui> list = new ArrayList<TingZiNanHui>();
        for (Object tingziObj : tingziList) {
            TingZiNanHuiPreview tingziPreview = (TingZiNanHuiPreview) tingziObj;
            TingZiNanHui tingzinh = convert(tingziPreview);
            list.add(tingzinh);

            SZTransaction szTrans = attachToTransaction(tingzinh, username, (User) users.get(0));
            session.save(szTrans);

            try {
                SolrInputDocument solrDocument = new SolrInputDocument();
                solrDocument.addField("id", "TingZiNanHui:" + tingzinh.getId());
                solrDocument.addField("tingzinhaddress", tingzinh.getAddress());
                solrDocument.addField("tingzinharea", tingzinh.getArea());
                solrDocument.addField("tingzinhstopnum", tingzinh.getStopnum());
                solrDocument.addField("tingzinhentitynum", tingzinh.getEntitynum());
                solrDocument.addField("tingzinhtype", tingzinh.getNanhuitingType());
                solrDocument.addField("tingzinhcomments", tingzinh.getComments());
                Date digtime = tingzinh.getDigtime();
                solrDocument.addField("tingzinhdigtime", digtime == null ? "" : formatter_en_US.format(digtime));
                solrDocument.addField("tingzinhdirection", tingzinh.getDirection());
                solrDocument.addField("tingzinhfinalstop", tingzinh.getFinalstop());
                Date finishdate = tingzinh.getFinishdate();
                solrDocument.addField("tingzinhfinishdate",
                        finishdate == null ? "" : formatter_en_US.format(finishdate));
                solrDocument.addField("tingzinhline", tingzinh.getLine());
                solrDocument.addField("tingzinhnextstop", tingzinh.getNextstop());
                solrDocument.addField("tingzinhpicnumber", tingzinh.getPicnumber());
                solrDocument.addField("tingzinhadop", tingzinh.getAdop());
                Date adstart = tingzinh.getAdstart();
                solrDocument.addField("tingzinhadstart", adstart == null ? "" : formatter_en_US.format(adstart));
                Date adend = tingzinh.getAdend();
                solrDocument.addField("tingzinhadend", adend == null ? "" : formatter_en_US.format(adend));
                Date lastCareDate = tingzinh.getLastCareDate();
                solrDocument.addField("tingzinhlastcare",
                        lastCareDate == null ? "" : formatter_en_US.format(lastCareDate));
                solrDocument.addField("tingzinhroad", tingzinh.getRoad());
                solrDocument.addField("tingzinhstop", tingzinh.getStop());
                solrDocument.addField("tingzinhtype", tingzinh.getType());
                solrDocument.addField("entitytype", tingzinh.getEntityType());
                solrDocument.addField("entityindex", tingzinh.getId());
                solrDocument.addField("modify", tingzinh.getModify());
                solrServer.add(solrDocument);
                solrServer.commit();
            } catch (SolrServerException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        transaction.commit();

        transaction = session.beginTransaction();
        transaction.begin();
        for (TingZiNanHui tingzi : list) {
            tingzi.setEntityindex(tingzi.getId().intValue());
            session.save(tingzi);
        }

        transaction.commit();

        session.close();

        returnStatus.addProperty("retCode", retCode);
        resp.getWriter().write(convertor.toJson(returnStatus));
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    private SZTransaction attachToTransaction(TingZiNanHui tingzi, String username, User approver) {
        Date now = new Date();
        SZTransaction transaction = new SZTransaction();
        transaction.setTimeStamp(now);
        User operator = UserPool.getUser(username);
        transaction.setUser(operator);
        transaction.setApprover(approver);
        transaction.setComments("提交南汇亭信息 - [操作人： " + operator.getName() + " | 审核：" + approver.getName() + "]");
        transaction.setType(PreTransaction.Type.TINGZINANHUIPREVIEWAPPROVE.name());
        transaction.setTingzinanhui(tingzi);
        return transaction;
    }

    private TingZiNanHui convert(TingZiNanHuiPreview tingziPreview) {
        TingZiNanHui tingzi = new TingZiNanHui();
        tingzi.setAddress(tingziPreview.getAddr());
        tingzi.setArea(tingziPreview.getArea());
        tingzi.setStopnum(tingziPreview.getStopnum());
        tingzi.setEntitynum(tingziPreview.getEntitynum());
        tingzi.setNanhuitingType(tingziPreview.getNanhuitingType());
        tingzi.setLastCareDate(tingziPreview.getLastCareDate());
        tingzi.setComments(tingziPreview.getComments());
        tingzi.setDigtime(tingziPreview.getDigtime());
        tingzi.setDirection(tingziPreview.getDirection());
        tingzi.setFinalstop(tingziPreview.getFinalstop());
        tingzi.setFinishdate(tingziPreview.getFinishdate());
        tingzi.setImage(tingziPreview.getImage());
        tingzi.setLine(tingziPreview.getLine());
        tingzi.setNextstop(tingziPreview.getNextstop());
        tingzi.setPicnumber(tingziPreview.getPicnumber());
        tingzi.setAdop(tingziPreview.getAdop());
        tingzi.setAdstart(tingziPreview.getAdstart());
        tingzi.setAdend(tingziPreview.getAdend());
        tingzi.setRoad(tingziPreview.getRoad());
        tingzi.setStop(tingziPreview.getStop());
        tingzi.setStatus(STATUS.normal.name());

        return tingzi;
    }
}
