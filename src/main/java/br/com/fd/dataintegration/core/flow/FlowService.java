package br.com.fd.dataintegration.core.flow;

import br.com.fd.dataintegration.core.runner.FlowRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowService {

	@Autowired FlowRepository flowRepository;
	@Autowired FlowRunner flowRunner;

	public List<Flow> getAll() {
		return flowRepository.findAll();
	}

	public Flow getById(String id) {
		return flowRepository.findById(id).orElseThrow(FlowNotFoundException::new);
	}

	public Flow create(Flow flow) {
		return flowRepository.save(flow);
	}

	public Flow update(Flow flow) {
		return flowRepository.save(flow);
	}

	public void run(String id) {
		flowRunner.run(getById(id));
	}
}
