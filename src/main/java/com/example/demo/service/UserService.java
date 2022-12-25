package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.User;
import com.example.demo.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	@Transactional
	public List<User> findAll() {
		return userMapper.findAll();
	}

	@Transactional
	public User findOne(Long id) {
		return userMapper.findOne(id);
	}

	@Transactional
	public void save(User user) {
		userMapper.save(user);
	}

	@Transactional
	public void update(User user) {
		userMapper.update(user);
	}

	@Transactional
	public void delete(Long id) {
		userMapper.delete(id);
	}

}
