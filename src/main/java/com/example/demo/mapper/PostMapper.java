package com.example.demo.mapper;
import java.util.List;

import com.example.demo.domain.Post;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface PostMapper {
    List<Post> findAll();
	
	Post findOne(Long id);
	
	void save(Post post);
	
	void update(Post post);
	
	void delete(Long id);

	int getPostsNum();
}
