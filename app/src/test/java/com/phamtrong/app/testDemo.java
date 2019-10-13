package com.phamtrong.app;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.phamtrong.app.dto.CustomerDto;
import com.phamtrong.app.repository.CustomerRepository;
import com.phamtrong.app.service.CustomerService;

class testDemo {

	@InjectMocks
	CustomerService customerService;
	
	@Mock
	CustomerRepository customerRepository;
	
	@BeforeEach
	void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test() {
		
		CustomerDto customerDto = new CustomerDto();
		Date date = new Date();
		assertNull(date);
		customerDto.setCustomerDate(date);
		when(customerRepository.existsByCustomerDate(new Date())).thenReturn(true);	
		CustomerDto customerDto2 = new CustomerDto(new Date());
		assertNull(customerDto2);
		assertEquals(new Date(), customerDto2.getCustomerDate());
	}

}
