package br.com.fd.dataintegration.core.step.join;

import br.com.fd.dataintegration.core.step.Step;

import java.util.List;

public class JoinTable extends Step {
	public static final String TYPE = "JoinTable";

	private String leftTable;
	private String rightTable;
	private List<String> keysLeftTable;
	private List<String> keysRightTable;
	private JoinType joinType;
	private String outputVariable;

	public JoinTable() {
		super(JoinTable.TYPE);
	}

	public String getLeftTable() {
		return leftTable;
	}

	public String getRightTable() {
		return rightTable;
	}

	public List<String> getKeysLeftTable() {
		return keysLeftTable;
	}

	public List<String> getKeysRightTable() {
		return keysRightTable;
	}

	public JoinType getJoinType() {
		return joinType;
	}

	public String getOutputVariable() {
		return outputVariable;
	}
}