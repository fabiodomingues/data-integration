package br.com.fd.dataintegration.core.table;

import br.com.fd.dataintegration.core.DataType;
import com.sun.org.apache.xpath.internal.operations.Bool;

public class Cell {

	private String name;
	private DataType type;
	private Object value;

	public Cell(String name, DataType type, Object value) {
		this.name = name;
		this.type = type;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public DataType getType() {
		return type;
	}

	public Object getValue() {
		return value;
	}

	public String getStringValue() {
		if (value == null) {
			return null;
		}

		return (String) value;
	}

	public Integer getIntegerValue() {
		if (value == null) {
			return null;
		}

		return (Integer) value;
	}

	public Double getNumberValue() {
		if (value == null) {
			return null;
		}

		return (Double) value;
	}

	public Boolean getBooleanValue() {
		if (value == null) {
			return null;
		}

		return (Boolean) value;
	}
}
