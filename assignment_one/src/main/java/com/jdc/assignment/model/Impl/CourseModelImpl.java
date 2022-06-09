package com.jdc.assignment.model.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.assignment.domain.Course;
import com.jdc.assignment.model.CourseModel;

public class CourseModelImpl implements CourseModel{
	
	private DataSource ds;
	
	public CourseModelImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<Course> getAll() {
		List<Course> list = new ArrayList<Course>();
		
		try(var connection = ds.getConnection();
				var statement = connection.prepareStatement("select * from courses")){
			
			var result = statement.executeQuery();
			while(result.next()) {
				var course = new Course();
				course.setId(result.getInt("id"));
				course.setName(result.getString("name"));
				course.setFees(result.getInt("fees"));
				course.setDuration(result.getInt("duration"));
				course.setDescription(result.getString("description"));
				
				list.add(course);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public void save(Course course) {
		try(var connection = ds.getConnection();
				var statement = connection.prepareStatement("insert into courses (name, fees, duration, description) values (?, ?, ?, ?)")){
			
			statement.setString(1, course.getName());
			statement.setInt(2, course.getFees());
			statement.setInt(3, course.getDuration());
			statement.setString(4, course.getDescription());
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Course find(int id) {
		try(var connection = ds.getConnection();
				var statement = connection.prepareStatement("select * from courses where id = ?")){
			
			statement.setInt(1, id);
			var result = statement.executeQuery();
			while(result.next()) {
				var course = new Course();
				course.setId(result.getInt("id"));
				course.setName(result.getString("name"));
				course.setFees(result.getInt("fees"));
				course.setDuration(result.getInt("duration"));
				course.setDescription(result.getString("description"));

				return course;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
