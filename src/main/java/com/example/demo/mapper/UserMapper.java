package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.User;

@Mapper
public interface UserMapper {
	List<User> findAll();
	
	User findOne(Long id);
	
	void save(User user);
	
	void update(User user);
	
	void delete(Long id);
}
