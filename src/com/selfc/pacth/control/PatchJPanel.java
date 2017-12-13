package com.selfc.pacth.control;

import javax.swing.JPanel;

public class PatchJPanel {

	private int x ,y ,width ,height;

	public PatchJPanel(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public JPanel createJPanel(){
		JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(x, y, width, height);
		return panel;
	}
}
