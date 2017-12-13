package com.selfc.pacth.control;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

/**
 * ÎÄ±¾Óò
 * @author hzz
 *
 */
public class PatchJTextArea {

	private int x ,y ,width ,height;
	private String title;
	
	public PatchJTextArea(int x, int y, int width, int height, String title) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.title = title;
	}
	
	public JTextArea createJTextArea(JPanel panel){
		JTextArea textarea = new JTextArea(title);
        textarea.setBounds(x, y, width, height);
        textarea.setBorder(new LineBorder(new java.awt.Color(127,157,185), 1, false));
        panel.add(textarea);
        return textarea;
	}
}
