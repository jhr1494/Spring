package com.team404.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team404.command.UserVO;
import com.team404.user.service.UserService;
@Controller
@RequestMapping("/user")
public class UserController {
	//주소API승인키(https://www.juso.go.kr/addrlink/main.do?cPath=99MM)
	//devU01TX0FVVEgyMDIwMTIyMzE1MzUyMTExMDU4Njg=
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@RequestMapping("/userJoin")
	public String userJoin() {
		return "user/userJoin";
	}
	
	@RequestMapping("userLogin")
	public String userLogin() {
		return "user/userLogin";
	}
	
	@RequestMapping("userMypage")
	public String userMypage() {
		return "user/userMypage";
	}
	
	//일반컨트롤러에서 비동기를 제어할 경우 @ResponseBody가 꼭 필요하다
	//일반컨트롤러에서는 비동기요청 메서드는 @ResponseBody를 붙입니다.
	@ResponseBody//응답요청을 viewResolver로 반환하는 것이 아닌, 요청이 들어온 곳으로 Response Header정보를 만들어서 보내준다
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public int idCheck(@RequestBody UserVO vo) {
		//회원가입중복체크
//		System.out.println(vo.toString());
		int result = userService.idCheck(vo);
		
		return result;
	}
	
	//join
	@RequestMapping(value = "join", method = RequestMethod.POST)
	public String join(UserVO vo, RedirectAttributes RA) {
		System.out.println(vo);
		int result = userService.join(vo);
		
		if(result == 1) { //가입성공
			RA.addFlashAttribute("msg", "가입을 축하합니다");
		}else {
			RA.addFlashAttribute("msg", "가입에 실패했습니다. 다시시도하세요");
		}
		return "redirect:/user/userLogin";
	}
	
	//login
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@RequestParam("id") String id,
						@RequestParam("pw") String pw,
						HttpSession session,
						RedirectAttributes RA) {
		
		UserVO vo = userService.login(id, pw);
		
		if(vo == null) {
			RA.addFlashAttribute("msg", "아이디와 비밀번호가 일치하지 않습니다.");
			return "redirect:/user/userLogin";
		}else {
			session.setAttribute("userVO", vo); //인터셉션 미구현
			RA.addFlashAttribute("vo", vo);
			return "redirect:/user/userMypage";
		}
		
	}
	
	
	
	
}
