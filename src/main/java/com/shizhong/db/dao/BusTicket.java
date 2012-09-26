package com.shizhong.db.dao;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "BUSTICKET")
public class BusTicket implements Displayable, IBusTicket {
	private Long id;

	private String type;

	private Blob image;

	private String pricetype;

	private Double normPrice;

	private Double airPrice;

	private String opunit;

	private String autoSale;

	private String line;
	
	private String status;

	@Column(name = "AIRPRICE")
	public Double getAirPrice() {
		return airPrice;
	}

	@Column(name = "AUTOSALE", length = 45)
	public String getAutoSale() {
		return autoSale;
	}

	@Id
	@GeneratedValue
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	@Column(name = "IMAGE")
	@Lob
	public Blob getImage() {
		return image;
	}

	@Column(name = "LINE")
	public String getLine() {
		return line;
	}

	@Column(name = "NORMPRICE")
	public Double getNormPrice() {
		return normPrice;
	}

	@Column(name = "OPUNIT", length = 45)
	public String getOpunit() {
		return opunit;
	}

	@Column(name = "pricetype", length = 45)
	public String getPricetype() {
		return pricetype;
	}

	public String getStatus() {
		return status;
	}

	@Override
	@Column(name = "TYPE", length = 45)
	public String getType() {
		return type;
	}

	public void seNormtPrice(Double price) {
		this.normPrice = price;
	}

	public void setAirPrice(Double airPrice) {
		this.airPrice = airPrice;
	}

	public void setAutoSale(String autoSale) {
		this.autoSale = autoSale;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public void setNormPrice(Double normPrice) {
		this.normPrice = normPrice;
	}

	public void setOpunit(String opunit) {
		this.opunit = opunit;
	}

	public void setPricetype(String pricetype) {
		this.pricetype = pricetype;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "BusTicket [id=" + id + ", type=" + type + ", image=" + image
				+ ", pricetype=" + pricetype + ", normPrice=" + normPrice
				+ ", airPrice=" + airPrice + ", opunit=" + opunit
				+ ", autoSale=" + autoSale + ", line=" + line + ", status="
				+ status + "]";
	}
}
