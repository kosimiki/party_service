package hu.unideb.inf.it.main.controllers.login;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.unideb.inf.it.main.controllers.BaseController;
import hu.unideb.inf.it.main.security.Authentication;
import hu.unideb.inf.it.main.service.LoaderFactory;
import hu.unideb.inf.it.main.service.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

@Component
public class LoginController extends BaseController {

	
	@Autowired
	private Authentication authentication;
	
	@Autowired
	private UserManager userManager;
	
	@FXML
	private Button belépésGomb;
	
	@FXML
	private TextField username;
	
	@FXML
	private TextField password;
	

	
	public LoginController(){
	
	}

	
	
	public void belépés(){
		try {
			userManager = this.getParent().getContext().getBean(UserManager.class);
			authentication = this.getParent().getContext().getBean(Authentication.class);
			if(userManager==null)
				System.out.println("null");
			
			String un = username.getText();
			String pwd = password.getText();
			//if(authentication.isSuccessfulAuthentication(un,pwd)){
				String rank = userManager.getUserByName(un).getRank();
				System.out.println(rank);
				switchToScene(rank);
			//}else{
				//Hiba üzenet, rossz jelszó vagy felhasználónév.
			//}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void switchToScene(String position) throws IOException{
		FXMLLoader loader = null;
		switch(position.toLowerCase()){
		case "admin":  loader =  new LoaderFactory().getAdminisztrátorLoader();break;
		case "vezető":  loader =  new LoaderFactory().getVezetőLoader();break;
		case "raktáros":  loader =  new LoaderFactory().getRaktárosLoader();break;
		}
		if(loader!=null){
			Scene sc = new Scene(loader.load());
			this.getParent().setCurrentScene(sc);
			BaseController controller = loader.getController();
			controller.init();
			controller.setParent(this.getParent());
			controller.setStage(this.getStage());
			controller.setScene(sc);
			this.getParent().setCurrentController(controller);
			this.getParent().refresh();
		}else{
			// maradunk de kell valami hibaüzenet
		}
		
	}

	
	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

}
