package com.proj.blogging2.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails{
	
	
	private static final long serialVersionUID = 1L;
	
	private Optional<User> user;
	
	public UserDetailsImpl(Optional<User> user) {
		this.user=user;
		System.out.println("user details impl.");
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		final Set<GrantedAuthority> grntdAuths = new HashSet<GrantedAuthority>();
		Set<Role> roles = null;
		if (user.isPresent()) {
			roles = user.get().getRoles();
		}
		
		 if (roles!=null) {
             for (Role roleObj : roles) {
                     grntdAuths.add(new SimpleGrantedAuthority(roleObj.getRoleName()));
             }
		 }

		return grntdAuths ;
	}

	@Override
	public String getPassword() {
		return user.get().getPassword();
	}

	@Override
	public String getUsername() {
		if(user==null) {
			return null;
		}
		return this.user.get().getUserName();
		
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
