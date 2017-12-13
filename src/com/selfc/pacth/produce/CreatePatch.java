package com.selfc.pacth.produce;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JTextArea;

import com.selfc.pacth.util.FileUtil;

/**
 * ���
 * @author hzz
 *
 */
public class CreatePatch {

	//�ļ�·��
	private Map<String, JTextArea> filePath;
	
	private static final String SUFFIX_JAVA_NAME = ".java"; 
	
	private static final String SUFFIX_CLASS_NAME = ".class"; 

	public CreatePatch(Map<String, JTextArea> filePath) {
		super();
		this.filePath = filePath;
	}
	
	/**
	 * ��ʼ�����
	 * @return
	 */
	public String initPackageing(){
		//Դ·��
		String sourcePath = filePath.get("fileJTextAreaA").getText();
		//Ŀ��·��
		String goalPath = filePath.get("fileJTextAreaB").getText();
		//����·��
		String patchPath = filePath.get("fileJTextAreaC").getText();
		//�����ļ�·����ȡ�ı�����·��
		List<String> pathlist = null;
		try {
			pathlist = FileUtil.getFileByPath(patchPath);
		} catch (Exception e) {
			e.printStackTrace();
			return "���ʧ�ܣ�"+e.getMessage();
		}
		//�ļ�·�����ɹ���
		List<String> fileNamelist = getFileNameByRule(pathlist, sourcePath);
		//��Ч���ļ�·��
		List<String> effectivePathList = new ArrayList<String>();
		//��Ч���ļ�·��
		List<String> unEffectivePathList = new ArrayList<String>();
		for (int i=0;i<fileNamelist.size();i++) {
			String fileName = fileNamelist.get(i);
			//��ȡ����·��
			String absolutePath = FileUtil.findFile(sourcePath,fileName);
			if (!"".equals(absolutePath)) {
				effectivePathList.add(absolutePath);
			} else {
				unEffectivePathList.add(pathlist.get(i));
			}
		}
		//ִ�д��
		packageing(sourcePath, goalPath, effectivePathList);
		//�������ʧ�ܵ��ļ��嵥
		FileUtil.generatedFile(goalPath, unEffectivePathList, "Failures.txt");
		//��������ɹ����ļ��嵥
		FileUtil.generatedFile(goalPath, effectivePathList, "Successful.txt");
		if (effectivePathList.size() > 0) {
			return "����ɹ�����"+effectivePathList.size()+"�����ļ��ɹ�����"+unEffectivePathList.size()+"�����ļ�ʧ�ܣ�";
		} else {
			return "���ʧ�ܣ���"+effectivePathList.size()+"�����ļ��ɹ�����"+unEffectivePathList.size()+"�����ļ�ʧ�ܣ�";
		}
	}
	
	/**
	 * ִ�д��
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
	 * ��ȡ�ļ���
	 * @param pathlist
	 * @param sourcePath
	 * @return
	 */
	public List<String> getFileNameByRule(List<String> pathlist,String sourcePath){
		List<String> list = new ArrayList<String>();
		//��ȡ�ļ�������
		for (String filePath:pathlist) {
			//�滻��Ч·��
			if (filePath.lastIndexOf("/") != -1) {
				filePath = filePath.substring(filePath.lastIndexOf("/")+1, filePath.length());
			}
			//�滻.java�ļ���
			if (filePath.lastIndexOf(SUFFIX_JAVA_NAME) != -1) {
				filePath = filePath.replace(SUFFIX_JAVA_NAME, SUFFIX_CLASS_NAME);
			}
			list.add(filePath);
		}
		return list;
	}
}
