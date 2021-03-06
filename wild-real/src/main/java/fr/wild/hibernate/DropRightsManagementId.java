package fr.wild.hibernate;

// Generated 10 nov. 2015 15:14:18 by Hibernate Tools 3.4.0.CR1

/**
 * DropRightsManagementId generated by hbm2java
 */
public class DropRightsManagementId implements java.io.Serializable {

	private short idAdminLevel;
	private String idCgiItem;
	private String idCgiAbility;

	public DropRightsManagementId() {
	}

	public DropRightsManagementId(short idAdminLevel, String idCgiItem,
			String idCgiAbility) {
		this.idAdminLevel = idAdminLevel;
		this.idCgiItem = idCgiItem;
		this.idCgiAbility = idCgiAbility;
	}

	public short getIdAdminLevel() {
		return this.idAdminLevel;
	}

	public void setIdAdminLevel(short idAdminLevel) {
		this.idAdminLevel = idAdminLevel;
	}

	public String getIdCgiItem() {
		return this.idCgiItem;
	}

	public void setIdCgiItem(String idCgiItem) {
		this.idCgiItem = idCgiItem;
	}

	public String getIdCgiAbility() {
		return this.idCgiAbility;
	}

	public void setIdCgiAbility(String idCgiAbility) {
		this.idCgiAbility = idCgiAbility;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DropRightsManagementId))
			return false;
		DropRightsManagementId castOther = (DropRightsManagementId) other;

		return (this.getIdAdminLevel() == castOther.getIdAdminLevel())
				&& ((this.getIdCgiItem() == castOther.getIdCgiItem()) || (this
						.getIdCgiItem() != null
						&& castOther.getIdCgiItem() != null && this
						.getIdCgiItem().equals(castOther.getIdCgiItem())))
				&& ((this.getIdCgiAbility() == castOther.getIdCgiAbility()) || (this
						.getIdCgiAbility() != null
						&& castOther.getIdCgiAbility() != null && this
						.getIdCgiAbility().equals(castOther.getIdCgiAbility())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdAdminLevel();
		result = 37 * result
				+ (getIdCgiItem() == null ? 0 : this.getIdCgiItem().hashCode());
		result = 37
				* result
				+ (getIdCgiAbility() == null ? 0 : this.getIdCgiAbility()
						.hashCode());
		return result;
	}

}
