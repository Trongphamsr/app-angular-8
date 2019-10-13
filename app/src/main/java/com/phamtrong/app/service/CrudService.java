package com.phamtrong.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.phamtrong.app.dto.MessageDto;
import com.phamtrong.app.entity.Email;
import com.phamtrong.app.entity.Response;
import com.phamtrong.app.enums.ActiveEnum;

public interface CrudService<T> {
	
	@Transactional
	Page<T> findAll (Boolean active, Pageable pageable);
	
	@Transactional
	Page<T> findName (Boolean active, Pageable pageable, String s);
	
	@Transactional
	T findOne(int id);
	
	@Transactional
	MessageDto create(T object);
	
	@Transactional
	MessageDto update(T object);
	
	@Transactional
	MessageDto deleteOrUndelete(int id, ActiveEnum activeEnum);
	
	@Transactional
	Page<T> filter(T object, Pageable pageable);
	
	@Transactional
	Response sendEmail(Email mail);
	
}
