package com.simple.command;

import java.util.ArrayList;

public class ReqVO {
	
	//폼의 name과 일치하는 변수를 멤버변수로 선언
	private String id;
	private String pw;
	private String name;
	private String age;
	private ArrayList<String> inter; //다중값은 list로 처리합니다.
	
	//생성자
	public ReqVO() {
	}
	
	public ReqVO(String id, String pw, String name, String age, ArrayList<String> inter) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.age = age;
		this.inter = inter;
	}
	
	//getter setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public ArrayList<String> getInter() {
		return inter;
	}

	public void setInter(ArrayList<String> inter) {
		this.inter = inter;
	}

	
	
	
	
	

}
