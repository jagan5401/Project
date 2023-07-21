package com.te.transactionalannotation.dto;

import com.te.transactionalannotation.BookingInfo;
import com.te.transactionalannotation.PaymentInfo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {

//	private BookingInfo bookingInfo;
//	private PaymentInfo paymentInfo;
	
	private Integer payId;
	private String accountNo;
	private Integer fare;
	private BookingInfo bookingInfo;
}
