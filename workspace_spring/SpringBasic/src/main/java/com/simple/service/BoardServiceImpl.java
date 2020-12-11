package com.simple.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.simple.command.BoardVO;
import com.simple.dao.BoardDAO;

@Service("board")
public class BoardServiceImpl implements BoardService {

	//DAO
	@Autowired
	@Qualifier("boardDAO")
	private BoardDAO dao;
	
	@Override
	public void boardRegist(BoardVO vo) {
		dao.boardRegist(vo);
		
	}

	@Override
	public ArrayList<BoardVO> getList() {
		
		return dao.getList();
	}

	@Override
	public void boardDelete(int num) {
		dao.boardDelete(num);
		
	}


}
