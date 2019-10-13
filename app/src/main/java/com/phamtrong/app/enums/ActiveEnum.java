package com.phamtrong.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ActiveEnum{
	
	
	IN_ACTIVE("0", "In-Active", "false"), 
	ACTIVE("1", "Active", "true");
	
	private ActiveEnum(String value, String display, String name) {
		this.value = value;
		this.display = display;
		this.name = name;
	}
	
	private final String value;
    private final String display;
    private final String name;
    
	public String getValue() {
		return value;
	}
	public String getDisplay() {
		return display;
	}
	public String getName() {
		return name;
	}
    
    
    
    
    
    
	
}
