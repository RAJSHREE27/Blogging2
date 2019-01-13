package com.proj.blogging2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proj.blogging2.model.Post;
import com.proj.blogging2.service.PostService;
import com.proj.blogging2.utils.Pager;

@Controller
public class HomeController {
	
	private final PostService postService;
	
	@Autowired
	public HomeController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping("/home")
	public String home(@RequestParam(defaultValue="0")int page ,Model model) {
			
		Page<Post> posts = postService.findAllOrderedByDatePageable(page);
		
		for(Post p : posts) {
			String s = p.getBody();
			p.setBody(s.substring(0, 20));
			//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
		}
		Pager pager = new Pager(posts);
		model.addAttribute("pager", pager);
		return "/home";
		
		
	}
}
