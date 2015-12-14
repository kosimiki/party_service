package hu.unideb.inf.it.main.service;

import java.util.List;

import hu.unideb.inf.it.main.Model.PartyOrder;

public interface OrderManager {

	
	public List<PartyOrder> getAllOrder();
	
	public void delete(PartyOrder order);

}
