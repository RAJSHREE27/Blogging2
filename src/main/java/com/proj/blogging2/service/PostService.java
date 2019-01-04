package com.proj.blogging2.service;

import java.util.Optional;

import com.proj.blogging2.model.Post;

public interface PostService {
	
	Optional<Post> findByPostId(long postId);
	
	Post save(Post post);
	
	void delete(Post post);
	
	
}
