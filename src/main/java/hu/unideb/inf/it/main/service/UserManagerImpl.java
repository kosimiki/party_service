package hu.unideb.inf.it.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.inf.it.main.DAO.UserDAO;
import hu.unideb.inf.it.main.Model.User;


@Component
public class UserManagerImpl implements UserManager{
	
	@Autowired
	private UserDAO userDAO;
	
	@Transactional
	@Override
	public void registerUser(User user) {
		userDAO.save(user);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<User> getAllUser() {
		return userDAO.findAll();
		
	}

	@Transactional(readOnly=true)
	@Override
	public User getUserByName(String username) {
		return userDAO.findByUsername(username);
	}
	
	@Transactional
	@Override
	public void updateUser(User user) {
		userDAO.save(user);
		
	}

	@Override
	public void delete(User user) {
		userDAO.delete(user);
		
	}

	@Override
	public User findOne(Long id) {
		
		return userDAO.findOne(id);
	}

	

}