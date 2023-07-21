package com.te.transactionalannotation.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.transactionalannotation.BookingInfo;
@Repository
public interface BookingRepo extends JpaRepository<BookingInfo, Integer> {

}
