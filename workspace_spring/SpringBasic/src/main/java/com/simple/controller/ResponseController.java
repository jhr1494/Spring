package com.simple.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simple.command.ReqVO;

@Controller
@RequestMapping("/response")
public class ResponseController {
	
	//화면처리
	@RequestMapping("/res_ex01")
	public String res_ex01() {
		
		return "response/res_ex01";
	}
	
	//model전달자 - 메서드 매개변수로 Model or ModelMap선언 - addAttribute(키(String), 값(Object))
	@RequestMapping("/res_ex02")
	public String res_ex02(Model model) {
		
		model.addAttribute("serverTime", new Date()); //serverTime이름으로 날짜를 지정
		model.addAttribute("name", "홍길동");
		model.addAttribute("addr", "서울시");
		
		return "response/res_ex02";
	}
	
	//ModelAndView객체 - 데이터와 뷰를 함께 전송
	//Model에 대한 정보와, 화면에 대한 정보를 한번에 저장
	@RequestMapping("/res_ex03")
	public ModelAndView res_ex03() {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("serverTime", new Date()); //데이터 저장
		mv.addObject("name", "이순신");
		
		mv.setViewName("response/res_ex03"); //화면정보 저장
		
		return mv;
	}
	
	//@ModelAttribute() - request, model 합쳐진 형태
	//화면에서 전달받은 값을 다음 화면으로 그대로 전달 - VO객체도 가능
	@RequestMapping("/res_ex04")
	public String res_ex04(@ModelAttribute("num") String num, //num이라는 키값을 불러와서 String num에 대입
						   @ModelAttribute("name") String name) {
		
		System.out.println("넘어온 값 : " + num);
		System.out.println("넘어온 값 : " + name);
		
		return "response/res_ex04";
	}
	
	//@ModelAttribute() 객체로 전달할 경우
	@RequestMapping("/res_ex05")
	public String res_ex05(@ModelAttribute("info") ReqVO vo) { //vo객체를 info라는 이름으로 전달
		
		System.out.println(vo.getId());
		System.out.println(vo.getName());
		
		return "response/res_ex05";
	}
	
	//================리다이렉트와 리다이렉트 어트리뷰트================
	
	//화면처리
	@RequestMapping("/res_redirect")
	public String res_redirect() {
		return "response/res_redirect";
	}
	
	/*
	  ★★일회성 데이터전송 !
	 RedirectAttributes
	 redirect:/ 옵션으로 사용시 일회성으로 데이터를 보내기 위해 
	 addFlashAttribute(키, 값)을 사용하여 보낸다.
	 */
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(@RequestParam("id") String id,
						@RequestParam("pw") String pw,
						RedirectAttributes RA) {
		if(id.equals(pw)) { //아이디 == 패스워드 --- 로그인성공(홈화면으로)
			return "response/res_redirect_ok";
			
		} else {
			//"redirect:/절대경로" - 다시 컨트롤러를 타게함.
			//이때 화면에 1회성 데이터로 값을 전달하고 싶으면
			//RedirectAttributes객체를 사용하면 됩니다.(리다이렉트시에 모델을 전달할 수 있음)
			
			//매개변수에 Model객체로 넣는다면 ???
			//(잘못된방식!)값이 반영되지 않고 URL에 get방식으로 표출됨!
//			model.addAttribute("msg", "아이디와 비밀번호를 확인하세요"); 
			
			RA.addFlashAttribute("msg", "아이디와 비밀번호를 확인하세요");
			
			return "redirect:/response/res_redirect"; //다시 새롭게 요청 ---/res_redirect로 이동
		}
		
	}
	
	//-------------------------------------------------
	
	//quiz01
	//화면처리
	@RequestMapping("/res_quiz01")
	public String res_quiz01() {
		return "response/res_quiz01";
	}
	
	//데이터 처리
	@RequestMapping(value = "res_login", method = RequestMethod.POST)
	public String res_login(@ModelAttribute("id") String id,
							@ModelAttribute("pw") String pw) {
		
		if(id.equals("kim12")&&pw.equals("1234")) {
			return "response/res_quiz02"; //성공페이지
		}else {
			
			return "response/res_quiz03"; //실패페이지
		}
	}
	
	
	
	
	
}//end class
