package com.shizhong.solr;

import javax.servlet.http.HttpServletRequest;

public interface SolrQueryConstructor {
    String construct(HttpServletRequest req);
}
