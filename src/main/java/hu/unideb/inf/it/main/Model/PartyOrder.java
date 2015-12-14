package hu.unideb.inf.it.main.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PartyOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long partyID;
	private Long userID;
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
	
	

}
