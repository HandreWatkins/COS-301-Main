package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the mainactivity database table.
 * 
 */
@Entity
@Table(name="mainactivity")
@NamedQueries({@NamedQuery(name="Mainactivity.findAll", query="SELECT m FROM Mainactivity m ORDER BY m.mainactivityId DESC"),
@NamedQuery(name="Mainactivity.findTIMER", query="SELECT m FROM Mainactivity m WHERE m.createDate<=:createdate ORDER BY m.mainactivityId DESC")
})
public class Mainactivity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MAINACTIVITY_MAINACTIVITYID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAINACTIVITY_MAINACTIVITYID_GENERATOR")
	@Column(name="mainactivity_id")
	private Integer mainactivityId;

	@Column(name="create_date")
	private Timestamp createDate;

	private String ip;

	@Column(name="\"responseTime\"")
	private double responseTime;

	private String uri;

	public Mainactivity() {
	}

	public Integer getMainactivityId() {
		return this.mainactivityId;
	}

	public void setMainactivityId(Integer mainactivityId) {
		this.mainactivityId = mainactivityId;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public double getResponseTime() {
		return this.responseTime;
	}

	public void setResponseTime(double responseTime) {
		this.responseTime = responseTime;
	}

	public String getUri() {
		return this.uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}