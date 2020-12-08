package ex02.construct;

public class Hotel {
	
	//Hotel클래스는 Chef에게 의존적이다
	//멤버변수
	private Chef chef;
	
	//생성자
	public Hotel(Chef chef) {
		this.chef = chef;
	}
	
	//getter
	public Chef getChef() {
		return chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}
	
	

}
