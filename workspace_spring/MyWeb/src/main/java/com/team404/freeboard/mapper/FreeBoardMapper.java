package com.team404.freeboard.mapper;

import java.util.ArrayList;

import com.team404.command.FreeBoardVO;

public interface FreeBoardMapper {
	
	public void regist(FreeBoardVO vo); //등록
	public ArrayList<FreeBoardVO> getList(); //조회
	public FreeBoardVO getContent(int bno); //상세조회
	public int update(FreeBoardVO vo); //수정
	public int delete(int bno); //삭제



}
