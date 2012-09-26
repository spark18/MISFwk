package com.shizhong.web.meta;

public class StringConvertor implements Convertor<String> {

	@Override
	public String convert(Object reqParam) {
		if (null == ((Object[]) reqParam) || ((Object[]) reqParam).length == 0) {
			return "";
		}
		return ((Object[]) reqParam)[0].toString();
	}

}
