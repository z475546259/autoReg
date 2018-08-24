package autoRegCaiNiao;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHost;

import net.sf.json.JSONObject;
import util.HttpClientUtil;
import util.RecordToFile;

public class MainEntiry {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		RecordToFile.record(new String[]{"15923584508","1654654","sgsdgfdg"}, "error.txt");
//		String reg_res = null;
//		while(reg_res==null||!"true".equals(JSONObject.fromObject(reg_res).get("Success").toString())){
//			System.out.println("sdsfds");
//			reg_res = "{'Success':true}";
//		}
		
		HttpClientUtil httpUtil = new HttpClientUtil();
//		httpUtil.setDeviceID(user.getDeviceID());
//		httpUtil.setUser_agent(user.getUser_agent());
		Map<String,String> para = new HashMap<String, String>();
		para.put("checkcode", "122111");
		para.put("random", "0.12156465456");
		HttpHost target  = new HttpHost("220.176.66.86", 27792,  "HTTPS");
		httpUtil.setTarget(target);
		String login_res = httpUtil.doPost("http://app.cainiaolc.com/checkcode/reg", para, "utf-8");
		System.out.println(login_res);
	}

}
