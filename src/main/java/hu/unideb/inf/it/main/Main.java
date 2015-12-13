package hu.unideb.inf.it.main;


import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import hu.unideb.inf.it.main.controllers.BaseController;
import hu.unideb.inf.it.main.service.LoaderFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	private BaseController currentController;
	private Stage primaryStage;
	private Scene currentScene;
	private LoaderFactory loaderFactory;
	private FXMLLoader loader;
	private ClassPathXmlApplicationContext context;

	@Override
	public void start(Stage stage) throws IOException {
		context =  new ClassPathXmlApplicationContext("spring.xml");
		primaryStage = stage;
		primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/party_service.png")));
		loaderFactory = new LoaderFactory();
		loader = loaderFactory.getLoginLoader();
		setCurrentScene(new Scene(loader.load()));
		setCurrentController( loader.getController());
		//setCurrentController(context.getBean(LoginController.class));
		currentController.init();
		currentController.setScene(currentScene);
		currentController.setParent(this);
		currentController.setStage(stage);
		currentController.showScene();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void refresh(){
		this.primaryStage.setScene(currentScene);
		this.primaryStage.show();
	}
	public BaseController getCurrentController() {
		return currentController;
	}

	
	public void setCurrentController(BaseController currentController) {
		this.currentController = currentController;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public Scene getCurrentScene() {
		return currentScene;
	}

	public void setCurrentScene(Scene currentScene) {
		this.currentScene = currentScene;
	}

	public ApplicationContext getContext() {
		return context;
	}

}
