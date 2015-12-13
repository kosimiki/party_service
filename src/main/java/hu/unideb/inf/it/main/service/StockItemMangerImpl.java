package hu.unideb.inf.it.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.unideb.inf.it.main.DAO.StockItemDAO;
import hu.unideb.inf.it.main.Model.StockItem;

@Component
public class StockItemMangerImpl implements StockItemManager {

	@Autowired
	StockItemDAO stockItemDAO;
	
	@Override
	public void saveStockItem(StockItem item) {
		stockItemDAO.save(item);
	}
	
	@Override
	public void updateStockItem(StockItem item) {
		stockItemDAO.save(item);
		
	}

	@Override
	public List<StockItem> getAllItem() {
	
		return stockItemDAO.findAll();
	}

	@Override
	public List<StockItem> getStockItemsByState(String state) {
		return stockItemDAO.findAllByState(state);
	}

	@Override
	public void delete(StockItem item) {
		stockItemDAO.delete(item);

	}

}
