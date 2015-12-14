package hu.unideb.inf.it.main.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.unideb.inf.it.main.Model.Request;

@Repository
public interface RequestDAO extends JpaRepository<Request, Long>{

}
