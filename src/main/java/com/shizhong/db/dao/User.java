package com.shizhong.db.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User {
	public static enum Role {
		MANAGER, OPERATOR
	}
	private Long id;

	private String name;

	private String passwd;
	
	private String role;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	@Column(name = "NAME", length = 45)
	public String getName() {
		return name;
	}

	@Column(name = "PASSWD", length = 45)
	public String getPasswd() {
		return passwd;
	}

	public String getRole() {
		return role;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", passwd=" + passwd
				+ ", role=" + role + "]";
	}

}
