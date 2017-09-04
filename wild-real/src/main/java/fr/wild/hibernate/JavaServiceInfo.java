package fr.wild.hibernate;

// Generated 10 nov. 2015 15:14:18 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * JavaServiceInfo generated by hbm2java
 */
public class JavaServiceInfo implements java.io.Serializable {

	private long idJavaServiceInfo;
	private JavaService javaService;
	private AdminLevel adminLevel;
	private Criticity criticity;
	private Date dateInfo;
	private String contents;

	public JavaServiceInfo() {
	}

	public JavaServiceInfo(long idJavaServiceInfo, JavaService javaService,
			AdminLevel adminLevel, Criticity criticity, Date dateInfo) {
		this.idJavaServiceInfo = idJavaServiceInfo;
		this.javaService = javaService;
		this.adminLevel = adminLevel;
		this.criticity = criticity;
		this.dateInfo = dateInfo;
	}

	public JavaServiceInfo(long idJavaServiceInfo, JavaService javaService,
			AdminLevel adminLevel, Criticity criticity, Date dateInfo,
			String contents) {
		this.idJavaServiceInfo = idJavaServiceInfo;
		this.javaService = javaService;
		this.adminLevel = adminLevel;
		this.criticity = criticity;
		this.dateInfo = dateInfo;
		this.contents = contents;
	}

	public long getIdJavaServiceInfo() {
		return this.idJavaServiceInfo;
	}

	public void setIdJavaServiceInfo(long idJavaServiceInfo) {
		this.idJavaServiceInfo = idJavaServiceInfo;
	}

	public JavaService getJavaService() {
		return this.javaService;
	}

	public void setJavaService(JavaService javaService) {
		this.javaService = javaService;
	}

	public AdminLevel getAdminLevel() {
		return this.adminLevel;
	}

	public void setAdminLevel(AdminLevel adminLevel) {
		this.adminLevel = adminLevel;
	}

	public Criticity getCriticity() {
		return this.criticity;
	}

	public void setCriticity(Criticity criticity) {
		this.criticity = criticity;
	}

	public Date getDateInfo() {
		return this.dateInfo;
	}

	public void setDateInfo(Date dateInfo) {
		this.dateInfo = dateInfo;
	}

	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

}
