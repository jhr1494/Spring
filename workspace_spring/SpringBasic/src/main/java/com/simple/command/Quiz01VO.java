package com.simple.command;

public class Quiz01VO {
	
	private String year;
	private String month;
	private String day;
	
	//생성자
	public Quiz01VO() {
		super();
	}
	public Quiz01VO(String year, String month, String day) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	
	//getter setter
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	
	
	
	

}
