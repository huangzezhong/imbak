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
 * ��ť
 * @author hzz
 *
 */
public class PatchJButton{
	
	private int x ,y ,width ,height;
	private String title;
	private JFrame jFrame;
	private JTextArea jTextArea;
	//�ļ�·��
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
	 * ������ť
	 * @return
	 */
	public JButton createButton(){
		JButton jb = new JButton();
		jb.setText(title);
        jb.setBounds(x, y, width, height);
        return jb;
	}
	/**
	 * �����ļ���ť
	 */
	public void createFileButton(String actionCommand){
		try {
			JButton jb = createButton();
			jb.setActionCommand(actionCommand);
	        //��Ӽ����¼�
	        jb.addActionListener(new ActionListener(){
	            @Override
	            public void actionPerformed(ActionEvent arg) {
            		//���ļ�
            		JFileChooser jf = new JFileChooser(); 
            		if (!"C".equals(arg.getActionCommand())) {
            			//ֻ��ѡ��Ŀ¼
                		jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	            	}
	                //��ʾ�򿪵��ļ��Ի���  
	                jf.showOpenDialog(jFrame);
	                //ʹ���ļ����ȡѡ����ѡ����ļ�  
	                File file =  jf.getSelectedFile();
	                //���ؾ���·��
	                String absolutePath = file.getAbsolutePath();
	                //��ť����¼�������Ӧ����
	                jTextArea.setText(absolutePath);
	            }           
	        });
	        jFrame.add(jb);
		} catch (Exception e) {
		}
	}
	
	/**
	 * ���������ť
	 * @return
	 */
	public void createPackageButton(Map<String, JTextArea> filePath){
		JButton jb = createButton();
		this.filePath = filePath;
		//��Ӽ����¼�
        jb.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg) {
            	String msg = packageing();
            	//JOptionPane�����Ի�����
            	JOptionPane.showMessageDialog(jFrame, msg, "����",JOptionPane.WARNING_MESSAGE); 
            }           
        });
		jFrame.add(jb);
	}
	
	/**
	 * ��ʼ�����
	 */
	public String packageing(){
		CreatePatch createPatch = new CreatePatch(filePath);
		String msg = createPatch.initPackageing();
		return msg;
	}
}
