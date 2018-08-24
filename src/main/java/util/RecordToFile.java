package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RecordToFile {
	public static void main(String[] args) {
		String[] strs = {"��ʼʱ��=1564798","����ʱ��=54798764"};
		RecordToFile.record(new String[]{"��ʼʱ��=1564798","����ʱ��=54798764"}, "countTime.txt");
	}
	/**
	 * ��¼�������ļ�
	 */
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static void record(String[] recStrs,String fileLocation) {
		 File file = new File(fileLocation);// Ҫд����ļ�·��  
	        if (!file.exists()) {// �ж��ļ��Ƿ����  
	            try {  
	                file.createNewFile();// ����ļ������ڴ����ļ�  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	        } else {  
//	            System.out.println("�ļ�"+file.getName()+"�Ѵ���!");  
	        }  
	          
	        for (String str : recStrs) {// ����listStr����  
	            FileOutputStream fos = null;  
	            PrintStream ps = null;  
	            try {  
	                fos = new FileOutputStream(file,true);// �ļ������  ׷��  
	                ps = new PrintStream(fos);  
	            } catch (FileNotFoundException e) {  
	                e.printStackTrace();  
	            }  
	            String string  = sdf.format(Calendar.getInstance().getTime())+str + "\r\n";// +����  
	            System.out.println(string);
	            ps.print(string); // ִ��д����  
	            ps.close(); // �ر���  
	              
	        }  
	}
}
