package com.te.userflow.controller;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.te.userflow.dto.AppointmentDTO;
import com.te.userflow.entity.Appointment;
import com.te.userflow.exception.AppointmentInvalidException;
import com.te.userflow.service.AppointmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/Appointment")
@PreAuthorize("hasRole('USER')")
public class AppointmentController {

	
	@Autowired
	private AppointmentService service;

	private static Logger logger = LoggerFactory.getLogger(AppointmentController.class);

	// register the Appointment
	@PostMapping("/add/{userId}/{doctorId}")
	@Operation(security = { @SecurityRequirement(name = "JWT Authentication")})//swagger
	public ResponseEntity<?> RegistrationTheDoctor(@RequestBody AppointmentDTO dto, @PathVariable Integer userId,
			@PathVariable Integer doctorId) throws ParseException, AppointmentInvalidException {
		Appointment addAppointment = service.addAppointment(dto, userId, doctorId);

		if (addAppointment != null) {
			return new ResponseEntity<String>("Appointment added success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("failed", HttpStatus.BAD_REQUEST);
		}

	}

	// fetch All appointment
	@GetMapping("/getAll")
	@PreAuthorize("hasRole('USER')")
	@Operation(security = { @SecurityRequirement(name = "JWT Authentication")})//swagger
	public ResponseEntity<?> getDoctorDetails() {
		List<Appointment> allAppointment = service.getAllAppointment();
		
		logger.warn("getDoctorDetails from controller");
		logger.info("This is sample info message");
		logger.warn("This is sample warn message");
		logger.error("This is sample error message");
		logger.debug("This is sample debug message");
		if (allAppointment != null) {
			return new ResponseEntity<>(allAppointment, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("failed", HttpStatus.BAD_REQUEST);

		}

	}

	@GetMapping("/get/{appointmentId}")
	@Operation(security = { @SecurityRequirement(name = "JWT Authentication")})//swagger
	public ResponseEntity<?> getAppointment(@PathVariable Integer appointmentId) {
		Appointment appointment = service.getAppointment(appointmentId);
		logger.warn("getAppointment from controller");
		if (appointment != null) {
			return new ResponseEntity<>(appointment, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("failed", HttpStatus.BAD_REQUEST);

		}

	}

}
