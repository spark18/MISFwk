package com.shizhong.db.cmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.shizhong.db.util.HibernateUtil;
import com.shizhong.db.util.SolrUtil;
import com.shizhong.solr.SolrQueryConstructorRepo;

public class EntityCountServlet extends BaseAjaxServlet {

    /**
	 * 
	 */
    private static final long serialVersionUID = -989293557544197178L;

    @SuppressWarnings("rawtypes")
    @Override
    public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String entity = req.getParameter("entity");
        String queryString = SolrQueryConstructorRepo.getRepo().getConstructor(entity).construct(req);

        String sql = "select count(*) from " + entity + " where status='normal'";
        if (!"*:*".equals(queryString)) {
            SolrQuery query = new SolrQuery();
            query.setRows(-1);
            query.setSortField("entityindex", ORDER.desc);
            query.setQuery(queryString.toString());

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
                query.setQuery(queryString.toString());

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
            StringBuilder idbuffer = new StringBuilder(512);
            for (Long id : ids) {
                idbuffer.append(id).append(",");
            }
            sql += " and id in(" + idbuffer.substring(0, idbuffer.length() - 1) + ")";
        }
        sql += " group by entitynum";
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();

        Query criteria = session.createQuery(sql);

        List pageResults = criteria.list();

        prepareResponse(resp);

        transaction.commit();
        session.close();

        resp.getWriter().print(pageResults.size());
        resp.getWriter().flush();
        resp.getWriter().close();
    }

}
