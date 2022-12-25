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
import com.example.demo.service.PostService;

@Controller
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostService postService;
	
	@GetMapping
	public String index(Model model) {
		model.addAttribute("posts", postService.findAll());
		return "index";
	}
	
	@GetMapping("{id}")
	public String show(@PathVariable Long id, Model model) {
		model.addAttribute("post", postService.findOne(id));
		return "show";
	}
	
	@GetMapping("new")
	public String newPost(@ModelAttribute("post") Post post, Model model) {
		return "new";
	}
	
	@GetMapping("{id}/edit")
	public String edit(@PathVariable Long id, @ModelAttribute("post") Post post, Model model) {
		model.addAttribute("post", postService.findOne(id));
		return "edit";
	}
	
	@PostMapping
	public String create(@ModelAttribute("post") @Validated Post post, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "new";
		} else {
			postService.save(post);
			return "redirect:/posts";
		}
	}
	
	@PutMapping("{id}")
	public String update(@PathVariable Long id, @ModelAttribute("post") @Validated Post post, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("post", post);
			return "edit";
		} else {
			post.setId(id);
			postService.update(post);
			return "redirect:/posts";
		}
	}
	
	@DeleteMapping("{id}")
	public String delete(@PathVariable Long id) {
		postService.delete(id);
		return "redirect:/posts"; 
	}
}
