package ex06.autowired;

import javax.annotation.Resource; //자바 11버전에서는 사용불가

public class Printer2 {

	/*
	 @Resource는 이름으로 찾는다 -> 타입으로 찾음
	 - 세터, 멤버변수에만 적용이 가능합니다.
	 
	 Resource의 강제 연결은 name속성을 이용합니다.
	 
	 */
	
	
	@Resource(name = "doc2") //name속성을 이용하여 doc2로 강제연결
	private Document document;
	

	//기본생성자
	public Printer2() {}
	
	public Printer2(Document document) {
		this.document = document;
	}
	
	//getter setter
	public Document getDocument() {
		return document;
	}
	
	public void setDocument(Document document) {
		this.document = document;
	}
	
	
}
