package br.com.fd.dataintegration.core.step.join;

import br.com.fd.dataintegration.core.runner.FlowContext;
import br.com.fd.dataintegration.core.runner.Runner;
import br.com.fd.dataintegration.core.table.Cell;
import br.com.fd.dataintegration.core.table.Row;

import java.util.ArrayList;
import java.util.List;

public class JoinTableRunner implements Runner<JoinTable> {

	@Override
	public void run(JoinTable joinTable, FlowContext context) {
		if (
				(joinTable.getKeysLeftTable() == null || joinTable.getKeysRightTable() == null) ||
				(joinTable.getKeysLeftTable().isEmpty() || joinTable.getKeysRightTable().isEmpty()) ||
				(joinTable.getKeysLeftTable().size() != joinTable.getKeysRightTable().size())
		) {
			throw new RuntimeException("JoinTableRunner.Error.BadStructure");
		}

		List<Row> leftRows = (List<Row>) context.get(joinTable.getLeftTable());
		List<Row> righthRows = (List<Row>) context.get(joinTable.getRightTable());

		List<Row> result = new ArrayList<>();

		switch (joinTable.getJoinType()) {
			case INNER: result = innerJoin(leftRows, righthRows, joinTable.getKeysLeftTable(), joinTable.getKeysRightTable()); break;
		}

		context.put(joinTable.getOutputVariable(), result);
	}

	private List<Row> innerJoin(List<Row> leftRows, List<Row> rightRows, List<String> keysLeftTable, List<String> keysRightTable) {
		List<Row> result = new ArrayList<>();

		for (Row leftRow : leftRows) {
			for (Row rightRow : rightRows) {
				boolean isTrue = false;

				for (int i = 0; i < keysLeftTable.size(); i++) {
					Cell leftCell = leftRow.get(keysLeftTable.get(i));
					Cell rightCell = rightRow.get(keysRightTable.get(i));

					if (leftCell.getValue().equals(rightCell.getValue())) {
						if (i == 0) {
							isTrue = true;
						}
					} else {
						isTrue = false;
					}
				}

				if (isTrue) {
					result.add(merge(leftRow, rightRow));
				}
			}
		}

		return result;
	}

	private Row merge(Row leftRow, Row rightRow) {
		Row row = new Row();

		for (Cell cell : leftRow.getCells()) {
			row.add(new Cell(cell.getName(),
							 cell.getType(),
							 cell.getValue()));
		}

		for (Cell cell : rightRow.getCells()) {
			String name = cell.getName();

			if (row.contains(name)) {
				name += "_1";
			}

			row.add(new Cell(name,
							 cell.getType(),
							 cell.getValue()));
		}

		row.add(rightRow.getCells());

		return row;
	}

}