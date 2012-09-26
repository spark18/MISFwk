package com.shizhong.db.cmd;

import java.util.HashMap;
import java.util.Map;

public class InfoConvertorRepo {
	private static final InfoConvertorRepo repo = new InfoConvertorRepo();
	
	private Map<String, InfoConvertor> infoConvertors = new HashMap<String, InfoConvertor>();
	{
		infoConvertors.put("ligan", new LiGanConvertor());
		infoConvertors.put("tingzi", new TingZiConvertor());
		infoConvertors.put("yangzhaodian", new YangZhaoDianConvertor());
		infoConvertors.put("tingzinh", new TingZiNanHuiConvertor());
		infoConvertors.put("line", new LineConvertor());
		infoConvertors.put("downline", new DownLineConvertor());
	}
	
	public static InfoConvertorRepo getInstance() {
		return repo;
	}
	
	public InfoConvertor findConvertor(String type) {
		return infoConvertors.get(type);
	}
}
