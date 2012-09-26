package com.shizhong.db.cmd.line;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.google.gson.Gson;
import com.shizhong.db.cmd.BaseAjaxServlet;
import com.shizhong.db.cmd.EntityList;
import com.shizhong.db.cmd.EntityRow;
import com.shizhong.db.dao.DownLine;
import com.shizhong.db.dao.STATUS;
import com.shizhong.db.util.HibernateUtil;

public class DownLineListViewServlet extends BaseAjaxServlet {

    private static final long serialVersionUID = -989293557544197178L;

    @SuppressWarnings("rawtypes")
    @Override
    public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();

        EntityList downlineList = new EntityList();

        Criteria criteria = session.createCriteria(DownLine.class);
        String line = req.getParameter("linenum");
        criteria.add(Restrictions.eq("line", line));
        criteria.add(Restrictions.eq("status", STATUS.normal.name()));
        criteria.addOrder(Order.asc("lineindex"));

        List pageResults = criteria.list();

        for (Object pageResult : pageResults) {
            EntityRow row = new EntityRow();
            DownLine downline = (DownLine) pageResult;
            row.setId(downline.getId());
            String[] cells = new String[] { downline.getName(), downline.getAlias(), downline.getStopaddress(),
                    downline.getStartend(), String.valueOf(downline.getId()), downline.getLine(),
                    String.valueOf(downline.getModify()) };
            row.setCells(cells);
            downlineList.addRow(row);
        }

        if (pageResults.isEmpty()) {
            downlineList.setTotal("0");
        } else {
            downlineList.setTotal(String.valueOf(pageResults.size()));
        }

        transaction.commit();
        session.close();

        prepareResponse(resp);

        Gson convertor = new Gson();
        String json = convertor.toJson(downlineList);
        resp.getWriter().write(json);
        resp.getWriter().flush();
        resp.getWriter().close();
    }
}
