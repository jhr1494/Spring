package com.simple.mapper;

import java.util.ArrayList;

import com.simple.command.BoardVO;

public interface BoardMapper {
	
	//sevice에서 BoardMapper로 연결해서 정상적으로 종작되도록 변경
	
	public void boardRegist(BoardVO vo); //board 등록 메서드
	public ArrayList<BoardVO> getList(); //모든 게시물 가져오기
	public void boardDelete(int num); //게시글 삭제 메서드
}
