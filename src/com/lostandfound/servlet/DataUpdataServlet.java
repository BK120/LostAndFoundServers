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
 * 用户修改消息
 * Servlet implementation class DataUpdataServlet
 */
@WebServlet("/DataUpdataServlet")
public class DataUpdataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataUpdataServlet() {
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
		System.out.println("DataUpdataServlet");
		ServletOutputStream out = response.getOutputStream();
		ServletInputStream in = request.getInputStream();
		String stream = ReadInputStream.readInputStream(in);
		Data data;
		LafService service = new LafService();
		try {
			data = JsonUtils.jsonToData(new JSONObject(stream));
			service.updataData(data);
		} catch (Exception e) {
			// 操作成功与否均在异常中捕获
			e.printStackTrace();
			JSONObject result = JsonUtils.strToJson(e.getMessage(), "result");
			out.write(result.toString().getBytes());
			out.flush();
		} finally{
			in.close();
			out.close();
		}
	}

}
