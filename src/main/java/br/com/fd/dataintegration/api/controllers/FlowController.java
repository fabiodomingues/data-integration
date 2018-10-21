package br.com.fd.dataintegration.api.controllers;

import br.com.fd.dataintegration.core.flow.Flow;
import br.com.fd.dataintegration.core.flow.FlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flows")
public class FlowController {

	@Autowired
	private FlowService flowService;

	@GetMapping
	public ResponseEntity<List<Flow>> getAll() {
		return ResponseEntity.ok(flowService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Flow> getById(@PathVariable String id) {
		return ResponseEntity.ok(flowService.getById(id));
	}

	@GetMapping("/{id}/run")
	public ResponseEntity<String> run(@PathVariable String id) {
		flowService.run(id);
		return ResponseEntity.ok("OK");
	}

	@PostMapping
	public ResponseEntity<Flow> create(@RequestBody Flow flow) {

		return ResponseEntity.status(HttpStatus.CREATED).body(flowService.create(flow));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Flow> update(@PathVariable String id, @RequestBody Flow flow) {
		return ResponseEntity.ok(flowService.update(flow));
	}

}