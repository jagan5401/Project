package com.te.transactionalannotation.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.te.transactionalannotation.BookingInfo;
import com.te.transactionalannotation.PaymentInfo;
import com.te.transactionalannotation.dto.RequestDTO;
import com.te.transactionalannotation.dto.ResponseDTO;
import com.te.transactionalannotation.service.ServiceClass;

@RestController
public class Controller {

	@Autowired
	private ServiceClass serviceClass;

	
	@PostMapping("/post")
	public ResponseEntity<ResponseDTO> check(@RequestBody RequestDTO requestDTO) {
		PaymentInfo booking = serviceClass.booking(requestDTO);
		if (booking != null) {
			return new ResponseEntity<ResponseDTO>(
					ResponseDTO.builder().status("success").pnrNo(UUID.randomUUID().toString().split("-")[0])
							.fare(requestDTO.getFare()).build(),
					HttpStatus.OK);
		} else {

			return new ResponseEntity<ResponseDTO>(
					ResponseDTO.builder().status("failed")
							.fare(requestDTO.getFare()).build(),
					HttpStatus.BAD_REQUEST);
		}

	}

}
