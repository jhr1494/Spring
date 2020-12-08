package ex07.quiz;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Computer {
	
	//키보드, 마우스, 모니터에 대한 멤버변수를 만들고 resource, autowired를 사용해서 자동주입
	//main에서 computer의 info기능을 확인
	
	@Autowired
	@Qualifier("moniter")
	private Moniter moniter;
	
	@Autowired
	private Mouse mouse;
	
	@Resource(name = "keyboard")
	private Keyboard keyboard;
	
	
	//생성자
	public Computer() {
		super();
	}


	public Computer(Moniter moniter, Mouse mouse, Keyboard keyboard) {
		super();
		this.moniter = moniter;
		this.mouse = mouse;
		this.keyboard = keyboard;
	}
	
	
	//getter setter
	public Moniter getMoniter() {
		return moniter;
	}


	public void setMoniter(Moniter moniter) {
		this.moniter = moniter;
	}


	public Mouse getMouse() {
		return mouse;
	}


	public void setMouse(Mouse mouse) {
		this.mouse = mouse;
	}


	public Keyboard getKeyboard() {
		return keyboard;
	}


	public void setKeyboard(Keyboard keyboard) {
		this.keyboard = keyboard;
	}


	public void info() {
		moniter.info();
		mouse.info();
		keyboard.info();
	}

}
