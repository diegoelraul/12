package net.kielsaenz.consultorio.service.exception;

import java.util.Locale;
import java.util.ResourceBundle;

public class ServiceException extends Exception{
	
	private String key;
	
	public ServiceException(String key){
		super(ResourceBundle.getBundle("msgService", Locale.getDefault()).getString(key));
		this.key = key;
	}
	
	public ServiceException(String key, Locale locale){
		super(ResourceBundle.getBundle("msgService", locale).getString(key));
		this.key = key;
	}
	
	public ServiceException(int hashCode, String message){
		super(message);
		this.key = hashCode + "";
	}
	
	@Override
	public String toString() {
		return new StringBuffer().append(key).append(": ").append(getMessage()).toString();
	}
	
	public String getKey() {
		return key;
	}
}
