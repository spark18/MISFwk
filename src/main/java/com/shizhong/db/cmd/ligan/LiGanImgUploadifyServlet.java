package com.shizhong.db.cmd.ligan;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.google.gson.Gson;
import com.shizhong.db.cmd.BaseAjaxServlet;
import com.shizhong.db.dao.Displayable;
import com.shizhong.db.dao.LiGan;
import com.shizhong.db.dao.STATUS;
import com.shizhong.db.dao.SZTransaction;
import com.shizhong.db.util.HibernateUtil;
import com.shizhong.db.util.SolrUtil;
import com.shizhong.web.session.UserPool;

public class LiGanImgUploadifyServlet extends BaseAjaxServlet {

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
				+ " ligan where ligan.id=" + rownum);
		List liganList = query.list();

		if (liganList.isEmpty()) {
			prepareResponse(resp);
			resp.getWriter().write(FAILURE);
			resp.getWriter().flush();
			resp.getWriter().close();
			return;
		}

		Displayable displayable = (Displayable) liganList.get(0);

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

		LiGan newLiGan = new LiGan();
		LiGan ligan = (LiGan)displayable;
		if ("LiGan".equalsIgnoreCase(entity)) {
			String user = req.getParameter("username");
			ligan.setStatus(STATUS.archive.name());
			session.save(ligan);
			
			SZTransaction szTransaction = new SZTransaction();
			szTransaction.setUser(UserPool.getUser(user));
			szTransaction.setTimeStamp(new Date());
			newLiGan.setAddress(ligan.getAddress());
			newLiGan.setArea(ligan.getArea());
			newLiGan.setComments(ligan.getComments());
			newLiGan.setDigdate(ligan.getDigdate());
			newLiGan.setDirection(ligan.getDirection());
			newLiGan.setEntityType(ligan.getEntityType());
			newLiGan.setFinalstop(ligan.getFinalstop());
			newLiGan.setFinishdate(ligan.getFinishdate());
			newLiGan.setImage(img);
			newLiGan.setEntityindex(ligan.getEntityindex());
			newLiGan.setLine(ligan.getLine());
			newLiGan.setModify(true);
			newLiGan.setStatus(STATUS.normal.name());
			newLiGan.setNextstop(ligan.getNextstop());
			newLiGan.setNumber(ligan.getNumber());
			newLiGan.setRoad(ligan.getRoad());
			newLiGan.setStop(ligan.getStop());
			newLiGan.setType(ligan.getType());
			
			Gson convertor = new Gson();
            Map<String, Object> modifiedFieldsMeta = new HashMap<String, Object>();
            modifiedFieldsMeta.put("entity", "ligan");
            Set<String> fields = new HashSet<String>();
            fields.add("image");
            modifiedFieldsMeta.put("fields", fields);
            modifiedFieldsMeta.put("id", ligan.getId());

            modifiedFieldsMeta.put("modifier", user);
            szTransaction.setComments(convertor.toJson(modifiedFieldsMeta));
            
			szTransaction.setLigan(newLiGan);
			session.save(szTransaction);
		} else {
			session.save(displayable);
		}
		transaction.commit();
		session.close();

		SolrQuery solrQuery = new SolrQuery();
        String queryStr = "id:LiGan\\:" + ligan.getId();
        solrQuery.setQuery(queryStr);
        SimpleDateFormat formatter_en_US = new SimpleDateFormat(
                "EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.ENGLISH);
        try {
            CommonsHttpSolrServer solrServer = SolrUtil.solrServer;
            QueryResponse response = solrServer.query(solrQuery);
            SolrDocumentList solrResults = response.getResults();
            solrServer.deleteByQuery(queryStr);

            SolrInputDocument solrDocument = new SolrInputDocument();
            solrDocument.addField("id", "LiGan:" + newLiGan.getId());
            solrDocument.addField("liganaddress", newLiGan.getAddress());
            solrDocument.addField("liganarea", newLiGan.getArea());
            solrDocument.addField("ligancomments", newLiGan.getComments());
            solrDocument.addField("ligandigtime",
                    formatter_en_US.format(newLiGan.getDigdate()));
            solrDocument.addField("ligandirection", newLiGan.getDirection());
            solrDocument.addField("liganfinalstop", newLiGan.getFinalstop());
            solrDocument.addField("liganfinishdate",
                    formatter_en_US.format(newLiGan.getFinishdate()));
            solrDocument.addField("liganline", newLiGan.getLine());
            solrDocument.addField("ligannextstop", newLiGan.getNextstop());
            solrDocument.addField("ligannumber", newLiGan.getNumber());
            solrDocument.addField("liganroad", newLiGan.getRoad());
            solrDocument.addField("liganstop", newLiGan.getStop());
            solrDocument.addField("ligantype", newLiGan.getType());
            solrDocument.addField("modify", newLiGan.getModify());
            solrDocument.addField("entityindex", newLiGan.getEntityindex());

            solrServer.add(solrDocument);
            solrServer.commit();
            System.out.println(solrResults);
        } catch (Exception e) {
            e.printStackTrace();
        }
		prepareResponse(resp);
		resp.getWriter().write(SUCCESS);
		resp.getWriter().flush();
		resp.getWriter().close();
	}
}
