package com.lostandfound.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/**
 * 保存图片的服务
 * @author lee
 *
 */
public class SavaPicService {
	/**
	 * 把图片保存到本地目录
	 * @param path
	 * @param picName
	 * @param in
	 * @throws Exception 
	 */
	public void savaPic(String path, String picName, InputStream in) throws Exception {
		File picFile = new File(path);
		OutputStream out = null;
		if (!picFile.exists()) {
			picFile.mkdirs();
		}
		try {
			out = new FileOutputStream(picFile.getPath() + "\\" + picName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] buf = new byte[2048];
		int len = 0;
		try {
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
				out.flush();
			}
			throw new Exception("图片上传成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("图片上传失败");
		} finally{
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
