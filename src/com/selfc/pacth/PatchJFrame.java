package com.selfc.pacth;

import java.awt.Container;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.selfc.pacth.control.PatchJButton;
import com.selfc.pacth.control.PatchJLabel;
import com.selfc.pacth.control.PatchJPanel;
import com.selfc.pacth.control.PatchJTextArea;
import com.selfc.pacth.util.FileUtil;

/**
 * ����
 * @author hzz
 *
 */
public class PatchJFrame extends JFrame {
	
	private static final String TITLE = "�������ݳ���-hzz-v1.0";

    public static void main(String[] args) {  
        //�Զ����ɵķ������  
        new PatchJFrame();  
    }  
    
    public PatchJFrame(){  
        this.setLayout(null);
        
        //��ʼ��һ��panel
        JPanel panel = new PatchJPanel(0, 0, 480, 200).createJPanel();
        //��ʼ��һ������
        Container container = this.getContentPane();
        //���ò���
        container.setLayout(null);
        //���ñ�ǩ���ı���
        new PatchJLabel(20, 20, 60, 20,"Դ·����").createJLabel(panel);
        JTextArea fileJTextAreaA = 
        		new PatchJTextArea(85, 20, 440, 20, "F:\\OSC\\osc_order1110").createJTextArea(panel);
        
        new PatchJLabel(20, 50, 70, 20,"Ŀ��·����").createJLabel(panel);
        JTextArea fileJTextAreaB = 
        		new PatchJTextArea(85, 50, 440, 20, "C:\\Users\\sony\\Desktop\\package").createJTextArea(panel);
        
        new PatchJLabel(20, 80, 70, 20,"����·����").createJLabel(panel);
        JTextArea fileJTextAreaC = 
        		new PatchJTextArea(85, 80, 440, 20, "C:\\Users\\sony\\Desktop\\package\\path.txt").createJTextArea(panel);
        //��panel��ӵ�����
        container.add(panel);
        
        //���ð�ť
        new PatchJButton(500,20,60,20,"ѡ��",this,fileJTextAreaA).createFileButton("A");
        new PatchJButton(500,50,60,20,"ѡ��",this,fileJTextAreaB).createFileButton("B");
        new PatchJButton(500,80,60,20,"ѡ��",this,fileJTextAreaC).createFileButton("C");
        
        Map<String, JTextArea> map = FileUtil
        		.getFilePath(fileJTextAreaA, fileJTextAreaB, fileJTextAreaC);
        new PatchJButton(250,200,70,30,"���",this,null).createPackageButton(map);
        
        this.setTitle(TITLE);  
        this.setSize(600, 300);  
        this.setLocation(200,200);  
        //��ʾ����true  
        this.setVisible(true);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
    }  
    
}
