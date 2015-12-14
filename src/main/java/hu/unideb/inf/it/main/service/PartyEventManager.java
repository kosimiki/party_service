package hu.unideb.inf.it.main.service;

import java.util.List;

import hu.unideb.inf.it.main.Model.PartyEvent;
import hu.unideb.inf.it.main.Model.StockItem;

public interface PartyEventManager {
	
	
	public List<PartyEvent> getAllPartyEvent();
	
	public void save(PartyEvent pe);
	
	public void update(PartyEvent pe);
	
	public void delete(PartyEvent pe);

	public PartyEvent findOne(Long id);
	
	public List<StockItem> getItems(Long id);
	
}
