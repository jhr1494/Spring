package com.simple.controller;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.simple.command.ScoreVO;
import com.simple.service.ScoreService;
import com.simple.service.ScoreServiceImpl;

@Controller
@RequestMapping("/service")
public class ScoreController {
	//1st - new연산자를 이용한 객체생성
	//전역변수 처리
	//부모형 ScoreService인터페이스에 ScoreServiceImpl저장
//	private ScoreService service =  new ScoreServiceImpl(); 
	
	//2nd
	//스프링컨테이너에 직접 bean으로 생성하고 자동주입명령 어노테이션 사용
	//servlet-context.xml에 bean생성
//	@Autowired //타입으로 찾고 이름으로 찾고
//	@Resource(name = "service") //이름으로 찾고 타입으로 찾고 
//	private ScoreService service;
	
	//3nd
	//@Service 어노테이션의 사용 후 자동주입 어노테이션 사용
	//servlet-context.xml에 bean생성 --- context:component-scan에 서비스패키지의 추가
	
	//인터페이스를 상속받은 클래스가 2개이상일경우 에러발생한텐데....????
	//@Autowired --- @Service("이름")과 @Qualifier("이름")를 사용하여 에러를 없앰
	//@Service("이름")과 @Qualifier("이름")은 항상 일치해야 합니다 !
	//@Resource(name = "이름") --- @Service("이름")과 일치해야 합니다 !
//	@Autowired
//	@Qualifier("xx")
	@Resource(name = "xx")
	private ScoreService service;

	//점수입력 화면처리
	@RequestMapping("/scoreRegist")
	public String score() {
		return "service/scoreRegist";
	}
	
	//점수입력 폼처리
	@RequestMapping(value = "/scoreForm", method = RequestMethod.POST)
	public String scoreForm(ScoreVO vo) {
		
		service.scoreRegist(vo);
		
		return "service/scoreResult";//결과화면
	}
	
	//점수목록 요청
	@RequestMapping("/scoreList")
	public String scoreList(Model model) {
		
		ArrayList<ScoreVO> list = service.getList();
		model.addAttribute("list", list); //모델에 list 저장
		
		return "service/scoreList"; //점수목록 화면
	}
}
