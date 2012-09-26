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
import com.shizhong.db.dao.STATUS;
import com.shizhong.db.dao.TingZi;
import com.shizhong.db.dao.SZTransaction;
import com.shizhong.db.util.HibernateUtil;
import com.shizhong.db.util.SolrUtil;
import com.shizhong.web.session.UserPool;

public class TingZiImgUploadifyServlet extends BaseAjaxServlet {

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
				+ " tingzi where tingzi.id=" + rownum);
		List tingziList = query.list();

		if (tingziList.isEmpty()) {
			prepareResponse(resp);
			resp.getWriter().write(FAILURE);
			resp.getWriter().flush();
			resp.getWriter().close();
			return;
		}

		Displayable displayable = (Displayable) tingziList.get(0);

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
//		String fileName = "";
		while (it.hasNext()) {
			FileItem item = it.next();
			if (!item.isFormField()) {
//				fileName = item.getName();
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

		TingZi tingzi = (TingZi) displayable;
		TingZi newTingzi = new TingZi();
		if ("TingZi".equalsIgnoreCase(entity)) {
			String user = req.getParameter("username");
			tingzi.setStatus(STATUS.archive.name());
			session.save(tingzi);
			
			SZTransaction szTransaction = new SZTransaction();
			szTransaction.setUser(UserPool.getUser(user));
			szTransaction.setTimeStamp(new Date());
			
			newTingzi.setAddress(tingzi.getAddress());
			newTingzi.setArea(tingzi.getArea());
			newTingzi.setComments(tingzi.getComments());
			newTingzi.setDigdate(tingzi.getDigdate());
			newTingzi.setDirection(tingzi.getDirection());
			newTingzi.setEntityType(tingzi.getEntityType());
			newTingzi.setFinalstop(tingzi.getFinalstop());
			newTingzi.setFinishdate(tingzi.getFinishdate());
			newTingzi.setImage(img);
			newTingzi.setLine(tingzi.getLine());
			newTingzi.setModify(true);
			newTingzi.setNextstop(tingzi.getNextstop());
			newTingzi.setNumber(tingzi.getNumber());
			newTingzi.setRoad(tingzi.getRoad());
			newTingzi.setStatus(STATUS.normal.name());
			newTingzi.setStop(tingzi.getStop());
			newTingzi.setEntityindex(tingzi.getEntityindex());
			newTingzi.setType(tingzi.getType());
			
			Gson convertor = new Gson();
            Map<String, Object> modifiedFieldsMeta = new HashMap<String, Object>();
            modifiedFieldsMeta.put("entity", "tingzi");
            Set<String> fields = new HashSet<String>();
            fields.add("image");
            modifiedFieldsMeta.put("fields", fields);
            modifiedFieldsMeta.put("id", tingzi.getId());

            modifiedFieldsMeta.put("modifier", user);
            szTransaction.setComments(convertor.toJson(modifiedFieldsMeta));
            szTransaction.setTingzi(newTingzi);
			session.save(szTransaction);
		} else {
			session.save(displayable);
		}
		transaction.commit();
		session.close();

		SolrQuery solrQuery = new SolrQuery();
        String queryStr = "id:TingZi\\:" + tingzi.getId();
        solrQuery.setQuery(queryStr);
        SimpleDateFormat formatter_en_US = new SimpleDateFormat(
                "EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.ENGLISH);
        try {
            CommonsHttpSolrServer solrServer = SolrUtil.solrServer;
            QueryResponse response = solrServer.query(solrQuery);
            SolrDocumentList solrResults = response.getResults();
            solrServer.deleteByQuery(queryStr);

            SolrInputDocument solrDocument = new SolrInputDocument();
            solrDocument.addField("id", "TingZi:" + newTingzi.getId());
            solrDocument.addField("tingziaddress", newTingzi.getAddress());
            solrDocument.addField("tingziarea", newTingzi.getArea());
            solrDocument.addField("tingzicomments", newTingzi.getComments());
            solrDocument.addField("tingzidigtime",
                    formatter_en_US.format(newTingzi.getDigdate()));
            solrDocument.addField("tingzidirection", newTingzi.getDirection());
            solrDocument.addField("tingzifinalstop", newTingzi.getFinalstop());
            solrDocument.addField("tingzifinishdate",
                    formatter_en_US.format(newTingzi.getFinishdate()));
            solrDocument.addField("tingziline", newTingzi.getLine());
            solrDocument.addField("tingzinextstop", newTingzi.getNextstop());
            solrDocument.addField("tingzinumber", newTingzi.getNumber());
            solrDocument.addField("tingziroad", newTingzi.getRoad());
            solrDocument.addField("tingzistop", newTingzi.getStop());
            solrDocument.addField("tingzitype", newTingzi.getType());
            solrDocument.addField("modify", newTingzi.getModify());
            solrDocument.addField("entityindex", newTingzi.getEntityindex());

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
