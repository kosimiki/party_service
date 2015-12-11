package hu.unideb.inf.it.main.controllers;

import java.io.IOException;

import hu.unideb.inf.it.main.Main;
import hu.unideb.inf.it.main.service.LoaderFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RaktárosController implements SceneController {
	private Scene scene;
	private Stage stage;
	private Main parent;
	
	@FXML
	private Button kilépésGomb;
	
	public RaktárosController(){
		
	}
	
	public RaktárosController(Stage stage, Main parent){
		 try {
			this.parent = parent;
			this.scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/RaktárosFelület.fxml")));
			this.stage = stage;
		 } catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void showScene(){
		stage.setScene(scene);
		stage.show();
	}
	
	public void kilépés(){
		try {
			FXMLLoader loader = new LoaderFactory().getLoginLoader();
			Scene sc = new Scene(loader.load());
			parent.setCurrentScene(sc);
			SceneController controller = loader.getController();
			controller.setParent(parent);
			controller.setStage(stage);
			controller.setScene(sc);
			parent.setCurrentController(controller);
			parent.refresh();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setParent(Main parent) {
		this.parent = parent;
		
	}

	@Override
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	
}