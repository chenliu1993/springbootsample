package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Post;
import com.example.demo.mapper.PostMapper;

@Service
public class PostService {

	@Autowired
	private PostMapper postMapper;

	@Transactional
	public List<Post> findAll() {
		return postMapper.findAll();
	}

	@Transactional
	public Post findOne(Long id) {
		return postMapper.findOne(id);
	}

	@Transactional
	public void save(Post post) {
		postMapper.save(post);
	}

	@Transactional
	public void update(Post post) {
		postMapper.update(post);
	}

	@Transactional
	public void delete(Long id) {
		postMapper.delete(id);
	}

	@Transactional
	public String findUserByName(String name) {
		return String.valueOf(postMapper.findUserByName(name));
	}
    
}
