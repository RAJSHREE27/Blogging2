package com.proj.blogging2.service;

import java.util.Optional;

import com.proj.blogging2.model.Post;

public interface PostService  {
	
	Optional<Post> findForId(Long id);
    Post save(Post post);
    void delete(Post post);
	
	
}
