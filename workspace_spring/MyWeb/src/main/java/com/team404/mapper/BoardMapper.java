package com.team404.mapper;

import java.util.ArrayList;

import com.team404.command.BoardVO;

public interface BoardMapper {
	
	public void boardRegist(BoardVO vo); //등록
	public ArrayList<BoardVO> getList(); //조회
	public void boardModify(int num); //수정
	public void boardDelete(int num); //삭제


}
