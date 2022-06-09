package com.jdc.assignment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdc.assignment.domain.Registration;
import com.jdc.assignment.model.ClassModel;
import com.jdc.assignment.model.RegistrationModel;

@WebServlet({
	"/registrations",
	"/registration-edit"
})
public class RegistrationServlet extends AbstractBeanFactoryServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var page = switch(req.getServletPath()) {
		case "/registrations" ->{
			var classId = req.getParameter("classId");
			
			var openClass = getBean("classModel", ClassModel.class).find(Integer.parseInt(classId));
			req.setAttribute("openClass", openClass);
			
			var registrations = getBean("registrationModel", RegistrationModel.class).findByClass(Integer.parseInt(classId));
			req.setAttribute("registrations", registrations);
			
			yield "/registrations.jsp";
		}
		default -> "/registration-edit.jsp";
		};
		
		getServletContext().getRequestDispatcher(page).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var student = req.getParameter("student");
		var phone = req.getParameter("phone");
		var email = req.getParameter("email");
		var classId = req.getParameter("classId");
		
		var registration = new Registration();
		registration.setOpenClass(getBean("classModel", ClassModel.class).find(Integer.parseInt(classId)));
		registration.setStudent(student);
		registration.setPhone(phone);
		registration.setEmail(email);
		
		getBean("registrationModel", RegistrationModel.class).save(registration);
		resp.sendRedirect("/registrations?classId=%s".formatted(classId));
	}
}
