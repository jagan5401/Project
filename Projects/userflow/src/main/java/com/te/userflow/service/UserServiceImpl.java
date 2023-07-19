package com.te.userflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.te.userflow.dto.UserDTO;
import com.te.userflow.entity.User;
import com.te.userflow.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public User addUser(UserDTO userDTO) {
		User user=new User();
		user.setName(userDTO.getName());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		user.setAge(userDTO.getAge());
		user.setRoles("ROLE_USER");
		user.setEmail(userDTO.getEmail());
		user.setGender(userDTO.getGender());
		user.setPhoneNo(userDTO.getPhoneNo());
		
	
		return repository.save(user);
	}

}
