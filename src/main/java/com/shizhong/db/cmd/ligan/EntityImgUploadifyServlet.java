package com.shizhong.db.cmd.ligan;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shizhong.db.cmd.BaseAjaxServlet;
import com.shizhong.db.imgupdater.ImgUpdaterRepo;
import com.shizhong.web.bizmodel.EntityType;
import com.shizhong.web.meta.Command;

public class EntityImgUploadifyServlet extends BaseAjaxServlet {

	private static final long serialVersionUID = 430195510740094785L;

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Command cmd = new Command(getServletName(), getInitParameter("param-meta"), req.getParameterMap());

		ImgUpdaterRepo updatersRepo = ImgUpdaterRepo.getInstance();
		EntityType entityType = cmd.entityType();

		boolean success = updatersRepo.getUpdater(entityType).update(
				cmd.getParameters(), req);

		prepareResponse(resp);
		resp.getWriter().write(success ? SUCCESS : FAILURE);
		resp.getWriter().flush();
		resp.getWriter().close();
	}
}
