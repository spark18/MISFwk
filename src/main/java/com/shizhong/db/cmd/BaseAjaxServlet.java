package com.shizhong.db.cmd;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.shizhong.web.session.UserPool;

public abstract class BaseAjaxServlet extends HttpServlet {

	protected final String SUCCESS = "SUCCESS";
	protected final String FAILURE = "FAILURE";
	protected static final Gson convertor = new Gson();
	protected static final SimpleDateFormat formatter = new SimpleDateFormat("MMM dd,yyyy",
			Locale.CHINESE);
	protected static final SimpleDateFormat parser = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.ENGLISH);
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
			prepareResponse(resp);
			resp.getWriter().write(FAILURE);
			resp.getWriter().flush();
			resp.getWriter().close();
			return;
		}
		
		exec(req, resp);
	}
	
	protected void prepareResponse(HttpServletResponse resp) {
		resp.setContentType("text/plain;charset=GBK");
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-control", "no-cache, must-revalidate");
	}
	
	abstract public void exec(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException;

}
