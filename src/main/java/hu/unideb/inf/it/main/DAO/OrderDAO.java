package hu.unideb.inf.it.main.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.unideb.inf.it.main.Model.PartyOrder;

@Repository
public interface OrderDAO extends JpaRepository<PartyOrder, Long>{

}
