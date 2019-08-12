package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class DateFilter
 */
//@WebFilter("/DateFilter")
public class DateFilter implements Filter {

	/**
	 * Default constructor. 
	 */
	public DateFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		if(request.getParameter("radio").equals("buy") | request.getParameter("radio").equals("edit")){//if buy or edit selected in the page
			String flightDate = request.getParameter("flightDate");//getting the value of flight date from html page
			String regex = "^[1-4]\\d{3}\\/((0?[1-6]\\/((3[0-1])|([1-2][0-9])|(0?[1-9])))|((1[0-2]|(0?[7-9]))\\/(30|([1-2][0-9])|(0?[1-9]))))$";
			//regex pattern of date
			if (flightDate.matches(regex)){//checking the date with regex
				chain.doFilter(request, response);// sends request to next resource
			} else {//if checking pattern does not match
				out.print("The Date you entered is not valid!");
				RequestDispatcher rd = request.getRequestDispatcher("/ServletTicketHTML.html");
				rd.include(request,response);
			}
		}
		else
			chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
