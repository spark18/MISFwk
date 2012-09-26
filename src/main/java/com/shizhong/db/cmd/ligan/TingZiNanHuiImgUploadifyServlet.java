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
import com.shizhong.db.dao.SZTransaction;
import com.shizhong.db.dao.TingZiNanHui;
import com.shizhong.db.util.HibernateUtil;
import com.shizhong.db.util.SolrUtil;
import com.shizhong.web.session.UserPool;

public class TingZiNanHuiImgUploadifyServlet extends BaseAjaxServlet {

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
		    ex.printStackTrace();
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

		TingZiNanHui tingziNH = (TingZiNanHui) displayable;
		TingZiNanHui newTingziNH = new TingZiNanHui();
		if ("TingZiNanHui".equalsIgnoreCase(entity)) {
			String user = req.getParameter("username");
			tingziNH.setStatus(STATUS.archive.name());
			session.save(tingziNH);
			
			SZTransaction szTransaction = new SZTransaction();
			szTransaction.setUser(UserPool.getUser(user));
			szTransaction.setTimeStamp(new Date());
			newTingziNH.setAddress(tingziNH.getAddress());
			newTingziNH.setArea(tingziNH.getArea());
			newTingziNH.setStopnum(tingziNH.getStopnum());
			newTingziNH.setPicnumber(tingziNH.getPicnumber());
			newTingziNH.setEntitynum(tingziNH.getEntitynum());
			newTingziNH.setNanhuitingType(tingziNH.getNanhuitingType());
			newTingziNH.setAdstart(tingziNH.getAdstart());
			newTingziNH.setAdend(tingziNH.getAdend());
			newTingziNH.setComments(tingziNH.getComments());
			newTingziNH.setDigtime(tingziNH.getDigtime());
			newTingziNH.setDirection(tingziNH.getDirection());
			newTingziNH.setEntityType(tingziNH.getEntityType());
			newTingziNH.setFinalstop(tingziNH.getFinalstop());
			newTingziNH.setFinishdate(tingziNH.getFinishdate());
			newTingziNH.setImage(img);
			newTingziNH.setLine(tingziNH.getLine());
			newTingziNH.setModify(true);
			newTingziNH.setNextstop(tingziNH.getNextstop());
			newTingziNH.setAdop(tingziNH.getAdop());
			newTingziNH.setRoad(tingziNH.getRoad());
			newTingziNH.setStatus(STATUS.normal.name());
			newTingziNH.setStop(tingziNH.getStop());
			newTingziNH.setLastCareDate(tingziNH.getLastCareDate());
			newTingziNH.setEntityindex(tingziNH.getEntityindex());
			newTingziNH.setType(tingziNH.getType());
            
            Gson convertor = new Gson();
            Map<String, Object> modifiedFieldsMeta = new HashMap<String, Object>();
            modifiedFieldsMeta.put("entity", "tingzinanhui");
            Set<String> fields = new HashSet<String>();
            fields.add("image");
            modifiedFieldsMeta.put("fields", fields);
            modifiedFieldsMeta.put("id", tingziNH.getId());

            modifiedFieldsMeta.put("modifier", user);
            szTransaction.setComments(convertor.toJson(modifiedFieldsMeta));
            szTransaction.setTingzinanhui(newTingziNH);
			session.save(szTransaction);
		} else {
			session.save(displayable);
		}
		transaction.commit();
		session.close();

		SolrQuery solrQuery = new SolrQuery();
        String queryStr = "id:TingZiNanHui\\:" + tingziNH.getId();
        solrQuery.setQuery(queryStr);
        SimpleDateFormat formatter_en_US = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.ENGLISH);
        try {
            CommonsHttpSolrServer solrServer = SolrUtil.solrServer;
            QueryResponse response = solrServer.query(solrQuery);
            SolrDocumentList solrResults = response.getResults();
            solrServer.deleteByQuery(queryStr);
            solrServer.commit();

            SolrInputDocument solrDocument = new SolrInputDocument();
            solrDocument.addField("id", "TingZiNanHui:" + newTingziNH.getId());
            solrDocument.addField("tingzinhaddress", newTingziNH.getAddress());
            solrDocument.addField("tingzinharea", newTingziNH.getArea());
            solrDocument.addField("tingzinhstopnum", newTingziNH.getStopnum());
            solrDocument.addField("tingzinhentitynum", newTingziNH.getEntitynum());
            solrDocument.addField("tingzinhtype", newTingziNH.getNanhuitingType());
            solrDocument.addField("tingzinhcomments", newTingziNH.getComments());
            Date digtimeDate = newTingziNH.getDigtime();
            solrDocument.addField("tingzinhdigtime", digtimeDate == null ? "" : formatter_en_US.format(digtimeDate));
            solrDocument.addField("tingzinhdirection", newTingziNH.getDirection());
            solrDocument.addField("tingzinhfinalstop", newTingziNH.getFinalstop());
            Date finishdateDate = newTingziNH.getFinishdate();
            solrDocument.addField("tingzinhfinishdate",
                    finishdateDate == null ? "" : formatter_en_US.format(finishdateDate));
            solrDocument.addField("tingzinhline", newTingziNH.getLine());
            solrDocument.addField("tingzinhnextstop", newTingziNH.getNextstop());
            solrDocument.addField("tingzinhpicnumber", newTingziNH.getPicnumber());
            solrDocument.addField("tingzinhroad", newTingziNH.getRoad());
            solrDocument.addField("tingzinhmodifytype", newTingziNH.getType());
            solrDocument.addField("tingzinhstop", newTingziNH.getStop());
            solrDocument.addField("tingzinhadop", newTingziNH.getAdop());
            Date adstartDate = newTingziNH.getAdstart();
            solrDocument.addField("tingzinhadstart", adstartDate == null ? "" : formatter_en_US.format(adstartDate));
            Date adendDate = newTingziNH.getAdend();
            solrDocument.addField("tingzinhadend", adendDate == null ? "" : formatter_en_US.format(adendDate));
            Date lastCareDateDate = newTingziNH.getLastCareDate();
            solrDocument.addField("tingzinhlastcare",
                    lastCareDateDate == null ? "" : formatter_en_US.format(lastCareDateDate));
            solrDocument.addField("modify", newTingziNH.getModify());
            solrDocument.addField("entityindex", newTingziNH.getEntityindex());

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
