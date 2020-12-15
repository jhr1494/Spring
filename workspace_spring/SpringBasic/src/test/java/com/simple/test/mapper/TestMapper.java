package com.simple.test.mapper;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.simple.command.BoardVO;

public interface TestMapper {
	//인터페이스이름과 동일한 이름의 구현xml을 파일을 생성
	
	public String getTime(); //조회
//	public int insert(BoardVO vo); //추가
	public int insert(Map<String, String> map); //추가
	public ArrayList<BoardVO> getList(); //조회
	public BoardVO selectOne(int num); //pk를 통한 단일조회
	
	//2개 이상의 파라미터 조회
	//파라미터 값이 setter의 기준이기때문에 2개이상의 파라미터 값은 불가
	//예외적으로 @Param을 사용하여 처리
	public ArrayList<BoardVO> getListTwo(@Param("xxx") String name, @Param("yyy") String title); 
	
	//10번게시글 수정
	public int updateOne(BoardVO vo); //수정
	
	//삭제
	public boolean delete(int num); //삭제

}
