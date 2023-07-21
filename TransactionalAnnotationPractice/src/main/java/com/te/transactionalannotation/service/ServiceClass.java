package com.te.transactionalannotation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.transactionalannotation.BookingInfo;
import com.te.transactionalannotation.PaymentInfo;
import com.te.transactionalannotation.dto.RequestDTO;
import com.te.transactionalannotation.repo.BookingRepo;
import com.te.transactionalannotation.repo.PaymentRepo;
import com.te.transactionalannotation.util.BookingUtil;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServiceClass {

	@Autowired
	private BookingRepo bookingRepo;

	@Autowired
	private PaymentRepo paymentRepo;

//	@Autowired
//	private BookingUtil util;

	public PaymentInfo booking(RequestDTO requestDTO) {
		PaymentInfo paymentInfo = new PaymentInfo();
//		paymentInfo.setPayId(requestDTO.getPaymentInfo().getPayId());
		paymentInfo.setAccountNo(requestDTO.getAccountNo());
		paymentInfo.setFare(requestDTO.getFare());

		BookingInfo bookingInfo = new BookingInfo();
//		bookingInfo.setId(requestDTO.getPaymentInfo().getBookingInfo().getId());
		bookingInfo.setName(requestDTO.getBookingInfo().getName());
		bookingInfo.setEmailId(requestDTO.getBookingInfo().getEmailId());

		paymentRepo.save(paymentInfo);
		paymentInfo.setBookingInfo(bookingInfo);
		Boolean checking = BookingUtil.checking(requestDTO.getAccountNo(),
				requestDTO.getFare());

		
//			bookingRepo.save(bookingInfo);
			return paymentInfo;
		
	}
}
