package br.com.fd.dataintegration.core.runner;

import br.com.fd.dataintegration.core.step.JsonInput;
import br.com.fd.dataintegration.core.step.JsonInputField;
import br.com.fd.dataintegration.core.table.Cell;
import br.com.fd.dataintegration.core.table.Row;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import java.util.ArrayList;
import java.util.List;

public class JsonInputRunner implements Runner<JsonInput> {

	@Override
	public void run(JsonInput jsonInput, FlowContext context) {
		System.out.println("JsonInputRunner.run(...)");

		String json = (String) context.get(jsonInput.getInputVariable());
		System.out.println(jsonInput);

		ReadContext readContext = JsonPath.parse(json);

		int lastSize = -1;
		List<List<?>> results = new ArrayList<>(jsonInput.getFields().size());

		for (JsonInputField jsonInputField : jsonInput.getFields()) {
			List<Object> result = readContext.read(jsonInputField.getPath());

			if (result.size() != lastSize && lastSize > 0 && result.size() != 0) {
				throw new RuntimeException("JsonInput.Error.BadStructure");
			}

			results.add( result );
			lastSize = result.size();
		}

		List<Row> rows = new ArrayList<>();

		for (int rowIndex = 0; rowIndex < results.get(0).size(); rowIndex++) {
			Row row = new Row();

			for (int columnIndex = 0; columnIndex < results.size(); columnIndex++) {
				row.add(new Cell(jsonInput.getFields().get(columnIndex).getName(),
								 jsonInput.getFields().get(columnIndex).getType(),
								 results.get(columnIndex).get(rowIndex)));
			}

			rows.add(row);
		}

		context.put(jsonInput.getOutputVariable(), rows);
	}

}