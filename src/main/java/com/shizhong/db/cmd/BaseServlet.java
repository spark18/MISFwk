package com.shizhong.db.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shizhong.web.session.UserPool;

public abstract class BaseServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7163602178014449345L;
	protected final String CUR_USER = "cur_user";

	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		checkLogin(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		checkLogin(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		checkLogin(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		checkLogin(req, resp);
	}

	private void checkLogin(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter("username");
		if (!UserPool.isUserLive(name)) {
			getServletContext().getRequestDispatcher("/").forward(req, resp);
			return;
		}

		exec(req, resp);
	}

	abstract public void exec(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException;

}
