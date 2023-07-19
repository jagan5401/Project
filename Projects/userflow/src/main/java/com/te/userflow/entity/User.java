package com.te.userflow.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	@Column(name = "UserName")
	private String name;
	private String password;

	private String phoneNo;

	private Integer age;

	private String email;

	private String gender;
	
	private String roles;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
	private List<Appointment>appointments;
}
