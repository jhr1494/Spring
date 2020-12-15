package com.team404.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //게터세터 자동
@AllArgsConstructor //모든 멤버변수를 초기화하는 생성자
@NoArgsConstructor //기본생성자
public class TestVO {
	private String id;
	private String pw;
}
