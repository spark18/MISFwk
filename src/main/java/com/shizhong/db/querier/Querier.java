package com.shizhong.db.querier;

import java.util.Map;

import com.shizhong.web.meta.Parameter;

public interface Querier<T> {
	@SuppressWarnings("rawtypes")
	T retrieve(Map<String, Parameter> restrictions);
}
