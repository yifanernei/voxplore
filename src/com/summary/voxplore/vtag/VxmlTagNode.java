package com.summary.voxplore.vtag;

import org.w3c.dom.Node;

import com.summary.voxplore.vbase.VxmlObject;

public  class VxmlTagNode {

	String nodeName;
	/*public VxmlTagNode(String nodeName)
	{
		this.nodeName=nodeName;
	};*/
	public void execute(){};
	
	public void execute(VxmlObject obj, Node node){};
	

	
	
	public String getNodeName() {
		return nodeName;
	}
	public void finalize() {
       this.nodeName=null;
      }
}
