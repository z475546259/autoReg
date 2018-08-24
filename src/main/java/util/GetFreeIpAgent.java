package util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import net.sf.json.JSONObject;

public class GetFreeIpAgent {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GetFreeIpAgent aa= new GetFreeIpAgent();
		List<Map<String,String>> agents =new ArrayList<>();
		agents = getFromDifferentSite();
	}
	
	public static List<Map<String,String>> getFromDifferentSite(){
		List<Map<String,String>> agents =new ArrayList<>();
		agents.addAll(get("https://www.kuaidaili.com/free/inha/1/",""));
		agents.addAll(get("http://www.ip3366.net/free/",""));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		agents.addAll(get("https://www.kuaidaili.com/free/inha/2/",""));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		agents.addAll(get("https://www.kuaidaili.com/free/inha/3/",""));
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}agents.addAll(get("https://www.kuaidaili.com/free/inha/4/",""));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		agents.addAll(get("https://www.kuaidaili.com/free/inha/5/",""));
		agents.addAll(get("http://www.xicidaili.com/nn/","parse2"));
		agents.addAll(get("http://www.xicidaili.com/nn/2","parse2"));
		agents.addAll(get("http://www.xicidaili.com/nn/3","parse2"));
		agents.addAll(get("http://www.xicidaili.com/nn/4","parse2"));
		agents.addAll(get("http://www.xicidaili.com/nn/5","parse2"));
		agents.addAll(get("http://www.xicidaili.com/nn/5","parse2"));
		agents.addAll(get("http://www.xicidaili.com/nn/6","parse2"));
		agents.addAll(get("http://www.xicidaili.com/nn/7","parse2"));
		agents.addAll(get("http://www.xicidaili.com/nn/8","parse2"));
		agents.addAll(get("http://www.xicidaili.com/nn/9","parse2"));
		agents.addAll(get("http://www.data5u.com/free/index.shtml","parse3"));
		agents.addAll(get("http://www.data5u.com/free/gngn/index.shtml","parse3"));
		agents.addAll(get("http://www.data5u.com/free/gnpt/index.shtml","parse3"));
		agents.addAll(get("http://www.data5u.com/free/gwgn/index.shtml","parse3"));
		agents.addAll(get("http://www.data5u.com/free/gwpt/index.shtml","parse3"));
		System.out.println(agents.size()+"-------");
		List<Map<String,String>> agents2 =new ArrayList<>();
		OperateOracle operateOracle = new OperateOracle();
		for (Map<String, String> map : agents) {
			if(!map.get("type").equals("HTTPS")){
				agents2.add(map);
			}
//			else{
//				map.put("lastStatu", "0");
//				operateOracle.AddIPAgent(map);
//			}
		}
		System.out.println(agents2.size()+"-------");
		return agents2;
	}
	
	public static List<Map<String,String>> get(String urlStr,String parseNum){
		List<Map<String,String>> lMaps = new ArrayList<>();
		 HttpURLConnection conn = null;
	     BufferedReader reader = null;
	     String rs = null;
		 String result =null;
//	        String urlStr ="http://www.ip3366.net/free/";//请求接口地址
        try {
        	URL url = new URL(urlStr);
	         conn = (HttpURLConnection) url.openConnection();
	         conn.setRequestProperty("User-agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36");
	         conn.setUseCaches(false);
	         conn.setConnectTimeout(30000);
	         conn.setReadTimeout(30000);
	         conn.setInstanceFollowRedirects(false);
	         conn.connect();
	         InputStream is = conn.getInputStream();
	         reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
	         String strRead = null;
	         StringBuffer sb = new StringBuffer();
	         while ((strRead = reader.readLine()) != null) {
	             sb.append(strRead);
	         }
	         rs = sb.toString();
	         System.out.println(rs);
	         if("parse2".equals(parseNum)){
	        	 lMaps = parse2(rs);	
	         }else if("parse3".equals(parseNum)){
	        	 lMaps = parse3(rs);
	         }else{
	        	 lMaps = parse(rs);	        	 
	         }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	        
	    return lMaps;   
	}
	
	/**
	 * http://www.ip3366.net/free/
	 * https://www.kuaidaili.com/free/inha/1/
	 * https://www.kuaidaili.com/free/inha/2/
	 * https://www.kuaidaili.com/free/inha/3/
	 * https://www.kuaidaili.com/free/inha/4/
	 * https://www.kuaidaili.com/free/inha/5/
	 * 
	 * @param htmlStr
	 * @return
	 */
	public static List<Map<String,String>> parse(String htmlStr){ 
		List<Map<String,String>> lMaps = new ArrayList<>();
	    Document doc = Jsoup.parse(htmlStr);  
	    // 根据id获取table  
	    Element table = doc.getElementsByClass("table table-bordered table-striped").first();  
	    // 使用选择器选择该table内所有的<tr> <tr/>  
	    Elements trs = table.select("tr");  
	    //遍历该表格内的所有的<tr> <tr/>  
	    for (int i = 1; i < trs.size(); ++i) { 
	    	Map<String,String> trObj = new HashMap<>();
	        // 获取一个tr  
	        Element tr = trs.get(i);  
	        // 获取该行的所有td节点  
	        Elements tds = tr.select("td");  
	        // 选择某一个td节点  
	        for (int j = 0; j < tds.size(); ++j) {  
	        	if(j==0){
	        		trObj.put("IP", tds.get(j).text());
	        	}else if(j==1){
	        		trObj.put("PORT", tds.get(j).text());
	        	}else if(j==3){
	        		trObj.put("type", tds.get(j).text());
	        	}
	        }
	        lMaps.add(trObj);
	    } 
	    return lMaps;
	}  
	/**
	 * http://www.xicidaili.com/nn/
	 * @param htmlStr
	 * @return
	 */
	public static List<Map<String,String>> parse2(String htmlStr){ 
		List<Map<String,String>> lMaps = new ArrayList<>();
	    Document doc = Jsoup.parse(htmlStr);  
	    // 根据id获取table  
	    Element table = doc.getElementById("ip_list");  
	    // 使用选择器选择该table内所有的<tr> <tr/>  
	    Elements trs = table.select("tr");  
	    //遍历该表格内的所有的<tr> <tr/>  
	    for (int i = 1; i < trs.size(); ++i) { 
	    	Map<String,String> trObj = new HashMap<>();
	        // 获取一个tr  
	        Element tr = trs.get(i);  
	        // 获取该行的所有td节点  
	        Elements tds = tr.select("td");  
	        // 选择某一个td节点  
	        for (int j = 1; j < tds.size(); ++j) {  
	        	if(j==1){
	        		trObj.put("IP", tds.get(j).text());
	        	}else if(j==2){
	        		trObj.put("PORT", tds.get(j).text());
	        	}else if(j==5){
	        		trObj.put("type", tds.get(j).text());
	        	}
	        }
	        lMaps.add(trObj);
	    } 
	    return lMaps;
	}  
	
	
	/**
	 * http://www.data5u.com/free/gnpt/index.shtml
	 * @param htmlStr
	 * @return
	 */
	public static List<Map<String,String>> parse3(String htmlStr){ 
		List<Map<String,String>> lMaps = new ArrayList<>();
	    Document doc = Jsoup.parse(htmlStr);  
	    // 根据id获取table  
	    Element div = doc.getElementsByClass("wlist").get(1);  
	    // 使用选择器选择该table内所有的<tr> <tr/>  
	    Elements uls = div.select("ul").select("li").get(1).select("ul");  
	    //遍历该表格内的所有的<tr> <tr/>  
	    for (int i = 1; i < uls.size(); ++i) { 
	    	Map<String,String> trObj = new HashMap<>();
	        // 获取一个tr  
	        Element ul = uls.get(i);  
	        // 获取该行的所有td节点  
	        Elements span = ul.select("span");  
	        // 选择某一个td节点  
	        for (int j = 0; j < span.size(); ++j) {  
	        	if(j==0){
	        		trObj.put("IP", span.get(j).select("li").get(0).text());
	        	}else if(j==1){
	        		trObj.put("PORT", span.get(j).select("li").get(0).text());
	        	}else if(j==3){
	        		trObj.put("type", span.get(j).select("li").get(0).text());
	        	}
	        }
	        lMaps.add(trObj);
	    } 
	    return lMaps;
	}  
}
