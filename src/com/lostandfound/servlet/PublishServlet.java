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

import com.lostandfound.beans.Data;
import com.lostandfound.service.LafService;
import com.lostandfound.service.ReadInputStream;
import com.lostandfound.utils.JsonUtils;

/**
 * 发布事件服务
 * Servlet implementation class PublishServlet
 */
@WebServlet("/PublishServlet")
public class PublishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublishServlet() {
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
		System.out.println("PublishServlet");
		ServletInputStream in = request.getInputStream();
		ServletOutputStream out = response.getOutputStream();
		String stream = ReadInputStream.readInputStream(in);
		Data data = new Data();
		LafService service = new LafService();
		try {
			data = JsonUtils.jsonToData(new JSONObject(stream));
			System.out.println(data.toString());
			service.addEvent(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String result = JsonUtils.strToJson(e.getMessage(), "result").toString();
			out.write(result.getBytes());
			out.flush();
		} finally {
			in.close();
			out.close();
		}
	}

}
