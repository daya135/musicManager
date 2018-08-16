package org.jzz.musicManger.utils;
import java.io.BufferedReader;
/*
 * 为啥只修改了文件编码抓取下来的网页就能显示特殊字符了...
 * 因为读取的时候没设置编码, 默认还是按GBK解码, 另外notepad++的UTF-8解码有问题
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;  
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;  
import org.apache.http.impl.client.HttpClients;  
import org.apache.http.message.BasicNameValuePair;  
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XiamiCatch2 {
	
	private static Logger logger = LoggerFactory.getLogger(XiamiCatch2.class);
	
	private static String userName;
	private static String passWord;
	private static String uid;
	private static int numPerPage;
	private static String fileName;
	
	/* 这么初始化的目的是不破坏后续方法内使用的变量，保持代码可移植性 */
	static {
//		userName = xiamiConfig.getUserName();
//		passWord = xiamiConfig.getPassWord();
//		uid = xiamiConfig.getUid();
//		numPerPage = xiamiConfig.getNumPerPage();
//		fileName = xiamiConfig.getFilename();
		InputStream in = XiamiCatch2.class.getResourceAsStream("/web.properties"); //神奇，不这么写就拿不到配置文件！！
		Properties prop = new Properties();
		try {
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {}
			}
		}
		userName = prop.getProperty("xiami_user");
		passWord = prop.getProperty("xiami_password");
		uid = prop.getProperty("xiami_uid");
		numPerPage = Integer.parseInt(prop.getProperty("numPerPage"));
		fileName = prop.getProperty("filename");
		
	}
	
	public void saveFile(String str) {
		try {
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fileName), false), "utf-8"));
			out.write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * @description 根据虾米收藏列表，抓取歌曲名称、艺术家、上架信息和歌曲虾米链接
	 * @param pageNum:需要处理的收藏页数（从零开始）
	 * */
	public static List<Song> CatchSongInfo(int pageNum) {
		if(pageNum < 1) 
			return null;
		
		CloseableHttpClient httpClient = null;
		//此处不是登陆页面的地址, 而是表单提交的地址!
		HttpPost httpPost = new HttpPost("https://login.xiami.com/member/login");
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		List<Song> songList = new ArrayList<Song>();
		formParams.add(new BasicNameValuePair("email", userName));
		formParams.add(new BasicNameValuePair("password", passWord));
		UrlEncodedFormEntity uefEntity = null;
		
		try {
			CookieStore cookieStore = new BasicCookieStore();
            httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
            uefEntity = new UrlEncodedFormEntity(formParams, "UTF-8");  
            httpPost.setEntity(uefEntity); 
			httpClient.execute(httpPost);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			List<Cookie> cookies = cookieStore.getCookies();
			for (Cookie cookie : cookies) {
				System.out.println(cookie);
			}
			System.out.println(response.getLastHeader("Set-Cookie"));
			
			httpPost = new HttpPost("https://passport.xiami.com/api/login?");
			List<NameValuePair> nvp = new ArrayList<NameValuePair>();
			nvp.add(new BasicNameValuePair("account", "18767673562"));
			nvp.add(new BasicNameValuePair("password", "6512bd43d9caa6e02c990b0a82652dca"));
			nvp.add(new BasicNameValuePair("nco_sign", "18767673562"));
			nvp.add(new BasicNameValuePair("nco_sessionid", "6512bd43d9caa6e02c990b0a82652dca"));
			nvp.add(new BasicNameValuePair("nco_token", "6512bd43d9caa6e02c990b0a82652dca"));
			String sCharSet = "utf-8";
			httpPost.setEntity(new UrlEncodedFormEntity(nvp, sCharSet));
			response = httpClient.execute(httpPost);
			System.out.println(response.getStatusLine());
			System.out.println(response.getEntity().toString());
			
			
		} catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {
            try {  
                httpClient.close();
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
		return songList;
	}

	public static void main (String[] args) {
		CatchSongInfo(1); 
	}
	
}
