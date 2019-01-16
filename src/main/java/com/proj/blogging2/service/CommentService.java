package com.proj.blogging2.service;

import java.util.List;

import com.proj.blogging2.model.Comment;


public interface CommentService {
	
	Comment save(Comment comment);
	List<Comment> findAllCommentsForAPost(long postId);
	
}
