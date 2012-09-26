package com.shizhong.db.cmd.ligan;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.google.gson.Gson;
import com.shizhong.db.cmd.BaseAjaxServlet;
import com.shizhong.db.cmd.EntityList;
import com.shizhong.db.cmd.EntityRow;
import com.shizhong.db.dao.YangZhaoDianPreview;
import com.shizhong.db.util.HibernateUtil;

public class YangZhaoDianListPreviewServlet extends BaseAjaxServlet {

    private static final long serialVersionUID = -989293557544197178L;

    @SuppressWarnings("rawtypes")
    @Override
    public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();

        EntityList yangzhaodianList = new EntityList();
        // page=1&rp=15&sortname=iso&sortorder=asc&query=&qtype=name
        int page = 1;
        String pageStr = req.getParameter("page");
        try {
            page = Integer.parseInt(pageStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        yangzhaodianList.setPage(String.valueOf(page));

        int rp = 100;
        String rpStr = req.getParameter("rp");
        try {
            rp = Integer.parseInt(rpStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Criteria criteria = session.createCriteria(YangZhaoDianPreview.class);

        criteria.setFirstResult((page - 1) * rp);
        String transactionIdStr = req.getParameter("transactionId");
        if (null == transactionIdStr || transactionIdStr.isEmpty()) {
            return;
        }
        long transactionId = 0;
        try {
            transactionId = Long.parseLong(transactionIdStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        criteria.add(Restrictions.eq("transaction.id", transactionId));
        criteria.setMaxResults(rp);
        List pageResults = criteria.list();

        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd,yyyy", Locale.CHINESE);
        for (Object pageResult : pageResults) {
            EntityRow row = new EntityRow();
            YangZhaoDianPreview yangzhaodian = (YangZhaoDianPreview) pageResult;
            row.setId(yangzhaodian.getId());
            Date adstart = yangzhaodian.getAdstart();
            Date adend = yangzhaodian.getAdend();
            Date lastCareDate = yangzhaodian.getLastCareDate();
            String[] cells = new String[] { yangzhaodian.getArea(), yangzhaodian.getStopnum(),
                    yangzhaodian.getPicnumber(), yangzhaodian.getEntitynum(), yangzhaodian.getYangzhaodianType(), yangzhaodian.getAdop(),
                    yangzhaodian.getAdstart() == null ? "" : formatter.format(adstart),
                    yangzhaodian.getAdend() == null ? "" : formatter.format(adend), yangzhaodian.getRoad(), yangzhaodian.getStop(),
                    yangzhaodian.getAddr(), yangzhaodian.getDirection(),
                    lastCareDate == null ? "" : formatter.format(lastCareDate), yangzhaodian.getComments(),
                    String.valueOf(yangzhaodian.getId()) };
            row.setCells(cells);
            yangzhaodianList.addRow(row);
        }

        if (pageResults.isEmpty()) {
            yangzhaodianList.setTotal("0");
        } else {
            yangzhaodianList.setTotal(String.valueOf(pageResults.size()));
        }

        transaction.commit();
        session.close();

        prepareResponse(resp);

        Gson convertor = new Gson();
        String json = convertor.toJson(yangzhaodianList);
        resp.getWriter().write(json);
        resp.getWriter().flush();
        resp.getWriter().close();
    }
}
