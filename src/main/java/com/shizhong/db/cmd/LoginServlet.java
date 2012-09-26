package com.shizhong.db.cmd;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.google.gson.JsonObject;
import com.shizhong.db.dao.User;
import com.shizhong.db.util.HibernateUtil;
import com.shizhong.web.session.UserPool;

public class LoginServlet extends BaseAjaxServlet {

	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		exec(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		exec(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		exec(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		exec(req, resp);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -989293557544197178L;

	@SuppressWarnings("rawtypes")
	public void exec(HttpServletRequest req, HttpServletResponse resp)
			throws  ServletException, IOException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();

		String name = req.getParameter("name");
		String passwd = req.getParameter("passwd");

		Query query = session.createQuery("from User user where user.name='" + name
				+ "' and user.passwd='" + passwd + "'");
		List users = query.list();

		transaction.commit();
		session.close();

		prepareResponse(resp);

		JsonObject returnStatus = new JsonObject();
		String retCode = SUCCESS;
		if(users.isEmpty()) {
			retCode = FAILURE;
		} else {
			User user = (User) users.get(0);
			UserPool.addUser(user);
			returnStatus.addProperty("username", user.getName());
		}
		returnStatus.addProperty("retCode", retCode);
		resp.getWriter().write(convertor.toJson(returnStatus));
		resp.getWriter().flush();
		resp.getWriter().close();
	}
}
