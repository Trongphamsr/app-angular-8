package com.phamtrong.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageEnum {
	
	
	/** Required data */
    REQUIRED("1000", "error.required", "The %s was null or empty."),
    
    /** Not valid data */
    NOT_VALID("1001", "error.not.valid", ""),
    
    /** Not exist data */
    NOT_EXIST("1039", "error.existdata", "%s could not found or in-actived. Id: %s."),

    /** Not exist data */
    REQUEST_TIMEOUT("1503", "error.request.timeout", "Can't send a message to %s"),

    /** Duplicated data */
    DUPLICATE("1031", "error.duplicate_code", "%s was used by another %s."),

    /** Duplicated data */
    DUPLICATE_STATUS("1040", "error.duplicate_status", "Id: %s."),

    /** Duplicated data */
    NOT_DELETED_YET("1041", "error.status_not_deleted", "Id: %s."),

    /** Duplicated data */
    PASSWORD_INCORRECT("1032", "error.change_password.incorrect", null),

    /** Success change password*/
    SUCCESS_CHANGE_PW("2004", "message.change_password.success", null),

    /** Use by another*/
    IN_USE("1044", "error.in_used", "Id: %s."),

    /** Success SAVE data*/
    SUCCESS_EDIT("2000", "success.response.edit", null),

    /** Success INSERT data*/
    SUCCESS_ADD("2001", "success.response.accepted", null),

    /** Success INSERT data*/
    SUCCESS_ADDS("2001", "success.response.mutilaccepted", null),
    
    /** Success PHYSICAL DELETE data*/
    SUCCESS_DELETE("2002", "success.response.nocontent", null),
    
    /** Success PHYSICAL DELETE data*/
    SUCCESS_UNDELETE("2004", "success.response.undelete", null),

    /** Success reset password */
    SUCESS_RESET_PW("2003", "success.response.reset.password", null),
    
    /** Success INSERT data*/
    SUCCESS_IMPORT_FILE("2005", "success.response.imported", null),
    
    /** Success INSERT data*/
    SUCCESS_SEARCH_FILE("2006", "success.response.search", null),
    ;

	private MessageEnum(String code, String key, String cause) {
		this.code = code;
		this.key = key;
		this.cause = cause;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public String getCode() {
		return code;
	}

	private final String code;
    private String key;
    private String cause;
  
}

