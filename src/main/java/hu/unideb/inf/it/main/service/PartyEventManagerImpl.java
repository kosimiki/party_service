package hu.unideb.inf.it.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.inf.it.main.DAO.PartyEventDAO;
import hu.unideb.inf.it.main.Model.PartyEvent;
import hu.unideb.inf.it.main.Model.StockItem;

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

	@Override
	public PartyEvent findOne(Long id) {
		return partyEventDAO.findOne(id);
	}

	@Transactional
	@Override
	public List<StockItem> getItems(Long id) {
		return partyEventDAO.getOne(id).getItems();
		
	}

	@Override
	public void delete(Long id) {
		partyEventDAO.delete(id);
	}

}
