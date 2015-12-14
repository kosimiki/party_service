package hu.unideb.inf.it.main.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.unideb.inf.it.main.Model.Order;

@Repository
public interface OrderDAO extends JpaRepository<Order, Long>{

}
