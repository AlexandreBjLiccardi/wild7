package fr.wild.hibernate;

// Generated 10 nov. 2015 15:14:18 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * ReadPrivateMessage generated by hbm2java
 */
public class ReadPrivateMessage implements java.io.Serializable {

	private ReadPrivateMessageId id;
	private PrivateMessage privateMessage;
	private User user;
	private Date dateRead;
	private String contents;

	public ReadPrivateMessage() {
	}

	public ReadPrivateMessage(ReadPrivateMessageId id,
			PrivateMessage privateMessage, User user) {
		this.id = id;
		this.privateMessage = privateMessage;
		this.user = user;
	}

	public ReadPrivateMessage(ReadPrivateMessageId id,
			PrivateMessage privateMessage, User user, Date dateRead,
			String contents) {
		this.id = id;
		this.privateMessage = privateMessage;
		this.user = user;
		this.dateRead = dateRead;
		this.contents = contents;
	}

	public ReadPrivateMessageId getId() {
		return this.id;
	}

	public void setId(ReadPrivateMessageId id) {
		this.id = id;
	}

	public PrivateMessage getPrivateMessage() {
		return this.privateMessage;
	}

	public void setPrivateMessage(PrivateMessage privateMessage) {
		this.privateMessage = privateMessage;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDateRead() {
		return this.dateRead;
	}

	public void setDateRead(Date dateRead) {
		this.dateRead = dateRead;
	}

	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

}
