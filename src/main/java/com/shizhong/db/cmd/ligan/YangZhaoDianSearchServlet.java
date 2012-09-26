package com.shizhong.db.cmd.ligan;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.shizhong.db.cmd.BaseAjaxServlet;
import com.shizhong.db.cmd.EntityList;
import com.shizhong.db.cmd.EntityRow;
import com.shizhong.db.util.SolrUtil;
import com.shizhong.solr.SolrQueryConstructorRepo;

public class YangZhaoDianSearchServlet extends BaseAjaxServlet {

    /**
	 * 
	 */
    private static final long serialVersionUID = -989293557544197178L;

    @Override
    public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SolrQuery query = new SolrQuery();

        String queryString = SolrQueryConstructorRepo.getRepo().getConstructor("YangZhaoDian").construct(req);

        query.setQuery(queryString);
        String rp = req.getParameter("rp");
        String page = req.getParameter("page");
        int rowPage = Integer.parseInt(rp);
        int pageInt = Integer.parseInt(page);
        query.setRows(rowPage);
        query.setStart(rowPage * (pageInt - 1));
        EntityList yangzhaodianList = new EntityList();

        long resultCount = 0;
        try {
            QueryResponse response = SolrUtil.solrServer.query(query);
            SolrDocumentList docs = response.getResults();
            resultCount = docs.getNumFound();
            yangzhaodianList.setTotal(String.valueOf(docs.getNumFound()));
            yangzhaodianList.setPage(String.valueOf(pageInt));

            System.out.println("文档个数：" + docs.getNumFound());
            System.out.println("查询时间：" + response.getQTime());
            for (SolrDocument doc : docs) {
                EntityRow row = new EntityRow();
                String yangzhaodianid = (String) doc.getFieldValue("id");
                if (yangzhaodianid.startsWith("YangZhaoDian")) {
                    int collonIndex = yangzhaodianid.indexOf(":");
                    if (collonIndex > 0) {
                        yangzhaodianid = yangzhaodianid.substring(collonIndex + 1);
                    }
                }
                Date adstart = null;
                try {
                    adstart = parser.parse((String) doc.getFieldValue("yangzhaodianadstart"));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Date adend = null;
                try {
                    adend = parser.parse((String) doc.getFieldValue("yangzhaodianadend"));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Date lastcaredate = null;
                try {
                    lastcaredate = parser.parse((String) doc.getFieldValue("yangzhaodianlastcare"));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                String stopnum = (String) doc.getFieldValue("yangzhaodianstopnum");
                String[] cells = new String[] { "", (String) doc.getFieldValue("yangzhaodianarea"), stopnum,
                        (String) doc.getFieldValue("yangzhaodianpicnumber"),
                        (String) doc.getFieldValue("yangzhaodianentitynum"),
                        (String) doc.getFieldValue("yangzhaodiantype"), (String) doc.getFieldValue("yangzhaodianadop"),
                        adstart == null ? "" : formatter.format(adstart), adend == null ? "" : formatter.format(adend),
                        (String) doc.getFieldValue("yangzhaodianroad"), (String) doc.getFieldValue("yangzhaodianstop"),
                        (String) doc.getFieldValue("yangzhaodianaddress"),
                        (String) doc.getFieldValue("yangzhaodiandirection"),
                        lastcaredate == null ? "" : formatter.format(lastcaredate),
                        (String) doc.getFieldValue("yangzhaodiancomments"), yangzhaodianid,
                        (String) doc.getFieldValue("modify") };
                row.setCells(cells);
                yangzhaodianList.addRow(row);

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
        Set<String> yangzhaodianNumCount = new HashSet<String>();
        
        try {
            QueryResponse response = SolrUtil.solrServer.query(query);
            SolrDocumentList docs = response.getResults();
            for (SolrDocument doc : docs) {

                String stopnum = (String) doc.getFieldValue("yangzhaodianstopnum");
                yangzhaodianNumCount.add(stopnum);
            }
        } catch (SolrServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        yangzhaodianList.setNumberCount(String.valueOf(yangzhaodianNumCount.size()));
        String tingziResult = convertor.toJson(yangzhaodianList);
        resp.getWriter().print(tingziResult);
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    public static boolean isBlank(String key) {
        return key == null || key.isEmpty();
    }
}
