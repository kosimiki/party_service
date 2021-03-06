package hu.unideb.inf.it.main.controllers;

import org.springframework.context.ApplicationContext;

import hu.unideb.inf.it.main.service.ContextManager;
import hu.unideb.inf.it.main.service.StockItemManager;
import hu.unideb.inf.it.main.service.UserManager;
import javafx.stage.Stage;

public class FormController {
	private Stage dialogStage;
	private Object element;
	private boolean ok;
	private ApplicationContext context;
	private UserManager userManager;
	private StockItemManager itemManager;
	

	public void setElement(Object element) {
		ContextManager cm = new ContextManager();
		setContext(cm.getContext());
		userManager = context.getBean(UserManager.class);
		setItemManager(context.getBean(StockItemManager.class));
		if(element != null){
			this.element = element;
			populateForm();
		}
	}


	public void populateForm() {
		System.out.println("új elem");
		
	}



	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}


	public Object getElement() {
		return element;
	}


	public ApplicationContext getContext() {
		return context;
	}


	public void setContext(ApplicationContext context) {
		this.context = context;
	}


	public UserManager getUserManager() {
		return userManager;
	}


	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}


	public StockItemManager getItemManager() {
		return itemManager;
	}


	public void setItemManager(StockItemManager itemManager) {
		this.itemManager = itemManager;
	}


	public void init() {
		System.out.println("nincs init");
		
	}



}
