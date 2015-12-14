package hu.unideb.inf.it.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.unideb.inf.it.main.DAO.RequestDAO;
import hu.unideb.inf.it.main.Model.Request;

@Component
public class RequestManagerImpl implements RequestManager {

	@Autowired
	private RequestDAO requestDAO;

	@Override
	public List<Request> findAllRequest() {
		return requestDAO.findAll();
	}

	@Override
	public void reject(Request r) {
		requestDAO.save(r);
	}

	@Override
	public void accept(Request r) {
		requestDAO.save(r);
	}

	@Override
	public Request findOne(Long id) {
		return requestDAO.findOne(id);
	}

}
