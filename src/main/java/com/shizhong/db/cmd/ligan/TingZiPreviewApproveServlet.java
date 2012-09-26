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
import com.shizhong.db.dao.TingZi;
import com.shizhong.db.dao.TingZiPreview;
import com.shizhong.db.dao.PreTransaction;
import com.shizhong.db.dao.STATUS;
import com.shizhong.db.dao.SZTransaction;
import com.shizhong.db.dao.User;
import com.shizhong.db.util.HibernateUtil;
import com.shizhong.db.util.SolrUtil;
import com.shizhong.web.session.UserPool;

public class TingZiPreviewApproveServlet extends BaseAjaxServlet {

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
        query = session.createQuery("from TingZiPreview tingziPreview where tingziPreview.transaction.user.name='"
                + username + "' and tingziPreview.status='" + EntityPreviewStatus.PREVIEW.name() + "'");
        List tingziList = query.list();

        for (Object tingziObj : tingziList) {
            TingZiPreview tingziPreview = (TingZiPreview) tingziObj;
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
        List<TingZi> list = new ArrayList<TingZi>();
        for (Object tingziObj : tingziList) {
            TingZiPreview tingziPreview = (TingZiPreview) tingziObj;
            TingZi tingzi = convert(tingziPreview);
            list.add(tingzi);

                SZTransaction szTrans = attachToTransaction(tingzi, username, (User) users.get(0));
                session.save(szTrans);

                try {
                SolrInputDocument solrDocument = new SolrInputDocument();
                solrDocument.addField("id", "TingZi:" + tingzi.getId());
                solrDocument.addField("tingziaddress", tingzi.getAddress());
                solrDocument.addField("tingziarea", tingzi.getArea());
                solrDocument.addField("tingzicomments", tingzi.getComments());
                solrDocument.addField("tingzidigtime", formatter_en_US.format(tingzi.getDigdate()));
                solrDocument.addField("tingzidirection", tingzi.getDirection());
                solrDocument.addField("tingzifinalstop", tingzi.getFinalstop());
                solrDocument.addField("tingzifinishdate", formatter_en_US.format(tingzi.getFinishdate()));
                solrDocument.addField("tingziline", tingzi.getLine());
                solrDocument.addField("tingzinextstop", tingzi.getNextstop());
                solrDocument.addField("tingzinumber", tingzi.getNumber());
                solrDocument.addField("tingziroad", tingzi.getRoad());
                solrDocument.addField("tingzistop", tingzi.getStop());
                solrDocument.addField("tingzitype", tingzi.getType());
                solrDocument.addField("entitytype", tingzi.getEntityType());
                solrDocument.addField("modify", tingzi.getModify());
                solrDocument.addField("entityindex", tingzi.getEntityindex());
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
        for(TingZi tingzi: list) {
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

    private SZTransaction attachToTransaction(TingZi tingzi, String username, User approver) {
        Date now = new Date();
        SZTransaction transaction = new SZTransaction();
        transaction.setTimeStamp(now);
        User operator = UserPool.getUser(username);
        transaction.setUser(operator);
        transaction.setApprover(approver);
        transaction.setComments("提交亭子信息 - 立杆\t[操作人： " + operator.getName() + " | 审核：" + approver.getName() + "]");
        transaction.setType(PreTransaction.Type.TINGZIPREVIEWAPPROVE.name());
        transaction.setTingzi(tingzi);
        return transaction;
    }

    private TingZi convert(TingZiPreview tingziPreview) {
        TingZi tingzi = new TingZi();
        tingzi.setAddress(tingziPreview.getAddr());
        tingzi.setArea(tingziPreview.getArea());
        tingzi.setComments(tingziPreview.getComments());
        tingzi.setDigdate(tingziPreview.getDigdate());
        tingzi.setDirection(tingziPreview.getDirection());
        tingzi.setFinalstop(tingziPreview.getFinalstop());
        tingzi.setFinishdate(tingziPreview.getFinishdate());
        tingzi.setImage(tingziPreview.getImage());
        tingzi.setLine(tingziPreview.getLine());
        tingzi.setNextstop(tingziPreview.getNextstop());
        tingzi.setNumber(tingziPreview.getNumber());
        tingzi.setRoad(tingziPreview.getRoad());
        tingzi.setStop(tingziPreview.getStop());
        tingzi.setStatus(STATUS.normal.name());

        return tingzi;
    }
}
