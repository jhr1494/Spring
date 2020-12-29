package com.team404.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
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
	
	@RequestMapping("/userLogin")
	public String userLogin() {
		return "user/userLogin";
	}
	
	@RequestMapping("/userMypage")
	public String userMypage(HttpSession session,
							 Model model) {
		//조인(user + board)데이터에 대한 처리방법
		UserVO vo = (UserVO) session.getAttribute("userVO");
		String userId = vo.getUserId();
		
		//1:N관계 맵핑으로 결과를 처리
		UserVO userInfo = userService.getInfo(userId);
//		System.out.println(userVO);
		model.addAttribute("userInfo", userInfo);
		
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
//	@RequestMapping(value = "login", method = RequestMethod.POST)
//	public String login(@RequestParam("userId") String id,
//						@RequestParam("userPw") String pw,
//						HttpSession session,
//						RedirectAttributes RA) {
//		
//		UserVO vo = userService.login(id, pw);
//		
//		if(vo == null) {
//			RA.addFlashAttribute("msg", "아이디와 비밀번호가 일치하지 않습니다.");
//			return "redirect:/user/userLogin";
//		}else {
//			session.setAttribute("userVO", vo); //인터셉션 미구현
//			RA.addFlashAttribute("vo", vo);
//			return "redirect:/user/userMypage";
//		}
//		
//	}
	
	
	//PostHandler 처리 전
//	@RequestMapping(value = "login", method = RequestMethod.POST)
//	public String login(UserVO vo,
//						Model model,
//						HttpSession session) {
//		
//		//로그인 성공시 회원정보를 받아오고, 로그인 실패시 null을 반환 → 로그인 직후의 POSTHandler로 전환할 수 있다.(UserLoginSuccessHandler)
//		UserVO result = userService.login(vo);
//		
//		if(result == null) {//로그인 실패
//			model.addAttribute("msg", "아이디 비밀번호를 확인하세요");
//			return "/user/userLogin";
//		}else {
//			//세션에 회원정보를 저장
//			session.setAttribute("userVO", result);
//
//			return "redirect:/";
//		}
//	}
	
	
	//PostHandler 처리
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView login(UserVO vo) {
		//로그인 성공시 회원정보를 받아오고, 로그인 실패시 null을 반환
		UserVO result = userService.login(vo);
		
		
		ModelAndView mv = new ModelAndView(); //뷰와 model정보를 동시에 저장하는 객체
		mv.setViewName("/user/userLogin"); //기존에 가지고있는 화면 정보 -- 로그인 실패시 기존화면으로
		
		//로그인 성공시 회원정보 저장, 로그인 실패시 msg정보 저장
		if(result != null) { //로그인 성공
			mv.addObject("login", result);
		}else { //로그인 실패
			mv.addObject("msg", "아이디 비밀번호를 확인하세요");
		}
		
		return mv;
	}

		
		//로그아웃
		@RequestMapping("/userLogout")
		public String logout(HttpSession session) {
			session.invalidate();
			return "redirect:/"; //홈
		}
		
		//로그인 구현 -> 권한처리
		
		
	
	
	
}
