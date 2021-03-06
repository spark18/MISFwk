package com.shizhong.db.dao;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "LIGANPREVIEW")
public class LiGanPreview  implements Displayable {
	
	private Long id;

	private String type;

	private String area;

	private String number;

	private String road;

	private String stop;

	private String addr;

	private String direction;

	private String line;

	private Date digdate;

	private String finalstop;

	private String nextstop;

	private Date finishdate;

	private Blob image;

	private String comments;
	
	private String status;
	
	private PreTransaction transaction;

	@Column(name = "ADDRESS", length = 200)
	public String getAddr() {
		return addr;
	}

	@Column(name = "AREA", length = 45)
	public String getArea() {
		return area;
	}

	@Column(name = "COMMENTS", length = 1000)
	public String getComments() {
		return comments;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DIGDATE", nullable = false)
	public Date getDigdate() {
		return digdate;
	}

	@Column(name = "DIRECTION", length = 45)
	public String getDirection() {
		return direction;
	}

	@Column(name = "FINALSTOP", length = 45)
	public String getFinalstop() {
		return finalstop;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FINISHDATE", nullable = false)
	public Date getFinishdate() {
		return finishdate;
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue
	public Long getId() {
		return id;
	}

	@Column(name = "IMAGE")
	@Lob
	public Blob getImage() {
		return image;
	}

	@Column(name = "LINE", length = 45)
	public String getLine() {
		return line;
	}

	@Column(name = "NEXTSTOP", length = 45)
	public String getNextstop() {
		return nextstop;
	}
	
	@Column(name = "NUMBER", length = 45)
	public String getNumber() {
		return number;
	}

	@Column(name = "ROAD", length = 45)
	public String getRoad() {
		return road;
	}

	public String getStatus() {
		return status;
	}

	@Column(name = "STOP", length = 45)
	public String getStop() {
		return stop;
	}

	@ManyToOne(cascade = CascadeType.ALL, targetEntity=PreTransaction.class)
	public PreTransaction getTransaction() {
		return transaction;
	}

	@Column(name = "TYPE", length = 45)
	public String getType() {
		return type;
	}
	
	public void setAddr(String addr) {
		this.addr = addr;
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

	public void setFinalstop(String finalstop) {
		this.finalstop = finalstop;
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

	public void setLine(String line) {
		this.line = line;
	}

	public void setNextstop(String nextstop) {
		this.nextstop = nextstop;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setRoad(String road) {
		this.road = road;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setStop(String stop) {
		this.stop = stop;
	}

	public void setTransaction(PreTransaction transaction) {
		this.transaction = transaction;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "LiGanPreview [id=" + id + ", type=" + type + ", area=" + area
				+ ", number=" + number + ", road=" + road + ", stop=" + stop
				+ ", addr=" + addr + ", direction=" + direction + ", line="
				+ line + ", digdate=" + digdate + ", finalstop=" + finalstop
				+ ", nextstop=" + nextstop + ", finishdate=" + finishdate
				+ ", image=" + image + ", comments=" + comments + ", status="
				+ status + ", transaction=" + transaction + "]";
	}

}
