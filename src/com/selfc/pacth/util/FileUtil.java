package com.selfc.pacth.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTextArea;

/**
 * �����ļ�����
 * @author hzz
 *
 */
public class FileUtil {

	/**
	 * ����ı��򼯺�
	 * @param fileJTextAreaA
	 * @param fileJTextAreaB
	 * @param fileJTextAreaC
	 * @return
	 */
	public static Map<String,JTextArea> getFilePath(JTextArea fileJTextAreaA
			,JTextArea fileJTextAreaB,JTextArea fileJTextAreaC){
		Map<String,JTextArea> map = new HashMap<String,JTextArea>();
		map.put("fileJTextAreaA", fileJTextAreaA);
		map.put("fileJTextAreaB", fileJTextAreaB);
		map.put("fileJTextAreaC", fileJTextAreaC);
		return map;
	}
	
	/**
	 * �����ļ�·����ȡ�ı�����·��
	 * @param path
	 * @return
	 * @throws Exception 
	 */
	public static List<String> getFileByPath(String path) throws Exception{
		File file = new File(path);
		try {
			BufferedReader bfr = new BufferedReader(new FileReader(file));
			String str = "";
			List<String> list = new ArrayList<String>();
			while (null != (str = bfr.readLine())) {
				if (!str.equals("")) {
					list.add(str);
				}
			}
			bfr.close();
			return list;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new Exception("δ�ҵ�ָ��Ŀ¼�ļ���"+path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ����·�������ļ�
	 * @param sourcePath Դ·��
	 * @param fileName �ļ���
	 */
	public static String findFile(String sourcePath,String fileName){
		File file = new File(sourcePath);
		String absolutePath = ""; 
		//�ж��Ƿ�Ŀ¼
		if (file.isDirectory()) {
			File[] fileList = file.listFiles();
			for (File f:fileList) {
				if (f.isFile() && f.getName().equals(fileName)) {
					return f.getAbsolutePath().replaceAll("/", "\\\\");	
				} else if (absolutePath == ""){
					absolutePath = findFile(f.getAbsolutePath(), fileName);
				}
			}
			
		}
		return absolutePath;
	}
	
	/**
	 * �����ļ�
	 * @param goalPath
	 * @param list
	 * @param fileName
	 */
	public static void generatedFile(String goalPath,List<String> list,String fileName){
		try {
			if (list.size() > 0) {
				BufferedWriter bfw = new BufferedWriter(new FileWriter(new File(goalPath+File.separator+fileName))); 
				char[] b = new char[1024];
				for (String path:list) {
					b = path.toCharArray();
					bfw.write(b);
					bfw.write("\r\n");
				}
				bfw.flush();
				bfw.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �����ļ�
	 * @param sourceFilePath Դ�ļ�·��
	 * @param sourcePath Դ·��
	 * @param goalPath Ŀ��·��
	 */
	public static void copyFile(String sourceFilePath,String sourcePath,String goalPath){
		//��Ŀ·��
		sourcePath = sourcePath.substring(sourcePath.lastIndexOf("\\")+1, sourcePath.length());
		//�ļ�������
		String floderName = sourceFilePath.substring(sourceFilePath.indexOf(sourcePath)+sourcePath.length()+1, sourceFilePath.lastIndexOf("\\"));
		//�ļ�����
		String fileName = sourceFilePath.substring(sourceFilePath.lastIndexOf("\\"), sourceFilePath.length());
		//�����ļ���
		String newFloderPath = createFloder(floderName, goalPath);
		try {
			//������������ȡ�ļ�
			InputStream is = new FileInputStream(new File(sourceFilePath));
			//��������������ļ�·��
			OutputStream out = new FileOutputStream(new File(newFloderPath+File.separator+fileName));
			int len = 0;
			byte[] b = new byte[1024];
			while ( (len = is.read(b)) > 0 ) {
				out.write(b,0,len);
			}
			out.flush();
			out.close();
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �����ļ���
	 * @param floderName
	 * @param goalPath
	 */
	public static String createFloder(String floderName,String goalPath){
		String newFloderPath = goalPath+File.separator+floderName;
		File file = new File(newFloderPath);
		if (!file.isDirectory()) {
			file.mkdirs();
		}
		return newFloderPath;
	}
	
	public static void main(String args[]){
		/*copyFile("C:\\Users\\sony\\Desktop\\log\\test.class",
				"C:\\Users\\sony\\Desktop", "C:\\Users\\sony\\Desktop\\log\\11");*/
	}
	
}
