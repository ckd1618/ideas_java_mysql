package com.codingdojo.web.controllers;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/2")
public class serv2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("randnum") == null) {

			Random r = new Random();
			Integer randnum = r.nextInt(100)+1;
			session.setAttribute("randnum", randnum);
		}
		
		int guessednum = Integer.parseInt(request.getParameter("number"));
		boolean greenbox = false;
		System.out.println("/n/n/n/n/n/n/n" + guessednum );
		int temp1 = (Integer) session.getAttribute("randnum");
		System.out.println("/n/n/n/n/n/n/n" + temp1 );
		if (guessednum < temp1) {
			session.setAttribute("output", "Too Low!");
		}
		else if (guessednum > (Integer) session.getAttribute("randnum")) {
			session.setAttribute("output", "Too High!");
		}
		else {
		
			String victory = guessednum + " was the number!";
			session.setAttribute("output", victory);
			greenbox = true;
		}
		session.setAttribute("greenbox", greenbox);	
		request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
	
	}

}
