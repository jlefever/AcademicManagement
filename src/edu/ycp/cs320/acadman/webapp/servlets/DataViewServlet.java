package edu.ycp.cs320.acadman.webapp.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.acadman.controller.Controller;
import edu.ycp.cs320.acadman.model.Year;

public class DataViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("In the DavaView servlet");
		
		List<Year> years = Controller.getYears();
		
		req.setAttribute("years", years);
		req.getRequestDispatcher("/_view/DataView.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/DataView.jsp").forward(req, resp);
	}
}