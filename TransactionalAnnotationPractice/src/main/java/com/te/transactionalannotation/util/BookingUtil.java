package com.te.transactionalannotation.util;

import java.util.HashMap;
import java.util.Map;

public class BookingUtil {

	public static Map<String, Integer>Paymentmap=new HashMap<>();
	
	static {
		Paymentmap.put("Acc1", 10000);
		Paymentmap.put("Acc2", 12000);
		Paymentmap.put("Acc3", 9000);
		Paymentmap.put("Acc4", 80000);
		Paymentmap.put("Acc5", 12000);

	
	}
	
	public static Boolean checking(String accountNo,Integer fare) {
		Integer integer = Paymentmap.get(accountNo);
		if (fare<=integer) {
			return true;
		}else {
			throw new InSufficientAmountException("Insufficient Amount");
		}
		
		
		
	}
	
}
