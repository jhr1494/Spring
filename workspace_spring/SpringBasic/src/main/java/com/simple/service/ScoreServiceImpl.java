package com.simple.service;

import org.springframework.stereotype.Service;

import com.simple.command.ScoreVO;

@Service("xx") //scoreService이름으로 bean생성
public class ScoreServiceImpl implements ScoreService {

	@Override
	public void scoreRegist(ScoreVO vo) {
		System.out.println(vo.toString());
		
	}

}
