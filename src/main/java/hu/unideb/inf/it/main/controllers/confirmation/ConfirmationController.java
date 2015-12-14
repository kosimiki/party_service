package hu.unideb.inf.it.main.controllers.confirmation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ConfirmationController {
	
	private boolean ok;
	private Stage stage;
	
	@FXML
    private Label toDeleteLabel;

	
	@FXML
    private Label kérdés;

    @FXML
    void okClicked() {
    	this.setOk(true);
    	this.stage.close();
    }

    @FXML
    void cancelClicked() {
    	this.setOk(false);
    	this.stage.close();
    }

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	
	public void setName(String name) {
		toDeleteLabel.setText(name);
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Label getToDeleteLabel() {
		return toDeleteLabel;
	}

	public void setToDeleteLabel(Label toDeleteLabel) {
		this.toDeleteLabel = toDeleteLabel;
	}

	public Label getKérdés() {
		return kérdés;
	}

	public void setKérdés(String kérdés) {
		this.kérdés.setText(kérdés);
	}

	

}
