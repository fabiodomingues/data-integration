package br.com.fd.dataintegration.core.step.mongodb.input;

import br.com.fd.dataintegration.core.runner.FlowContext;
import br.com.fd.dataintegration.core.runner.Runner;
import br.com.fd.dataintegration.core.table.Cell;
import br.com.fd.dataintegration.core.table.Row;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDBInputRunner implements Runner<MongoDBInput> {

	@Override
	public void run(MongoDBInput mongoDBInput, FlowContext context) {
		try (MongoClient mongoClient = new MongoClient(mongoDBInput.getHost(), mongoDBInput.getPort())) {
			MongoDatabase db = mongoClient.getDatabase(mongoDBInput.getDatabase());

			Document documentQuery = Document.parse(mongoDBInput.getQuery() == null || mongoDBInput.getQuery().isEmpty() ? "{}" : mongoDBInput.getQuery());
			FindIterable<Document> documents = db.getCollection(mongoDBInput.getCollection()).find(documentQuery);

			documents.projection(getFieldsProjection(mongoDBInput));

			MongoCursor<Document> iterator = documents.iterator();

			List<Row> rows = new ArrayList<>();

			while (iterator.hasNext()) {
				Document document = iterator.next();
				Row row = new Row();

				for (MongoDBInputField field : mongoDBInput.getFields()) {
					Object value = null;

					switch (field.getType()) {
						case STRING:
							value = document.getString(field.getName());
							break;
						case INTEGER:
							value = document.getInteger(field.getName());
							break;
						case NUMBER:
							value = document.getDouble(field.getName());
							break;
						case BOOLEAN:
							value = document.getBoolean(field.getName());
							break;
					}

					row.add(new Cell(field.getName(),
									 field.getType(),
									 value));
				}

				rows.add(row);
			}

			context.put(mongoDBInput.getOutputVariable(), rows);
		}
	}

	private Document getFieldsProjection(MongoDBInput mongoDBInput) {
		Document document = new Document();

		for (MongoDBInputField field : mongoDBInput.getFields()) {
			document.append(field.getName(), 1);
		}

		return document;
	}

}