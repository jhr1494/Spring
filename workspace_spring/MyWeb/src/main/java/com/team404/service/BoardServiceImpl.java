package com.team404.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team404.command.BoardVO;
import com.team404.mapper.BoardMapper;

@Service("board")
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public void boardRegist(BoardVO vo) {
		boardMapper.boardRegist(vo);

	}

	@Override
	public ArrayList<BoardVO> getList() {
		
		return boardMapper.getList();
	}

	@Override
	public void boardModify(int num) {
		boardMapper.boardModify(num);

	}

	@Override
	public void boardDelete(int num) {
		boardMapper.boardDelete(num);

	}

}
