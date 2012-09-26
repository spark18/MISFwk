package com.shizhong.web.meta;

import java.util.HashMap;
import java.util.Map;


@SuppressWarnings("rawtypes")
public class Parameter<T> {
	private Object reqParam;
	private T value;
	private String name;
	private static final Map<Class, Convertor> convertors = new HashMap<Class, Convertor>();
	static {
		convertors.put(Integer.class, new IntegerConvertor());
		convertors.put(Long.class, new LongConvertor());
		convertors.put(String.class, new StringConvertor());
	}

	@SuppressWarnings("unchecked")
    public Parameter(String name, Object reqParam, Class<T> type) {
		super();
		this.name = name;
		this.reqParam = reqParam;
		value = (T) convertors.get(type).convert(reqParam);
	}

	public Object getReqParam() {
		return reqParam;
	}

	public T getValue() {
		return value;
	}

	public void setReqParam(String reqParam) {
		this.reqParam = reqParam;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Parameter [reqParam=" + reqParam + ", value=" + value + "]";
	}

}
