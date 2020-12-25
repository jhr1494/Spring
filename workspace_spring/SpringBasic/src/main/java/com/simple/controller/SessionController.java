package com.simple.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/session")
public class SessionController {
	
	//화면처리
	@RequestMapping("/login")
	public String login() {
		return "session/login";
	}
	
	@RequestMapping("/join")
	public String join() {
		return "session/join";
	}
	
	@RequestMapping("/main")
	public String main() {
		return "session/main";
	}
	
	@RequestMapping("/mypage")
	public String mypage(HttpSession session) {
		
		//인터셉터를 이용한 세션처리
		/*
		 Filter : DispatcherServlet 실행 전
		 Interceptor : Controller 실행 전
		  - HandelerInterceptorAdapter 를 상속받은 클래스 사용 (ex LoginInterceptor.java)
		 */
		//마이페이지에서 세션정보가 없으면 Redirect를 이용해서 로그인페이지로 
		//이렇게 처리할 페이지가 20개라면 ???
//		if(session.getAttribute("user_id") == null) {
//			return "redirect:/session/login";
//		}
		
		return "session/mypage";
	}
	
	//form처리
	@RequestMapping(value = "joinForm", method = RequestMethod.POST )
	public String joinForm(@RequestParam("id") String id,
						   @RequestParam("pw") String pw,
						   @RequestParam("name") String name) {
		
		//가입처리 진행...
		
		return "redirect:/session/main";//컨트롤러를 태워서 보냄
	}
	
	@RequestMapping(value = "loginForm", method = RequestMethod.POST)
	public String loginForm(@RequestParam("id") String id,
							@RequestParam("pw") String pw,
							HttpSession session,
							RedirectAttributes RA) {
		/*
		 HttpSession session
		 스프링에서는 매개변수에 HttpSession session을 추가하면
		 return(redirect(필수))시에 session을 포함하여 전달된다 
		 */
		//아이디가 admin, 비밀번호가 1234라면 로그인 성공이라고 가정
		if(id.equals("admin") && pw.equals("1234")) {
			
			//세션에 VO나 id값을 저장
			session.setAttribute("user_id", "admin");
			session.setAttribute("user_name", "홍길동");
			
			return "redirect:/session/mypage";
		}else { //로그인 실패
			return "redirect:/session/login";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); //세션정보삭제
		
		return "redirect:/"; //홈화면
	}
}
