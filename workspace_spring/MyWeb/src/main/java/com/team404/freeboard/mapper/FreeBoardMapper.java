package com.team404.freeboard.mapper;

import java.util.ArrayList;

import com.team404.command.FreeBoardVO;
import com.team404.common.util.Criteria;

public interface FreeBoardMapper {
	
	public void regist(FreeBoardVO vo); //등록
//	public ArrayList<FreeBoardVO> getList(); //조회 --- 페이징 전
	public ArrayList<FreeBoardVO> getList(Criteria cri); //조회 --- 페이징 후
	public int getTotal(Criteria cri); //전체게시글수
	
	public FreeBoardVO getContent(int bno); //상세조회
	public int update(FreeBoardVO vo); //수정
	public int delete(int bno); //삭제



}
