package com.team404.reply.mapper;

import java.util.ArrayList;

import com.team404.command.ReplyVO;

public interface ReplyMapper {
	public int replyRegist(ReplyVO vo);
	public ArrayList<ReplyVO> getList(int bno);
}
