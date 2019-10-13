package com.phamtrong.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

	

	/** The message code */
    private String code;

    /** The message content */
    private String message;

    /** The message cause. Null if not error */
    private String cause;

	public MessageDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MessageDto(String code, String message, String cause) {
		super();
		this.code = code;
		this.message = message;
		this.cause = cause;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}
    
    
    
}
