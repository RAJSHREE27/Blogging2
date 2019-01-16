package com.proj.blogging2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.blogging2.model.Comment;
import com.proj.blogging2.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService{
	
	private final CommentRepository commentRepository;
	
	@Autowired
	public CommentServiceImpl (CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	@Override
	public Comment save(Comment comment) {
		return commentRepository.saveAndFlush(comment);
	}

	@Override
	public List<Comment> findAllCommentsForAPost(long postId) {
		
		List<Comment> allComments = new ArrayList<Comment>();
		allComments = commentRepository.findAllCommentsForAPost(postId);
		
		return allComments;
		
	}
	
	
}
