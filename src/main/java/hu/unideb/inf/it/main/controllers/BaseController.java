package hu.unideb.inf.it.main.controllers;

import java.io.IOException;

import hu.unideb.inf.it.main.Main;
import hu.unideb.inf.it.main.service.LoaderFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BaseController {
	private Scene scene;
	private Stage stage;
	private Main parent;
	


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
