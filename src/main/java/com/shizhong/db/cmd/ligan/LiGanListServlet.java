package com.shizhong.db.cmd.ligan;

import java.io.IOException;
import java.math.BigInteger;
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
import com.shizhong.db.dao.LiGan;
import com.shizhong.db.util.HibernateUtil;

public class LiGanListServlet extends BaseAjaxServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -989293557544197178L;

	@SuppressWarnings("rawtypes")
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();

		EntityList liGanList = new EntityList();
		// page=1&rp=15&sortname=iso&sortorder=asc&query=&qtype=name
		int page = 1;
		String pageStr = req.getParameter("page");
		try {
			page = Integer.parseInt(pageStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		liGanList.setPage(String.valueOf(page));

		int rp = 100;
		String rpStr = req.getParameter("rp");
		try {
			rp = Integer.parseInt(rpStr);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Criteria criteria = session.createCriteria(LiGan.class);
		String number = req.getParameter("number");
		if(null != number) {
			criteria.add(Restrictions.eq("number", number));
		}
		criteria.add(Restrictions.eq("status", "normal"));
		criteria.addOrder(Order.asc("entityindex"));

		criteria.setFirstResult((page - 1) * rp);
		criteria.setMaxResults(rp);
		List pageResults = criteria.list();

		for (Object pageResult : pageResults) {
			EntityRow row = new EntityRow();
			LiGan liGan = (LiGan) pageResult;
			row.setId(liGan.getId());
			String[] cells = new String[] { liGan.getLine(), liGan.getArea(),
					liGan.getNumber(), liGan.getRoad(), liGan.getStop(),
					liGan.getAddress(), liGan.getDirection(),
					formatter.format(liGan.getDigdate()), liGan.getFinalstop(),
					liGan.getNextstop(),
					formatter.format(liGan.getFinishdate()),
					liGan.getComments(), String.valueOf(liGan.getId()), String.valueOf(liGan.getModify()) };
			row.setCells(cells);
			liGanList.addRow(row);
		}

		if (pageResults.isEmpty()) {
			liGanList.setTotal("0");
		} else {
		    Query countLiGan = session.createQuery("select count(*) from LiGan lg where lg.status='normal'");
            List list = countLiGan.list();
            liGanList.setTotal(String.valueOf(list.get(0)));
		}

		transaction.commit();
		
		SQLQuery sqlQuery = session.createSQLQuery("select count(distinct number) as numbercount from ligan");
		List list = sqlQuery.list();
		BigInteger numberCount = (BigInteger) list.get(0);
		liGanList.setNumberCount(numberCount.toString());
		session.close();

		prepareResponse(resp);

		Gson convertor = new Gson();
		String json = convertor.toJson(liGanList);
		resp.getWriter().write(json);
		resp.getWriter().flush();
		resp.getWriter().close();
	}
}
