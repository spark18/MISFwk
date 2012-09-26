package com.shizhong.db.cmd.ligan;

import java.io.IOException;
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

public class LiGanSearchServlet extends BaseAjaxServlet {

    /**
	 * 
	 */
    private static final long serialVersionUID = -989293557544197178L;

    @Override
    public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SolrQuery liganQuery = new SolrQuery();

        StringBuilder queryString = constructLiganQuery(req);

        liganQuery.setQuery(queryString.toString());
        String rp = req.getParameter("rp");
        String page = req.getParameter("page");
        int rowPage = Integer.parseInt(rp);
        int pageInt = Integer.parseInt(page);
        liganQuery.setRows(rowPage);
        liganQuery.setStart(rowPage * (pageInt - 1));
        EntityList equipList = new EntityList();

        int totalCount = 0;
        long resultCount = 0;
        try {
            QueryResponse response = SolrUtil.solrServer.query(liganQuery);
            SolrDocumentList docs = response.getResults();
            resultCount = docs.getNumFound();
            equipList.setPage(String.valueOf(pageInt));

            totalCount += docs.getNumFound();
            System.out.println("文档个数：" + docs.getNumFound());
            System.out.println("查询时间：" + response.getQTime());
            for (SolrDocument doc : docs) {
                EntityRow row = new EntityRow();
                String liganid = (String) doc.getFieldValue("id");
                if (liganid.startsWith("LiGan")) {
                    int collonIndex = liganid.indexOf(":");
                    if (collonIndex > 0) {
                        liganid = liganid.substring(collonIndex + 1);
                    }
                }
                Date digtime = null;
                try {
                    digtime = parser.parse((String) doc.getFieldValue("ligandigtime"));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Date finishtime = null;
                try {
                    finishtime = parser.parse((String) doc.getFieldValue("liganfinishdate"));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                String liganNum = (String) doc.getFieldValue("ligannumber");
                String[] cells = new String[] { (String) doc.getFieldValue("liganline"),
                        (String) doc.getFieldValue("liganarea"), liganNum,
                        (String) doc.getFieldValue("liganroad"), (String) doc.getFieldValue("liganstop"),
                        (String) doc.getFieldValue("liganaddress"), (String) doc.getFieldValue("ligandirection"),
                        digtime == null ? "---" : formatter.format(digtime),
                        (String) doc.getFieldValue("liganfinalstop"), (String) doc.getFieldValue("ligannextstop"),
                        finishtime == null ? "---" : formatter.format(finishtime),
                        (String) doc.getFieldValue("ligancomments"), liganid, "", "ligan", (String)doc.getFieldValue("modify") };
                row.setCells(cells);
                equipList.addRow(row);

            }
        } catch (SolrServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        liganQuery = new SolrQuery();
        liganQuery.setQuery(queryString.toString());
        liganQuery.setRows((int) resultCount);
        liganQuery.setStart(0);
        Set<String> liganNumCount = new HashSet<String>();
        
        try {
            QueryResponse response = SolrUtil.solrServer.query(liganQuery);
            SolrDocumentList docs = response.getResults();
            for (SolrDocument doc : docs) {

                String stopnum = (String) doc.getFieldValue("ligannumber");
                liganNumCount.add(stopnum);
            }
        } catch (SolrServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        StringBuilder tingziQuery = extractTingZiQuery(req);
        SolrQuery tzQuery = new SolrQuery();

        tzQuery.setQuery(tingziQuery.toString());
        try {
            QueryResponse response = SolrUtil.solrServer.query(tzQuery);
            SolrDocumentList docs = response.getResults();
            equipList.setTotal(String.valueOf(docs.getNumFound()));

            resultCount = docs.getNumFound();
            totalCount += docs.getNumFound();
            System.out.println("文档个数：" + docs.getNumFound());
            System.out.println("查询时间：" + response.getQTime());
            for (SolrDocument doc : docs) {
                EntityRow row = new EntityRow();
                String tingziid = (String) doc.getFieldValue("id");
                if (tingziid.startsWith("TingZi")) {
                    int collonIndex = tingziid.indexOf(":");
                    if (collonIndex > 0) {
                        tingziid = tingziid.substring(collonIndex + 1);
                    }
                }
                Date digtime = null;
                try {
                    digtime = parser.parse((String) doc.getFieldValue("tingzidigtime"));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Date finishtime = null;
                try {
                    finishtime = parser.parse((String) doc.getFieldValue("tingzifinishdate"));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                String tingziNum = (String) doc.getFieldValue("tingzinumber");
                String[] cells = new String[] { (String) doc.getFieldValue("tingziline"),
                        (String) doc.getFieldValue("tingziarea"), tingziNum,
                        (String) doc.getFieldValue("tingziroad"), (String) doc.getFieldValue("tingzistop"),
                        (String) doc.getFieldValue("tingziaddress"), (String) doc.getFieldValue("tingzidirection"),
                        digtime == null ? "---" : formatter.format(digtime),
                        (String) doc.getFieldValue("tingzifinalstop"), (String) doc.getFieldValue("tingzinextstop"),
                        finishtime == null ? "---" : formatter.format(finishtime),
                        (String) doc.getFieldValue("tingzicomments"), tingziid, "", "tingzi", (String)doc.getFieldValue("modify") };
                row.setCells(cells);
                equipList.addRow(row);

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        equipList.setTotal("" + totalCount);
        prepareResponse(resp);
        
        tzQuery = new SolrQuery();
        tzQuery.setQuery(tingziQuery.toString());
        tzQuery.setRows((int) resultCount);
        tzQuery.setStart(0);
        Set<String> tingziNumCount = new HashSet<String>();
        
        try {
            QueryResponse response = SolrUtil.solrServer.query(tzQuery);
            SolrDocumentList docs = response.getResults();
            for (SolrDocument doc : docs) {

                String stopnum = (String) doc.getFieldValue("tingzinumber");
                tingziNumCount.add(stopnum);
            }
        } catch (SolrServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        equipList.setNumberCount(String.valueOf(liganNumCount.size() + tingziNumCount.size()));
        String liganResult = convertor.toJson(equipList);
        resp.getWriter().print(liganResult);
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    private StringBuilder extractTingZiQuery(HttpServletRequest req) {
        String line = req.getParameter("line");
        String area = req.getParameter("area");
        String number = req.getParameter("number");
        String road = req.getParameter("road");
        String stop = req.getParameter("stop");
        String address = req.getParameter("address");
        String direction = req.getParameter("direction");
        String finalstop = req.getParameter("finalstop");
        String nextstop = req.getParameter("nextstop");
        String digdate = req.getParameter("digdate");
        String finishdate = req.getParameter("finishdate");
        String comments = req.getParameter("comments");

        StringBuilder queryString = new StringBuilder();
        if (!isBlank(line)) {
            queryString.append("tingziline:").append(line).append("*~0.1");
        }

        if (!isBlank(area)) {
            queryString.append(" tingziarea:").append(area).append("*~0.1");
        }

        if (!isBlank(number)) {
            queryString.append(" tingzinumber:").append(number).append("*~0.1");
        }

        if (!isBlank(road)) {
            queryString.append(" tingziroad:").append(road).append("*~0.1");
        }

        if (!isBlank(stop)) {
            queryString.append(" tingzistop:").append(stop).append("*~0.1");
        }

        if (!isBlank(address)) {
            queryString.append(" tingziaddress:").append(address).append("*~0.1");
        }

        if (!isBlank(direction)) {
            queryString.append(" tingzidirection:").append(direction).append("*~0.1");
        }

        if (!isBlank(finalstop)) {
            queryString.append(" tingzifinalstop:").append(finalstop).append("*~0.1");
        }

        if (!isBlank(nextstop)) {
            queryString.append(" tingzinextstop:").append(nextstop).append("*~0.1");
        }

        if (!isBlank(digdate)) {
            String digdatestrInCST = digdate;
            try {
                digdatestrInCST = parser.format(formatter.parse(digdate));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            queryString.append(" tingzidigtime:\"").append(digdatestrInCST).append("\"*~0.1");
        }

        if (!isBlank(finishdate)) {
            String finishdateInCst = finishdate;

            try {
                finishdateInCst = parser.format(formatter.parse(finishdate));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            queryString.append(" tingzifinishdate:\"").append(finishdateInCst).append("\"*~0.1");
        }

        if (!isBlank(comments)) {
            queryString.append(" tingzicomments:").append(comments).append("*~0.1");
        }

        if (queryString.length() == 0) {
            queryString.append("entitytype:TingZi");
        }
        return queryString;
    }

    private StringBuilder constructLiganQuery(HttpServletRequest req) {
        String line = req.getParameter("line");
        String area = req.getParameter("area");
        String number = req.getParameter("number");
        String road = req.getParameter("road");
        String stop = req.getParameter("stop");
        String address = req.getParameter("address");
        String direction = req.getParameter("direction");
        String finalstop = req.getParameter("finalstop");
        String nextstop = req.getParameter("nextstop");
        String digdate = req.getParameter("digdate");
        String finishdate = req.getParameter("finishdate");
        String comments = req.getParameter("comments");

        StringBuilder queryString = new StringBuilder();
        if (!isBlank(line)) {
            queryString.append("liganline:").append(line).append("*~0.1");
        }

        if (!isBlank(area)) {
            queryString.append(" liganarea:").append(area).append("*~0.1");
        }

        if (!isBlank(number)) {
            queryString.append(" ligannumber:").append(number).append("*~0.1");
        }

        if (!isBlank(road)) {
            queryString.append(" liganroad:").append(road).append("*~0.1");
        }

        if (!isBlank(stop)) {
            queryString.append(" liganstop:").append(stop).append("*~0.1");
        }

        if (!isBlank(address)) {
            queryString.append(" liganaddress:").append(address).append("*~0.1");
        }

        if (!isBlank(direction)) {
            queryString.append(" ligandirection:").append(direction).append("*~0.1");
        }

        if (!isBlank(finalstop)) {
            queryString.append(" liganfinalstop:").append(finalstop).append("*~0.1");
        }

        if (!isBlank(nextstop)) {
            queryString.append(" ligannextstop:").append(nextstop).append("*~0.1");
        }

        if (!isBlank(digdate)) {
            String digdatestrInCST = digdate;
            try {
                digdatestrInCST = parser.format(formatter.parse(digdate));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            queryString.append(" ligandigtime:\"").append(digdatestrInCST).append("\"*~0.1");
        }

        if (!isBlank(finishdate)) {
            String finishdateInCst = finishdate;

            try {
                finishdateInCst = parser.format(formatter.parse(finishdate));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            queryString.append(" liganfinishdate:\"").append(finishdateInCst).append("\"*~0.1");
        }

        if (!isBlank(comments)) {
            queryString.append(" ligancomments:").append(comments).append("*~0.1");
        }

        if (queryString.length() == 0) {
            queryString.append("entitytype:LiGan");
        }
        return queryString;
    }

    public static boolean isBlank(String key) {
        return key == null || key.isEmpty();
    }
}
