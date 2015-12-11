package hu.unideb.inf.it.main.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.unideb.inf.it.main.Model.User;


@Repository
public interface UserDAO extends JpaRepository<User, Long>{
	
	User findByUsername(String username);
}
