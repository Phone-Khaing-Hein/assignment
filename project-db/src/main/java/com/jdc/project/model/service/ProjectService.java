package com.jdc.project.model.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import com.jdc.project.model.dto.Project;
import com.jdc.project.model.service.utils.ProjectHelper;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectHelper projectHelper;
	
	@Autowired
	private SimpleJdbcInsert projectInsert;
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private RowMapper<Project> rowMapper;
	
	public ProjectService() {
		rowMapper = new BeanPropertyRowMapper<Project>(Project.class);
	}
	
	public int create(Project project) {
		projectHelper.validate(project);
		return projectInsert.executeAndReturnKey(projectHelper.insertParams(project)).intValue();
	}

	public Project findById(int id) {
		return template.queryForObject("select p.id, p.name, p.description, p.manager managerId, m.name managerName, p.start startDate, p.months from project p join member m on m.id = p.manager where p.id = :id", 
										projectHelper.insertId(id), rowMapper);
	}

	public List<Project> search(String project, String manager, LocalDate dateFrom, LocalDate dateTo) {
		return template.query("select p.id, p.name, p.description, p.manager managerId, m.name managerName, p.start startDate, p.months from project p join member m on m.id = p.manager where p.name like :projectName or m.name like :managerName or p.start >= :from or p.start <= :to",
				projectHelper.insertKeyword(project, manager, dateFrom, dateTo), rowMapper);
	}

	public int update(int id, String name, String description, LocalDate startDate, int month) {
		return template.update("update project set name = :name, description = :description, start = :start, months = :months where id = :id", projectHelper.update(id, name, description, startDate, month));
	}

	public int deleteById(int id) {
		return template.update("delete from project where id = :id", projectHelper.insertId(id));
	}

}