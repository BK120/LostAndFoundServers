package com.lostandfound.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lostandfound.service.SavaPicService;
import com.lostandfound.utils.StaticValues;

/**
 * 保存图片服务
 * Servlet implementation class SavaPicServlet
 */
@WebServlet("/SavaPicServlet")
public class SavaPicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SavaPicServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("SavaPicServlet");
		ServletInputStream in = request.getInputStream();
		ServletOutputStream out = response.getOutputStream();
		SavaPicService service = new SavaPicService();
		try {
			service.savaPic(StaticValues.filePath, StaticValues.fileName, in);
		} catch (Exception e) {
			e.printStackTrace();
			out.write(e.getMessage().getBytes());
			out.flush();
		} finally {
			in.close();
			out.close();
		}
	}

}
