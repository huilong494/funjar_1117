package org.iisi.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.iisi.bean.edit;
import org.iisi.db.editJDBC;

public class editServlet1 extends HttpServlet {

	private String Pid;
	private String Time;
	private String Reply;
	private String eid;
	private String dpid;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		eid = (String) session.getAttribute("Employee");
		dpid = request.getParameter("checkbox");
		
		editJDBC db = new editJDBC();
		try{
		List<edit> ed = db.edit(eid);// List<bean>

		request.setAttribute("edit", ed);//("名,value")
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("editPSE.jsp");
		dispatcher.forward(request, response);
		}
		catch(Exception e)
		{
			
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		eid = (String) session.getAttribute("Employee");
		editJDBC db = new editJDBC();
		try {
			List<edit> ed = db.edit(eid);
			request.setAttribute("edit", ed);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("editPSE.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e)

		{
		}

	}
}
