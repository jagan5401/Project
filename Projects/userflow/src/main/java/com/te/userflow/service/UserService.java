package com.te.userflow.service;

import com.te.userflow.dto.UserDTO;
import com.te.userflow.entity.User;
import com.te.userflow.security.AuthRequest;

public interface UserService {



	User addUser(UserDTO userDTO);


	
}
