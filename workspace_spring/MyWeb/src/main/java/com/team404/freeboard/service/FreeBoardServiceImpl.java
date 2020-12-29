package com.team404.freeboard.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team404.command.FreeBoardVO;
import com.team404.common.util.Criteria;
import com.team404.freeboard.mapper.FreeBoardMapper;

@Service("freeBoardService") //이 패키지가 읽히는지 확인(컴포넌트 스캔)
public class FreeBoardServiceImpl implements FreeBoardService {
	
	@Autowired
	private FreeBoardMapper freeBoardMapper;
	
	@Override
	public void regist(FreeBoardVO vo) {
//		System.out.println(vo.toString());
		freeBoardMapper.regist(vo);

	}

	//조회 --페이징 전
//	@Override
//	public ArrayList<FreeBoardVO> getList() {
//		return freeBoardMapper.getList();
//	}
	
	//조회 -- 페이징 후
	@Override
	public ArrayList<FreeBoardVO> getList(Criteria cri) {
		
		//Around 예시
//		System.out.println("getList실행");
//		//고의적 에러발생
//		String s = null;
//		s.equals("234");
		
		return freeBoardMapper.getList(cri);
	}
	
	@Override
	public int getTotal(Criteria cri) {
		
		return freeBoardMapper.getTotal(cri);
	}


	@Override
	public FreeBoardVO getContent(int bno) {
		return freeBoardMapper.getContent(bno);
	}

	@Override
	public int update(FreeBoardVO vo) {
		return freeBoardMapper.update(vo);
	}

	@Override
	public int delete(int bno) {
		return freeBoardMapper.delete(bno);
	}

	
	
	
	
}
