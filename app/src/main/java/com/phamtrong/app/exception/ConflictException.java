package com.phamtrong.app.exception;

import com.phamtrong.app.dto.MessageDto;

public class ConflictException extends BusinessException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConflictException(MessageDto _payload) {
		super(_payload);
	}
	
}