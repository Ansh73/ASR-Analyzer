package com.kevin.maven.quickstart;

import com.google.gson.Gson;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Gson gson = new Gson();
		System.out.println(gson.toJson("Hello World!"));
		AuthenticateUser authObj = new AuthenticateUser();
		authObj.doAuthentication();
		ASRAnalyzer asrObj = new ASRAnalyzer();
		asrObj.startProcessing();
	}

}
