package com.selfc.pacth.control;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 标签
 * @author hzz
 *
 */
public class PatchJLabel {

	private int x ,y ,width ,height;
	private String title;
	
	public PatchJLabel(int x, int y, int width, int height, String title) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.title = title;
	}
	
	/**
	 * 创建标签
	 * @param panel
	 */
	public void createJLabel(JPanel panel){
		JLabel emptyLable = new JLabel(title);
		emptyLable.setBounds(x, y, width, height);
		panel.add(emptyLable);
	}
	
}
