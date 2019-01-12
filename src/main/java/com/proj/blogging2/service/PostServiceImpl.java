package com.proj.blogging2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.proj.blogging2.model.Post;
import com.proj.blogging2.model.User;
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
	    
	    @Override
	    public Page<Post> findByUserOrderedByDatePageable(User user, int page) {
	        return postRepository.findByUserOrderByCreateDateDesc(user, PageRequest.of(subtractPageByOne(page), 5));
	    }

	    @Override
	    public Page<Post> findAllOrderedByDatePageable(int page) {
	        return postRepository.findAllByOrderByCreateDateDesc(PageRequest.of(subtractPageByOne(page), 5));
	    }
	   
	    private int subtractPageByOne(int page){
	        return (page < 1) ? 0 : page - 1;
	    }

}
