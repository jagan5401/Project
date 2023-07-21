package com.te.userflow.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.te.userflow.dto.DoctorDetailsDTO;
import com.te.userflow.entity.DoctorDetails;
import com.te.userflow.exception.InvalidCredentialException;
import com.te.userflow.repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService {


	@Autowired
	private BCryptPasswordEncoder  passwordEncoder;
	@Autowired
	private DoctorRepository repository;

	@Override
	public DoctorDetails addDoctor(DoctorDetailsDTO dto) {
		
		DoctorDetails doctor = new DoctorDetails();
//BeanUtils.copyProperties(dto, doctor);
		doctor.setPassword(passwordEncoder.encode(dto.getPassword()));
		doctor.setName(dto.getName());
		doctor.setPhoneNo(dto.getPhoneNo());
		doctor.setSpecialistIn(dto.getSpecialistIn());
		doctor.setRoles("ROLE_DOCTOR");

		return repository.save(doctor);
	}


	@Override
	public DoctorDetails findByName(String name) {

		 DoctorDetails details = repository.findByName(name);
		 if (details!=null) {
			return details;
		}else {
			throw new InvalidCredentialException("Invalid DoctorName");
		}
	}

}
