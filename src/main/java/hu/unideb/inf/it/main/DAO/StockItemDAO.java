package hu.unideb.inf.it.main.DAO;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.unideb.inf.it.main.Model.StockItem;


@Repository
public interface StockItemDAO extends JpaRepository<StockItem, Long>{
	
	StockItem findByName(String username);
	List<StockItem> findAllByState(String stateo);
}
