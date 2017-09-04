package fr.wild.hibernate;

// Generated 16 nov. 2015 18:21:27 by Hibernate Tools 3.4.0.CR1

/**
 * ModelsExecution generated by hbm2java
 */
public class ModelsExecution implements java.io.Serializable {

	private ModelsExecutionId id;
	private ExecutionList executionList;
	private Model model;

	public ModelsExecution() {
	}

	public ModelsExecution(ModelsExecutionId id) {
		this.id = id;
	}

	public ModelsExecution(ModelsExecutionId id, ExecutionList executionList,
			Model model) {
		this.id = id;
		this.executionList = executionList;
		this.model = model;
	}

	public ModelsExecutionId getId() {
		return this.id;
	}

	public void setId(ModelsExecutionId id) {
		this.id = id;
	}

	public ExecutionList getExecutionList() {
		return this.executionList;
	}

	public void setExecutionList(ExecutionList executionList) {
		this.executionList = executionList;
	}

	public Model getModel() {
		return this.model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

}
