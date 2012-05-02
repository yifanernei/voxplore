package com.summary.voxplore.handler;

public abstract class EventHandler implements Handler{
	
	/*
	 * each class extending it will have to define its own implementation
	 */
	public abstract void execute();

}
