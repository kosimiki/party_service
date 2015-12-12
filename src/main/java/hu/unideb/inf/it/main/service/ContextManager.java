package hu.unideb.inf.it.main.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextManager {
	
	private ClassPathXmlApplicationContext context;
	
	public ContextManager() {
		context =  new ClassPathXmlApplicationContext("spring.xml");
		
	}
	
	public ApplicationContext getContext(){
		return this.context;
	}

}
