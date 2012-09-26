package com.shizhong.db.cmd.ligan;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.google.gson.Gson;
import com.shizhong.db.cmd.BaseAjaxServlet;
import com.shizhong.db.dao.Displayable;
import com.shizhong.db.dao.STATUS;
import com.shizhong.db.dao.SZTransaction;
import com.shizhong.db.dao.YangZhaoDian;
import com.shizhong.db.util.HibernateUtil;
import com.shizhong.web.session.UserPool;

public class YangZhaoDianImgUploadifyServlet extends BaseAjaxServlet {

	private static final long serialVersionUID = 430195510740094785L;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String rownum = req.getParameter("id");
		String entity = req.getParameter("entity");

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();

		Query query = session.createQuery("from " + entity
				+ " yangzhaodian where yangzhaodian.id=" + rownum);
		List yangzhaodianList = query.list();

		if (yangzhaodianList.isEmpty()) {
			prepareResponse(resp);
			resp.getWriter().write(FAILURE);
			resp.getWriter().flush();
			resp.getWriter().close();
			return;
		}

		Displayable displayable = (Displayable) yangzhaodianList.get(0);

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
		Blob img = displayable.getImage();
		while (it.hasNext()) {
			FileItem item = it.next();
			if (!item.isFormField()) {
				img = session.getLobHelper().createBlob(
						new byte[(int) item.getSize()]);
				try {
					img.setBytes(1, item.get());
					// img.setBinaryStream(1).write(item.get());
					// img.setBinaryStream(1).flush();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
//		displayable.setImage(img);

		if ("YangZhaoDian".equalsIgnoreCase(entity)) {
		    YangZhaoDian yangzhaodian = (YangZhaoDian) displayable;
		    yangzhaodian.setStatus(STATUS.archive.name());
		    session.save(displayable);

			String user = req.getParameter("username");
			SZTransaction szTransaction = new SZTransaction();
			szTransaction.setUser(UserPool.getUser(user));
			szTransaction.setTimeStamp(new Date());
			YangZhaoDian newYangZhaoDian = new YangZhaoDian();
            newYangZhaoDian.setAddress(yangzhaodian.getAddress());
            newYangZhaoDian.setArea(yangzhaodian.getArea());
            newYangZhaoDian.setComments(yangzhaodian.getComments());
            newYangZhaoDian.setRoad(yangzhaodian.getRoad());
            newYangZhaoDian.setType(yangzhaodian.getType());
            newYangZhaoDian.setImage(img);
            newYangZhaoDian.setEntityindex(yangzhaodian.getEntityindex());
            newYangZhaoDian.setAdop(yangzhaodian.getAdop());
            newYangZhaoDian.setAdstart(yangzhaodian.getAdstart());
            newYangZhaoDian.setAdend(yangzhaodian.getAdend());
            newYangZhaoDian.setLastCareDate(yangzhaodian.getLastCareDate());
            newYangZhaoDian.setDirection(yangzhaodian.getDirection());
            newYangZhaoDian.setEntityindex(yangzhaodian.getEntityindex());
            newYangZhaoDian.setEntitynum(yangzhaodian.getEntitynum());
            newYangZhaoDian.setEntityType(yangzhaodian.getEntityType());
            newYangZhaoDian.setModify(true);
            newYangZhaoDian.setYangzhaodianType(yangzhaodian.getYangzhaodianType());
            newYangZhaoDian.setStop(yangzhaodian.getStop());
            newYangZhaoDian.setStopnum(yangzhaodian.getStopnum());
            newYangZhaoDian.setType(yangzhaodian.getType());
            newYangZhaoDian.setStatus(STATUS.normal.name());

            szTransaction.setTimeStamp(new Date());
            Gson convertor = new Gson();
            Map<String, Object> modifiedFieldsMeta = new HashMap<String, Object>();
            modifiedFieldsMeta.put("entity", "yangzhaodian");
            Set<String> fields = new HashSet<String>();
            fields.add("image");
            modifiedFieldsMeta.put("fields", fields);
            modifiedFieldsMeta.put("id", yangzhaodian.getId());

            modifiedFieldsMeta.put("modifier", user);
            szTransaction.setComments(convertor.toJson(modifiedFieldsMeta));
            szTransaction.setYangzhaodian(newYangZhaoDian);
			session.save(szTransaction);
		} else {
			session.save(displayable);
		}
		transaction.commit();
		session.close();

		prepareResponse(resp);
		resp.getWriter().write(SUCCESS);
		resp.getWriter().flush();
		resp.getWriter().close();
	}
}
