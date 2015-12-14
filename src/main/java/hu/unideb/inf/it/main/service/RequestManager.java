package hu.unideb.inf.it.main.service;

import java.util.List;

import hu.unideb.inf.it.main.Model.Request;

public interface RequestManager {
	
	public List<Request> findAllRequest();
	
	public void reject(Request r);
	
	public void accept(Request r);
	
}
