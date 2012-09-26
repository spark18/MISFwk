package com.shizhong.web.meta;

class IntegerConvertor implements Convertor<Integer> {

	@Override
	public Integer convert(Object reqParam) {
		return Integer.parseInt(((Object[])reqParam)[0].toString());
	}

}
