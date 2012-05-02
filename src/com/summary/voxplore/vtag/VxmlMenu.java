package com.summary.voxplore.vtag;

public class VxmlMenu extends VxmlTagNode{

	public VxmlMenu(String nodeName) {
		this.nodeName=nodeName;

	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("Executing "+this.getNodeName());
	}

}
