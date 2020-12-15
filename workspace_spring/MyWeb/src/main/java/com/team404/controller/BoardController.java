package com.team404.controller;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.team404.command.BoardVO;
import com.team404.service.BoardService;

@Controller
@RequestMapping("/freeBoard")
public class BoardController {
	
	@Resource(name = "board")
	private BoardService sevice;
	
	//화면처리
	//등록
	@RequestMapping("/freeRegist")
	public String freeRegist() {
		return "freeBoard/freeRigist";
	}
	
	//목록
	@RequestMapping("/freeList")
	public String freeList(Model model) {
		ArrayList<BoardVO> list = sevice.getList();
		model.addAttribute("list", list);
		
		return "freeBoard/freeList";
	}
	
	
	//폼처리
	@RequestMapping(value = "/boardForm", method = RequestMethod.POST)
	public String boardForm(BoardVO vo) {
		sevice.boardRegist(vo);
		return "freeBoard/freeList";
	}
	
	
	//수정
	
	
	//삭제



}
