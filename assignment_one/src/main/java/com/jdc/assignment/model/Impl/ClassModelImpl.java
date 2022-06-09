package com.jdc.assignment.model.Impl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.assignment.domain.Course;
import com.jdc.assignment.domain.OpenClass;
import com.jdc.assignment.model.ClassModel;

public class ClassModelImpl implements ClassModel{
	
	private DataSource ds;
	
	public ClassModelImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<OpenClass> fidByCourse(int courseId) {
		var list = new ArrayList<OpenClass>();
		try(var connection = ds.getConnection();
				var statement = connection.prepareStatement("""
						select cl.id, cl.start_date, cl.teacher, c.id courseId, c.fees, c.duration, c.description, c.name from classes cl
						join courses c on cl.course_id = c.id
						where c.id = ?
						""")){
			
			statement.setInt(1, courseId);
			var result = statement.executeQuery();
			while(result.next()) {
				var course = new Course();
				course.setId(result.getInt("courseId"));
				course.setName(result.getString("name"));
				course.setFees(result.getInt("fees"));
				course.setDuration(result.getInt("duration"));
				course.setDescription(result.getString("description"));
				
				var openClass = new OpenClass();
				openClass.setId(result.getInt("id"));
				openClass.setCourse(course);
				openClass.setStartDate(result.getDate("start_date").toLocalDate());
				openClass.setTeacher(result.getString("teacher"));
				
				list.add(openClass);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void save(OpenClass openClass) {
		try(var connection = ds.getConnection();
				var statement = connection.prepareStatement("insert into classes (course_id, start_date, teacher) values (?, ?, ?)")){
			
			statement.setInt(1, openClass.getCourse().getId());
			statement.setDate(2, Date.valueOf(openClass.getStartDate()));
			statement.setString(3, openClass.getTeacher());
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public OpenClass find(int id) {
		try(var connection = ds.getConnection();
				var statement = connection.prepareStatement("select * from classes where id = ?")){
			
			statement.setInt(1, id);
			var result = statement.executeQuery();
			var course = new CourseModelImpl(ds);
			
			while(result.next()) {
				var c = new OpenClass();
				c.setId(result.getInt("id"));
				c.setCourse(course.find(result.getInt("course_id")));
				c.setStartDate(result.getDate("start_date").toLocalDate());
				c.setTeacher(result.getString("teacher"));

				return c;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
