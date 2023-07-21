package com.te.userflow.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.te.userflow.entity.DoctorDetails;

import lombok.AllArgsConstructor;
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

	
	DoctorDetails doctorDetails;
	

	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		//System.out.println("inside my user details getAuthorities method");

		SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(doctorDetails.getRoles());
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(grantedAuthority);
	//	System.out.println("13");
		return authorities;
	}

	@Override
	public String getPassword() {
		//System.out.println("14");
		return doctorDetails.getPassword();
	}

	@Override
	public String getUsername() {
		//System.out.println("CustomUserDetails2 name");
		return doctorDetails.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	


}
