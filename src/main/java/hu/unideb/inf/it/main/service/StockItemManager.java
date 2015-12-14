package hu.unideb.inf.it.main.service;

import java.util.List;

import hu.unideb.inf.it.main.Model.StockItem;

public interface StockItemManager {


	public void saveStockItem(StockItem item);

	public void updateStockItem(StockItem item);
	
	public List<StockItem> getAllItem() ;


	public List<StockItem> getStockItemsByState(String name) ;

	public void delete(StockItem item);
	
	public StockItem findOne(Long id);
}
