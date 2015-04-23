package com.gojava2.kickstarter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gojava2.kickstarter.entity.Category;
import com.gojava2.kickstarter.entity.Project;
import com.gojava2.kickstarter.repository.CategoryRepository;
import com.gojava2.kickstarter.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	public List<Project> getAll(int id) {
		Category category = categoryRepository.findOne(id);
        return projectRepository.findByCategory(category, new PageRequest(0, 20, Direction.DESC, "id"));
	}
	
	public Project get(int id) {
		return projectRepository.findOne(id);
	}
}