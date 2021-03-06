package fr.wild.hibernate;

// Generated 16 nov. 2015 11:38:05 by Hibernate Tools 3.4.0.CR1

/**
 * ReferenceFile generated by hbm2java
 */
public class ReferenceFile implements java.io.Serializable {

	private ReferenceFileId id;
	private UserFile userFile;
	private ReferenceConcept referenceConcept;
	private String searchModality;

	public ReferenceFile() {
	}

	public ReferenceFile(ReferenceFileId id, UserFile userFile,
			ReferenceConcept referenceConcept, String searchModality) {
		this.id = id;
		this.userFile = userFile;
		this.referenceConcept = referenceConcept;
		this.searchModality = searchModality;
	}

	public ReferenceFileId getId() {
		return this.id;
	}

	public void setId(ReferenceFileId id) {
		this.id = id;
	}

	public UserFile getUserFile() {
		return this.userFile;
	}

	public void setUserFile(UserFile userFile) {
		this.userFile = userFile;
	}

	public ReferenceConcept getReferenceConcept() {
		return this.referenceConcept;
	}

	public void setReferenceConcept(ReferenceConcept referenceConcept) {
		this.referenceConcept = referenceConcept;
	}

	public String getSearchModality() {
		return this.searchModality;
	}

	public void setSearchModality(String searchModality) {
		this.searchModality = searchModality;
	}

}
