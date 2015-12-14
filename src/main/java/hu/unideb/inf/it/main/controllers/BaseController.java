package hu.unideb.inf.it.main.controllers;

import java.io.IOException;

import hu.unideb.inf.it.main.Main;
import hu.unideb.inf.it.main.Model.User;
import hu.unideb.inf.it.main.service.LoaderFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BaseController {
	private Scene scene;
	private Stage stage;
	private Main parent;
	
	public boolean showEditDialog(Object element) {
		try {
			FXMLLoader loader = new FXMLLoader();
			if(element.getClass().equals(User.class)){
				loader.setLocation(Main.class
						.getResource("/fxml/UserEdit.fxml"));
			}
			
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(this.stage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			FormController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setElement(element);
			dialogStage.showAndWait();
			return controller.isOk();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void setScene(Scene scene) {
		this.scene = scene;

	}


	public void showScene(){
		this.getStage().setScene(this.getScene());
		this.getStage().show();
	}


	public void setParent(Main parent) {
		this.parent = parent;

	}


	public void setStage(Stage stage) {
		this.stage = stage;

	}
	
	public void init(){
	
	}

	public void kilépés(){
		try {
			FXMLLoader loader = new LoaderFactory().getLoginLoader();
			Scene sc = new Scene(loader.load());
			parent.setCurrentScene(sc);
			BaseController controller = loader.getController();
			controller.setParent(parent);
			controller.setStage(stage);
			controller.setScene(sc);
			parent.setCurrentController(controller);
			parent.refresh();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Scene getScene() {
		return scene;
	}

	public Stage getStage() {
		return stage;
	}

	public Main getParent() {
		return parent;
	}
}
