package flow;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetFileFromUrl {
	 public static File getImageFromNetByUrl(String strUrl,String path) throws Exception {
	        String imageName = strUrl.substring(strUrl.lastIndexOf("/") + 1,
	                strUrl.length());
//	        _FakeX509TrustManager.allowAllSSL();
	        URL url = new URL(strUrl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        // conn.setRequestMethod("GET");
	        conn.setRequestProperty("User-Agent",
	                "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
	        conn.setConnectTimeout(5 * 1000);
	        InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据
	        byte[] btImg = readInputStream(inStream);// 得到图片的二进制数据
	        inStream.close();
	        conn.disconnect();
	        File file =null;
	        try {
	            file = new File(path);
//	            DirectoryUtil.createFile(path+imageName);
	            FileOutputStream fops = new FileOutputStream(file);
	            fops.write(btImg);
	            fops.flush();
	            fops.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return file;
	    }
	     
	    public static byte[] readInputStream(InputStream inStream) throws Exception {
	        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	        byte[] buffer = new byte[1024];
	        int len = 0;
	        while ((len = inStream.read(buffer)) != -1) {
	            outStream.write(buffer, 0, len);
	        }
	        inStream.close();
	        return outStream.toByteArray();
	    }
}
