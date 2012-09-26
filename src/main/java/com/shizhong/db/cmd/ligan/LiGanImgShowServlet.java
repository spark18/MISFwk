package com.shizhong.db.cmd.ligan;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.shizhong.db.cmd.BaseAjaxServlet;
import com.shizhong.db.dao.Displayable;
import com.shizhong.db.util.HibernateUtil;

public class LiGanImgShowServlet extends BaseAjaxServlet {

	private static final long serialVersionUID = 430195510740094785L;

	@SuppressWarnings({ "rawtypes" })
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String rownum = req.getParameter("id");
		String entity = req.getParameter("entity");
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();

		Query query = session
				.createQuery("from " + entity + " ligan where ligan.id=" + rownum);
		List liganList = query.list();

		if (liganList.isEmpty()) {
			prepareResponse(resp);
			resp.getWriter().write(FAILURE);
			resp.getWriter().flush();
			resp.getWriter().close();
			return;
		}

		Displayable ligan = (Displayable) liganList.get(0);
		transaction.commit();
		session.close();

		try {
			InputStream inputStream = ligan.getImage().getBinaryStream();
			byte[] buffer = new byte[1024];
			int size = 0;
			while((size = inputStream.read(buffer)) > 0) {
				resp.getOutputStream().write(buffer, 0, size);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		prepareResponse(resp);
		resp.getOutputStream().flush();
		resp.getOutputStream().close();
	}
}
