package fr.wild.hibernate;

// Generated 10 nov. 2015 15:14:18 by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * River generated by hbm2java
 */
public class River implements java.io.Serializable {

	private String idRiver;
	private User userByIdUserAdmin;
	private User userByIdUserCreator;
	private River river;
	private Approval approval;
	private String shortName;
	private String fullName;
	private String description;
	private String tags;
	private Set<River> rivers = new HashSet<River>(0);
	private Set<Drop> drops = new HashSet<Drop>(0);

	public River() {
	}

	public River(String idRiver, User userByIdUserAdmin,
			User userByIdUserCreator, Approval approval) {
		this.idRiver = idRiver;
		this.userByIdUserAdmin = userByIdUserAdmin;
		this.userByIdUserCreator = userByIdUserCreator;
		this.approval = approval;
	}

	public River(String idRiver, User userByIdUserAdmin,
			User userByIdUserCreator, River river, Approval approval,
			String shortName, String fullName, String description,
			String tags, Set<River> rivers, Set<Drop> drops) {
		this.idRiver = idRiver;
		this.userByIdUserAdmin = userByIdUserAdmin;
		this.userByIdUserCreator = userByIdUserCreator;
		this.river = river;
		this.approval = approval;
		this.shortName = shortName;
		this.fullName = fullName;
		this.description = description;
		this.tags = tags;
		this.rivers = rivers;
		this.drops = drops;
	}

	public String getIdRiver() {
		return this.idRiver;
	}

	public void setIdRiver(String idRiver) {
		this.idRiver = idRiver;
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

	public River getRiver() {
		return this.river;
	}

	public void setRiver(River river) {
		this.river = river;
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

	public Set<River> getRivers() {
		return this.rivers;
	}

	public void setRivers(Set<River> rivers) {
		this.rivers = rivers;
	}

	public Set<Drop> getDrops() {
		return this.drops;
	}

	public void setDrops(Set<Drop> drops) {
		this.drops = drops;
	}

}
