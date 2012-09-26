package com.shizhong.db.imgupdater;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shizhong.web.meta.Parameter;

public interface ImgUpdater<T> {
	@SuppressWarnings("rawtypes")
	boolean update(Map<String, Parameter> parameters, HttpServletRequest req);
}
