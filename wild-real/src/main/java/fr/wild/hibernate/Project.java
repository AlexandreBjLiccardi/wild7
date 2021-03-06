package fr.wild.hibernate;

// Generated 10 nov. 2015 15:14:18 by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Project generated by hbm2java
 */
public class Project implements java.io.Serializable {

	private String idProject;
	private User userByIdUserAdmin;
	private User userByIdUserCreator;
	private Approval approval;
	private String shortName;
	private String fullName;
	private String description;
	private String tags;
	private Set<Drop> drops = new HashSet<Drop>(0);

	public Project() {
	}

	public Project(String idProject, User userByIdUserAdmin,
			User userByIdUserCreator, Approval approval) {
		this.idProject = idProject;
		this.userByIdUserAdmin = userByIdUserAdmin;
		this.userByIdUserCreator = userByIdUserCreator;
		this.approval = approval;
	}

	public Project(String idProject, User userByIdUserAdmin,
			User userByIdUserCreator, Approval approval, String shortName,
			String fullName, String description, String tags,
			Set<Drop> drops) {
		this.idProject = idProject;
		this.userByIdUserAdmin = userByIdUserAdmin;
		this.userByIdUserCreator = userByIdUserCreator;
		this.approval = approval;
		this.shortName = shortName;
		this.fullName = fullName;
		this.description = description;
		this.tags = tags;
		this.drops = drops;
	}

	public String getIdProject() {
		return this.idProject;
	}

	public void setIdProject(String idProject) {
		this.idProject = idProject;
	}

	public User getUserByIdUserAdmin() {
		return this.userByIdUserAdmin;
	}

	public void setUserByIdUserAdmin(User userByIdUserAdmin) {
		this.userByIdUserAdmin = userByIdUserAdmin;
	}

	public User getUserByIdUserCreator() {
		return this.userByIdUserCreator;
	}

	public void setUserByIdUserCreator(User userByIdUserCreator) {
		this.userByIdUserCreator = userByIdUserCreator;
	}

	public Approval getApproval() {
		return this.approval;
	}

	public void setApproval(Approval approval) {
		this.approval = approval;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTags() {
		return this.tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Set<Drop> getDrops() {
		return this.drops;
	}

	public void setDrops(Set<Drop> drops) {
		this.drops = drops;
	}

}
