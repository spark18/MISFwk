package com.shizhong.db.dao;

import java.sql.Blob;

public interface IBusTicketPreview {

	public Double getAirPrice();

	public String getAutoSale();

	public Long getId();

	public Blob getImage();

	public Double getNormPrice();

	public String getOpunit();

	public String getPricetype();

	public String getType();

	public String getLine();
	
	public String getStatus();

	public void seNormtPrice(Double price);

	public void setAirPrice(Double airPrice);

	public void setAutoSale(String autoSale);

	public void setId(Long id);

	public void setImage(Blob image);

	public void setNormPrice(Double normPrice);

	public void setOpunit(String opunit);

	public void setPricetype(String pricetype);

	public void setType(String type);

	public void setLine(String line);
	
	public void setStatus(String status);
}