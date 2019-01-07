package com.proj.blogging2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proj.blogging2.model.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
	
	Post findByPostId(long postId);
	Post saveAndFlush(Post post);
	void delete(Post post);
	
}
