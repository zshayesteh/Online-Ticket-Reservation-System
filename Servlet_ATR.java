package Servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet_ATR
 */
//@WebServlet("/Servlet_ATR")
public class Servlet_ATR extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ticketPackage.Ticket t;
	ticketPackage.TicketDao TDAO;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet_ATR() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		String operation=request.getParameter("radio");//getting the value of operation from html page

		if(operation.equals("show")){//forwarding the request to the show Servlet
			RequestDispatcher rd=request.getRequestDispatcher("showServlet");
			rd.forward(request, response);
		}
		else if(operation.equals("buy"))//forwarding the request to the buy Servlet
		{	
			RequestDispatcher rd=request.getRequestDispatcher("buyServlet");
			rd.forward(request, response);
		}
		else if(operation.equals("edit"))//forwarding the request to the edit Servlet
		{
			RequestDispatcher rd=request.getRequestDispatcher("editServlet");
			rd.forward(request, response);
		}
		else if(operation.equals("cansel"))//forwarding the request to the cansel Servlet
		{
			RequestDispatcher rd=request.getRequestDispatcher("canselServlet");
			rd.forward(request, response);
		}
		else{//including the request to the html page
			RequestDispatcher rd=request.getRequestDispatcher("/ServletTicketHTML.html");
			rd.include(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
