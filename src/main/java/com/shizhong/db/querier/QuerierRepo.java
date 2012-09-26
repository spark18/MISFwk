package com.shizhong.db.querier;

import static com.shizhong.web.bizmodel.EntityType.*;

import java.util.HashMap;
import java.util.Map;

import com.shizhong.web.bizmodel.EntityType;

public class QuerierRepo {
	private final Map<EntityType, Object> queriers = new HashMap<EntityType, Object>();

	private static final QuerierRepo instance = new QuerierRepo();
	{
		queriers.put(LiGan, new LiGanQuerier());
		queriers.put(UpLineView, new UpLineQuerier());
		queriers.put(DownLineView, new DownLineQuerier());
		queriers.put(LiGanPreview, new LiGanPreviewQuerier());
		queriers.put(SZTransaction, new SZTransactionQuerier());
		queriers.put(BusTicketPreview, new BusTicketPreviewQuerier());
		queriers.put(BusTicketImgPreview, new BusTicketPreviewQuerier());
		queriers.put(BusTicketView, new BusTicketViewQuerier());
		queriers.put(BusTicketImgView, new BusTicketViewQuerier());
		queriers.put(BusTicketAirPreview, new BusTicketAirPreviewQuerier());
		queriers.put(BusTicketAirImgPreview, new BusTicketAirPreviewQuerier());
		queriers.put(BusTicketAirView, new BusTicketAirViewQuerier());
		queriers.put(BusTicketAirImgView, new BusTicketAirViewQuerier());
		queriers.put(TingZiPreview, new TingZiPreviewQuerier());
		queriers.put(TingZi, new TingZiQuerier());
		queriers.put(TingZiNanHuiPreview, new TingZiNanHuiPreviewQuerier());
		queriers.put(TingZiNanHui, new TingZiNanHuiQuerier());
		queriers.put(YangZhaoDianPreview, new YangZhaoDianPreviewQuerier());
		queriers.put(YangZhaoDian, new YangZhaoDianQuerier());
	}

	public static final QuerierRepo getInstance() {
		return instance;
	}

	@SuppressWarnings("unchecked")
	public <T> Querier<T> getQuerier(EntityType name) {
		return (Querier<T>) queriers.get(name);
	}

}
