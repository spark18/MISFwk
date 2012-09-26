package com.shizhong.db.imgupdater;

import static com.shizhong.web.bizmodel.EntityType.BusTicketAirImgPreview;
import static com.shizhong.web.bizmodel.EntityType.BusTicketAirImgView;
import static com.shizhong.web.bizmodel.EntityType.BusTicketImgPreview;
import static com.shizhong.web.bizmodel.EntityType.BusTicketImgView;

import java.util.HashMap;
import java.util.Map;

import com.shizhong.web.bizmodel.EntityType;

public class ImgUpdaterRepo {
	private final Map<EntityType, Object> updaters = new HashMap<EntityType, Object>();

	private static final ImgUpdaterRepo instance = new ImgUpdaterRepo();
	{
		updaters.put(BusTicketAirImgPreview, new BusTicketAirImgPreviewUpdater());
		updaters.put(BusTicketAirImgView, new BusTicketAirImgUpdater());
		updaters.put(BusTicketImgPreview, new BusTicketImgPreviewUpdater());
		updaters.put(BusTicketImgView, new BusTicketImgUpdater());

	}

	public static final ImgUpdaterRepo getInstance() {
		return instance;
	}

	@SuppressWarnings("unchecked")
	public <T> ImgUpdater<T> getUpdater(EntityType name) {
		return (ImgUpdater<T>) updaters.get(name);
	}

}
