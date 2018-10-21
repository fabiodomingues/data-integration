package br.com.fd.dataintegration.core.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

	@Autowired ProjectRepository projectRepository;

	public List<Project> getAll() {
		return projectRepository.findAll();
	}

	public Project getById(String id) {
		return projectRepository.findById(id).orElseThrow(ProjectNotFoundException::new);
	}

	public Project create(Project project) {
		return projectRepository.save(project);
	}

	public Project update(Project project) {
		return projectRepository.save(project);
	}

}
