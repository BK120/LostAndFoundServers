package com.lostandfound.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.lostandfound.beans.User;
import com.lostandfound.service.LafService;
import com.lostandfound.service.ReadInputStream;
import com.lostandfound.utils.JsonUtils;

/**
 * 用户注册服务 Servlet implementation class SignServlet
 */
@WebServlet("/SignServlet")
public class SignServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取客户端传回来的JSON（包含一个User类），调用注册方法将新用户插入
		System.out.println("SignServlet");
		ServletOutputStream out = response.getOutputStream();
		ServletInputStream in = request.getInputStream();
		String stream = ReadInputStream.readInputStream(in);
		User user = new User();
		LafService service = new LafService();
		try {
			user = JsonUtils.jsonToUser(new JSONObject(stream));
			service.sign(user);
		} catch (Exception e) {
			// 注册成功或失败都在异常捕获中
			e.printStackTrace();
			JSONObject j = JsonUtils.strToJson(e.getMessage(), "result");
			out.write(j.toString().getBytes());
			out.flush();
		} finally {
			in.close();
			out.close();
		}
	}

}
