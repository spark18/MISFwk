package com.shizhong.db.cmd;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.shizhong.db.dao.PreTransaction;
import com.shizhong.db.dao.User;
import com.shizhong.web.session.UserPool;

public class UploadifyServlet extends BaseAjaxServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -989293557544197178L;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp)
			throws  ServletException, IOException {

		String savePath = this.getServletConfig().getServletContext()
				.getRealPath("");
		savePath = savePath + "/uploads/";

		File f1 = new File(savePath);
		System.out.println(savePath);

		if (!f1.exists()) {
			f1.mkdirs();
		}

		DiskFileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fac);
		upload.setHeaderEncoding("utf-8");

		List fileList = null;
		try {
			fileList = upload.parseRequest(req);
		} catch (FileUploadException ex) {
			return;
		}

		Iterator<FileItem> it = fileList.iterator();
		String username = req.getParameter("username");
		User curUser = UserPool.getUser(username);
		PreTransaction transaction = null;
		while (it.hasNext()) {
			FileItem item = it.next();
			if (!item.isFormField()) {
				
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(new ByteArrayInputStream(
								item.get())));

				String type = req.getParameter("type");
				InfoConvertor convertor = InfoConvertorRepo.getInstance().findConvertor(type);
				transaction = convertor.persist(reader, curUser);
				break;
			}
		}
		
		prepareResponse(resp);
		
		if(null != transaction) {
			resp.getWriter().println(transaction.getId());
		} else {
			throw new IOException();
		}
		resp.getWriter().flush();
		resp.getWriter().close();
	}

}
