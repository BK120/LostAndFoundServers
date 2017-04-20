package com.lostandfound.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.lostandfound.beans.User;
import com.lostandfound.service.LafService;
import com.lostandfound.service.ReadInputStream;
import com.lostandfound.utils.JsonUtils;

/**
 * 用户通过电话（帐号）或姓名查询用户信息
 * Servlet implementation class SelectUserServlet
 */
@WebServlet("/SelectUserServlet")
public class SelectUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectUserServlet() {
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
		System.out.println("SelectUserServlet");
		ServletInputStream in = request.getInputStream();
		ServletOutputStream out = response.getOutputStream();
		LafService service = new LafService();
		String stream = ReadInputStream.readInputStream(in);
		JSONObject paramJson = null;
		String condition = "";
		try {
			paramJson = new JSONObject(stream);
			condition = JsonUtils.jsonToStr(paramJson, "condition");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		List<User> list;
		String result;
		try {
			list = service.findUser(condition);
			if (list.size()==0) {
				result = JsonUtils.strToJson("没有相似结果", "result").toString();
			}else {
				result = JsonUtils.userToJson(list.get(0)).toString();
			}
			out.write(result.getBytes());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			in.close();
			out.close();
		}
	}

}
