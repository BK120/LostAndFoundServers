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

import com.lostandfound.beans.Data;
import com.lostandfound.service.LafService;
import com.lostandfound.service.ReadInputStream;
import com.lostandfound.utils.JsonUtils;

/**
 * 查询事件服务
 * Servlet implementation class SelectDatasServlet
 */
@WebServlet("/SelectDatasServlet")
public class SelectDatasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int howMuch = 0;//用于确定查询条件，0-查询全部，1-丢失，2-招领；3-用户全部，4-用户丢失，5-用户招领
	private String phone;
	private List<Data> datas;
	private String result;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectDatasServlet() {
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
		System.out.println("SelectDatasServlet");
		ServletInputStream in = request.getInputStream();
		ServletOutputStream out = response.getOutputStream();
		LafService service = new LafService();
		String stream = ReadInputStream.readInputStream(in);
		JSONObject paramJson = null;
		try {
			paramJson = new JSONObject(stream);
			howMuch = paramJson.getInt("howMuch");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			switch (howMuch) {
			case 0://查询所有事件
				datas = service.selectDatas();
				break;
			case 1://查询所有丢失事件
				datas = service.selectDataLost();
				break;
			case 2://查询所有招领事件
				datas = service.selectDataFound();
				break;
			case 3:
				phone = paramJson.getString("phone");
				datas = service.selectDataNotFinish(phone);
				break;
			case 4:
				phone = paramJson.getString("phone");
				datas = service.selectDataLost(phone);
				break;
			case 5:
				phone = paramJson.getString("phone");
				datas = service.selectDataFound(phone);
			}
			if (datas.size()==0) {
				result = JsonUtils.strToJson("暂无相关查询结果", "result").toString();
			}else {
				result = JsonUtils.datasToJson(datas).toString();
			}
			out.write(result.getBytes());
			out.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			in.close();
			out.close();
		}
	}

}
