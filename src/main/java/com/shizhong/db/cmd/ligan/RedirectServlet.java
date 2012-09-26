package com.shizhong.db.cmd.ligan;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shizhong.db.cmd.BaseServlet;

public class RedirectServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -989293557544197178L;


	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp)
			throws  ServletException, IOException {
		String name = req.getParameter("username");
		req.setAttribute("username", name);
		
		String target = req.getParameter("target");
		System.out.println(target);
		getServletContext().getRequestDispatcher("/" + target).forward(req, resp);
	}
}
