package org.ligson.pt.serializable.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id = UUID.randomUUID().toString();
	private String name;
	private boolean sex;
	private String description;
	private int height;
	private Date birth = new Date();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public User(String name, boolean sex, String description, int height, Date birth) {
		super();
		this.name = name;
		this.sex = sex;
		this.description = description;
		this.height = height;
		this.birth = birth;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", sex=" + sex + ", description=" + description + ", height="
				+ height + ", birth=" + birth + "]";
	}

}
