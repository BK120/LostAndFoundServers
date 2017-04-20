package com.lostandfound.service;

import java.io.IOException;
import java.io.InputStream;

/**
 * 封装读流过程
 * 
 * @author lee
 *
 */
public class ReadInputStream {
	public static String readInputStream(InputStream in) {
		StringBuilder sb = new StringBuilder();
		byte[] buf = new byte[2048];
		try {
			while ((in.read(buf)) != -1) {
				sb.append(new String(buf, "UTF-8"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString().trim();
	}
}
