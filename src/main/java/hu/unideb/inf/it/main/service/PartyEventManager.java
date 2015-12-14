package hu.unideb.inf.it.main.service;

import java.util.List;

import hu.unideb.inf.it.main.Model.PartyEvent;

public interface PartyEventManager {
	
	
	public List<PartyEvent> getAllPartyEvent();
	
	public void save(PartyEvent pe);
	
	public void update(PartyEvent pe);
	
	public void delete(PartyEvent pe);

	
	
}
