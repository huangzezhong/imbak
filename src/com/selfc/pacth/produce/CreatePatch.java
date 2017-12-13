package com.selfc.pacth.produce;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JTextArea;

import com.selfc.pacth.util.FileUtil;

/**
 * 打包
 * @author hzz
 *
 */
public class CreatePatch {

	//文件路径
	private Map<String, JTextArea> filePath;
	
	private static final String SUFFIX_JAVA_NAME = ".java"; 
	
	private static final String SUFFIX_CLASS_NAME = ".class"; 

	public CreatePatch(Map<String, JTextArea> filePath) {
		super();
		this.filePath = filePath;
	}
	
	/**
	 * 初始化打包
	 * @return
	 */
	public String initPackageing(){
		//源路径
		String sourcePath = filePath.get("fileJTextAreaA").getText();
		//目标路径
		String goalPath = filePath.get("fileJTextAreaB").getText();
		//补丁路径
		String patchPath = filePath.get("fileJTextAreaC").getText();
		//根据文件路径获取文本内容路径
		List<String> pathlist = null;
		try {
			pathlist = FileUtil.getFileByPath(patchPath);
		} catch (Exception e) {
			e.printStackTrace();
			return "打包失败！"+e.getMessage();
		}
		//文件路径生成规则
		List<String> fileNamelist = getFileNameByRule(pathlist, sourcePath);
		//有效的文件路径
		List<String> effectivePathList = new ArrayList<String>();
		//无效的文件路径
		List<String> unEffectivePathList = new ArrayList<String>();
		for (int i=0;i<fileNamelist.size();i++) {
			String fileName = fileNamelist.get(i);
			//获取绝对路径
			String absolutePath = FileUtil.findFile(sourcePath,fileName);
			if (!"".equals(absolutePath)) {
				effectivePathList.add(absolutePath);
			} else {
				unEffectivePathList.add(pathlist.get(i));
			}
		}
		//执行打包
		packageing(sourcePath, goalPath, effectivePathList);
		//生产打包失败的文件清单
		FileUtil.generatedFile(goalPath, unEffectivePathList, "Failures.txt");
		//生产打包成功的文件清单
		FileUtil.generatedFile(goalPath, effectivePathList, "Successful.txt");
		if (effectivePathList.size() > 0) {
			return "打包成功！“"+effectivePathList.size()+"”个文件成功，“"+unEffectivePathList.size()+"”个文件失败！";
		} else {
			return "打包失败！“"+effectivePathList.size()+"”个文件成功，“"+unEffectivePathList.size()+"”个文件失败！";
		}
	}
	
	/**
	 * 执行打包
	 * @param sourcePath
	 * @param goalPath
	 * @param effectivePathList
	 * @return
	 */
	public void packageing(String sourcePath,String goalPath,List<String> effectivePathList){
		for (String effectivePath:effectivePathList) {
			FileUtil.copyFile(effectivePath, sourcePath, goalPath);
		}
	}
	
	/**
	 * 截取文件名
	 * @param pathlist
	 * @param sourcePath
	 * @return
	 */
	public List<String> getFileNameByRule(List<String> pathlist,String sourcePath){
		List<String> list = new ArrayList<String>();
		//获取文件夹名称
		for (String filePath:pathlist) {
			//替换无效路径
			if (filePath.lastIndexOf("/") != -1) {
				filePath = filePath.substring(filePath.lastIndexOf("/")+1, filePath.length());
			}
			//替换.java文件名
			if (filePath.lastIndexOf(SUFFIX_JAVA_NAME) != -1) {
				filePath = filePath.replace(SUFFIX_JAVA_NAME, SUFFIX_CLASS_NAME);
			}
			list.add(filePath);
		}
		return list;
	}
}
