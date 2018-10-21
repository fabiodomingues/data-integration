package br.com.fd.dataintegration.api.controllers;

import br.com.fd.dataintegration.core.project.Project;
import br.com.fd.dataintegration.core.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@GetMapping
	public ResponseEntity<List<Project>> getAll() {
		return ResponseEntity.ok(projectService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Project> get(@PathVariable String id) {
		return ResponseEntity.ok(projectService.getById(id));
	}

	@PostMapping
	public ResponseEntity<Project> create(@RequestBody Project project) {

		return ResponseEntity.status(HttpStatus.CREATED).body(projectService.create(project));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Project> update(@PathVariable String id, @RequestBody Project project) {
		return ResponseEntity.ok(projectService.update(project));
	}

}