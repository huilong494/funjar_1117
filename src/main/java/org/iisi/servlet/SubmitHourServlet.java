package org.iisi.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.iisi.db.JDBCSetHour;
import org.iisi.db.SearchHour;



public class SubmitHourServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 String[] credit;
	private int i = 0;


	public SubmitHourServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String url = request.getParameter("url");
		String[] credit = (String[]) session.getAttribute("Ucredit");
		JDBCSetHour db = new JDBCSetHour();
		
		
		ArrayList<SearchHour> list=(ArrayList<SearchHour>) session.getAttribute("ViewList");
		int a = db.SubmitHour(credit,list);
		/*System.out.println(a);*/
	

		RequestDispatcher dispatcher=request.getRequestDispatcher("sethour.jsp");
		dispatcher.forward(request, response);
		
	}

}
