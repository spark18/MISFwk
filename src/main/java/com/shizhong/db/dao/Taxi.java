package com.shizhong.db.dao;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TAXI")
public class Taxi {
	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "TYPE", length = 45)
	private String type;

	@Column(name = "AREA", length = 45)
	private String area;

	@Column(name = "NUMBER", length = 45)
	private String number;

	@Column(name = "ROAD", length = 45)
	private String road;

	@Column(name = "ADDRESS", length = 200)
	private String addr;

	@Column(name = "DIRECTION", length = 45)
	private String direction;

	@Column(name = "ADCONTENT", length = 45)
	private String adContent;

	@Temporal(TemporalType.DATE)
	@Column(name = "ADENDTIME", nullable = false)
	private Date adEndTime;

	@Temporal(TemporalType.DATE)
	@Column(name = "DIGDATE", nullable = false)
	private Date digdate;

	@Temporal(TemporalType.DATE)
	@Column(name = "FINISHDATE", nullable = false)
	private Date finishdate;

	@Column(name = "IMAGE")
	private Blob image;

	@Column(name = "COMMENTS", length = 1000)
	private String comments;
	
	public String getAdContent() {
		return adContent;
	}

	public String getAddr() {
		return addr;
	}

	public Date getAdEndTime() {
		return adEndTime;
	}

	public String getArea() {
		return area;
	}

	public String getComments() {
		return comments;
	}

	public Date getDigdate() {
		return digdate;
	}

	public String getDirection() {
		return direction;
	}

	public Date getFinishdate() {
		return finishdate;
	}

	public Long getId() {
		return id;
	}

	public Blob getImage() {
		return image;
	}

	public String getNumber() {
		return number;
	}

	public String getRoad() {
		return road;
	}

	public String getType() {
		return type;
	}

	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public void setAdEndTime(Date adEndTime) {
		this.adEndTime = adEndTime;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setDigdate(Date digdate) {
		this.digdate = digdate;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public void setFinishdate(Date finishdate) {
		this.finishdate = finishdate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setRoad(String road) {
		this.road = road;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Taxi [id=" + id + ", type=" + type + ", area=" + area
				+ ", number=" + number + ", road=" + road + ", addr=" + addr
				+ ", direction=" + direction + ", adContent=" + adContent
				+ ", adEndTime=" + adEndTime + ", digdate=" + digdate
				+ ", finishdate=" + finishdate + ", image=" + image
				+ ", comments=" + comments + "]";
	}

}
