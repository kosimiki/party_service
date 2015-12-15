package hu.unideb.inf.it.main.Model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PartyOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long partyID;
	private Long userID;
	@Temporal(TemporalType.DATE)
	private Date partyDate;
	@Temporal(TemporalType.DATE)
	private Date requestDate;
	
	private Boolean done;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPartyID() {
		return partyID;
	}
	public void setPartyID(Long partyID) {
		this.partyID = partyID;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public Date getPartyDate() {
		return partyDate;
	}
	public void setPartyDate(Date partyDate) {
		this.partyDate = partyDate;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public Boolean getDone() {
		return done;
	}
	public void setDone(Boolean done) {
		this.done = done;
	}
	
	

}
