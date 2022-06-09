package com.jdc.assignment.model.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.assignment.domain.Course;
import com.jdc.assignment.domain.OpenClass;
import com.jdc.assignment.domain.Registration;
import com.jdc.assignment.model.RegistrationModel;

public class RegistrationModelImpl implements RegistrationModel{

	private DataSource ds;
	
	public RegistrationModelImpl(DataSource ds) {
		this.ds = ds;
	}
	
	@Override
	public List<Registration> findByClass(int classId) {
		var list = new ArrayList<Registration>();
		try(var connection = ds.getConnection();
				var statement = connection.prepareStatement("""
						select r.id, r.student, r.phone, r.email, c.id classId, c.start_date, c.teacher, c.course_id, cs.id courseId, cs.name, cs.fees, cs.duration, description from registrations r
						join classes c on r.class_id = c.id
						join courses cs on c.course_id = cs.id
						where c.id = ?
						""")){
			
			statement.setInt(1, classId);
			var result = statement.executeQuery();
			while(result.next()) {
				var course = new Course();
				course.setId(result.getInt("courseId"));
				course.setName(result.getString("name"));
				course.setFees(result.getInt("fees"));
				course.setDuration(result.getInt("duration"));
				course.setDescription(result.getString("description"));
				
				var openClass = new OpenClass();
				openClass.setId(result.getInt("classId"));
				openClass.setCourse(course);
				openClass.setStartDate(result.getDate("start_date").toLocalDate());
				openClass.setTeacher(result.getString("teacher"));
				
				var r = new Registration();
				r.setId(result.getInt("id"));
				r.setOpenClass(openClass);
				r.setStudent(result.getString("student"));
				r.setEmail(result.getString("email"));
				r.setPhone(result.getString("phone"));
				
				list.add(r);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void save(Registration r) {
		try(var connection = ds.getConnection();
				var statement = connection.prepareStatement("insert into registrations (class_id, student, phone, email) values (?, ?, ?, ?)")){
			
			statement.setInt(1, r.getOpenClass().getId());
			statement.setString(2, r.getStudent());
			statement.setString(3, r.getPhone());
			statement.setString(4, r.getEmail());
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
