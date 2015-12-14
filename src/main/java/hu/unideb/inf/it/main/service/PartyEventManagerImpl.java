package hu.unideb.inf.it.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.unideb.inf.it.main.DAO.PartyEventDAO;
import hu.unideb.inf.it.main.Model.PartyEvent;

@Component
public class PartyEventManagerImpl implements PartyEventManager {

	@Autowired
	private PartyEventDAO partyEventDAO;
	
	

	@Override
	public List<PartyEvent> getAllPartyEvent() {
		return partyEventDAO.findAll();
	}

	@Override
	public void save(PartyEvent pe) {
		partyEventDAO.save(pe);
	}

	@Override
	public void update(PartyEvent pe) {
		partyEventDAO.save(pe);
	}

	@Override
	public void delete(PartyEvent pe) {
		partyEventDAO.delete(pe);
	}

}
