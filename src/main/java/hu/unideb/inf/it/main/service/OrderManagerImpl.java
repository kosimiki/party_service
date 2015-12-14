
package hu.unideb.inf.it.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.unideb.inf.it.main.DAO.OrderDAO;
import hu.unideb.inf.it.main.Model.Order;

@Component
public class OrderManagerImpl implements OrderManager {

	@Autowired
	private OrderDAO orderDAO;
	



	@Override
	public List<Order> getAllOrder() {
		return orderDAO.findAll();
	}

	@Override
	public void delete(Order order) {
		orderDAO.delete(order);
	}

}
