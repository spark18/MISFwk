package com.shizhong.db.updater;

import java.util.Map;

import com.shizhong.web.meta.Parameter;

public interface Updater<T> {
	@SuppressWarnings("rawtypes")
	T update(Map<String, Parameter> parameters);
}
