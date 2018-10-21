package br.com.fd.dataintegration.core.table;

import java.util.HashMap;
import java.util.Map;

public class Row {

	private Map<String, Cell> cells;

	public Row() {
		this.cells = new HashMap<>();
	}

	public Cell get(String name) {
		return cells.get(name);
	}

	public void add(Cell cell) {
		cells.put(cell.getName(), cell);
	}

	@Override
	public String toString() {
		return "Row{" +
				"cells=" + cells +
				'}';
	}
}