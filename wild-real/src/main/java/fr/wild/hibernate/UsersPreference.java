package fr.wild.hibernate;

// Generated 10 nov. 2015 15:14:18 by Hibernate Tools 3.4.0.CR1

/**
 * UsersPreference generated by hbm2java
 */
public class UsersPreference implements java.io.Serializable {

	private UsersPreferenceId id;
	private Preference preference;
	private User user;
	private String value;

	public UsersPreference() {
	}

	public UsersPreference(UsersPreferenceId id, Preference preference,
			User user) {
		this.id = id;
		this.preference = preference;
		this.user = user;
	}

	public UsersPreference(UsersPreferenceId id, Preference preference,
			User user, String value) {
		this.id = id;
		this.preference = preference;
		this.user = user;
		this.value = value;
	}

	public UsersPreferenceId getId() {
		return this.id;
	}

	public void setId(UsersPreferenceId id) {
		this.id = id;
	}

	public Preference getPreference() {
		return this.preference;
	}

	public void setPreference(Preference preference) {
		this.preference = preference;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
