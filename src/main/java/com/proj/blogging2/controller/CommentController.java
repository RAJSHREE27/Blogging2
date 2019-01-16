package com.proj.blogging2.controller;

import java.security.Principal;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.proj.blogging2.model.Comment;
import com.proj.blogging2.model.Post;
import com.proj.blogging2.model.User;
import com.proj.blogging2.service.CommentService;
import com.proj.blogging2.service.PostService;
import com.proj.blogging2.service.UserService;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	private PostService postService;
	private UserService userService;
	private CommentService commentService;
	
	@Autowired
	public CommentController(PostService postService,UserService userService , CommentService commentService ) {
		this.postService = postService;
		this.userService = userService;
		this.commentService = commentService;
		
	}
	
	
	@RequestMapping(value = "/newcomment", method = RequestMethod.POST)
	public String newComment(@Valid Comment comment, BindingResult result,  Principal principal, Model model) {
		
		if(result.hasErrors()) {
			return "/CommentForm";
		}else {
			
			commentService.save(comment);
			return "redirect:/blog/getpost/"+ comment.getPost().getPostId();
		
		}
		
		
	}
	
	//passing the id of post is sufficient till GET method
	// for POST , id of post is not necessary
	
	@RequestMapping(value = "/commentpost/{id}", method = RequestMethod.GET)
	public String getCommentById(@PathVariable long id, Principal principal, Model model) {
		
		Optional<Post> post = postService.findForId(id);
		
		if(post.isPresent()) {
			Optional<User> user = userService.findByUserName(principal.getName());
			
			if(user.isPresent()) {
				Comment comment = new Comment();
				comment.setUser(user.get());
				comment.setPost(post.get());
				
				model.addAttribute("comment", comment);
				
				return "/CommentForm";
				
			}else {
				return "/error";
			}
			
			
		}else {
			return "/error";
		}
		
	}
	
	//if we click on comment button then we can see all the comments related to the post id order by create-date
	
	
	
	
}
