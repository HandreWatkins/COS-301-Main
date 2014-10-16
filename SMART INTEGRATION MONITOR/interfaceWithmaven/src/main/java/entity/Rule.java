package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the rules database table.
 * 
 */
@Entity
@Table(name="rules")
@NamedQuery(name="Rule.findAll", query="SELECT r FROM Rule r")
public class Rule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RULES_RULESID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RULES_RULESID_GENERATOR")
	@Column(name="rules_id")
	private Integer rulesId;

	@Column(name="date_time")
	private Timestamp dateTime;

	@Column(name="expected_time")
	private double expectedTime;

	private String uri;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_requesting")
	private Users user;

	public Rule() {
	}

	public Integer getRulesId() {
		return this.rulesId;
	}

	public void setRulesId(Integer rulesId) {
		this.rulesId = rulesId;
	}

	public Timestamp getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	public double getExpectedTime() {
		return this.expectedTime;
	}

	public void setExpectedTime(double expectedTime) {
		this.expectedTime = expectedTime;
	}

	public String getUri() {
		return this.uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Users getUser() {
		return this.user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

}