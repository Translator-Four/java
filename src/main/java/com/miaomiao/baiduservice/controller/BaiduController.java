package com.miaomiao.baiduservice.controller;

import javax.servlet.http.HttpServletRequest;

import com.miaomiao.baiduservice.TransApi;
import org.apache.tomcat.jni.Thread;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;

@Controller
public class BaiduController {
	
	// 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
	private static final String APP_ID = "20240804002115829";
	private static final String SECURITY_KEY = "0b4mV75kD6Zd079e_xkk";
	
	@ResponseBody
	@PostMapping(value="/json/translation", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getByJson(@RequestBody String query) throws InterruptedException {
		if (!StringUtils.hasText(query)){
			return "未输入中文";
		}
		System.out.println(query);


		TransApi api = new TransApi(APP_ID, SECURITY_KEY);

		String result_string = api.getTransResult(query.replace("\"", ""), "auto", "en");
		System.out.println(result_string);
		return result_string;
	}
	
	@ResponseBody
	@GetMapping(value="/json/translation")
	public String get(HttpServletRequest request) throws InterruptedException {

		String query = request.getParameter("query");
		if (!StringUtils.hasText(query)){
			return "未输入中文";
		}
		System.out.println(query);
		
		TransApi api = new TransApi(APP_ID, SECURITY_KEY);
		String result_string = api.getTransResult(query, "auto", "en");
		
		System.out.println(result_string);
		return result_string;
	}
}
