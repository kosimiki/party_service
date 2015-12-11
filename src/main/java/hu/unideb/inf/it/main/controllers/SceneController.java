package hu.unideb.inf.it.main.controllers;

import hu.unideb.inf.it.main.Main;
import javafx.scene.Scene;
import javafx.stage.Stage;

public interface SceneController {

	public void setScene(Scene scene);
	public void showScene();
	public void setParent(Main parent);
	public void setStage(Stage stage);

}
