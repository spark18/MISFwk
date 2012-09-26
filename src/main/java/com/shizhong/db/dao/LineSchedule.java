package com.shizhong.db.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "LINESCHEDULE")
public class LineSchedule {
	@Id
	@Column(name = "ID")
	private Long id;

	@Temporal(TemporalType.TIME)
	@Column(name = "TIME", nullable = false)
	private Date time;

	@Column(name = "LINEID")
	private Long lineId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Long getLineId() {
		return lineId;
	}

	public void setLineId(Long lineId) {
		this.lineId = lineId;
	}

	@Override
	public String toString() {
		return "LineSchedule [id=" + id + ", time=" + time + ", lineId="
				+ lineId + "]";
	}


}
