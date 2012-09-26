package com.shizhong.web.meta;

class LongConvertor implements Convertor<Long> {

	@Override
	public Long convert(Object reqParam) {
		return Long.parseLong(((Object[])reqParam)[0].toString());
	}

}
