package br.com.fd.dataintegration.core.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Row {

	private Map<String, Cell> cells;

	public Row() {
		this.cells = new HashMap<>();
	}

	public List<Cell> getCells() {
		return new ArrayList<>(cells.values());
	}

	public boolean contains(String name) {
		return cells.containsKey(name);
	}

	public Cell get(String name) {
		return cells.get(name);
	}

	public void add(List<Cell> cells) {
		for (Cell cell : cells) {
			add(cell);
		}
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