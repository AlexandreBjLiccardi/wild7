package fr.wild.hibernate;

// Generated 10 nov. 2015 15:14:18 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * PreferencesGroup generated by hbm2java
 */
public class PreferencesGroup implements java.io.Serializable {

	private int idPreferencesGroup;
	private String preferencesGroupLabel;
	private String preferencesGroupDescription;
	private Set<Preference> preferences = new HashSet<Preference>(0);

	public PreferencesGroup() {
	}

	public PreferencesGroup(int idPreferencesGroup, String preferencesGroupLabel) {
		this.idPreferencesGroup = idPreferencesGroup;
		this.preferencesGroupLabel = preferencesGroupLabel;
	}

	public PreferencesGroup(int idPreferencesGroup,
			String preferencesGroupLabel, String preferencesGroupDescription,
			Set<Preference> preferences) {
		this.idPreferencesGroup = idPreferencesGroup;
		this.preferencesGroupLabel = preferencesGroupLabel;
		this.preferencesGroupDescription = preferencesGroupDescription;
		this.preferences = preferences;
	}

	public int getIdPreferencesGroup() {
		return this.idPreferencesGroup;
	}

	public void setIdPreferencesGroup(int idPreferencesGroup) {
		this.idPreferencesGroup = idPreferencesGroup;
	}

	public String getPreferencesGroupLabel() {
		return this.preferencesGroupLabel;
	}

	public void setPreferencesGroupLabel(String preferencesGroupLabel) {
		this.preferencesGroupLabel = preferencesGroupLabel;
	}

	public String getPreferencesGroupDescription() {
		return this.preferencesGroupDescription;
	}

	public void setPreferencesGroupDescription(
			String preferencesGroupDescription) {
		this.preferencesGroupDescription = preferencesGroupDescription;
	}

	public Set<Preference> getPreferences() {
		return this.preferences;
	}

	public void setPreferences(Set<Preference> preferences) {
		this.preferences = preferences;
	}

}
