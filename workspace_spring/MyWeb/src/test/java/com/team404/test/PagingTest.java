package com.team404.test;


import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.team404.command.FreeBoardVO;
import com.team404.common.util.Criteria;
import com.team404.test.mapper.TestMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/springconfig/DB-context.xml")
public class PagingTest {
	
	@Autowired
	private TestMapper testMapper;
	
//	@Test
//	public void Test() {
//		for(int i = 0; i <= 100; i++) {
//			FreeBoardVO vo = new FreeBoardVO();
//			vo.setTitle("bbb제목 " + i);
//			vo.setWriter("bbb작성자 " + i);
//			vo.setContent("bbb내용입니다 " + i);
//			
//			testMapper.insertTest(vo);
//		}
//		
//	}

	@Test //데이터 조회
	public void test2() {

		Criteria cri = new Criteria(); //1페이지의 10개데이터
//		Criteria cri = new Criteria(3, 10); //3번페이지의 10개 데이터
		ArrayList<FreeBoardVO> list = testMapper.selectTest(cri);
		
		System.out.println(list.toString());
		
	}
}
