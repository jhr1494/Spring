package com.team404.service;

import java.util.ArrayList;

import com.team404.command.BoardVO;

public interface BoardService {
	
	public void boardRegist(BoardVO vo); //등록
	public ArrayList<BoardVO> getList(); //조회
	public void boardModify(int num); //수정
	public void boardDelete(int num); //삭제

}
