package hu.unideb.inf.it.main.service;

import java.util.List;

import hu.unideb.inf.it.main.Model.Order;

public interface OrderManager {

	
	public List<Order> getAllOrder();
	
	public void delete(Order order);

}
