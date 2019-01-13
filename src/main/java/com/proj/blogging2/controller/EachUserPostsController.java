package com.proj.blogging2.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proj.blogging2.model.Post;
import com.proj.blogging2.model.User;
import com.proj.blogging2.service.PostService;
import com.proj.blogging2.service.UserService;
import com.proj.blogging2.utils.Pager;

@Controller
@RequestMapping("/userblogs")
public class EachUserPostsController {
	
	private PostService postService;
	private UserService userService;
	
	@Autowired
	public EachUserPostsController(PostService postService, UserService userService) {
		this.postService = postService;
		this.userService = userService;
		
	}
	
	@GetMapping("/{userName}")
	public String userBlogs(@RequestParam(defaultValue="0")int page, Model model, @PathVariable String userName) {
		
		
		Optional<User> user = userService.findByUserName(userName);
		Page<Post> userPosts = postService.findByUserOrderedByDatePageable(user, page); 
		
		Pager pager = new Pager(userPosts);
		
		model.addAttribute("pager_blog",pager);
		model.addAttribute("user",user.get());
		return "/UserBlogs";
		
		
	}
	
	
}
