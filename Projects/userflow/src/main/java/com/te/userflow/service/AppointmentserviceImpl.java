package com.te.userflow.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.userflow.dto.AppointmentDTO;
import com.te.userflow.entity.Appointment;
import com.te.userflow.entity.DoctorDetails;
import com.te.userflow.entity.User;
import com.te.userflow.exception.AppointmentInvalidException;
import com.te.userflow.exception.AppointmentNotFoundException;
import com.te.userflow.repository.AppointmentRepository;
import com.te.userflow.repository.DoctorRepository;
import com.te.userflow.repository.UserRepository;
import com.te.userflow.security.AuthRequest;

@Service
public class AppointmentserviceImpl implements AppointmentService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public Appointment addAppointment(AppointmentDTO dto, Integer userId, Integer doctorId)
			throws AppointmentInvalidException {

		Appointment appointment = new Appointment();

		DoctorDetails doctor = doctorRepository.findById(doctorId).get();
		User user = userRepository.findById(userId).get();

		List<Appointment> appointmentDate = appointmentRepository.findByDate(dto.getDate());
		List<Appointment> tempList = new ArrayList<>();
		for (Appointment appointmentDate1 : appointmentDate) {

			if (doctor != null && (appointmentDate1.getTime().equals(dto.getTime())
					&& appointmentDate1.getDate().equals(dto.getDate()))&&appointmentDate1.getDoctorDetails().getDoctorId()==doctorId) {
				tempList.add(appointmentDate1);
			}
		}

		if (tempList.isEmpty()) {
			appointment.setDate(dto.getDate());
			appointment.setTime(dto.getTime());
			appointment.setDoctorDetails(doctor);
			appointment.setUser(user);
			return appointmentRepository.save(appointment);
		} else {
			throw new AppointmentInvalidException("  Already had a appointment book different one");
		}

	}

	@Override
	public List<Appointment> getAllAppointment() {

		List<Appointment> findAll = appointmentRepository.findAll();
		List<Appointment> sortingDate = new ArrayList<>();
		for (Appointment appointment : findAll) {
			if (appointment.getDate().equals(LocalDate.now())
					|| appointment.getDate().equals(LocalDate.now().plusDays(1))) {
				sortingDate.add(appointment);
			}
		}
		sortingDate.sort(Comparator.comparing(Appointment::getDate).thenComparing(Appointment::getTime));
		return sortingDate;
	}

	@Override
	public Appointment getAppointment(Integer appointmentId) {
		Appointment findById = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new AppointmentNotFoundException("Appointment empty"));
		return findById;

	}

}
