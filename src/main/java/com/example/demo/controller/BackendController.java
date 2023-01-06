package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Post;
import com.example.demo.domain.PostEntity;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;
import com.example.demo.util.FileUtil;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Controller
@RequestMapping("/posts")
@Slf4j
public class BackendController {

	@Autowired
	private PostService postService;

	@Autowired
	private UserService userService;

	private FileUtil fileUtil = new FileUtil();


	
	@GetMapping
	public String index(Model model) {
		List<PostEntity> postEntities = new LinkedList<PostEntity>();
		List<Post> allPosts = postService.findAll();
		for(int i=0;i<allPosts.size();i++) {
			PostEntity postEntity = new PostEntity();
			log.info(String.valueOf(allPosts.get(i).getId()));
			postEntity.setId(allPosts.get(i).getId());
			log.info(userService.findNameById(allPosts.get(i).getUserID()));
			postEntity.setAuthor(userService.findNameById(allPosts.get(i).getUserID()));
			log.info(allPosts.get(i).getTheme());
			postEntity.setTheme(allPosts.get(i).getTheme());
			log.info(fileUtil.ReadFromPost(allPosts.get(i).getPath()));
			postEntity.setContent(fileUtil.ReadFromPost(allPosts.get(i).getPath()));
			postEntities.add(postEntity);
		}
		
		model.addAttribute("posts", postEntities);
		return "index";
	}
	
	@GetMapping("{id}")
	public String show(@PathVariable Long id, Model model) {
		Post post = postService.findOne(id);
		PostEntity postEntity = new PostEntity();
		log.info(String.valueOf(post.getId()));
		postEntity.setId(post.getId());
		log.info(userService.findNameById(post.getUserID()));
		postEntity.setAuthor(userService.findNameById(post.getUserID()));
		log.info(post.getTheme());
		postEntity.setTheme(post.getTheme());
		log.info(post.getPath());
		log.info(fileUtil.ReadFromPost(post.getPath()));
		postEntity.setContent(fileUtil.ReadFromPost(post.getPath()));

		model.addAttribute("post", postEntity);
		return "show";
	}
	
	@GetMapping("new")
	public String newPost(@ModelAttribute("post") PostEntity postEntity, Model model) {
		return "new";
	}
	
	@GetMapping("{id}/edit")
	public String edit(@PathVariable Long id, @ModelAttribute("post") PostEntity postEntity, Model model) {
		Post post = postService.findOne(id);
		postEntity.setTheme(post.getTheme());
		postEntity.setId(post.getId());
		postEntity.setAuthor(userService.findNameById(post.getUserID()));
		postEntity.setContent(fileUtil.ReadFromPost(post.getPath()));
		model.addAttribute("post", postEntity);
		return "edit";
	}
	
	@PostMapping
	public String create(@ModelAttribute("post") @Validated PostEntity postEntity, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "new";
		} else {
			postService.save(postEntity);
			return "redirect:/posts";
		}
	}
	
	@PutMapping("{id}")
	public String update(@PathVariable Long id, @ModelAttribute("post") @Validated PostEntity postEntity, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("post", postEntity);
			return "edit";
		} else {
			postEntity.setId(id);
			postService.update(postEntity);
			return "redirect:/posts";
		}
	}
	
	@DeleteMapping("{id}")
	public String delete(@PathVariable Long id) {
		postService.delete(id);
		return "redirect:/posts"; 
	}
}
