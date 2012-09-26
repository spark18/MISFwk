package com.shizhong.solr;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

public class TingZiNanHuiSolrUtil implements SolrQueryConstructor {
    protected static final SimpleDateFormat formatter = new SimpleDateFormat("MMM dd,yyyy", Locale.CHINESE);
    protected static final SimpleDateFormat parser = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy",
            Locale.ENGLISH);
    private static final TingZiNanHuiSolrUtil tingzinhSolrUtil = new TingZiNanHuiSolrUtil();
    private TingZiNanHuiSolrUtil(){}
    public static final TingZiNanHuiSolrUtil getInstance() {
        return tingzinhSolrUtil;
    }

    public String construct(HttpServletRequest req) {
        String line = req.getParameter("line");
        String area = req.getParameter("area");
        String picnumber = req.getParameter("picnumber");
        String entitynum = req.getParameter("entitynum");
        String stopnum = req.getParameter("stopnum");
        String nanhuitingtype = req.getParameter("nanhuitingtype");
        String road = req.getParameter("road");
        String stop = req.getParameter("stop");
        String address = req.getParameter("address");
        String direction = req.getParameter("direction");
        String finalstop = req.getParameter("finalstop");
        String nextstop = req.getParameter("nextstop");
        String digdate = req.getParameter("digdate");
        String finishdate = req.getParameter("finishdate");
        String comments = req.getParameter("comments");
        String adop = req.getParameter("adop");
        String adstart = req.getParameter("adstart");
        String adend = req.getParameter("adend");
        String lastcaredate = req.getParameter("lastcaredate");

        StringBuilder queryString = new StringBuilder();
        if (!isBlank(line)) {
            queryString.append("tingzinhline:").append(line).append("*~0.1");
        }

        if (!isBlank(area)) {
            queryString.append(" tingzinharea:").append(area).append("*~0.1");
        }
        if (!isBlank(adop)) {
            queryString.append(" tingzinhadop:").append(adop).append("*~0.1");
        }
        if (!isBlank(nanhuitingtype)) {
            queryString.append(" tingzinhtype:").append(nanhuitingtype).append("*~0.1");
        }
        if (!isBlank(adstart)) {
            String adstartstrInCST = adstart;
            try {
                adstartstrInCST = parser.format(formatter.parse(adstart));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            queryString.append(" tingzinhadstart:\"").append(adstartstrInCST).append("\"");
        }
        if (!isBlank(adend)) {
            String adendstrInCST = adend;
            try {
                adendstrInCST = parser.format(formatter.parse(adend));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            queryString.append(" tingzinhadend:\"").append(adendstrInCST).append("\"");
        }

        if (!isBlank(lastcaredate)) {
            String lastcaredatestrInCST = lastcaredate;
            try {
                lastcaredatestrInCST = parser.format(formatter.parse(lastcaredate));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            queryString.append(" tingzinhlastcare:\"").append(lastcaredatestrInCST).append("\"");
        }

        if (!isBlank(picnumber)) {
            queryString.append(" tingzinhpicnumber:").append(picnumber).append("*~0.1");
        }
        if (!isBlank(stopnum)) {
            queryString.append(" tingzinhstopnum:").append(picnumber).append("*~0.1");
        }
        if (!isBlank(entitynum)) {
            queryString.append(" tingzinhentitynum:").append(picnumber).append("*~0.1");
        }

        if (!isBlank(road)) {
            queryString.append(" tingzinhroad:").append(road).append("*~0.1");
        }

        if (!isBlank(stop)) {
            queryString.append(" tingzinhstop:").append(stop).append("*~0.1");
        }

        if (!isBlank(address)) {
            queryString.append(" tingzinhaddress:").append(address).append("*~0.1");
        }

        if (!isBlank(direction)) {
            queryString.append(" tingzinhdirection:").append(direction).append("*~0.1");
        }

        if (!isBlank(finalstop)) {
            queryString.append(" tingzinhfinalstop:").append(finalstop).append("*~0.1");
        }

        if (!isBlank(nextstop)) {
            queryString.append(" tingzinhnextstop:").append(nextstop).append("*~0.1");
        }

        if (!isBlank(digdate)) {
            String digdatestrInCST = digdate;
            try {
                digdatestrInCST = parser.format(formatter.parse(digdate));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            queryString.append(" tingzinhdigdate:\"").append(digdatestrInCST).append("\"");
        }

        if (!isBlank(finishdate)) {
            String finishdateInCst = finishdate;

            try {
                finishdateInCst = parser.format(formatter.parse(finishdate));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            queryString.append(" tingzinhfinishdate:\"").append(finishdateInCst).append("\"");
        }

        if (!isBlank(comments)) {
            queryString.append(" tingzinhcomments:").append(comments).append("*~0.1");
        }

        if (queryString.length() == 0) {
            queryString.append("entitytype:TingZiNanHui");
        }
        return queryString.toString();
    }

    public static boolean isBlank(String key) {
        return key == null || key.isEmpty();
    }
}
