package com.shizhong.db.dao;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "BUSTICKETAIR")
public class BusTicketAir implements Displayable, IBusTicket {
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

	/* (non-Javadoc)
	 * @see com.shizhong.db.dao.IBusTicketPreview#getAirPrice()
	 */
	@Override
	@Column(name = "AIRPRICE")
	public Double getAirPrice() {
		return airPrice;
	}

	/* (non-Javadoc)
	 * @see com.shizhong.db.dao.IBusTicketPreview#getAutoSale()
	 */
	@Override
	@Column(name = "AUTOSALE", length = 45)
	public String getAutoSale() {
		return autoSale;
	}

	/* (non-Javadoc)
	 * @see com.shizhong.db.dao.IBusTicketPreview#getId()
	 */
	@Override
	@Id
	@GeneratedValue
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see com.shizhong.db.dao.IBusTicketPreview#getImage()
	 */
	@Override
	@Column(name = "IMAGE")
	@Lob
	public Blob getImage() {
		return image;
	}

	@Column(name = "LINE")
	public String getLine() {
		return line;
	}

	/* (non-Javadoc)
	 * @see com.shizhong.db.dao.IBusTicketPreview#getNormPrice()
	 */
	@Override
	@Column(name = "NORMPRICE")
	public Double getNormPrice() {
		return normPrice;
	}

	/* (non-Javadoc)
	 * @see com.shizhong.db.dao.IBusTicketPreview#getOpunit()
	 */
	@Override
	@Column(name = "OPUNIT", length = 45)
	public String getOpunit() {
		return opunit;
	}

	/* (non-Javadoc)
	 * @see com.shizhong.db.dao.IBusTicketPreview#getPricetype()
	 */
	@Override
	@Column(name = "pricetype", length = 45)
	public String getPricetype() {
		return pricetype;
	}

	public String getStatus() {
		return status;
	}

	/* (non-Javadoc)
	 * @see com.shizhong.db.dao.IBusTicketPreview#getType()
	 */
	@Override
	@Column(name = "TYPE", length = 45)
	public String getType() {
		return type;
	}

	/* (non-Javadoc)
	 * @see com.shizhong.db.dao.IBusTicketPreview#seNormtPrice(java.lang.Double)
	 */
	@Override
	public void seNormtPrice(Double price) {
		this.normPrice = price;
	}

	/* (non-Javadoc)
	 * @see com.shizhong.db.dao.IBusTicketPreview#setAirPrice(java.lang.Double)
	 */
	@Override
	public void setAirPrice(Double airPrice) {
		this.airPrice = airPrice;
	}

	/* (non-Javadoc)
	 * @see com.shizhong.db.dao.IBusTicketPreview#setAutoSale(java.lang.String)
	 */
	@Override
	public void setAutoSale(String autoSale) {
		this.autoSale = autoSale;
	}

	/* (non-Javadoc)
	 * @see com.shizhong.db.dao.IBusTicketPreview#setId(java.lang.Long)
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see com.shizhong.db.dao.IBusTicketPreview#setImage(java.sql.Blob)
	 */
	@Override
	public void setImage(Blob image) {
		this.image = image;
	}

	public void setLine(String line) {
		this.line = line;
	}

	/* (non-Javadoc)
	 * @see com.shizhong.db.dao.IBusTicketPreview#setNormPrice(java.lang.Double)
	 */
	@Override
	public void setNormPrice(Double normPrice) {
		this.normPrice = normPrice;
	}

	/* (non-Javadoc)
	 * @see com.shizhong.db.dao.IBusTicketPreview#setOpunit(java.lang.String)
	 */
	@Override
	public void setOpunit(String opunit) {
		this.opunit = opunit;
	}

	/* (non-Javadoc)
	 * @see com.shizhong.db.dao.IBusTicketPreview#setPricetype(java.lang.String)
	 */
	@Override
	public void setPricetype(String pricetype) {
		this.pricetype = pricetype;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/* (non-Javadoc)
	 * @see com.shizhong.db.dao.IBusTicketPreview#setType(java.lang.String)
	 */
	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "BusTicketAir [id=" + id + ", type=" + type + ", image=" + image
				+ ", pricetype=" + pricetype + ", normPrice=" + normPrice
				+ ", airPrice=" + airPrice + ", opunit=" + opunit
				+ ", autoSale=" + autoSale + ", line=" + line + ", status="
				+ status + "]";
	}
}
