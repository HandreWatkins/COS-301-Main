package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the bookmark database table.
 * 
 */
@Entity
@Table(name="bookmark")
@NamedQuery(name="Bookmark.findAll", query="SELECT b FROM Bookmark b ORDER BY b.bookmarkId DESC")
public class Bookmark implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BOOKMARK_BOOKMARKID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BOOKMARK_BOOKMARKID_GENERATOR")
	@Column(name="bookmark_id")
	private Integer bookmarkId;

	@Column(name="date_time")
	private Timestamp dateTime;

	private String discription;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_requesting")
	private Users user;

	public Bookmark() {
	}

	public Integer getBookmarkId() {
		return this.bookmarkId;
	}

	public void setBookmarkId(Integer bookmarkId) {
		this.bookmarkId = bookmarkId;
	}

	public Timestamp getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	public String getDiscription() {
		return this.discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public Users getUser() {
		return this.user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

}