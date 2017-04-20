package com.lostandfound.beans;

import java.io.IOException;

public class MyUtils {
	public static int getInput() {
		byte[] buff = new byte[255];
		try {
			System.in.read(buff, 0, 255);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String result = new String(buff);
		return Integer.parseInt(result.trim());
	}
	
	public static void main(String[] args) {
		System.out.println(getInput());
	}
}
