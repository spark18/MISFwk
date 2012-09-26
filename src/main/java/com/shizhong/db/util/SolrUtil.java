package com.shizhong.db.util;

import java.net.MalformedURLException;

import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;

public class SolrUtil {
	private static final String SOLR_URL = "http://localhost:8180/apache-solr-3.5.0/";

	public static CommonsHttpSolrServer solrServer = null;

	static {
		try {
			solrServer = new CommonsHttpSolrServer(SOLR_URL);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
