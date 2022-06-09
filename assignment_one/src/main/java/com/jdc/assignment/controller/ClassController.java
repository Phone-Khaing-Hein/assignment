package com.jdc.assignment.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdc.assignment.domain.OpenClass;
import com.jdc.assignment.model.ClassModel;
import com.jdc.assignment.model.CourseModel;

@WebServlet({
	"/classes",
	"/class-edit"
})
public class ClassController extends AbstractBeanFactoryServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var page = switch(req.getServletPath()) {
		case "/classes" ->{
			var courseId = req.getParameter("courseId");
			
			var course = getBean("courseModel", CourseModel.class).find(Integer.parseInt(courseId));
			req.setAttribute("course", course);
			var classes = getBean("classModel", ClassModel.class).fidByCourse(Integer.parseInt(courseId));
			req.setAttribute("classes", classes);
			yield "/classes.jsp";
		}
		default -> "/class-edit.jsp";
		};
		
		getServletContext().getRequestDispatcher(page).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var courseId = req.getParameter("courseId");
		var startDate = req.getParameter("startDate");
		var teacher  = req.getParameter("teacher");
		
		var c = new OpenClass();
		c.setCourse(getBean("courseModel", CourseModel.class).find(Integer.parseInt(courseId)));
		c.setStartDate(LocalDate.parse(startDate));
		c.setTeacher(teacher);
		
		
		getBean("classModel", ClassModel.class).save(c);
		
		resp.sendRedirect("/classes?courseId=%s".formatted(courseId));
	}
}
