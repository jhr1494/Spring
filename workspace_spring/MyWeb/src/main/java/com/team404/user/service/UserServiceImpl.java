package com.team404.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team404.command.UserVO;
import com.team404.user.mapper.UserMapper;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public int idCheck(UserVO vo) {

		return userMapper.idCheck(vo);
	}

	@Override
	public int join(UserVO vo) {
	
		return userMapper.join(vo);
	}

	@Override
	public UserVO login(String id, String pw) {
		return userMapper.login(id, pw);
	}

}
