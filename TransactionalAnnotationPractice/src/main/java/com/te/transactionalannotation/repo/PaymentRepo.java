package com.te.transactionalannotation.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.transactionalannotation.BookingInfo;
import com.te.transactionalannotation.PaymentInfo;
@Repository
public interface PaymentRepo extends JpaRepository<PaymentInfo, Integer> {

}
