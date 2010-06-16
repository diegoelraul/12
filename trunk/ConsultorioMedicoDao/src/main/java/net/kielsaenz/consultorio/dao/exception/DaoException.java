package net.kielsaenz.consultorio.dao.exception;

import java.util.Locale;
import java.util.ResourceBundle;

public class DaoException extends Exception{
	
	private String key;
	
	public DaoException(String key){
		super(ResourceBundle.getBundle("net.kielsaenz.consultorio.service.exception.msg", Locale.getDefault()).getString(key));
		this.key = key;
	}
	
	public DaoException(String key, Locale locale){
		super(ResourceBundle.getBundle("net.kielsaenz.consultorio.service.exception.msg", locale).getString(key));
		this.key = key;
	}

	public DaoException(int hashCode, String message){
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
