package com.computerdatabase.classes;

public class Log {
	private String message;
	private int gravity;
	
	public Log() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Log(String message, int gravity) {
		super();
		this.message = message;
		this.gravity = gravity;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getGravity() {
		return gravity;
	}
	public void setGravity(int gravity) {
		this.gravity = gravity;
	}
}
