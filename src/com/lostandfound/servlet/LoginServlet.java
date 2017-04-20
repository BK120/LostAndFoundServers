package com.lostandfound.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lostandfound.service.LafService;

/**
 * 用户登录服务 Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		// 获取用户传入的帐号及密码进行验证
		System.out.println("LoginServlet");
		OutputStream out = response.getOutputStream();
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		LafService service = new LafService();
		try {
			service.login(phone, password);
		} catch (Exception e) {
			// 登陆成功或失败都在异常捕获中
			e.printStackTrace();
			out.write(e.getMessage().getBytes());
			out.flush();
		} finally {
			out.close();
		}
	}

}
