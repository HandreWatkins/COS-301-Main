package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the distressedresources database table.
 * 
 */
@Entity
@Table(name="distressedresources")
@NamedQuery(name="Distressedresource.findAll", query="SELECT d FROM Distressedresource d")
public class Distressedresource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DISTRESSEDRESOURCES_DISTRESSEDRESOURCESID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DISTRESSEDRESOURCES_DISTRESSEDRESOURCESID_GENERATOR")
	@Column(name="distressedresources_id")
	private Integer distressedresourcesId;

	private String ip;

	@Column(name="last_update")
	private Timestamp lastUpdate;

	private double responsetime;

	@Column(name="time_expected")
	private Integer timeExpected;

	private String uri;

	public Distressedresource() {
	}

	public Integer getDistressedresourcesId() {
		return this.distressedresourcesId;
	}

	public void setDistressedresourcesId(Integer distressedresourcesId) {
		this.distressedresourcesId = distressedresourcesId;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public double getResponsetime() {
		return this.responsetime;
	}

	public void setResponsetime(double responsetime) {
		this.responsetime = responsetime;
	}

	public Integer getTimeExpected() {
		return this.timeExpected;
	}

	public void setTimeExpected(Integer timeExpected) {
		this.timeExpected = timeExpected;
	}

	public String getUri() {
		return this.uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}