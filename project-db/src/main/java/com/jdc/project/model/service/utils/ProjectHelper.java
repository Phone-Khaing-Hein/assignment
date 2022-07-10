package com.jdc.project.model.service.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.jdc.project.model.ProjectDbException;
import com.jdc.project.model.dto.Project;

@Component
public class ProjectHelper {
	
	@Value("${project.empty.name}")
	private String noName;
	@Value("${project.empty.manager}")
	private String noManager;
	@Value("${project.empty.start}")
	private String noStartDate;	

		
	public void validate(Project dto) {
		if(!StringUtils.hasLength(dto.getName())){
			throw new ProjectDbException(noName);
		}
		
		if(dto.getManagerId() == 0){
			throw new ProjectDbException(noManager);
		}
		
		if(dto.getStartDate() == null){
			throw new ProjectDbException(noStartDate);
		}
	}

	public Map<String, Object> insertParams(Project dto) {
		var params = new HashMap<String, Object>();
		params.put("name", dto.getName());
		params.put("description", dto.getDescription());
		params.put("manager", dto.getManagerId());
		params.put("start", Date.valueOf(dto.getStartDate()));
		params.put("months", dto.getMonths());
		return params;
	}
	
	public MapSqlParameterSource insertId(int id) {
		var params = new MapSqlParameterSource();
		params.addValue("id", id);
		return params;
	}
	
	public MapSqlParameterSource insertKeyword(String project, String manager, LocalDate dateFrom, LocalDate dateTo) {
		var params = new MapSqlParameterSource();
		
		if(project == null && manager == null && dateFrom == null && dateTo == null) {
			params.addValue("projectName", "%%");
			params.addValue("managerName", "%%");
			params.addValue("to", null);
			params.addValue("from", null);
		}else {
			if(project != null) {
				params.addValue("projectName", "%".concat(project).concat("%"));
			}else {
				params.addValue("projectName", "%!@#$#@!");
			}
			
			if(manager != null) {
				params.addValue("managerName", "%".concat(manager).concat("%"));
			}else {
				params.addValue("managerName", "%!@#$#@!");
			}
			
			if(dateFrom != null) {
				params.addValue("from", Date.valueOf(dateFrom));
			}else {
				params.addValue("from", null);
			}
			
			if(dateTo != null) {
				params.addValue("to", Date.valueOf(dateTo));
			}else {
				params.addValue("to", null);
			}
		}
		
		return params;
	}
	
	public MapSqlParameterSource update(int id, String name, String description, LocalDate startDate, int month) {
		var params = new MapSqlParameterSource();
		params.addValue("id", id);
		params.addValue("name", name);
		params.addValue("description", description);
		params.addValue("start", startDate);
		params.addValue("months", month);
		return params;
	}
}