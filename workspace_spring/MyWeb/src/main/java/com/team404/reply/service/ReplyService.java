package com.team404.reply.service;

import java.util.ArrayList;

import com.team404.command.ReplyVO;
import com.team404.common.util.Criteria;

public interface ReplyService {
	public int replyRegist(ReplyVO vo);
	//public ArrayList<ReplyVO> getList(int bno);
	public ArrayList<ReplyVO> getList(int bno, Criteria cri);
	public int update(ReplyVO vo);
	public int check(ReplyVO vo);
	public int delete(ReplyVO vo);
}
