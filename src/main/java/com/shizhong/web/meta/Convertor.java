package com.shizhong.web.meta;

interface Convertor<T> {
	T convert(Object reqParam);
}
