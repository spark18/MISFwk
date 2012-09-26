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
import com.shizhong.db.dao.TingZiPreview;
import com.shizhong.db.util.HibernateUtil;

public class TingZiListPreviewServlet extends BaseAjaxServlet {

	private static final long serialVersionUID = -989293557544197178L;

	@SuppressWarnings("rawtypes")
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
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

		Criteria criteria = session.createCriteria(TingZiPreview.class);

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
			TingZiPreview tingzi = (TingZiPreview) pageResult;
			row.setId(tingzi.getId());
            String[] cells = new String[] { tingzi.getLine(), tingzi.getArea(), tingzi.getNumber(), tingzi.getEquiptype(),
                    tingzi.getFactory(), tingzi.getRoad(), tingzi.getStop(), tingzi.getAddr(), tingzi.getDirection(),
                    formatter.format(tingzi.getDigdate()), tingzi.getFinalstop(), tingzi.getNextstop(),
                    formatter.format(tingzi.getFinishdate()), tingzi.getComments(), String.valueOf(tingzi.getId()) };
			row.setCells(cells);
			tingziList.addRow(row);
		}

		if (pageResults.isEmpty()) {
			tingziList.setTotal("0");
		} else {
			tingziList.setTotal(String.valueOf(pageResults.size()));
		}

		transaction.commit();
		session.close();

		prepareResponse(resp);

		Gson convertor = new Gson();
		String json = convertor.toJson(tingziList);
		resp.getWriter().write(json);
		resp.getWriter().flush();
		resp.getWriter().close();
	}
}
