package com.te.userflow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorDetailsDTO {

	private String name;
	private String password;
	private String phoneNo;
	private String specialistIn;




}
