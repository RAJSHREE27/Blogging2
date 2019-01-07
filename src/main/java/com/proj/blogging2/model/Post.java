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
import org.hibernate.validator.constraints.Length;

import lombok.Data;


@Data
@Entity
@Table(name="post_info")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "post_id")
	private long postId;
	
	
	@Column(name = "post_title", nullable = false)
    @Length(min = 5, message = "*Your title must have at least 5 characters")
    @NotEmpty(message = "*Please provide title")
	private String title;
	
	
	@Column(name = "post_body")
	@Lob
	@NotEmpty(message = "*Please provide a body")
	private String body;
	
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false, updatable = false)
    @CreationTimestamp
	private Date createDate;
	
	

	@ManyToOne
    @JoinColumn(name = "user_name", referencedColumnName = "user_name", nullable = false)
    @NotNull
	private User user;
	
}