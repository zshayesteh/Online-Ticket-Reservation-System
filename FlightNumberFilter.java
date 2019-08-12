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
 * Servlet Filter implementation class FlightNumberFilter
 */
//@WebFilter("/FlightNumberFilter")
public class FlightNumberFilter implements Filter {

	/**
	 * Default constructor. 
	 */
	public FlightNumberFilter() {
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
		PrintWriter out=response.getWriter(); 
		if(request.getParameter("radio").equals("buy") | request.getParameter("radio").equals("edit")){//if buy or edit selected in the page
			int flightNumber = Integer.parseInt(request.getParameter("flightNumber"));//getting the value of flight number from the html page
			if(flightNumber<1000 & flightNumber>99)//checking the flight number to be in the range of 100-1000
			{
				chain.doFilter(request, response);// sends request to next resource
			} else {//if flight date is not in the valid range
				out.print("The flight number is not valid!");
				RequestDispatcher rd = request.getRequestDispatcher("/ServletTicketHTML.html");
				rd.include(request, response);
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
