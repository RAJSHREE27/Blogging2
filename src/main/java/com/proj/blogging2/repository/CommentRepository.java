package com.proj.blogging2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proj.blogging2.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
