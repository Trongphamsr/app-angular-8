package com.phamtrong.app.exception;

import com.phamtrong.app.dto.MessageDto;

public class EntityNotfoundException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntityNotfoundException(MessageDto _payload) {
		super(_payload);
	}

}
