package com.proj.blogging2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proj.blogging2.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	@Query(value= "SELECT * FROM COMMENT_INFO C WHERE C.POST_ID= :id ORDER BY C.CMMNT_CREATE_DATE DESC", nativeQuery = true)
	List<Comment> findAllCommentsForAPost(@Param("id")long id);
		
	
}
