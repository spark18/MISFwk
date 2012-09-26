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
import com.shizhong.db.dao.LiGan;
import com.shizhong.db.dao.LiGanPreview;
import com.shizhong.db.dao.PreTransaction;
import com.shizhong.db.dao.STATUS;
import com.shizhong.db.dao.SZTransaction;
import com.shizhong.db.dao.User;
import com.shizhong.db.util.HibernateUtil;
import com.shizhong.db.util.SolrUtil;
import com.shizhong.web.session.UserPool;

public class LiGanPreviewApproveServlet extends BaseAjaxServlet {

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
        query = session.createQuery("from LiGanPreview liganPreview where liganPreview.transaction.user.name='"
                + username + "' and liganPreview.status='" + EntityPreviewStatus.PREVIEW.name() + "'");
        List liganList = query.list();

        for (Object liganObj : liganList) {
            LiGanPreview liganPreview = (LiGanPreview) liganObj;
            liganPreview.setStatus(EntityPreviewStatus.COMMITTED.name());
            session.save(liganPreview);
            // session.evict(liganPreview);
        }

        transaction.commit();
        session.close();

        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();

        CommonsHttpSolrServer solrServer = SolrUtil.solrServer;
        List<LiGan> newliganList = new ArrayList<LiGan>();
        SimpleDateFormat formatter_en_US = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.ENGLISH);
        for (Object liganObj : liganList) {
            LiGanPreview liganPreview = (LiGanPreview) liganObj;
            LiGan ligan = convert(liganPreview);
            newliganList.add(ligan);

            SZTransaction szTrans = attachToTransaction(ligan, username, (User) users.get(0));
            session.save(szTrans);

            try {
                SolrInputDocument solrDocument = new SolrInputDocument();
                solrDocument.addField("id", "LiGan:" + ligan.getId());
                solrDocument.addField("liganaddress", ligan.getAddress());
                solrDocument.addField("liganarea", ligan.getArea());
                solrDocument.addField("ligancomments", ligan.getComments());
                solrDocument.addField("ligandigtime", formatter_en_US.format(ligan.getDigdate()));
                solrDocument.addField("ligandirection", ligan.getDirection());
                solrDocument.addField("liganfinalstop", ligan.getFinalstop());
                solrDocument.addField("liganfinishdate", formatter_en_US.format(ligan.getFinishdate()));
                solrDocument.addField("liganline", ligan.getLine());
                solrDocument.addField("ligannextstop", ligan.getNextstop());
                solrDocument.addField("ligannumber", ligan.getNumber());
                solrDocument.addField("liganroad", ligan.getRoad());
                solrDocument.addField("liganstop", ligan.getStop());
                solrDocument.addField("ligantype", ligan.getType());
                solrDocument.addField("entitytype", ligan.getEntityType());
                solrDocument.addField("modify", ligan.getModify());
                solrDocument.addField("entityindex", ligan.getEntityindex());
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
        for (LiGan ligan : newliganList) {
            ligan.setEntityindex(ligan.getId().intValue());
            session.save(ligan);
        }
        transaction.commit();
        session.close();

        returnStatus.addProperty("retCode", retCode);
        resp.getWriter().write(convertor.toJson(returnStatus));
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    private SZTransaction attachToTransaction(LiGan ligan, String username, User approver) {
        Date now = new Date();
        SZTransaction transaction = new SZTransaction();
        transaction.setTimeStamp(now);
        User operator = UserPool.getUser(username);
        transaction.setUser(operator);
        transaction.setApprover(approver);
        transaction.setComments("提交设备信息 - 立杆\t[操作人： " + operator.getName() + " | 审核：" + approver.getName() + "]");
        transaction.setType(PreTransaction.Type.LIGANPREVIEWAPPROVE.name());
        transaction.setLigan(ligan);
        return transaction;
    }

    private LiGan convert(LiGanPreview liganPreview) {
        LiGan ligan = new LiGan();
        ligan.setAddress(liganPreview.getAddr());
        ligan.setArea(liganPreview.getArea());
        ligan.setComments(liganPreview.getComments());
        ligan.setDigdate(liganPreview.getDigdate());
        ligan.setDirection(liganPreview.getDirection());
        ligan.setFinalstop(liganPreview.getFinalstop());
        ligan.setFinishdate(liganPreview.getFinishdate());
        ligan.setImage(liganPreview.getImage());
        ligan.setLine(liganPreview.getLine());
        ligan.setNextstop(liganPreview.getNextstop());
        ligan.setNumber(liganPreview.getNumber());
        ligan.setRoad(liganPreview.getRoad());
        ligan.setStop(liganPreview.getStop());
        ligan.setStatus(STATUS.normal.name());

        return ligan;
    }
}
