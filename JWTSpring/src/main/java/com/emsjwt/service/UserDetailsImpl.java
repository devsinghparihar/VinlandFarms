package com.emsjwt.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.emsjwt.model.Login;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String username;


	private String password;

	private Collection<? extends GrantedAuthority> authorities;
	private String id;

	public UserDetailsImpl( String username,String password,Collection<? extends GrantedAuthority> authorities, String id ) {

		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.id = id;
	}

	public static UserDetailsImpl getUser(Login user) {
		List<GrantedAuthority> authorities =List.of(new SimpleGrantedAuthority(user.getRole()));

		return new UserDetailsImpl(user.getUsername(), user.getPassword(), authorities,user.getId());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}


	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
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
