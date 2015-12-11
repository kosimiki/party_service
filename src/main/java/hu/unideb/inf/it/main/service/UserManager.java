package hu.unideb.inf.it.main.service;

import java.util.List;

import hu.unideb.inf.it.main.Model.User;

public interface UserManager {

	
	
	public void registerUser(User user);

	public void updateUser(User user);
	
	public List<User> getAllUser() ;


	public User getUserByName(String username) ;



}
