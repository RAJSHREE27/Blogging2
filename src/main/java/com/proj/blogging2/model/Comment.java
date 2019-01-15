package com.proj.blogging2.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name="comment_info")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "comment_id")
	private long commentId;
	
	@Column(name = "comment_body")
	@Lob
	@NotEmpty(message = "*Write a comment")
	private String body;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "cmmnt_createDate", nullable = false, updatable = false)
    @CreationTimestamp
	private Date createDate;
	
	@ManyToOne
	@JoinColumn(name = "post_id",referencedColumnName = "post_id", nullable = false)
    @NotNull
	private Post post;
	
	
	@ManyToOne
    @JoinColumn(name = "user_name", referencedColumnName = "user_name", nullable = false)
    @NotNull
	private User user;
	
}

