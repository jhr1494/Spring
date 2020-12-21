package com.team404.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team404.command.ReplyVO;
import com.team404.reply.service.ReplyService;

@RestController //@ResponseBody가 자동적으로 생략된 형태의 컨트롤러
@RequestMapping("/reply")
public class ReplyController {
	
	@Autowired
	@Qualifier("replyService")
	private ReplyService replyService;
	
	//등록요청
	@PostMapping("/replyRegist")
	public int replyRegist(@RequestBody ReplyVO vo) {
		
		System.out.println(vo.toString());
		int result = replyService.replyRegist(vo);
		return result; //레스트컨트롤러 return결과는 호출구문으로 갑니다(ResponseBidy)
	}
	
	//목록요청
	@GetMapping("/getList/{bno}")
	public ArrayList<ReplyVO> getList(@PathVariable("bno") int bno) {
		
		ArrayList<ReplyVO> list = replyService.getList(bno);
		
		return list;
	}

}
