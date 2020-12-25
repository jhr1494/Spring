package com.team404.reply.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.team404.command.ReplyVO;
import com.team404.common.util.Criteria;

public interface ReplyMapper {
	public int replyRegist(ReplyVO vo);
	//public ArrayList<ReplyVO> getList(int bno);
	public ArrayList<ReplyVO> getList(@Param("bno") int bno, @Param("cri") Criteria cri);
	//마이바티스는 2개이상의 파라미터를 받을 수 없기 때문에 @Param을 사용합니다.
	public int getTotal(int bno); 
	
	public int update(ReplyVO vo); //댓글수정
	public int check(ReplyVO vo); //비밀번호 검증
	public int delete(ReplyVO vo); //댓글삭제
}
