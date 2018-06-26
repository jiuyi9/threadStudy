package com.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

//项目中定位资源的方式
public class TestClassPath {
	
	private Properties prop = new Properties();
	
	public static void main(String[] args) {
		TestClassPath path = new TestClassPath();
		path.execute();
		Properties prop = path.prop;
		System.out.println(prop.getProperty("key1"));
		System.out.println(prop.getProperty("key2"));
		System.out.println(prop.getProperty("className"));
	}

	private void execute() {
		//找到target下的class路径
		URL url = Thread.currentThread().getContextClassLoader().getResource("testClassPath.properties");
		System.out.println(url.getPath());
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("testClassPath.properties");
		
		try {
			prop.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}	
