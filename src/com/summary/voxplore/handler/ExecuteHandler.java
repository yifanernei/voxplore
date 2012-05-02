package com.summary.voxplore.handler;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public abstract class ExecuteHandler implements Handler{
	
	public static String name;
	public static Context cx ;
	public static Scriptable scope ;

	
	public ExecuteHandler(){
		this.name="Never say Never";
		cx = Context.enter();
		scope = cx.initStandardObjects();
		
	}
	/*
	 * each class extending it will have to define its own implementation
	 */
	public abstract void execute();

}
