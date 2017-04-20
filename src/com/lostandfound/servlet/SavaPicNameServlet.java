package com.lostandfound.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lostandfound.utils.StaticValues;

/**
 * 保存图片名字服务
 * Servlet implementation class UserPicInsertServlet
 */
@WebServlet("/SavaPicNameServlet")
public class SavaPicNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SavaPicNameServlet() {
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
		// TODO Auto-generated method stub
		System.out.println("SavaPicNameServlet");
		ServletOutputStream out = response.getOutputStream();
		String parameter = request.getParameter("userOrdata");
		StaticValues.fileName = request.getParameter("fileName");
		if (parameter.equals("user")) {
			StaticValues.filePath = StaticValues.PATH + "/userpic";
			out.write("get".getBytes());
			out.flush();
		} else if (parameter.equals("data")) {
			StaticValues.filePath = StaticValues.PATH + "/datapic";
			out.write("get".getBytes());
			out.flush();
		}
	}
}
