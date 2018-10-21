package br.com.fd.dataintegration.core.step.mongodb;

import br.com.fd.dataintegration.core.runner.FlowContext;
import br.com.fd.dataintegration.core.runner.Runner;
import br.com.fd.dataintegration.core.table.Cell;
import br.com.fd.dataintegration.core.table.Row;
import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDBOutputRunner implements Runner<MongoDBOutput> {

	@Override
	public void run(MongoDBOutput mongoDBOutput, FlowContext context) {
		try (MongoClient mongoClient = new MongoClient(mongoDBOutput.getHost(), mongoDBOutput.getPort())) {
			MongoDatabase db = mongoClient.getDatabase(mongoDBOutput.getDatabase());
			db.getCollection(mongoDBOutput.getCollection()).insertMany(getObjects(mongoDBOutput, context));
		}
	}

	private List<Document> getObjects(MongoDBOutput mongoDBOutput, FlowContext context) {
		List<Row> rows = (List<Row>) context.get(mongoDBOutput.getInputVariable());

		List<Document> result = new ArrayList<>();

		for (Row row : rows) {
			Document dbObject = new Document();

			for (MongoDBFields field : mongoDBOutput.getFields()) {
				Cell cell = row.get(field.getName());
				dbObject.append(field.getName(), cell.getValue());
			}

			result.add(dbObject);
		}

		return result;
	}

}