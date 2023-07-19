package com.te.userflow.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.te.userflow.entity.Appointment;
import com.te.userflow.entity.DoctorDetails;
import com.te.userflow.entity.User;
import com.te.userflow.repository.AppointmentRepository;
import com.te.userflow.repository.DoctorRepository;
import com.te.userflow.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private DoctorRepository repository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		DoctorDetails doctorDetails = repository.findByName(username);
		//System.out.println("10");
		if (doctorDetails == null) {
		//	System.out.println("inside employee user details 2");
			User user = userRepository.findByName(username);
			return new CustomUserDetails2(user);
		} else {
		//	System.out.println("inside employee user details1");
			return new CustomUserDetails(doctorDetails);
		}
	}
}