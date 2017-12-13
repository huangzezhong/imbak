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
 * 处理文件工具
 * @author hzz
 *
 */
public class FileUtil {

	/**
	 * 添加文本域集合
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
	 * 根据文件路径获取文本内容路径
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
			throw new Exception("未找到指定目录文件："+path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据路径查找文件
	 * @param sourcePath 源路径
	 * @param fileName 文件名
	 */
	public static String findFile(String sourcePath,String fileName){
		File file = new File(sourcePath);
		String absolutePath = ""; 
		//判断是否目录
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
	 * 生成文件
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
	 * 拷贝文件
	 * @param sourceFilePath 源文件路径
	 * @param sourcePath 源路径
	 * @param goalPath 目标路径
	 */
	public static void copyFile(String sourceFilePath,String sourcePath,String goalPath){
		//项目路径
		sourcePath = sourcePath.substring(sourcePath.lastIndexOf("\\")+1, sourcePath.length());
		//文件夹名称
		String floderName = sourceFilePath.substring(sourceFilePath.indexOf(sourcePath)+sourcePath.length()+1, sourceFilePath.lastIndexOf("\\"));
		//文件名称
		String fileName = sourceFilePath.substring(sourceFilePath.lastIndexOf("\\"), sourceFilePath.length());
		//创建文件夹
		String newFloderPath = createFloder(floderName, goalPath);
		try {
			//设置输入流读取文件
			InputStream is = new FileInputStream(new File(sourceFilePath));
			//设置输出流生成文件路径
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
	 * 创建文件夹
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
