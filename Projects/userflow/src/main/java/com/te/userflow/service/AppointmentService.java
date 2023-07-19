package com.te.userflow.service;

import java.util.List;

import com.te.userflow.dto.AppointmentDTO;
import com.te.userflow.entity.Appointment;
import com.te.userflow.entity.DoctorDetails;
import com.te.userflow.exception.AppointmentInvalidException;
import com.te.userflow.security.AuthRequest;

public interface AppointmentService {

	Appointment addAppointment(AppointmentDTO dto, Integer userId, Integer doctorId) throws AppointmentInvalidException;

	List<Appointment> getAllAppointment();

	Appointment getAppointment(Integer appointmentId);

}
