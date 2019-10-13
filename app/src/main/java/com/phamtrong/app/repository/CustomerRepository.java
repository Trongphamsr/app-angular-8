package com.phamtrong.app.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.phamtrong.app.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	@Query("SELECT CASE WHEN COUNT(h) > 0 THEN true ELSE false END FROM Customer h WHERE h.customerDate = :customerDate")
	boolean existsByCustomerDate(Date customerDate);
	
	
	List<Customer> findByCustomerDateIn(Collection<Date> customerDate);
	
//	@Query("SELECT c FROM Customer c WHERE c.name LIKE %:name%")
//	List<Customer> findByName(String name);
	
	Page<Customer> findByName(Pageable pageable, String name);
	 
//	 List<Employee> findByNameContaining(String q);
}
