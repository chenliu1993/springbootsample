package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Post;
import com.example.demo.domain.PostEntity;
import com.example.demo.mapper.PostMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.util.FileUtil;

@Service
public class PostService {

	@Autowired
	private PostMapper postMapper;

	@Autowired
	private UserMapper userMapper;

	private FileUtil fileUtil = new FileUtil();

	@Transactional
	public List<Post> findAll() {
		return postMapper.findAll();
	}

	@Transactional
	public Post findOne(Long id) {
		return postMapper.findOne(id);
	}

	@Transactional
	public void save(PostEntity postEntity) {
		// UUID uuid = UUID.randomUUID();
		Post post = new Post();
		if(postEntity.getId()==null){
			postEntity.setId((Long)1);
		}
		post.setId(postEntity.getId());
		post.setUserID(userMapper.findUserByName(postEntity.getAuthor()));
		post.setTheme(postEntity.getTheme());

		String path = fileUtil.postDir + "/" + post.getUserID() + "-" + post.getId() + ".txt";
		fileUtil.WriteToPost(path, postEntity.getContent());
		post.setPath(path);

		postMapper.save(post);
	}

	@Transactional
	public void update(PostEntity postEntity) {
		// UUID uuid = UUID.randomUUID();
		Post post = new Post();
		post.setId(postEntity.getId());
		post.setUserID(userMapper.findUserByName(postEntity.getAuthor()));
		post.setTheme(postEntity.getTheme());

		String path = fileUtil.postDir + "/" + post.getUserID() + "-" + post.getId()+ ".txt";
		fileUtil.WriteToPost(path, postEntity.getContent());
		post.setPath(path);
		postMapper.update(post);
	}

	@Transactional
	public void delete(Long id) {
		postMapper.delete(id);
	}
    
}
