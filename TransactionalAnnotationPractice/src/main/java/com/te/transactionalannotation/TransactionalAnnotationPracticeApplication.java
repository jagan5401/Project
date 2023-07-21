package com.te.transactionalannotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class TransactionalAnnotationPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionalAnnotationPracticeApplication.class, args);
	}

}
