package com.shizhong.db.cmd.ligan;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shizhong.db.cmd.BaseAjaxServlet;
import com.shizhong.db.dao.Displayable;
import com.shizhong.db.querier.QuerierRepo;
import com.shizhong.web.bizmodel.EntityType;
import com.shizhong.web.meta.Command;

public class EntityImgShowServlet extends BaseAjaxServlet {

	private static final long serialVersionUID = 430195510740094785L;

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Command cmd = new Command(getServletName(),
				getInitParameter("param-meta"), req.getParameterMap());

		QuerierRepo querierRepo = QuerierRepo.getInstance();
		EntityType entityType = cmd.entityType();

		Object entity = querierRepo.getQuerier(entityType).retrieve(
				cmd.getParameters());
		if (!(entity instanceof Displayable)) {
			resp.getOutputStream().flush();
			resp.getOutputStream().close();
			return;
		}

		Displayable displayable = (Displayable) entity;
		try {
			InputStream inputStream = displayable.getImage().getBinaryStream();
			byte[] buffer = new byte[1024];
			int size = 0;
			while ((size = inputStream.read(buffer)) > 0) {
				resp.getOutputStream().write(buffer, 0, size);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// prepareResponse(resp);
		resp.getOutputStream().flush();
		resp.getOutputStream().close();
	}
}
