package com.summary.voxplore.vtag;

public class VxmlTagBuilderFactory {
	
	String nodeName;
	

	public VxmlTagBuilderFactory(){}
	
	public static VxmlTagNode getVxmlTag(String tagName)
	{
		
		
		if(tagName.equals("form"))
			return new VxmlForm(tagName);
		if(tagName.equals("block"))
			return new VxmlBlock(tagName);
		if(tagName.equals("menu"))
			return new VxmlMenu(tagName);
		if(tagName.equals("filled"))
			return new VxmlTagNode();
		if(tagName.equals("prompt"))
			return new VxmlPrompt(tagName);
		if(tagName.equals("var"))
			return new VxmlTagNode();
		if(tagName.equals("log"))
			return new VxmlLog();
		if(tagName.equals("value"))
			return new VxmlValue("value");
		if(tagName.equals("assign"))
			return new VxmlAssign("assign");
		if(tagName.equals("if"))
			return new VxmlIfElse("if");
		else
			return new VxmlTagNode();
	}
	
}
