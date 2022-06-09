package com.jdc.assignment.model;

import java.util.List;

import com.jdc.assignment.domain.OpenClass;

public interface ClassModel {
	List<OpenClass> fidByCourse(int courseId);
	void save(OpenClass openClass);
	OpenClass find(int id);
}
