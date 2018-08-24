package flow;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SMSUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//��ȡ�ֻ�����ӿ�
//		String mobile = getmobile("");
//		System.out.println(mobile);
		//�ͷ��ֻ���
//		String rs = "��������ơ�935017�����Բ�����Ƶ���֤�룬����й©��";
//		System.out.println(release("15504313143"));
//		rs = rs.substring(rs.indexOf("��������ơ�")+6,rs.indexOf("��"));
//		System.out.println(rs);
		System.out.println(getsms("15543199749",0));
	}
	public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
	public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000; 
	private static final String token ="00485873556eb5b94eead38a4b5aed8518f7018c";
	private static final String baseUrl ="http://api.fxhyd.cn/UserInterface.aspx";
	private static final int itemid =6573;
	/**
	 * ��ȡ�ֻ��ţ����Դ����ϴλ�ȡ���ֻ���ǰ��λ
	 * @param exNo
	 * @return
	 */
	public static String getmobile(String exNo){
		String excludeno = "170|171|172|173|174|175|176|177|178|179";
		if(!"".equals(exNo)){excludeno=excludeno+"|"+exNo;}
		HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            String strUrl = baseUrl+"?action=getmobile&token="+token+"&itemid="+itemid+"&excludeno="+excludeno;
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
            if(rs.contains("success")){
            	rs = rs.split("\\|")[1];
            }else{rs="";}
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
	}
	
	
	/**
	 * ��ȡ�ֻ��ţ����Դ����ϴλ�ȡ���ֻ���ǰ��λ
	 * @param exNo
	 * @return
	 */
	public static String getsms(String mobile,int count){
		if(count>120){
			return "��ȡ���ų�ʱ����";
		}
		HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            String strUrl = baseUrl+"?action=getsms&token="+token+"&itemid="+itemid+"&mobile="+mobile;
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
            if(rs.contains("��������ơ�")){
            	rs = rs.substring(rs.indexOf("��������ơ�")+6,rs.indexOf("��"));
            }else{
            	try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	System.out.println("��ȡ����״̬��"+rs);
            	rs = getsms(mobile,++count);
            }
//            ��������ơ�935017�����Բ�����Ƶ���֤�룬����й©��
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
	}
	
	
	
	/**
	 * �ͷ��ֻ���
	 * @param mobile
	 * @return
	 */
	public static boolean release(String mobile){
		boolean flag = false;
		HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            String strUrl = baseUrl+"?action=release&token="+token+"&itemid="+itemid+"&mobile="+mobile;
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
            if(rs.contains("success")){
            	flag = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
		
		return flag;
	}

	/**
	 * �����ֻ���
	 * @param mobile
	 * @return
	 */
	public static boolean addignore(String mobile){
		boolean flag = false;
		HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            String strUrl = baseUrl+"?action=addignore&token="+token+"&itemid="+itemid+"&mobile="+mobile;
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
            if(rs.contains("success")){
            	flag = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
		
		return flag;
	}

}
