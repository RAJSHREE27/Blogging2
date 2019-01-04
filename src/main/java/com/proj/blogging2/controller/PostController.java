/* optional class is used to handle null value exceptions. whenever there is a ull object it gives - empty 
 * output rather than showing nullpointerexceptions*/

/*To avoid the abnormal termination, we use Optional class. 
we are using Optional So that our program can execute without crashing.
*/

/* Principal class is to show the current authorized user
 * the user who is working is the principal user */

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


import com.proj.blogging2.model.Post;
import com.proj.blogging2.model.User;
import com.proj.blogging2.service.PostService;
import com.proj.blogging2.service.UserService;


@Controller
public class PostController {
	
	private PostService postService;
	private UserService userService;
	
	@Autowired
	public PostController(PostService postService, UserService userService ) {
		this.postService = postService;
		this.userService = userService;
		
	}
	
	
	@RequestMapping(value = "/newpost", method = RequestMethod.GET)
   	public String newPost(Principal principal, Model model) {
		
	    Optional<User> user = userService.findByUserName(principal.getName());

	    if (user.isPresent()) {
	        Post post = new Post();
	        post.setUser(user.get());
	        
	        model.addAttribute("post", post);
	
		        return "/postForm";
		
		    } else {
		        return "/error";
		    }
			
	 }

	//to save new post
	@RequestMapping(value = "/newpost", method = RequestMethod.POST)
	public String createNewPost(@Valid Post post, BindingResult bindingResult) {
		
			if (bindingResult.hasErrors()) {
	            return "/postForm";
	        } else {
	            postService.save(post);
	            return "redirect:/blog/" + post.getUser().getUserName();
	        }
			
	}
	
	//String bcoz it it returns a url
	
	@RequestMapping(value = "/editpost/{id}", method = RequestMethod.GET)
	public String editWithPostId(@PathVariable long id, Principal principal, Model model) {
		
		Optional<Post> opost = postService.findByPostId(id);
		
		if(opost.isPresent()) {
			Post post = opost.get();
			
			if(isPrincipalOwnerOfPost(principal, post)) {
				model.addAttribute("post", post);
				return "/postForm";
				
			}else {
				return "/403";
			}
			
		}else {
			return "/error";
		}
		
	}
	
	
	@RequestMapping(value = "/getpost/{id}", method = RequestMethod.GET)
	public String getPostById(@PathVariable long id, Principal principal, Model model) {
		
		Optional<Post> opost= postService.findByPostId(id);
		
		if(opost.isPresent()) {
			Post post = opost.get();
			
			if(isPrincipalOwnerOfPost(principal,post)) {
				model.addAttribute("username" , principal.getName());
				
				
			}
			
			return "/post";
			
		}else {
			
			return "/error";
		}
		
	} 
	
	@RequestMapping(value = "/delpost/{id}", method = RequestMethod.DELETE)
	public String deletePostById(@PathVariable long id, Principal principal, Model model) {

		Optional<Post> opost= postService.findByPostId(id);

		if(opost.isPresent()) {
			Post post = opost.get();
			
			if(isPrincipalOwnerOfPost(principal,post)) {
				postService.delete(post);
				return "redirect:/home";
				
			}
			
			return "/post";
			
		}else {
			
			return "/error";
		}
	}
	

	private boolean isPrincipalOwnerOfPost(Principal principal, Post post) {
		return principal!=null && principal.getName().equals(post.getUser().getUserName());
	}
	
}

