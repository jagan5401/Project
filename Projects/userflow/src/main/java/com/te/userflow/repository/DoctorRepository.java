package com.te.userflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.userflow.entity.DoctorDetails;
@Repository
public interface DoctorRepository extends JpaRepository<DoctorDetails, Integer>{

	DoctorDetails findByName(String username);



}
