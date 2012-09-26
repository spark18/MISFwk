package com.shizhong.db.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "MODIFYHISTORY")
public class ModifyHistory {
	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "LIGANID")
	private Long liGanId;

	@Temporal(TemporalType.DATE)
	@Column(name = "MODIFYDATE", nullable = false)
	private Date modifyDate;

	@Column(name = "MODIFYTYPE")
	private Long modifyType;

	@Column(name = "COMMENTS", length = 2000)
	private String comments;
	
	@Column(name = "VERSION", length = 45)
	private String version;

	public String getComments() {
		return comments;
	}

	public Long getId() {
		return id;
	}

	public Long getLiGanId() {
		return liGanId;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public Long getModifyType() {
		return modifyType;
	}

	public String getVersion() {
		return version;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLiGanId(Long liGanId) {
		this.liGanId = liGanId;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public void setModifyType(Long modifyType) {
		this.modifyType = modifyType;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "ModifyHistory [id=" + id + ", liGanId=" + liGanId
				+ ", modifyDate=" + modifyDate + ", modifyType=" + modifyType
				+ ", comments=" + comments + ", version=" + version + "]";
	}

}
