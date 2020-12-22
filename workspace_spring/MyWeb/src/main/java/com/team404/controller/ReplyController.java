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
import com.team404.common.util.Criteria;
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
	@GetMapping("/getList/{bno}/{pageNum}")
	public ArrayList<ReplyVO> getList(@PathVariable("bno") int bno,
									  @PathVariable("pageNum") int pageNum) {

		//1. 화면에서 더보기 버튼을 생성하고, 처음실행할 때 pageNum 1번과 해당 게시글 번호를 보냅니다
		//2. getList(bno, cri)를 받습니다
		//3. mybatis에 매개값이 2개 전달되는 경우 @Param어노테이션을 사용합니다.
		//4. 페이징쿼리구문을 실행
		//5. 이 게시글에 대한 토탈 댓글 수
		
		//데이터
		Criteria cri = new Criteria(pageNum, 20); //20개의 데이터
		ArrayList<ReplyVO> list = replyService.getList(bno, cri);
		
		//게시글에 대한 토탈
		
		//해쉬맵에 키, value로 저장해서 반환
		
		return list;
	}
	
	//댓글 수정
	@PostMapping("/update")
	public int update(@RequestBody ReplyVO vo) {
//		System.out.println(vo.toString());
		int result = replyService.update(vo);
		return result; //성공시 1반환, 실패시 0 반환
	}
	
	//댓글삭제
	@PostMapping("/delete")
	public int delete(@RequestBody ReplyVO vo) {
		System.out.println(vo.toString());
		int result = replyService.check(vo);
		
		if(result == 1) { //성공시
			return replyService.delete(vo);
		}else { //비밀번호 틀린경우
			return -1;
		}
		

	}

}
