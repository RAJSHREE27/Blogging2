package com.proj.blogging2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.blogging2.model.Post;
import com.proj.blogging2.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {
	
	 private final PostRepository postRepository;

	    @Autowired
	    public PostServiceImpl(PostRepository postRepository) {
	        this.postRepository = postRepository;
	    }
	    
	    @Override
	    public Optional<Post> findForId(Long id) {
	        return postRepository.findById(id);
	    }

	    @Override
	    public Post save(Post post) {
	        return postRepository.saveAndFlush(post);
	    }
	    
	    @Override
	    public void delete(Post post) {
	        postRepository.delete(post);
	    }

}
