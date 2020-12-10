package com.simple.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.simple.command.ReqVO;

@Controller //컴포넌트 스캔이 읽어서 빈으로 생성
@RequestMapping("/request")
public class RequestController {

	//req_ex01화면 처리
	//void형 메서드는 들어온 요청경로 그랟로 뷰리졸버로 값이 반환됩니다
//	@RequestMapping("/request/req_ex01")
//	public void req_ex01() {
//		System.out.println("실행됨");
//	}
	
	@RequestMapping("/req_ex01") //get, post 방식 다 받음
	public String req_ex01() {
		
		return "request/req_ex01";
	}
	
	//basic1요청처리(get방식만 허용)
	@RequestMapping(value="/basic1", method=RequestMethod.GET)
	public void basic1() {
		System.out.println("basic1 요청");
	}
	
	//basic2요청처리(post방식만 허용)
	@RequestMapping(value="/basic2", method=RequestMethod.POST)
	public void basic2() {
		System.out.println("basic2 요청");
	}
	
	//basic3요청처리(get방식만 허용) --- 스프링 4버전부터 사용가능 !!
	@GetMapping("/basic3")
	public void basic3() {
		System.out.println("basic3 요청");
	}
	
	//basic3요청처리(get방식만 허용) --- 스프링 4버전부터 사용가능 !!
	@PostMapping("/basic4")
	public void basic4() {
		System.out.println("basic4 요청");
	}
	
	//=======================요청파라미터 처리하기=======================
	
	//req_ex02화면처리
	@RequestMapping("/req_ex02")
	public String req_ex02() {
		return "request/req_ex02";
	}
	
	//1st - 원초적 방법
//	@RequestMapping("/param")
//	public String param(HttpServletRequest request) {
//		System.out.println(request.getParameter("id"));
//		System.out.println(request.getParameter("pw"));
//		
//		return "request/req_ex02_result";
//	}
	
	//2nd - 어노테이션 방법
	//@RequestParam(value=＂파라미터값", required = false, defaultValue = “기본값") 
	//@RequestParam에 옵션이 없는 경우 값이 반드시 지정되어야 한다 --- 400번 에러발생 !
//	@RequestMapping("/param")
//	public String param(@RequestParam("id") String id,
//						@RequestParam("pw") String pw,
//						@RequestParam("name") String name,
//						@RequestParam("age") int age,
//						@RequestParam(value="inter", required = false, defaultValue="선택없음") ArrayList<String> list //다중값의 경우 리스트형식으로 받는다
//						) {
//		
//		System.out.println(id);
//		System.out.println(pw);
//		System.out.println(name);
//		System.out.println(age);
//		System.out.println(list.toString());
//		
//		//필수가 아닌 파라미터 일 경우에는 required = false옵션을 지정할 수 있습니다.
//		//필수가 아닌 파라미터 값일 경우에는 null을 지정하게 되는데 기본값의 설정으로 defaultValue옵션을 쓰면 됩니다.
//		return "request/req_ex02_result";
//	}
	
	
	//3nd - 커맨드객체를 사용하는 방법
	@RequestMapping("/param")
	public String param(ReqVO vo) {
		
		System.out.println(vo.getId());
		System.out.println(vo.getPw());
		System.out.println(vo.getName());
		System.out.println(vo.getAge());
		System.out.println(vo.getInter().toString()); //null값인 경우 에러가 발생되기 때문에 JS로 제어를 해주어야함
		
		return "request/req_ex02_result";
	}
	
	//quiz01
//	//화면요청처리
//	@RequestMapping("/req_quiz01")
//	public String req_quiz01() {
//		return "request/req_quiz01";
//	}
//	
//	//파라미터 전송
//	@RequestMapping(value="/login", method=RequestMethod.POST)
//	public String login(@RequestParam("id") String id,
//						@RequestParam("pw") String pw) {
//		
//		if(id.equals("abc123") && pw.equals("xxx123")) {
//			return "request/req_quiz01_ok";
//		}else {
//			return "request/req_quiz01_no";
//		}
//		
//	}
	
	//quiz01_ans
	//화면요청처리
	@RequestMapping("/req_quiz01")
	public String req_quiz01() {
		return "request/req_quiz01";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(ReqVO vo) { //req객체로 받음
		
		if(vo.getId().equals("abc123") && vo.getPw().equals("xxx123")) {
			return "request/req_quiz01_ok"; //성공
		}else {
			return "request/req_quiz01_no";	//실패		
		}
	}
	
	

	
	
	
	
	
}
