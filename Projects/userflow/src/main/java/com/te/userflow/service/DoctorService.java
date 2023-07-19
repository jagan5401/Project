package com.te.userflow.service;

import java.util.List;

import com.te.userflow.dto.DoctorDetailsDTO;
import com.te.userflow.entity.DoctorDetails;

public interface DoctorService {

	public DoctorDetails addDoctor(DoctorDetailsDTO dto);

	public DoctorDetails findByName(String name);

}
