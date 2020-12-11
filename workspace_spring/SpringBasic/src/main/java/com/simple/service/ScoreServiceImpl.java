package com.simple.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.simple.command.ScoreVO;
import com.simple.dao.ScoreDAO;

@Service("xx") //scoreService이름으로 bean생성
public class ScoreServiceImpl implements ScoreService {
	
	@Autowired
	@Qualifier("scoreDAO")
	private ScoreDAO scoreDAO;
	
	@Override
	public void scoreRegist(ScoreVO vo) {
		scoreDAO.scoreRegist(vo);
	}

	@Override
	public ArrayList<ScoreVO> getList() {
		//해당 메서드에서 처리할 것이 없는 경우 리턴으로 바로 반환!
		return scoreDAO.getList();
	}

	@Override
	public void scoreDelete(int index) {
		
		scoreDAO.scoreDelete(index);
	
		
	}

}
