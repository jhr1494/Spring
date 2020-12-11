package com.simple.controller;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.simple.command.BoardVO;
import com.simple.service.BoardService;


@Controller
@RequestMapping("/service")
public class BoardServiceController {
	
	@Resource(name = "board")
	private BoardService service; //부모인터페이스
	
	//화면처리
	@RequestMapping("/boardRegister")
	public void boardRegister() {}
	
	//작성폼처리
	@RequestMapping(value = "/boardForm", method = RequestMethod.POST)
	public String boardForm(BoardVO vo) {
		
		service.boardRegist(vo);
		
		return "service/boardResult";
	}
	
	//게시글 목록
	@RequestMapping("/boardList")
	public String boardList(Model model) {
		
		ArrayList<BoardVO> list = service.getList();
		model.addAttribute("list", list);
		
		return "service/boardList";
	}
	
	//게시글 삭제
	@RequestMapping("/boardDelete")
	public String boardDelete(@RequestParam("num") int num) {
		service.boardDelete(num);
		
		return "redirect:/service/boardList";
	}
	

}
