package com.summary.voxplore.handler;



public class IfElseHandler extends ExecuteHandler{
	
	 

	public IfElseHandler() {
		
	}

	@Override
	public void execute() {
	System.out.println("Its IF Else Handler "+name);	
	}
	

	public boolean evaluateCondition(String expr) {
		
		try {
    	    Object result=ExecuteHandler.cx.evaluateString(scope, expr, "<cmd>", 1, null);
    	    return cx.toBoolean(result);

    	} catch(Exception  ex)
    	{
    		System.out.println(" in evaluating script "+ex);
    	}
	
		return false;
	}
	
}
