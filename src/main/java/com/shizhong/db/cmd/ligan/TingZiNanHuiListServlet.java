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
import com.shizhong.db.dao.TingZiNanHui;
import com.shizhong.db.util.HibernateUtil;

public class TingZiNanHuiListServlet extends BaseAjaxServlet {

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

        EntityList tingziList = new EntityList();
        // page=1&rp=15&sortname=iso&sortorder=asc&query=&qtype=name
        int page = 1;
        String pageStr = req.getParameter("page");
        try {
            page = Integer.parseInt(pageStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tingziList.setPage(String.valueOf(page));

        int rp = 100;
        String rpStr = req.getParameter("rp");
        try {
            rp = Integer.parseInt(rpStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Criteria criteria = session.createCriteria(TingZiNanHui.class);
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
            TingZiNanHui tingzi = (TingZiNanHui) pageResult;
            row.setId(tingzi.getId());
            Date adstart = tingzi.getAdstart();
            Date adend = tingzi.getAdend();
            Date digtime = tingzi.getDigtime();
            Date finishdate = tingzi.getFinishdate();
            Date lastCareDate = tingzi.getLastCareDate();
            String[] cells = new String[] { tingzi.getLine(), "", tingzi.getArea(), tingzi.getStopnum(),
                    tingzi.getPicnumber(), tingzi.getEntitynum(), tingzi.getNanhuitingType(), tingzi.getAdop(),
                    adstart == null ? "" : formatter.format(adstart), adend == null ? "" : formatter.format(adend),
                    tingzi.getRoad(), tingzi.getStop(), tingzi.getAddress(), tingzi.getDirection(),
                    tingzi.getFinalstop(), tingzi.getNextstop(), digtime == null ? "" : formatter.format(digtime),
                    finishdate == null ? "" : formatter.format(finishdate),
                    lastCareDate == null ? "" : formatter.format(lastCareDate), tingzi.getComments(),
                    String.valueOf(tingzi.getId()), String.valueOf(tingzi.getModify()) };
            row.setCells(cells);
            tingziList.addRow(row);
        }

        if (pageResults.isEmpty()) {
            tingziList.setTotal("0");
        } else {
            Query countTingZi = session.createQuery("select count(*) from TingZiNanHui tz where tz.status='normal'");
            List list = countTingZi.list();
            tingziList.setTotal(String.valueOf(list.get(0)));
        }

        transaction.commit();
        
        SQLQuery sqlQuery = session.createSQLQuery("select count(distinct stopnum) as numbercount from tingzinanhui");
        List list = sqlQuery.list();
        BigInteger numberCount = (BigInteger) list.get(0);
        tingziList.setNumberCount(numberCount.toString());
        
        session.close();

        prepareResponse(resp);

        Gson convertor = new Gson();
        String json = convertor.toJson(tingziList);
        resp.getWriter().write(json);
        resp.getWriter().flush();
        resp.getWriter().close();
    }
}
