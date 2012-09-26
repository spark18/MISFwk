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

import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.google.gson.JsonObject;
import com.shizhong.db.cmd.BaseAjaxServlet;
import com.shizhong.db.dao.EntityPreviewStatus;
import com.shizhong.db.dao.PreTransaction;
import com.shizhong.db.dao.STATUS;
import com.shizhong.db.dao.SZTransaction;
import com.shizhong.db.dao.User;
import com.shizhong.db.dao.YangZhaoDian;
import com.shizhong.db.dao.YangZhaoDianPreview;
import com.shizhong.db.util.HibernateUtil;
import com.shizhong.db.util.SolrUtil;
import com.shizhong.web.session.UserPool;

public class YangZhaoDianPreviewApproveServlet extends BaseAjaxServlet {

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
                .createQuery("from YangZhaoDianPreview yangzhaodianPreview where yangzhaodianPreview.transaction.user.name='"
                        + username + "' and yangzhaodianPreview.status='" + EntityPreviewStatus.PREVIEW.name() + "'");
        List yangzhaodianList = query.list();

        for (Object yangzhaodianObj : yangzhaodianList) {
            YangZhaoDianPreview yangzhaodianPreview = (YangZhaoDianPreview) yangzhaodianObj;
            yangzhaodianPreview.setStatus(EntityPreviewStatus.COMMITTED.name());
            session.save(yangzhaodianPreview);
        }

        transaction.commit();
        session.close();

        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();

        CommonsHttpSolrServer solrServer = SolrUtil.solrServer;
        SimpleDateFormat formatter_en_US = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.ENGLISH);
        List<YangZhaoDian> list = new ArrayList<YangZhaoDian>();
        for (Object yangzhaodianObj : yangzhaodianList) {
            YangZhaoDianPreview yangzhaodianPreview = (YangZhaoDianPreview) yangzhaodianObj;
            YangZhaoDian yangzhaodian = convert(yangzhaodianPreview);
            list.add(yangzhaodian);

            SZTransaction szTrans = attachToTransaction(yangzhaodian, username, (User) users.get(0));
            session.save(szTrans);

            try {
                SolrInputDocument solrDocument = new SolrInputDocument();
                solrDocument.addField("id", "YangZhaoDian:" + yangzhaodian.getId());
                solrDocument.addField("yangzhaodianaddress", yangzhaodian.getAddress());
                solrDocument.addField("yangzhaodianarea", yangzhaodian.getArea());
                solrDocument.addField("yangzhaodianstopnum", yangzhaodian.getStopnum());
                solrDocument.addField("yangzhaodianentitynum", yangzhaodian.getEntitynum());
                solrDocument.addField("yangzhaodiantype", yangzhaodian.getYangzhaodianType());
                solrDocument.addField("yangzhaodiancomments", yangzhaodian.getComments());
                solrDocument.addField("yangzhaodiandirection", yangzhaodian.getDirection());
                solrDocument.addField("yangzhaodianpicnumber", yangzhaodian.getPicnumber());
                solrDocument.addField("yangzhaodianadop", yangzhaodian.getAdop());
                Date adstart = yangzhaodian.getAdstart();
                solrDocument.addField("yangzhaodianadstart", adstart == null ? "" : formatter_en_US.format(adstart));
                Date adend = yangzhaodian.getAdend();
                solrDocument.addField("yangzhaodianadend", adend == null ? "" : formatter_en_US.format(adend));
                Date lastCareDate = yangzhaodian.getLastCareDate();
                solrDocument.addField("yangzhaodianlastcaredate",
                        lastCareDate == null ? "" : formatter_en_US.format(lastCareDate));
                solrDocument.addField("yangzhaodianroad", yangzhaodian.getRoad());
                solrDocument.addField("yangzhaodianstop", yangzhaodian.getStop());
                solrDocument.addField("yangzhaodianmodifytype", yangzhaodian.getType());
                solrDocument.addField("entitytype", yangzhaodian.getEntityType());
                solrDocument.addField("entityindex", yangzhaodian.getId());
                solrDocument.addField("modify", yangzhaodian.getModify());
                solrServer.add(solrDocument);
                solrServer.commit();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        transaction.commit();

        transaction = session.beginTransaction();
        transaction.begin();
        for (YangZhaoDian yangzhaodian : list) {
            yangzhaodian.setEntityindex(yangzhaodian.getId().intValue());
            session.save(yangzhaodian);
        }

        transaction.commit();

        session.close();

        returnStatus.addProperty("retCode", retCode);
        resp.getWriter().write(convertor.toJson(returnStatus));
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    private SZTransaction attachToTransaction(YangZhaoDian yangzhaodian, String username, User approver) {
        Date now = new Date();
        SZTransaction transaction = new SZTransaction();
        transaction.setTimeStamp(now);
        User operator = UserPool.getUser(username);
        transaction.setUser(operator);
        transaction.setApprover(approver);
        transaction.setComments("提交扬招点信息 - [操作人： " + operator.getName() + " | 审核：" + approver.getName() + "]");
        transaction.setType(PreTransaction.Type.YANGZHAODIANPREVIEWAPPROVE.name());
        transaction.setYangzhaodian(yangzhaodian);
        return transaction;
    }

    private YangZhaoDian convert(YangZhaoDianPreview yangzhaodianPreview) {
        YangZhaoDian yangzhaodian = new YangZhaoDian();
        yangzhaodian.setAddress(yangzhaodianPreview.getAddr());
        yangzhaodian.setArea(yangzhaodianPreview.getArea());
        yangzhaodian.setStopnum(yangzhaodianPreview.getStopnum());
        yangzhaodian.setEntitynum(yangzhaodianPreview.getEntitynum());
        yangzhaodian.setYangzhaodianType(yangzhaodianPreview.getYangzhaodianType());
        yangzhaodian.setLastCareDate(yangzhaodianPreview.getLastCareDate());
        yangzhaodian.setComments(yangzhaodianPreview.getComments());
        yangzhaodian.setDirection(yangzhaodianPreview.getDirection());
        yangzhaodian.setImage(yangzhaodianPreview.getImage());
        yangzhaodian.setPicnumber(yangzhaodianPreview.getPicnumber());
        yangzhaodian.setAdop(yangzhaodianPreview.getAdop());
        yangzhaodian.setAdstart(yangzhaodianPreview.getAdstart());
        yangzhaodian.setAdend(yangzhaodianPreview.getAdend());
        yangzhaodian.setRoad(yangzhaodianPreview.getRoad());
        yangzhaodian.setStop(yangzhaodianPreview.getStop());
        yangzhaodian.setStatus(STATUS.normal.name());

        return yangzhaodian;
    }
}
