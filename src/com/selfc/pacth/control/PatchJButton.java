package com.selfc.pacth.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.selfc.pacth.produce.CreatePatch;

/**
 * 按钮
 * @author hzz
 *
 */
public class PatchJButton{
	
	private int x ,y ,width ,height;
	private String title;
	private JFrame jFrame;
	private JTextArea jTextArea;
	//文件路径
	private Map<String, JTextArea> filePath;
	
	public PatchJButton(int x, int y, int width, int height,String title,
			JFrame jFrame,JTextArea jTextArea
			) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.title = title;
		this.jFrame = jFrame;
		this.jTextArea = jTextArea;
	}
	
	/**
	 * 创建按钮
	 * @return
	 */
	public JButton createButton(){
		JButton jb = new JButton();
		jb.setText(title);
        jb.setBounds(x, y, width, height);
        return jb;
	}
	/**
	 * 创建文件按钮
	 */
	public void createFileButton(String actionCommand){
		try {
			JButton jb = createButton();
			jb.setActionCommand(actionCommand);
	        //添加监听事件
	        jb.addActionListener(new ActionListener(){
	            @Override
	            public void actionPerformed(ActionEvent arg) {
            		//打开文件
            		JFileChooser jf = new JFileChooser(); 
            		if (!"C".equals(arg.getActionCommand())) {
            			//只能选择目录
                		jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	            	}
	                //显示打开的文件对话框  
	                jf.showOpenDialog(jFrame);
	                //使用文件类获取选择器选择的文件  
	                File file =  jf.getSelectedFile();
	                //返回绝对路径
	                String absolutePath = file.getAbsolutePath();
	                //按钮点击事件，做相应处理
	                jTextArea.setText(absolutePath);
	            }           
	        });
	        jFrame.add(jb);
		} catch (Exception e) {
		}
	}
	
	/**
	 * 创建打包按钮
	 * @return
	 */
	public void createPackageButton(Map<String, JTextArea> filePath){
		JButton jb = createButton();
		this.filePath = filePath;
		//添加监听事件
        jb.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg) {
            	String msg = packageing();
            	//JOptionPane弹出对话框类
            	JOptionPane.showMessageDialog(jFrame, msg, "标题",JOptionPane.WARNING_MESSAGE); 
            }           
        });
		jFrame.add(jb);
	}
	
	/**
	 * 初始化打包
	 */
	public String packageing(){
		CreatePatch createPatch = new CreatePatch(filePath);
		String msg = createPatch.initPackageing();
		return msg;
	}
}
