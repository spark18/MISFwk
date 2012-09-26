package com.shizhong.db.cmd.ligan;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
import com.shizhong.db.dao.LiGanPreview;
import com.shizhong.db.util.HibernateUtil;

public class LiGanListPreviewServlet extends BaseAjaxServlet {

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

		Criteria criteria = session.createCriteria(LiGanPreview.class);

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

		SimpleDateFormat formatter = new SimpleDateFormat("MMM dd,yyyy",
				Locale.CHINESE);
		for (Object pageResult : pageResults) {
			EntityRow row = new EntityRow();
			LiGanPreview liGan = (LiGanPreview) pageResult;
			row.setId(liGan.getId());
			String[] cells = new String[] { liGan.getLine(), liGan.getArea(),
					liGan.getNumber(), liGan.getRoad(), liGan.getStop(),
					liGan.getAddr(), liGan.getDirection(),
					formatter.format(liGan.getDigdate()), liGan.getFinalstop(),
					liGan.getNextstop(),
					formatter.format(liGan.getFinishdate()),
					liGan.getComments(), String.valueOf(liGan.getId()) };
			row.setCells(cells);
			liGanList.addRow(row);
		}

		if (pageResults.isEmpty()) {
			liGanList.setTotal("0");
		} else {
			liGanList.setTotal(String.valueOf(pageResults.size()));
		}

		transaction.commit();
		session.close();

		prepareResponse(resp);

		Gson convertor = new Gson();
		String json = convertor.toJson(liGanList);
		resp.getWriter().write(json);
		resp.getWriter().flush();
		resp.getWriter().close();
	}
}
