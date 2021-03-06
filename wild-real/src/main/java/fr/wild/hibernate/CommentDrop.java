package fr.wild.hibernate;

// Generated 10 nov. 2015 15:14:18 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CommentDrop generated by hbm2java
 */
public class CommentDrop implements java.io.Serializable {

	private long idCommentDrop;
	private Drop drop;
	private User user;
	private CommentDrop commentDrop;
	private Approval approval;
	private Date dateCreation;
	private Date dateLastModif;
	private String contents;
	private Set<CommentDrop> commentDrops = new HashSet<CommentDrop>(0);

	public CommentDrop() {
	}

	public CommentDrop(long idCommentDrop, Drop drop, User user,
			CommentDrop commentDrop, Approval approval, Date dateCreation) {
		this.idCommentDrop = idCommentDrop;
		this.drop = drop;
		this.user = user;
		this.commentDrop = commentDrop;
		this.approval = approval;
		this.dateCreation = dateCreation;
	}

	public CommentDrop(long idCommentDrop, Drop drop, User user,
			CommentDrop commentDrop, Approval approval, Date dateCreation,
			Date dateLastModif, String contents, Set<CommentDrop> commentDrops) {
		this.idCommentDrop = idCommentDrop;
		this.drop = drop;
		this.user = user;
		this.commentDrop = commentDrop;
		this.approval = approval;
		this.dateCreation = dateCreation;
		this.dateLastModif = dateLastModif;
		this.contents = contents;
		this.commentDrops = commentDrops;
	}

	public long getIdCommentDrop() {
		return this.idCommentDrop;
	}

	public void setIdCommentDrop(long idCommentDrop) {
		this.idCommentDrop = idCommentDrop;
	}

	public Drop getDrop() {
		return this.drop;
	}

	public void setDrop(Drop drop) {
		this.drop = drop;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CommentDrop getCommentDrop() {
		return this.commentDrop;
	}

	public void setCommentDrop(CommentDrop commentDrop) {
		this.commentDrop = commentDrop;
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

	public Set<CommentDrop> getCommentDrops() {
		return this.commentDrops;
	}

	public void setCommentDrops(Set<CommentDrop> commentDrops) {
		this.commentDrops = commentDrops;
	}

}
