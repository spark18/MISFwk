package com.shizhong.db.dao;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PRETRANSACTION")
public class PreTransaction {
	public static enum Type {
		LIGANPREVIEWLOAD, LIGANPREVIEWAPPROVE, LIGANMODIFY, 
		TINGZIPREVIEWLOAD, TINGZIPREVIEWAPPROVE, TINGZIMODIFY, 
		TINGZINANHUIPREVIEWLOAD, TINGZINANHUIPREVIEWAPPROVE, TINGZINANHUIMODIFY, 
		UPLINEPREVIEWLOAD, UPLINEPREVIEWAPPROVE, UPLINEMODIFY, 
		DOWNLINEPREVIEWLOAD, DOWNLINEPREVIEWAPPROVE, DOWNLINEMODIFY,
		YANGZHAODIANPREVIEWLOAD, YANGZHAODIANPREVIEWAPPROVE, YANGZHAODIANMODIFY,
	}

	private Long id;

	private Date timeStamp;

	private User user;

	private User approver;

	private String type;

	private String comments;

	@ManyToOne(cascade = CascadeType.ALL)
	public User getApprover() {
		return approver;
	}

	@Column(name = "COMMENTS", length = 2000)
	public String getComments() {
		return comments;
	}

	@Id
	@GeneratedValue
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	@Column(name = "TYPE", length = 45)
	public String getType() {
		return type;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	public User getUser() {
		return user;
	}

	public void setApprover(User approver) {
		this.approver = approver;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TIMESTAMP", nullable = false)
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "PreTransaction [id=" + id + ", timeStamp=" + timeStamp
				+ ", user=" + user + ", approver=" + approver + ", type="
				+ type + ", comments=" + comments + "]";
	}

}
