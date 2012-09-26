package com.shizhong.solr;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

public class YangZhaoDianSolrUtil implements SolrQueryConstructor {
    protected static final SimpleDateFormat formatter = new SimpleDateFormat("MMM dd,yyyy", Locale.CHINESE);
    protected static final SimpleDateFormat parser = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy",
            Locale.ENGLISH);
    private static final YangZhaoDianSolrUtil yangzhaodianSolrUtil = new YangZhaoDianSolrUtil();
    private YangZhaoDianSolrUtil(){}
    public static final YangZhaoDianSolrUtil getInstance() {
        return yangzhaodianSolrUtil;
    }

    public String construct(HttpServletRequest req) {
        String area = req.getParameter("area");
        String picnumber = req.getParameter("picnumber");
        String entitynum = req.getParameter("entitynum");
        String stopnum = req.getParameter("stopnum");
        String yangzhaodiantype = req.getParameter("yangzhaodiantype");
        String road = req.getParameter("road");
        String stop = req.getParameter("stop");
        String address = req.getParameter("address");
        String direction = req.getParameter("direction");
        String comments = req.getParameter("comments");
        String adop = req.getParameter("adop");
        String adstart = req.getParameter("adstart");
        String adend = req.getParameter("adend");
        String lastcaredate = req.getParameter("lastcaredate");

        StringBuilder queryString = new StringBuilder();

        if (!isBlank(area)) {
            queryString.append(" yangzhaodianarea:").append(area).append("*~0.1");
        }
        if (!isBlank(adop)) {
            queryString.append(" yangzhaodianadop:").append(adop).append("*~0.1");
        }
        if (!isBlank(yangzhaodiantype)) {
            queryString.append(" yangzhaodiantype:").append(yangzhaodiantype).append("*~0.1");
        }
        if (!isBlank(adstart)) {
            String adstartstrInCST = adstart;
            try {
                adstartstrInCST = parser.format(formatter.parse(adstart));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            queryString.append(" yangzhaodianadstart:\"").append(adstartstrInCST).append("\"");
        }
        if (!isBlank(adend)) {
            String adendstrInCST = adend;
            try {
                adendstrInCST = parser.format(formatter.parse(adend));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            queryString.append(" yangzhaodianadend:\"").append(adendstrInCST).append("\"");
        }

        if (!isBlank(lastcaredate)) {
            String lastcaredatestrInCST = lastcaredate;
            try {
                lastcaredatestrInCST = parser.format(formatter.parse(lastcaredate));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            queryString.append(" yangzhaodianlastcare:\"").append(lastcaredatestrInCST).append("\"");
        }

        if (!isBlank(picnumber)) {
            queryString.append(" yangzhaodianpicnumber:").append(picnumber).append("*~0.1");
        }
        if (!isBlank(stopnum)) {
            queryString.append(" yangzhaodianstopnum:").append(stopnum).append("*~0.1");
        }
        if (!isBlank(entitynum)) {
            queryString.append(" yangzhaodianentitynum:").append(entitynum).append("*~0.1");
        }

        if (!isBlank(road)) {
            queryString.append(" yangzhaodianroad:").append(road).append("*~0.1");
        }

        if (!isBlank(stop)) {
            queryString.append(" yangzhaodianstop:").append(stop).append("*~0.1");
        }

        if (!isBlank(address)) {
            queryString.append(" yangzhaodianaddress:").append(address).append("*~0.1");
        }

        if (!isBlank(direction)) {
            queryString.append(" yangzhaodiandirection:").append(direction).append("*~0.1");
        }

        if (!isBlank(comments)) {
            queryString.append(" yangzhaodiancomments:").append(comments).append("*~0.1");
        }

        if (queryString.length() == 0) {
            queryString.append("entitytype:YangZhaoDian");
        }
        return queryString.toString();
    }

    public static boolean isBlank(String key) {
        return key == null || key.isEmpty();
    }
}
