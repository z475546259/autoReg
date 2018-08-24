package flow;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.http.HttpHost;

import net.sf.json.JSONObject;
import util.GetFreeIpAgent;
import util.HttpClientUtil;
import util.OperateOracle;
import util.RecordToFile;
import util.Utils;
import util.cnUser;

public class RegFlow {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OperateOracle operateOracle = new OperateOracle();
		String mobile = "";
		String code="";
		cnUser user = new cnUser();
//		List<Map<String,String>> agents = GetFreeIpAgent.getFromDifferentSite();
		String random = "0."+Utils.randomIntString(17);
		File imgFile = null;
		//0��ȡͼ����֤���ļ�
		
		try {
			imgFile = GetFileFromUrl.getImageFromNetByUrl("http://m.cainiaolc.com/captcha/"+random, "code.png");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//1ʶ��ͼ����֤��
		String codeRes = "";
		for(int i=0;i<5;i++){
			try {
				//�ۺ� ʶ����֤��
				codeRes = JuheDemo.post("1005", imgFile);
				break;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				RecordToFile.record(new String[]{"�ۺ� ���� ʶ�� ��ʱ....������...."}, "log.txt");
				continue;
			}
		}
		if("".equals(codeRes)){
			RecordToFile.record(new String[]{"����5�ζ�ʶ�𲻳��� �Ǿ��˳�ϵͳ"}, "error.txt");
			System.exit(0);
		}
		int count=5;
		do {
			count--;
			String ip = null;
			String port = null;
			
			HttpHost target  = null;
			Map<String,String> agent =null;
			user.setDeviceID(Utils.randomHexString(16));
			 Random randomNum = new Random();
		     int s = randomNum.nextInt(Utils.user_agents.length);
		     user.setUser_agent(Utils.user_agents[s]);
		     user.setCnuserID("0");
			if("".equals(codeRes)){
				RecordToFile.record(new String[]{"����3��ʼ�ղ���ʶ����֤�룬�˳�ϵͳ���鿴ԭ��"}, "log.txt");
				System.exit(0);
			}
			RecordToFile.record(new String[]{"ʶ�𵽵���֤����=="+codeRes}, "log.txt");
			
			//2��ȡ�ֻ���
			
			//3���Ͷ�����֤��
			HttpClientUtil httpUtil = new HttpClientUtil();
			httpUtil.setDeviceID(user.getDeviceID());
			httpUtil.setUser_agent(user.getUser_agent());
			Map<String,String> para = new HashMap<String, String>();
			para.put("checkcode", codeRes);
			para.put("random", random);
			
			while(true){
				mobile = SMSUtil.getmobile("");
				RecordToFile.record(new String[]{"��ȡ�����ֻ�����=="+mobile}, "log.txt");
				para.put("telephone", mobile);
				
				//��ȡ����
				String agentStr = httpUtil.doGet("http://ip.11jsq.com/index.php/api/entry?method=proxyServer.generate_api_url&packid=1&fa=0&fetch_key=&qty=1&time=1&pro=&city=&port=1&format=txt&ss=1&css=&dt=1&specialTxt=3&specialJson=", "utf-8");
				try {
					System.out.println("��ȡ������=="+agentStr);
					ip = agentStr.split(":")[0];
					port = agentStr.split(":")[1];
					RecordToFile.record(new String[]{"ʹ�ô���"+ip+":"+port}, "log.txt");
					target  = new HttpHost(ip, Integer.parseInt(port));
					httpUtil.setTarget(target);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					httpUtil.setTarget(null);
				}
				
//				if(agents.size()>0){
//					agent = agents.get(0);
//					agents.remove(0);
					//RecordToFile.record(new String[]{"ʹ�ô���"+agent.get("IP")+":"+agent.get("PORT")+",����ʽ"+agent.get("type")}, "log.txt");
					
//				}else{
//					httpUtil.setTarget(null);
//				}
				String login_res = httpUtil.doPost("http://app.cainiaolc.com/checkcode/reg", para, "utf-8");
				RecordToFile.record(new String[]{"������֤����=="+login_res}, "log.txt");
				
				if(login_res==null){
					if(agent!=null){
						agent.put("lastStatu", "0");
						operateOracle.AddIPAgent(agent);
					}
					SMSUtil.release(mobile);
					continue;
				}
				String  codeSendStatu = "";
				
				try {
				    codeSendStatu = net.sf.json.JSONObject.fromObject(login_res).get("Success").toString();
				    if(agent!=null){
						agent.put("lastStatu", "1");
						operateOracle.AddIPAgent(agent);
					}
				} catch (Exception e) {
					if(agent!=null){
						agent.put("lastStatu", "2");
						operateOracle.AddIPAgent(agent);
						RecordToFile.record(new String[]{mobile+"��ͼ����֤��"+code+"ʹ�ô���"+agent.get("IP")+":"+agent.get("PORT")+",����ʽ"+agent.get("type"),login_res}, "log.txt");
					}
					SMSUtil.release(mobile);
					continue;
				} 
				
				if("true".equals(codeSendStatu)){
					code = SMSUtil.getsms(mobile,0);
					RecordToFile.record(new String[]{"��ȡ������֤����=="+code}, "log.txt");
					if("��ȡ���ų�ʱ����".equals(code)){
						RecordToFile.record(new String[]{mobile+"��ȡ���ų�ʱ����"}, "log.txt");
						SMSUtil.addignore(mobile);
						continue;
					}
					break;
				}else if(login_res.contains("ͼ����֤�����")){
					
					try {
						imgFile = GetFileFromUrl.getImageFromNetByUrl("http://m.cainiaolc.com/captcha/"+random, "code.png");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					for(int i=0;i<5;i++){
						try {
							//�ۺ� ʶ����֤��
							codeRes = JuheDemo.post("1005", imgFile);
							para.put("checkcode", codeRes);
							RecordToFile.record(new String[]{mobile+"��ͼ����֤��"+code+"ʹ�ô���"+agent.get("IP")+":"+agent.get("PORT")+",����ʽ"+agent.get("type"),login_res}, "log.txt");
							break;
						} catch (Exception e) {
							// TODO Auto-generated catch block
							RecordToFile.record(new String[]{"�ۺ� ���� ʶ�� ��ʱ....������...."}, "log.txt");
							continue;
						}
					}
					if("".equals(codeRes)){
						RecordToFile.record(new String[]{"����5�ζ�ʶ�𲻳��� �Ǿ��˳�ϵͳ"}, "error.txt");
						RecordToFile.record(new String[]{"ͼ����֤��������»�ȡͼ����֤�벢ʶ����̳���",login_res}, "error.txt");
						System.exit(0);
					}
				}else if(login_res.contains("�Ѿ�ע��")){
						SMSUtil.addignore(mobile);
						RecordToFile.record(new String[]{mobile+"�Ѿ�ע��",login_res}, "log.txt");		
						mobile = SMSUtil.getmobile("");
						para.put("telephone", mobile);
				}else if(login_res.contains("����̫Ƶ��")&&agent==null){
					System.exit(0);
				}
			}
			//4ע��
			Map<String,String> para2 = new HashMap<String, String>();
			para2.put("telephone", mobile);
			para2.put("checkcode", code);
			para2.put("random", random);
			para2.put("password", "d5c91303b3963ea463d4d97b616f06224f2469bdb4d9984ca696dd37c7059a7b");
			para2.put("code", codeRes);
			String reg_res  =  null;
			try {
				reg_res  = httpUtil.doPost("http://app.cainiaolc.com/user/reg", para2, "utf-8");				
			} catch (Exception e) {
				RecordToFile.record(new String[]{"ע�������쳣=="+e.getMessage()}, "error.txt");
			}
			RecordToFile.record(new String[]{"ע����=="+reg_res}, "log.txt");
			boolean localflag = false;
//			while(reg_res==null||!reg_res.contains("Success")||!"true".equals(JSONObject.fromObject(reg_res).get("Success").toString())){
//				if(localflag){
//					RecordToFile.record(new String[]{"����ñ���ipע�ᶼ��ʾʧ�����˳�ϵͳ"}, "log.txt");
//					System.exit(0);
//				}
//				RecordToFile.record(new String[]{mobile,code,reg_res}, "error.txt");
//				if(agents.size()>0){
//					agent = agents.get(0);
//					agents.remove(0);
//					System.out.println("ʹ�ô���"+agent.get("IP")+":"+agent.get("PORT")+",����ʽ"+agent.get("type"));
//					target  = new HttpHost(agent.get("IP"), Integer.parseInt(agent.get("PORT")),  agent.get("type"));
//					httpUtil.setTarget(target);
//				}else{
//					httpUtil.setTarget(null);
//					localflag = true;
//				}
//				try {
//					reg_res  = httpUtil.doPost("http://app.cainiaolc.com/user/reg", para2, "utf-8");				
//				} catch (Exception e) {
//					RecordToFile.record(new String[]{"ע�������쳣=="+e.getMessage()}, "error.txt");
//				}
//			}
			user.setTelephone(mobile);
			user.setPassword("d5c91303b3963ea463d4d97b616f06224f2469bdb4d9984ca696dd37c7059a7b");
			user.setRandomPwd("475546259");
			operateOracle.updateAppData("�������",user);
			RecordToFile.record(new String[]{"�����ù����ֻ���״̬=="+mobile}, "log.txt");
		} while (count>0);
		
		//��¼���
	}

}
