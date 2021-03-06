package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="rules")
@NamedQueries({
    @NamedQuery(name="Rule.findAll", query="SELECT r FROM Rule r ORDER BY r.rulesId DESC"),
    @NamedQuery(name="Rule.finduri", query="SELECT r FROM Rule r WHERE r.uri = :uri"),
    @NamedQuery(name="Rule.finduser", query="SELECT r FROM Rule r WHERE r.user = :user ORDER BY r.rulesId DESC")
})
public class Rule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="rules_id_seq", sequenceName = "rules_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="rules_id_seq" )
	@Column(name="rules_id")
	private Integer rulesId;

	@Column(name="date_time")
	private Timestamp dateTime;

	@Column(name="expected_time")
	private double expectedTime;

	private String uri;

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