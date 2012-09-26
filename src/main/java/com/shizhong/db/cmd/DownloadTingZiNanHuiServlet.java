package com.shizhong.db.cmd;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.shizhong.db.dao.TingZiNanHui;
import com.shizhong.db.util.HibernateUtil;
import com.shizhong.db.util.SolrUtil;
import com.shizhong.solr.SolrQueryConstructorRepo;

public class DownloadTingZiNanHuiServlet extends BaseAjaxServlet {

    /**
	 * 
	 */
    private static final long serialVersionUID = -989293557544197178L;

    @SuppressWarnings("rawtypes")
    @Override
    public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String tingZiNHQuery = SolrQueryConstructorRepo.getRepo().getConstructor("TingZiNanHui").construct(req);
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();

        Criteria criteria = session.createCriteria(TingZiNanHui.class);
        criteria.add(Restrictions.eq("status", "normal"));
        if (!"*:*".equals(tingZiNHQuery)) {
            SolrQuery query = new SolrQuery();
            query.setRows(-1);
            query.setSortField("entityindex", ORDER.desc);
            query.setQuery(tingZiNHQuery);

            QueryResponse response = null;
            try {
                response = SolrUtil.solrServer.query(query);
            } catch (SolrServerException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            SolrDocumentList docs = response.getResults();
            List<Long> ids = new ArrayList<Long>();
            for (int i = 0; i < docs.getNumFound(); i += 100) {
                query = new SolrQuery();
                query.setRows(100);
                query.setStart(i);
                query.setSortField("entityindex", ORDER.desc);
                query.setQuery(tingZiNHQuery);

                response = null;
                try {
                    response = SolrUtil.solrServer.query(query);
                } catch (SolrServerException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                SolrDocumentList paginatedDocs = response.getResults();
                for (int j = 0; j < paginatedDocs.size(); j++) {
                    SolrDocument document = paginatedDocs.get(j);
                    String docId = document.getFieldValue("id").toString();
                    int sepIndex = docId.indexOf(":");
                    ids.add(Long.parseLong(docId.substring(sepIndex + 1)));
                }
            }
            criteria.add(Restrictions.in("id", ids.toArray()));
        }
        criteria.addOrder(Order.asc("entityindex"));

        List pageResults = criteria.list();

        resp.setContentType("csv/text;charset=GBK");
        resp.setCharacterEncoding("GBK");
        resp.addHeader("Content-Disposition", "attachment;filename=tingzinanhui.csv");
        PrintWriter writer = resp.getWriter();
        writer.write("区域,站点编号,画面编号,设施编号,设施型号,广告客户/画面名称,画面上画日期,画面到期日期,路名,站名,地址,车向,线路,开往方向,各线路下一站,挖坑日期,完成日期,上次养护日期,备注\n");
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd日 yyyy", Locale.CHINESE);
        for (Object pageResult : pageResults) {
            TingZiNanHui nanhui = (TingZiNanHui) pageResult;
            Date adstart = nanhui.getAdstart();
            String adstartstr = adstart == null ? "" : formatter.format(adstart);
            Date adend = nanhui.getAdend();
            String adendstr = adend == null ? "" : formatter.format(adend);
            Date finishdate = nanhui.getFinishdate();
            String finishdatestr = finishdate == null ? "" : formatter.format(finishdate);
            Date lastCareDate = nanhui.getLastCareDate();
            String lastcaredatestr = lastCareDate == null ? "" : formatter.format(lastCareDate);
            Date digtime = nanhui.getDigtime();
            String digtimestr = digtime == null ? "" : formatter.format(digtime);
            writer.write(nanhui.getArea() + "," + nanhui.getStopnum() + "," + nanhui.getPicnumber() + ","
                    + nanhui.getEntitynum() + "," + nanhui.getNanhuitingType() + "," + nanhui.getAdop() + ","
                    + adstartstr + "," + adendstr + "," + nanhui.getRoad() + "," + nanhui.getStop() + ","
                    + nanhui.getAddress() + "," + nanhui.getDirection() + "," + nanhui.getLine() + ","
                    + nanhui.getFinalstop() + "," + nanhui.getNextstop() + "," + digtimestr + "," + finishdatestr + ","
                    + lastcaredatestr + "," + nanhui.getComments() + "\n");
        }

        transaction.commit();
        session.close();

        writer.flush();
        writer.close();
    }

}
