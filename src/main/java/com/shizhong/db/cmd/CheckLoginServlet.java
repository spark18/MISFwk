package com.shizhong.db.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckLoginServlet extends BaseAjaxServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -989293557544197178L;

	public void exec(HttpServletRequest req, HttpServletResponse resp)
			throws  ServletException, IOException {
		prepareResponse(resp);

		String retCode = SUCCESS;
		resp.getWriter().write(retCode);
		resp.getWriter().flush();
		resp.getWriter().close();
	}
}
