package com.team404.reply.service;

import java.util.ArrayList;

import com.team404.command.ReplyVO;

public interface ReplyService {
	public int replyRegist(ReplyVO vo);
	public ArrayList<ReplyVO> getList(int bno);
}
