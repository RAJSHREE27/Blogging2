package com.proj.blogging2.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.proj.blogging2.model.Post;
import com.proj.blogging2.model.User;

public interface PostService  {
	
	Optional<Post> findForId(Long id);
    Post save(Post post);
    void delete(Post post);
    
    // all posts of a user ordered by date
    Page<Post> findByUserOrderedByDatePageable(User user, int page);
    
    // all posts  ordered by date
    Page<Post> findAllOrderedByDatePageable(int page);

	
}
