package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class showServlet
 */
// @WebServlet("/showServlet")
public class showServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public showServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		ticketPackage.TicketDao TDAO = new ticketPackage.TicketDao();
		ticketPackage.Ticket t = new ticketPackage.Ticket();
		ArrayList<ticketPackage.Ticket> tList = new ArrayList<ticketPackage.Ticket>();

		if (request.getParameter("id").isEmpty()) {//if the user wants to read all tickets
			try {
				tList = TDAO.readAll();//getting all tickets from DB
				//html page
				out.print("<html>");
				out.print("<head>");
				out.print("<meta charset=\"ISO-8859-1\">");
				out.print("<title>Airplane Ticket Reservation System</title>");
				out.print("</head>");
				out.print("<body>");
				out.print("<h1>Welcome to the Airplane Ticket Reservation System</h1><br>");
				out.print("<form name=\"myForm\" action=\"Servlet_ATR\"  method=\"get\">");
				out.print("ticketID: <input type=\"text\" name=\"id\"><br> ");
				out.print("ownerName: <input type=\"text\" name=\"ownerName\"><br>");
				out.print("source: <input type=\"text\" name=\"source\"><br>");
				out.print("destination: <input type=\"text\" name=\"destination\"><br>");
				out.print("flightDate: <input type=\"text\" name=\"flightDate\"><br>");
				out.print("flightNumber: <input type=\"text\" name=\"flightNumber\"><br>");
				out.print("<input type=\"radio\" name=\"radio\" id=\"radio\" value=\"show\"> show<br>");
				out.print("<input type=\"radio\" name=\"radio\" id=\"radio\" value=\"buy\"> buy<br>");
				out.print("<input type=\"radio\" name=\"radio\" id=\"radio\" value=\"edit\"> edit<br>");
				out.print("<input type=\"radio\" name=\"radio\" id=\"radio\" value=\"cansel\"> cansel<br>");
				out.print("<input type=\"submit\" name=\"submit\" value=\"submit\"><br>");
				out.print("</form><br>");
				for (int i = 0; i < tList.size(); i++){//showing all tickets with their details
					out.println("ticket ID: " + tList.get(i).getTicketID() + " owner name: "
							+ tList.get(i).getOwnerName() + " source: " + tList.get(i).getSource() + " destination: "
							+ tList.get(i).getDestination() + " flight date: " + tList.get(i).getFlightDate()
							+ " flight number: " + tList.get(i).getFlightNumber());
					out.print("<br>");
				}
				out.print("<br>");
				out.print("</body>");
				out.print("</html>");
			} catch (SQLException e) {
			}
		} else {//if the user wants to read a specific ticket he/she will enter its ID
			int id = Integer.parseInt(request.getParameter("id"));//getting ticket ID from html page
			try {
				t = TDAO.read(id);//getting a ticket from DB by its ID
				//html page
				out.print("<html>");
				out.print("<head>");
				out.print("<meta charset=\"ISO-8859-1\">");
				out.print("<title>Airplane Ticket Reservation System</title>");
				out.print("</head>");
				out.print("<body>");
				out.print("<h1>Welcome to the Airplane Ticket Reservation System</h1><br>");
				out.print("<form name=\"myForm\" action=\"Servlet_ATR\"  method=\"get\">");
				out.print("ticketID: <input type=\"text\" name=\"id\"><br> ");
				out.print("ownerName: <input type=\"text\" name=\"ownerName\"><br>");
				out.print("source: <input type=\"text\" name=\"source\"><br>");
				out.print("destination: <input type=\"text\" name=\"destination\"><br>");
				out.print("flightDate: <input type=\"text\" name=\"flightDate\"><br>");
				out.print("flightNumber: <input type=\"text\" name=\"flightNumber\"><br>");
				out.print("<input type=\"radio\" name=\"radio\" id=\"radio\" value=\"show\"> show<br>");
				out.print("<input type=\"radio\" name=\"radio\" id=\"radio\" value=\"buy\"> buy<br>");
				out.print("<input type=\"radio\" name=\"radio\" id=\"radio\" value=\"edit\"> edit<br>");
				out.print("<input type=\"radio\" name=\"radio\" id=\"radio\" value=\"cansel\"> cansel<br>");
				out.print("<input type=\"submit\" name=\"submit\" value=\"submit\"><br>");
				out.print("</form><br>");
				out.print("ticket ID: " + t.getTicketID() + " owner name: " + t.getOwnerName() + " source: "
						+ t.getSource() + " destination: " + t.getDestination() + " flight date: " + t.getFlightDate()
						+ " flight number: " + t.getFlightNumber());//printing the ticket details
				out.print("<br>");
				out.print("</body>");
				out.print("</html>");
			} catch (SQLException e) {
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
