package com.te.userflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.userflow.dto.DoctorDetailsDTO;
import com.te.userflow.entity.DoctorDetails;
import com.te.userflow.service.DoctorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/Doctor")
public class DoctorController {


	@Autowired
	private DoctorService doctorService;

	//register the doctor
	@PostMapping("/add")
	@PreAuthorize("hasRole('DOCTOR')")
	@Operation(security = { @SecurityRequirement(name = "JWT Authentication")})//swagger
	public ResponseEntity<?> RegistrationTheDoctor(@RequestBody DoctorDetailsDTO dto) {
		DoctorDetails addDoctor = doctorService.addDoctor(dto);

		if (addDoctor != null) {
			return new ResponseEntity<String>("Doctor Registration successfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("failed", HttpStatus.BAD_REQUEST);
		}

	}


//fetch a perticular doctor
	@GetMapping("/get/{doctorname}")
	@PreAuthorize("hasRole('USER')")
	@Operation(security = { @SecurityRequirement(name = "JWT Authentication")})//swagger
	public ResponseEntity<?> getDoctorDetails(@PathVariable String doctorname) {
		DoctorDetails doctor = doctorService.findByName(doctorname);
		if (doctor != null) {
			return new ResponseEntity<>(doctor, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("failed", HttpStatus.BAD_REQUEST);

		}

	}


}
