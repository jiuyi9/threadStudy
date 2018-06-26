package com.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestInetAddress {
	
	public static void main(String[] args) {
		try {
			System.out.println(InetAddress.getLocalHost().getHostName());
			System.out.println(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
