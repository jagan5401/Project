package com.te.userflow.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.te.userflow.entity.DoctorDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDTO {

	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	private LocalTime time;

	//private DoctorDetails doctorDetails;


}
