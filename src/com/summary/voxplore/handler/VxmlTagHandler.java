package com.summary.voxplore.handler;

public class VxmlTagHandler{
	
	public static Handler getTagHandler(String nodename)
		throws HandlerException{
		
		if(nodename.equals("if"))
			return new IfElseHandler();
		else if(nodename.equals("foreach"))
			return new ForEachHandler();
		else 
			throw new HandlerException(nodename);
		
	}




	public static void main(String args[])
	{
		
		try {
			IfElseHandler hh = (IfElseHandler)VxmlTagHandler.getTagHandler("if");
			hh.execute();	
			
		    ForEachHandler ff = (ForEachHandler)VxmlTagHandler.getTagHandler("foreach");
			ff.execute();
			
			
			hh.execute();
		
			System.out.println(hh.name == ff.name);
		} catch (HandlerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}