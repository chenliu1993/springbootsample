package com.example.demo.service;

import java.util.List;
// import java.util.UUID;
import java.io.IOException;
import java.nio.file.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.demo.domain.Post;
import com.example.demo.domain.PostEntity;
import com.example.demo.domain.User;
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

		Long userId = userMapper.findUserByName(postEntity.getAuthor());
		if(userId==null) {
			// New user, for now we create it...
			userId=Long.valueOf(userMapper.getUsersNum()+1);
			User user=new User();
			user.setId(userId);
			user.setName(postEntity.getAuthor());
			
			userMapper.save(user);
		}
		
		post.setUserID(userId);

		if(postEntity.getId()==null){
			postEntity.setId(Long.valueOf(postMapper.getPostsNum()+1));
		}
		post.setId(postEntity.getId());

		post.setTheme(postEntity.getTheme());

		String path = fileUtil.postDir + "/" + post.getUserID() + "-" + String.valueOf(postMapper.getPostsNumWithAuthor(post.getUserID())+1) + ".txt";
		fileUtil.WriteToPost(path, postEntity.getContent());
		post.setPath(path);
		postMapper.save(post);
	}

	@Transactional
	public void update(PostEntity postEntity) {
		// UUID uuid = UUID.randomUUID();
		Post post = postMapper.findOne(postEntity.getId());
		post.setTheme(postEntity.getTheme());

		fileUtil.WriteToPost(post.getPath(), postEntity.getContent());
		postMapper.update(post);
	}

	@Transactional
	public void delete(Long id) {
		Post post = postMapper.findOne(id);
		try {
			Files.deleteIfExists(Paths.get(post.getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		postMapper.delete(id);
	}
    
}
