package com.example.masteryourself.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class ActivityModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long actid;
	
	private String activity;
	private double hour;
	private String date;
	private String comment;
	
	@ManyToOne
	@JoinColumn(name = "categoryid")
	private Category category;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "id")
	private User user;
	
	public ActivityModel() {}
	
	public ActivityModel(String activity, double hour, String date, String comment, Category category, User user) {
		super();
		
		this.activity = activity;
		this.hour = hour;
		this.date = date;
		this.comment = comment;
		this.category = category;
		this.user = user; 
	}
	
	
	
	

	public Long getActid() {
		return actid;
	}

	public void setActid(Long actid) {
		this.actid = actid;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public double getHour() {
		return hour;
	}

	public void setHour(double hour) {
		this.hour = hour;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Activity [actid=" + actid + ", activity=" + activity + ", hour=" + hour + ", date=" + date + ", comment="
				+ comment + ", user= " + user + "]";
	}

	
	
	
}
