package com.simple.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.simple.command.ScoreVO;

//자체가 싱글톤
@Repository("scoreDAO") //scoreDAO이름으로 bean생성
public class ScoreDAOImpl implements ScoreDAO{

//	//DB라고 가정
//	private ArrayList<ScoreVO> list = new ArrayList<>();
//	
//	//DB관련된 작업...
//	//폼 입력전송 → servlet-context.xml(읽기) → ScoreServiceImpl(생성) → ScoreDAOImpl(생성, 불러옴) → 결과화면
//	@Override
//	public void scoreRegist(ScoreVO vo) {
//		System.out.println(vo.toString());
//		
//		list.add(vo); //DB추가작업..
//		
//	}
//	
//	//추가 메서드 발생시 꼭 Service, DAO 인터페이스에 추가를 합니다 ~
//	//이벤트발생 → ScoreServiceImpl(생성, DAO호출) → ScoreDAOImpl(생성, 반환) → 결과화면에 반환
//	@Override
//	public ArrayList<ScoreVO> getList() {
////		System.out.println(list.toString());
//		return list;
//	}
//
//	@Override
//	public void scoreDelete(int index) {
//		
//		list.remove(index); //삭제
//		
//	}
	
	
	//==============JDBC TEMPLATE==============
	
	@Autowired
	private JdbcTemplate jdbcTemplate; //jdbc템플릿 주입
	
	
	@Override
	public void scoreRegist(ScoreVO vo) {
		//insert, update, delete는 jdbc템플릿 update(sql문, 바인딩값)이 대신합니다.
		String sql = "insert into score values(score_seq.nextval, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, new Object[] {vo.getName(), vo.getKor(), vo.getEng(), vo.getMath()} );
		
	}

	@Override
	public ArrayList<ScoreVO> getList() {
		String sql = "select * from score";
		List<ScoreVO> list = jdbcTemplate.query(sql, new Object[] {}, new RowMapper<ScoreVO>() {

			@Override
			public ScoreVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				//제네릭에는 select구문을 처리해 줄 vo객체를 적어줍니다.
				//mapRow메서드에는 select구문의 처리결과를 넣어주는데, 
				//개발자는 resultSet을 어떻게 처리할지 구문만 작성하면 됩니다.
				
				ScoreVO vo = new ScoreVO();
				
				vo.setNum(rs.getInt("num"));
				vo.setName(rs.getString("name"));
				vo.setKor(rs.getString("kor"));
				vo.setEng(rs.getString("eng"));
				vo.setMath(rs.getString("math"));
				
				return vo;
			}});
		return (ArrayList<ScoreVO>) list;
	}

	@Override
    public void scoreDelete(int index) {

        String sql = "delete from score where num = ?";
        jdbcTemplate.update(sql, new Object[] {index});

    }
	

}
