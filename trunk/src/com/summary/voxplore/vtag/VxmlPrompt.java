package com.summary.voxplore.vtag;

import org.w3c.dom.Node;

import com.summary.voxplore.vbase.VxmlObject;

public class VxmlPrompt  extends VxmlTagNode implements VxmlVoiceImpl{


	public VxmlPrompt(String nodeName) {
		this.nodeName=nodeName;

	}
	
	@Override
	public void execute(VxmlObject obj, Node node) {
		// TODO Auto-generated method stub
		System.out.println("Executing "+this.getNodeName());
		printVxmlVoice(node.getTextContent());
	}

	public void printVxmlVoice(String str) {
		// TODO Auto-generated method stub
		System.out.println("<<speak>>"+ str +"<<speak>>");
	}
}
