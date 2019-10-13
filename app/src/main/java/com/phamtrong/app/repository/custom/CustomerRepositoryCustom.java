package com.phamtrong.app.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.phamtrong.app.dto.CustomerDto;
import com.phamtrong.app.entity.Customer;

@Repository
public interface CustomerRepositoryCustom {
	
	Page<Customer> findByConditions(CustomerDto customerDto, Pageable pageable);

}
