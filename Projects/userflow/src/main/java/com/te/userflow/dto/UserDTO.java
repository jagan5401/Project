package com.te.userflow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	private String name;
	private String password;
	private String phoneNo;
	private Integer age;
	private String email;
	private String gender;

}
