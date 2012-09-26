package com.shizhong.db.cmd.ligan;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.shizhong.db.cmd.BaseAjaxServlet;
import com.shizhong.db.cmd.EntityList;
import com.shizhong.db.cmd.EntityRow;
import com.shizhong.db.util.SolrUtil;
import com.shizhong.solr.SolrQueryConstructorRepo;

public class TingZiNanHuiSearchServlet extends BaseAjaxServlet {

    /**
	 * 
	 */
    private static final long serialVersionUID = -989293557544197178L;

    @Override
    public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String rowPage = req.getParameter("rp");
        String page = req.getParameter("page");

        int rp = Integer.parseInt(rowPage);
        int p = Integer.parseInt(page);
        SolrQuery query = new SolrQuery();
        query.setRows(rp);
        query.setStart(rp * (p - 1));
        query.setSortField("entityindex", ORDER.desc);
        String queryString = SolrQueryConstructorRepo.getRepo().getConstructor("TingZiNanHui").construct(req);

        query.setQuery(queryString);

        EntityList tingzinhList = new EntityList();

        long resultCount = 0;
        try {
            QueryResponse response = SolrUtil.solrServer.query(query);
            SolrDocumentList docs = response.getResults();
            resultCount = docs.getNumFound();
            tingzinhList.setTotal(String.valueOf(docs.getNumFound()));
            tingzinhList.setPage(page);

            System.out.println("文档个数：" + docs.getNumFound());
            System.out.println("查询时间：" + response.getQTime());
            for (SolrDocument doc : docs) {
                EntityRow row = new EntityRow();
                String tingziid = (String) doc.getFieldValue("id");
                if (tingziid.startsWith("TingZiNanHui")) {
                    int collonIndex = tingziid.indexOf(":");
                    if (collonIndex > 0) {
                        tingziid = tingziid.substring(collonIndex + 1);
                    }
                }
                Date digtime = null;
                try {
                    digtime = parser.parse((String) doc.getFieldValue("tingzinhdigtime"));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Date finishtime = null;
                try {
                    finishtime = parser.parse((String) doc.getFieldValue("tingzinhfinishdate"));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Date lastcaredate = null;
                try {
                    lastcaredate = parser.parse((String) doc.getFieldValue("tingzinhlastcare"));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Date adstart = null;
                try {
                    adstart = parser.parse((String) doc.getFieldValue("tingzinhadstart"));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Date adend = null;
                try {
                    adend = parser.parse((String) doc.getFieldValue("tingzinhadend"));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                String stopnum = (String) doc.getFieldValue("tingzinhstopnum");
                String[] cells = new String[] { (String) doc.getFieldValue("tingzinhline"), "",
                        (String) doc.getFieldValue("tingzinharea"), stopnum,
                        (String) doc.getFieldValue("tingzinhpicnumber"),
                        (String) doc.getFieldValue("tingzinhentitynum"), (String) doc.getFieldValue("tingzinhtype"),
                        (String) doc.getFieldValue("tingzinhadop"), 
                        adstart == null? "": formatter.format(adstart),
                        adend == null? "": formatter.format(adend),(String) doc.getFieldValue("tingzinhroad"),
                        (String) doc.getFieldValue("tingzinhstop"), (String) doc.getFieldValue("tingzinhaddress"),
                        (String) doc.getFieldValue("tingzinhdirection"),
                        (String) doc.getFieldValue("tingzinhfinalstop"),
                        (String) doc.getFieldValue("tingzinhnextstop"),
                        digtime == null ? "" : formatter.format(digtime),
                        finishtime == null ? "" : formatter.format(finishtime),
                        lastcaredate == null? "": formatter.format(lastcaredate),
                        (String) doc.getFieldValue("tingzinhcomments"), tingziid, (String) doc.getFieldValue("modify"), };
                row.setCells(cells);
                tingzinhList.addRow(row);

            }
        } catch (SolrServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        prepareResponse(resp);

        query = new SolrQuery();
        query.setQuery(queryString);
        query.setRows((int) resultCount);
        query.setStart(0);
        Set<String> tingzinhNumCount = new HashSet<String>();
        
        try {
            QueryResponse response = SolrUtil.solrServer.query(query);
            SolrDocumentList docs = response.getResults();
            for (SolrDocument doc : docs) {

                String stopnum = (String) doc.getFieldValue("tingzinhstopnum");
                tingzinhNumCount.add(stopnum);
            }
        } catch (SolrServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        tingzinhList.setNumberCount(String.valueOf(tingzinhNumCount.size()));
        String tingziResult = convertor.toJson(tingzinhList);
        resp.getWriter().print(tingziResult);
        resp.getWriter().flush();
        resp.getWriter().close();
    }
}
