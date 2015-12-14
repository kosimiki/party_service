package hu.unideb.inf.it.main.controllers.admin;

import org.jasypt.encryption.StringEncryptor;

import hu.unideb.inf.it.main.Model.User;
import hu.unideb.inf.it.main.controllers.FormController;
import hu.unideb.inf.it.main.security.EmailValidator;
import hu.unideb.inf.it.main.security.StrongEncryptor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UserFormController extends FormController {

	@FXML
	private TextField usernameField;

	@FXML
	private TextField passField;

	@FXML
	private TextField fullnameField;

	@FXML
	private TextField rankField;

	@FXML
	private TextField emailField;

	@FXML
	private Label passwordLabel;

	@FXML
	private TextField phoneField;

	@FXML
	void resetValue(ActionEvent event) {

	}
	
	@FXML
	void handleOkClicked() {
		if(isInputValid()){
			User userToEdit =(User)this.getElement(); 
			User user = new User();
			if(userToEdit!=null)
				user.setId(userToEdit.getId());
			user.setFullname(fullnameField.getText());
			user.setUsername(usernameField.getText());
			user.setEmail(emailField.getText());
			StrongEncryptor se = new StrongEncryptor();
			user.setPassword(se.encrypt(passField.getText()));
			user.setPhonenumber(phoneField.getText());
			user.setRank(rankField.getText());
			this.getUserManager().registerUser(user);
			this.getDialogStage().close();
		}
	}

	@FXML
	void handleCancel() {
		this.getDialogStage().close();

	}
	
	public void populateForm(){
		User user = (User)this.getElement();
		usernameField.setText(user.getUsername());
		fullnameField.setText(user.getFullname());
		passwordLabel.setText("Új jelszó");
		emailField.setText(user.getEmail());
		rankField.setText(user.getRank());
		phoneField.setText(user.getPhonenumber());
	}
	
	public boolean isInputValid() {
		String errorMsg = "";
		
		
		if (usernameField.getText().isEmpty() || usernameField.getText() == null) {
			errorMsg += "Nem lehet üres felhasználónevet megadni.";
		}
		
		if (fullnameField.getText().isEmpty() || fullnameField.getText() == null) {
			errorMsg += "Meg kell adni a felhasználó teljes nevét.";
		}
		if (passField.getText().isEmpty() || passField.getText() == null) {
			errorMsg += "Nem lehet üres jelszót megadni.";
		}
		
		if (emailField.getText().isEmpty() || emailField.getText() == null) {
			errorMsg += "Nem lehet az e-mail cím mezőt üresen hagyni.";
		}
		try{
			Long.parseLong(phoneField.getText());
		}catch(NumberFormatException e){
			errorMsg += "A telefonszám csakis számokat tartalmazhat.";
		}
		
		EmailValidator eValidator = new EmailValidator(emailField.getText());
		if(!eValidator.validate()){
			errorMsg += "Nem érvényes e-mail cím";
		}
		
		if(usernameField.getText().length()<4 || passField.getText().length()<4){
			errorMsg += "A felhasználó név és jelszó minimum 4 betüsek lehetnek.";
		}
		
		if (errorMsg.isEmpty()) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(this.getDialogStage());
			alert.setTitle("Hiba");
			alert.setHeaderText("Kérem töltse ki a mezőket megfelelően.");
			alert.setContentText(errorMsg);
			alert.showAndWait();
			
			return false;

		}

	}

}
