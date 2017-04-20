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
 * 用户删除指定ID事件 
 * Servlet implementation class DataDeleteServlet
 */
@WebServlet("/DataDeleteServlet")
public class DataDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataDeleteServlet() {
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
		System.out.println("DataDeleteServlet");
		OutputStream out = response.getOutputStream();
		String idString = request.getParameter("id");
		int id = Integer.parseInt(idString);
		LafService service = new LafService();
		try {
			service.deleteData(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.write(e.getMessage().getBytes());
			out.flush();
		}finally {
			out.close();
		}
	}

}
