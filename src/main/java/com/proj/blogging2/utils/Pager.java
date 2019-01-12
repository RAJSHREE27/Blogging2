package com.proj.blogging2.utils;

import org.springframework.data.domain.Page;

import com.proj.blogging2.model.Post;

import lombok.Data;

@Data
public class Pager {
	
	private final Page<Post> posts;
	
	public int getPageIndex() {
		return posts.getNumber()+1;
	}
	
	public int getPageSize() {
		return posts.getSize();
	}
	
	public boolean hasNext() {
		return posts.hasNext();
	}
	
	public boolean hasPrevious() {
		return posts.hasPrevious();
	}
	
	public int getTotalPages() {
		return posts.getTotalPages();
	}
	
	public long getTotalElements() {
		return posts.getTotalElements();
	}
	
	public Page<Post> getPosts(){
		return posts;
	}
	
    public boolean indexOutOfBounds() {
        return getPageIndex() < 0 || getPageIndex() > getTotalElements();
    }
	
}
