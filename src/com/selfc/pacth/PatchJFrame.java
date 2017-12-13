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
 * 窗口
 * @author hzz
 *
 */
public class PatchJFrame extends JFrame {
	
	private static final String TITLE = "增量备份程序-hzz-v1.0";

    public static void main(String[] args) {  
        //自动生成的方法存根  
        new PatchJFrame();  
    }  
    
    public PatchJFrame(){  
        this.setLayout(null);
        
        //初始化一个panel
        JPanel panel = new PatchJPanel(0, 0, 480, 200).createJPanel();
        //初始化一个容器
        Container container = this.getContentPane();
        //设置布局
        container.setLayout(null);
        //设置标签、文本域
        new PatchJLabel(20, 20, 60, 20,"源路径：").createJLabel(panel);
        JTextArea fileJTextAreaA = 
        		new PatchJTextArea(85, 20, 440, 20, "F:\\OSC\\osc_order1110").createJTextArea(panel);
        
        new PatchJLabel(20, 50, 70, 20,"目标路径：").createJLabel(panel);
        JTextArea fileJTextAreaB = 
        		new PatchJTextArea(85, 50, 440, 20, "C:\\Users\\sony\\Desktop\\package").createJTextArea(panel);
        
        new PatchJLabel(20, 80, 70, 20,"补丁路径：").createJLabel(panel);
        JTextArea fileJTextAreaC = 
        		new PatchJTextArea(85, 80, 440, 20, "C:\\Users\\sony\\Desktop\\package\\path.txt").createJTextArea(panel);
        //把panel添加到容器
        container.add(panel);
        
        //设置按钮
        new PatchJButton(500,20,60,20,"选择",this,fileJTextAreaA).createFileButton("A");
        new PatchJButton(500,50,60,20,"选择",this,fileJTextAreaB).createFileButton("B");
        new PatchJButton(500,80,60,20,"选择",this,fileJTextAreaC).createFileButton("C");
        
        Map<String, JTextArea> map = FileUtil
        		.getFilePath(fileJTextAreaA, fileJTextAreaB, fileJTextAreaC);
        new PatchJButton(250,200,70,30,"打包",this,null).createPackageButton(map);
        
        this.setTitle(TITLE);  
        this.setSize(600, 300);  
        this.setLocation(200,200);  
        //显示窗口true  
        this.setVisible(true);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
    }  
    
}
