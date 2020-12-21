package com.team404.reply.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team404.command.ReplyVO;
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
	public ArrayList<ReplyVO> getList(int bno) {
		
		return replyMapper.getList(bno);
	}

}
