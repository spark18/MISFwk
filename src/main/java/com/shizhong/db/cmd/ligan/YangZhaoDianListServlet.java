package com.shizhong.db.cmd.ligan;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.google.gson.Gson;
import com.shizhong.db.cmd.BaseAjaxServlet;
import com.shizhong.db.cmd.EntityList;
import com.shizhong.db.cmd.EntityRow;
import com.shizhong.db.dao.YangZhaoDian;
import com.shizhong.db.util.HibernateUtil;

public class YangZhaoDianListServlet extends BaseAjaxServlet {

    /**
	 * 
	 */
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

        Criteria criteria = session.createCriteria(YangZhaoDian.class);
        String number = req.getParameter("number");
        if (null != number) {
            criteria.add(Restrictions.eq("number", number));
        }
        criteria.add(Restrictions.eq("status", "normal"));
        criteria.addOrder(Order.asc("entityindex"));

        criteria.setFirstResult((page - 1) * rp);
        criteria.setMaxResults(rp);
        List pageResults = criteria.list();

        for (Object pageResult : pageResults) {
            EntityRow row = new EntityRow();
            YangZhaoDian yangzhaodian = (YangZhaoDian) pageResult;
            row.setId(yangzhaodian.getId());
            Date adstart = yangzhaodian.getAdstart();
            Date adend = yangzhaodian.getAdend();
            Date lastCareDate = yangzhaodian.getLastCareDate();
            String[] cells = new String[] { "", yangzhaodian.getArea(), yangzhaodian.getStopnum(),
                    yangzhaodian.getPicnumber(), yangzhaodian.getEntitynum(), yangzhaodian.getYangzhaodianType(), yangzhaodian.getAdop(),
                    adstart == null ? "" : formatter.format(adstart), adend == null ? "" : formatter.format(adend),
                    yangzhaodian.getRoad(), yangzhaodian.getStop(), yangzhaodian.getAddress(), yangzhaodian.getDirection(),
                    lastCareDate == null ? "" : formatter.format(lastCareDate), yangzhaodian.getComments(),
                    String.valueOf(yangzhaodian.getId()), String.valueOf(yangzhaodian.getModify()) };
            row.setCells(cells);
            yangzhaodianList.addRow(row);
        }

        if (pageResults.isEmpty()) {
            yangzhaodianList.setTotal("0");
        } else {
            Query countYangZhaoDian = session
                    .createQuery("select count(*) from YangZhaoDian yzd where yzd.status='normal'");
            List list = countYangZhaoDian.list();
            yangzhaodianList.setTotal(String.valueOf(list.get(0)));
        }

        transaction.commit();
        
        SQLQuery sqlQuery = session.createSQLQuery("select count(distinct picnumber) as numbercount from yangzhaodian");
        List list = sqlQuery.list();
        BigInteger numberCount = (BigInteger) list.get(0);
        yangzhaodianList.setNumberCount(numberCount.toString());
        
        session.close();

        prepareResponse(resp);

        Gson convertor = new Gson();
        String json = convertor.toJson(yangzhaodianList);
        resp.getWriter().write(json);
        resp.getWriter().flush();
        resp.getWriter().close();
    }
}
