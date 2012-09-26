package com.shizhong.db.imgupdater;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.shizhong.db.dao.BusTicketPreview;
import com.shizhong.db.dao.Displayable;
import com.shizhong.db.util.HibernateUtil;
import com.shizhong.web.meta.Parameter;

public class BusTicketImgPreviewUpdater implements ImgUpdater<BusTicketPreview> {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean update(Map<String, Parameter> parameters,
			HttpServletRequest req) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();

		Criteria criteria = session.createCriteria(BusTicketPreview.class);
		Parameter<Long> id = parameters.get("id");
		criteria.add(Restrictions.eq(id.getName(), id.getValue()));
		BusTicketPreview busTicketAir = (BusTicketPreview) criteria.uniqueResult();

		if (null == busTicketAir) {
			return false;
		}

		Displayable displayable = (Displayable) busTicketAir;

		DiskFileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fac);
		upload.setHeaderEncoding("utf-8");

		List fileList = null;
		try {
			fileList = upload.parseRequest(req);
		} catch (FileUploadException ex) {
			return false;
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

		displayable.setImage(img);
		session.save(displayable);
		transaction.commit();
		session.close();

		return true;
	}

}
