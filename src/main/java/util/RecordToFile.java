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
		String[] strs = {"开始时间=1564798","结束时间=54798764"};
		RecordToFile.record(new String[]{"开始时间=1564798","结束时间=54798764"}, "countTime.txt");
	}
	/**
	 * 记录东西到文件
	 */
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static void record(String[] recStrs,String fileLocation) {
		 File file = new File(fileLocation);// 要写入的文件路径  
	        if (!file.exists()) {// 判断文件是否存在  
	            try {  
	                file.createNewFile();// 如果文件不存在创建文件  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	        } else {  
//	            System.out.println("文件"+file.getName()+"已存在!");  
	        }  
	          
	        for (String str : recStrs) {// 遍历listStr集合  
	            FileOutputStream fos = null;  
	            PrintStream ps = null;  
	            try {  
	                fos = new FileOutputStream(file,true);// 文件输出流  追加  
	                ps = new PrintStream(fos);  
	            } catch (FileNotFoundException e) {  
	                e.printStackTrace();  
	            }  
	            String string  = sdf.format(Calendar.getInstance().getTime())+str + "\r\n";// +换行  
	            System.out.println(string);
	            ps.print(string); // 执行写操作  
	            ps.close(); // 关闭流  
	              
	        }  
	}
}
