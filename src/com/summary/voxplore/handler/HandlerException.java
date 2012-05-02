package com.summary.voxplore.handler;

public class HandlerException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HandlerException(String tagname) {

		System.out.println("No Handler Defined for the tag "+tagname);
	}
	

}
