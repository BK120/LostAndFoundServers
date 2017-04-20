package com.lostandfound.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lostandfound.beans.Data;
import com.lostandfound.service.LafService;

/**
 * Servlet implementation class CheckDetailServlet
 */
@WebServlet("/CheckDetailServlet")
public class CheckDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("CheckDetailServlet");
		//response.setContentType("html");//设置返回文本类型
		response.setCharacterEncoding("gbk");//设置编码格式
		PrintWriter writer = response.getWriter();
		String parameterString = request.getParameter("id");
		int parameter = Integer.parseInt(parameterString);
		LafService service = new LafService();
		Data data;
		try {
			data = service.selectData(parameter);
			if (data==null) {
				writer.println("<html><font size5 color=red>无法正常显示数据</font></html>");
			}else {
				String type = data.getType().equals("lost") ? "丢失" : "招领";
				String last = data.getType().equals("lost") ? "如有线索":"如有认识物主";
				writer.println("<html><title>失物招领</title><body><h1 align=\"center\">失物招领</h1><hr size=\"1\" color=\"black\" style=\"margin: 30px\"/>"
						+ "<table border=\"0\" align=\"center\"><tr>"+
			"<td width=\"250px\" align=\"center\"><img alt=\"图片加载失败\" src=\"datapic/"+data.getPicName()+"\" width=\"200px\" height=\"200px\"></td>"+
			"<td>"+
			"<h2>"+type+"："+data.getName()+"</h2>"+
			"本人于"+data.getIncidentDate1()+" "+data.getIncidentTime1()+"至"+data.getIncidentDate2()+" "+data.getIncidentTime2()+"在"+data.getPlace()+type+data.getName()+
			"<br />详细描述："+ data.getDetail()+"<br />备注："+data.getRemark()+"<br />"+last+"，请通过以下方式联系我。<br />电话："+data.getUserPhone()+
			"</td></tr></table></body></html>");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
