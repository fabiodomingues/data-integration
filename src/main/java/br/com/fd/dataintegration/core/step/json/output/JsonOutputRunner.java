package br.com.fd.dataintegration.core.step.json.output;

import br.com.fd.dataintegration.core.runner.FlowContext;
import br.com.fd.dataintegration.core.runner.Runner;
import br.com.fd.dataintegration.core.table.Cell;
import br.com.fd.dataintegration.core.table.Row;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import java.util.ArrayList;
import java.util.List;

public class JsonOutputRunner implements Runner<JsonOutput> {

	@Override
	public void run(JsonOutput jsonOutput, FlowContext context) {
		System.out.println("JsonOutputRunner.run(...)");

		List<Row> rows = (List<Row>) context.get(jsonOutput.getInputVariable());

		JsonArray jsonArray = new JsonArray();

		for (Row row : rows) {
			JsonObject jsonObject = new JsonObject();

			for (JsonOutputField field : jsonOutput.getFields()) {

				Cell cell = row.get(field.getName());
				switch (cell.getType()) {
					case STRING:
						jsonObject.add(cell.getName(), cell.getStringValue());
						break;
					case INTEGER:
						jsonObject.add(cell.getName(), cell.getIntegerValue());
						break;
					case NUMBER:
						jsonObject.add(cell.getName(), cell.getNumberValue());
						break;
					case BOOLEAN:
						jsonObject.add(cell.getName(), cell.getBooleanValue());
						break;
				}
			}

			jsonArray.add(jsonObject);
		}

		if (jsonOutput.isArray()) {
			String output;

			if (jsonOutput.getArrayElementName() != null && !jsonOutput.getArrayElementName().isEmpty()) {
				output = new JsonObject().add(jsonOutput.getArrayElementName() , jsonArray).toString();
			} else {
				output = jsonArray.toString();
			}

			context.put(jsonOutput.getOutputVariable(), output);
		} else {
			List<String> output = new ArrayList<>();

			for (JsonValue value : jsonArray.values()) {
				output.add(value.toString());
			}

			context.put(jsonOutput.getOutputVariable(), output);
		}
	}

}