package com.simple.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.simple.command.ScoreVO;

//자체가 싱글톤
@Repository("scoreDAO") //scoreDAO이름으로 bean생성
public class ScoreDAOImpl implements ScoreDAO{
	
	//DB라고 가정
	private ArrayList<ScoreVO> list = new ArrayList<>();
	
	//DB관련된 작업...
	//폼 입력전송 → servlet-context.xml(읽기) → ScoreServiceImpl(생성) → ScoreDAOImpl(생성, 불러옴) → 결과화면
	@Override
	public void scoreRegist(ScoreVO vo) {
		System.out.println(vo.toString());
		
		list.add(vo); //DB추가작업..
		
	}
	
	//추가 메서드 발생시 꼭 Service, DAO 인터페이스에 추가를 합니다 ~
	//이벤트발생 → ScoreServiceImpl(생성, DAO호출) → ScoreDAOImpl(생성, 반환) → 결과화면에 반환
	@Override
	public ArrayList<ScoreVO> getList() {
		System.out.println(list.toString());
		return list;
	}

}
