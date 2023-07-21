package com.te.userflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.userflow.dto.UserDTO;
import com.te.userflow.entity.User;
import com.te.userflow.exception.InvalidCredentialException;
import com.te.userflow.security.AuthRequest;
import com.te.userflow.security.AuthenticationResponse;
import com.te.userflow.security.JwtUtil;
import com.te.userflow.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/User")
public class UserController {

	@Autowired
	private AuthenticationManager manager;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserDetailsService detailsService;
	@Autowired
	private UserService service;

	// register the user
	@PostMapping("/add")
	@PreAuthorize("hasRole('USER')")
	@Operation(security = { @SecurityRequirement(name = "JWT Authentication")})//swagger
	public ResponseEntity<?> RegistrationTheUser(@RequestBody UserDTO user) {
		User adduser = service.addUser(user);
		if (adduser != null) {
			return new ResponseEntity<String>("User Registration successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("failed", HttpStatus.BAD_REQUEST);
		}

	}

	// authentication for user and doctor
	@PostMapping("/getAuthenticate")
	public ResponseEntity<?> getAuthentication(@RequestBody AuthRequest request) throws InvalidCredentialException {
		try {

			manager.authenticate(new UsernamePasswordAuthenticationToken(request.getName(), request.getPassword()));
			UserDetails userDetails = detailsService.loadUserByUsername(request.getName());
			String generateToken = jwtUtil.generateToken(userDetails);
			return new ResponseEntity<AuthenticationResponse>(
					new AuthenticationResponse().builder().jwt(generateToken).message("Logged In Successfully").build(),
					HttpStatus.OK);

		} catch (Exception e) {

			throw new InvalidCredentialException("Invalid credential");
		}

	}

}
