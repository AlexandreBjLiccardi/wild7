package fr.wild.hibernate;

// Generated 10 nov. 2015 15:14:18 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CommentFile generated by hbm2java
 */
public class CommentFile implements java.io.Serializable {

	private long idCommentFile;
	private CommentFile commentFile;
	private UserFile userFile;
	private User user;
	private Approval approval;
	private Date dateCreation;
	private Date dateLastModif;
	private String contents;
	private Set<CommentFile> commentFiles = new HashSet<CommentFile>(0);

	public CommentFile() {
	}

	public CommentFile(long idCommentFile, CommentFile commentFile,
			UserFile userFile, User user, Approval approval, Date dateCreation) {
		this.idCommentFile = idCommentFile;
		this.commentFile = commentFile;
		this.userFile = userFile;
		this.user = user;
		this.approval = approval;
		this.dateCreation = dateCreation;
	}

	public CommentFile(long idCommentFile, CommentFile commentFile,
			UserFile userFile, User user, Approval approval, Date dateCreation,
			Date dateLastModif, String contents, Set<CommentFile> commentFiles) {
		this.idCommentFile = idCommentFile;
		this.commentFile = commentFile;
		this.userFile = userFile;
		this.user = user;
		this.approval = approval;
		this.dateCreation = dateCreation;
		this.dateLastModif = dateLastModif;
		this.contents = contents;
		this.commentFiles = commentFiles;
	}

	public long getIdCommentFile() {
		return this.idCommentFile;
	}

	public void setIdCommentFile(long idCommentFile) {
		this.idCommentFile = idCommentFile;
	}

	public CommentFile getCommentFile() {
		return this.commentFile;
	}

	public void setCommentFile(CommentFile commentFile) {
		this.commentFile = commentFile;
	}

	public UserFile getUserFile() {
		return this.userFile;
	}

	public void setUserFile(UserFile userFile) {
		this.userFile = userFile;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Approval getApproval() {
		return this.approval;
	}

	public void setApproval(Approval approval) {
		this.approval = approval;
	}

	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateLastModif() {
		return this.dateLastModif;
	}

	public void setDateLastModif(Date dateLastModif) {
		this.dateLastModif = dateLastModif;
	}

	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Set<CommentFile> getCommentFiles() {
		return this.commentFiles;
	}

	public void setCommentFiles(Set<CommentFile> commentFiles) {
		this.commentFiles = commentFiles;
	}

}