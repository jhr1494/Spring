package com.team404.reply.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team404.command.ReplyVO;
import com.team404.common.util.Criteria;
import com.team404.reply.mapper.ReplyMapper;



@Service("replyService")
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyMapper replyMapper;
	
	@Override
	public int replyRegist(ReplyVO vo) {
		
		return replyMapper.replyRegist(vo);
	}

	@Override
	public ArrayList<ReplyVO> getList(int bno, Criteria cri) {
		
		return replyMapper.getList(bno, cri);
	}

	@Override
	public int update(ReplyVO vo) {
		//1. sql문을 2번 실행(비번확인(select검증), update실행)
		//2. sql문을 한번에 실행
		return replyMapper.update(vo);
	}

	@Override
	public int check(ReplyVO vo) {
		
		return replyMapper.check(vo);
	}

	@Override
	public int delete(ReplyVO vo) {
		
		return replyMapper.delete(vo);
	}
	
	



}
