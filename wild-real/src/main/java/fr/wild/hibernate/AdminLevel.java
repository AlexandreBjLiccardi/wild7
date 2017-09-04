package fr.wild.hibernate;

// Generated 10 nov. 2015 15:14:18 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * AdminLevel generated by hbm2java
 */
public class AdminLevel implements java.io.Serializable {

	private short idAdminLevel;
	private String shortName;
	private String fullName;
	private String description;
	private Set<DropRightsManagement> dropRightsManagements = new HashSet<DropRightsManagement>(
			0);
	private Set<User> users = new HashSet<User>(0);
	private Set<JavaServiceInfo> javaServiceInfos = new HashSet<JavaServiceInfo>(
			0);
	private Set<ExecutionInfo> executionInfos = new HashSet<ExecutionInfo>(0);
	private Set<User> users_1 = new HashSet<User>(0);
	private Set<Preference> preferences = new HashSet<Preference>(0);
	private Set<User> users_2 = new HashSet<User>(0);

	public AdminLevel() {
	}

	public AdminLevel(short idAdminLevel, String shortName, String fullName) {
		this.idAdminLevel = idAdminLevel;
		this.shortName = shortName;
		this.fullName = fullName;
	}

	public AdminLevel(short idAdminLevel, String shortName, String fullName,
			String description,
			Set<DropRightsManagement> dropRightsManagements, Set<User> users,
			Set<JavaServiceInfo> javaServiceInfos,
			Set<ExecutionInfo> executionInfos, Set<User> users_1,
			Set<Preference> preferences, Set<User> users_2) {
		this.idAdminLevel = idAdminLevel;
		this.shortName = shortName;
		this.fullName = fullName;
		this.description = description;
		this.dropRightsManagements = dropRightsManagements;
		this.users = users;
		this.javaServiceInfos = javaServiceInfos;
		this.executionInfos = executionInfos;
		this.users_1 = users_1;
		this.preferences = preferences;
		this.users_2 = users_2;
	}

	public short getIdAdminLevel() {
		return this.idAdminLevel;
	}

	public void setIdAdminLevel(short idAdminLevel) {
		this.idAdminLevel = idAdminLevel;
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

	public Set<DropRightsManagement> getDropRightsManagements() {
		return this.dropRightsManagements;
	}

	public void setDropRightsManagements(
			Set<DropRightsManagement> dropRightsManagements) {
		this.dropRightsManagements = dropRightsManagements;
	}

	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<JavaServiceInfo> getJavaServiceInfos() {
		return this.javaServiceInfos;
	}

	public void setJavaServiceInfos(Set<JavaServiceInfo> javaServiceInfos) {
		this.javaServiceInfos = javaServiceInfos;
	}

	public Set<ExecutionInfo> getExecutionInfos() {
		return this.executionInfos;
	}

	public void setExecutionInfos(Set<ExecutionInfo> executionInfos) {
		this.executionInfos = executionInfos;
	}

	public Set<User> getUsers_1() {
		return this.users_1;
	}

	public void setUsers_1(Set<User> users_1) {
		this.users_1 = users_1;
	}

	public Set<Preference> getPreferences() {
		return this.preferences;
	}

	public void setPreferences(Set<Preference> preferences) {
		this.preferences = preferences;
	}

	public Set<User> getUsers_2() {
		return this.users_2;
	}

	public void setUsers_2(Set<User> users_2) {
		this.users_2 = users_2;
	}

}
