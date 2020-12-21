package com.team404.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team404.command.FreeBoardVO;
import com.team404.common.util.Criteria;
import com.team404.common.util.PageVO;
import com.team404.freeboard.service.FreeBoardService;

@Controller
@RequestMapping("/freeBoard")
public class FreeBoardController {
	
	@Resource(name = "freeBoardService")
	private FreeBoardService freeBoardSevice;
	
	//화면처리
//	//글 목록 ---1. 기본방식(페이지네이션 전)
//	@RequestMapping("/freeList")
//	public String freeList(Model model) {
//		
//		//화면으로 넘어갈 때 글 정보를 가지고 갈 수 있도록 처리 getList()로 조회하 결과를 리스트 화면에 출력
//		ArrayList<FreeBoardVO> list = freeBoardSevice.getList();
//		model.addAttribute("list", list);
//		
//		return "freeBoard/freeList";
//	}
	
	//글 목록 ---페이지네이션 후
	@RequestMapping("/freeList")
	public String freeList(Model model, Criteria cri) {
		
		/*
		 * //2. 페이지방식 
		 * int total = freeBoardSevice.getTotal(); 
		 * System.out.println(total);
		 * PageVO pageVO = new PageVO(cri, total);
		 * 
		 * ArrayList<FreeBoardVO> list = freeBoardSevice.getList(cri);
		 * 
		 * //화면에 전달할 값들 model.addAttribute("list", list); model.addAttribute("pageVO",
		 * pageVO);
		 */
		
		//3. 검색과 페이지
//		System.out.println(cri.toString());
		
		ArrayList<FreeBoardVO> list = freeBoardSevice.getList(cri);
//		System.out.println(list.toString());
		
		//
		int total = freeBoardSevice.getTotal(cri); 
		PageVO pageVO = new PageVO(cri, total);
		 
		//화면에 전달할 값들 
		model.addAttribute("list", list); 
		model.addAttribute("pageVO", pageVO);

		
		return "freeBoard/freeList";
	}

	
	
	
	
	
	//글 등록 화면
	@RequestMapping("/freeRegist")
	public String freeRegist() {
		return "freeBoard/freeRegist";
	}
	
	//글 등록
	@RequestMapping(value = "/registForm", method = RequestMethod.POST)
	public String registForm(FreeBoardVO vo, RedirectAttributes RA) {
		
		freeBoardSevice.regist(vo); //insert실행
		RA.addFlashAttribute("msg", "정상적으로 등록처리 되었습니다."); //msg를 리스트화면으로 전달
		
		return "redirect:/freeBoard/freeList";
	}
	
	
//	//글 상세
//	@RequestMapping(value = "/freeDetail", method = RequestMethod.GET)
//	public String freeDetail(@RequestParam("bno") int bno, Model model) {
//		
//		//화면으로 넘어갈때 bno기반의 데이터를 가지고 상세화면으로 가도록 getContent()로 처리
//		FreeBoardVO vo = freeBoardSevice.getContent(bno);
////		System.out.println(vo.toString());
//		model.addAttribute("vo", vo); //bno게시글에 대한 정보
//		
//		return "freeBoard/freeDetail";
//	}
//	
//	
//	
//	//글 수정화면
//	@RequestMapping("/freeModify")
//	public String freeModify(@RequestParam("bno"), Model model) {
//		
//		FreeBoardVO vo = freeBoardSevice.getContent(bno);
//		model.addAttribute("vo", vo);
//		
//		return "freeBoard/freeModify";
//	}
	
	
	//상세화면과 변경화면이 기능이 같기 때문에 하나로 묶어서 사용합니다
	@RequestMapping(value = {"/freeDetail", "/freeModify"}, method = RequestMethod.GET)
	public void freeModify(@RequestParam("bno") int bno, Model model) {
		
		//화면으로 넘어갈때 bno기반의 데이터를 가지고 상세화면으로 가도록 getContent()로 처리
		FreeBoardVO vo = freeBoardSevice.getContent(bno);
		model.addAttribute("vo", vo); //bno게시글에 대한 정보
		
		//void형 메서드는 요청의 결과가 디스패쳐서블릿으로 return합니다
	}
	
	//글 수정
		@RequestMapping(value = "/freeUpdate", method = RequestMethod.POST)
		public String freeUpdate(FreeBoardVO vo, RedirectAttributes RA) {
			
			int num = freeBoardSevice.update(vo); 
			if(num == 1) { //업데이트성공
				RA.addFlashAttribute("msg", "게시글이 수정되었습니다.");
			}else { //업데이트 실패
				RA.addFlashAttribute("msg", "실패하였습니다.");
			}
			
			return "redirect:/freeBoard/freeList";
		}
	
	
	
	//글 삭제
		@RequestMapping(value = "/freeDelete", method = RequestMethod.POST)
		public String freeDelete(@RequestParam("bno") int bno, RedirectAttributes RA) {
			int num = freeBoardSevice.delete(bno); 
			if(num == 1) { 
				RA.addFlashAttribute("msg", "게시글이 삭제되었습니다.");
			}else {
				RA.addFlashAttribute("msg", "실패하였습니다.");
			}
			
			return "redirect:/freeBoard/freeList";
		}



}
