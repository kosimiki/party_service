package hu.unideb.inf.it.main.controllers;

import java.io.IOException;
import java.util.List;

import hu.unideb.inf.it.main.Main;
import hu.unideb.inf.it.main.Model.User;
import hu.unideb.inf.it.main.service.ContextManager;
import hu.unideb.inf.it.main.service.UserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdminisztrátorController extends BaseController {

	@FXML
	private TableColumn<User, String> usernameColumn;

	@FXML
	private TableColumn<User, String> rankColumn;

	@FXML
	private TableColumn<User, String> fullnameColumn;

	@FXML
	private TableView<User> table;

	@FXML
	private TableColumn<User, String> phoneColumn;

	@FXML
	private TableColumn<User, String> emailColumn;

	private ObservableList<User> users;

	private UserManager userManager;

	public void init() {
		ContextManager cm = new ContextManager();
		userManager = cm.getContext().getBean(UserManager.class);
		List<User> userList = userManager.getAllUser();
		for (User u : userList) {
			System.out.println(u.getUsername());
		}
		users = FXCollections.observableArrayList(userList);
		usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		fullnameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("fullname"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
		rankColumn.setCellValueFactory(new PropertyValueFactory<User, String>("rank"));
		phoneColumn.setCellValueFactory(new PropertyValueFactory<User, String>("phonenumber"));
		table.setItems(users);
	}

	@FXML
	void újFelhasználó() {
		this.showEditDialog(null);
		reload();
	}

	@FXML
	void felhasználóMódosítása() {
		User user = table.getSelectionModel().getSelectedItem();
		this.showEditDialog(user);
		reload();
	}

	private void reload() {
		users = FXCollections.observableArrayList(userManager.getAllUser());
		table.setItems(users);
	}

	@FXML
	void felhasználóTörlése() {
		User user = table.getSelectionModel().getSelectedItem();
		if (user != null) {
			Confirmation c = new Confirmation();
			boolean answer = c.showConfirmationWindow(this.getStage(), user.getUsername());
			if (answer == true) {
				this.userManager.delete(user);
				reload();

			}
		}
	}

	public boolean showEditDialog(User element) {
		try {
			FXMLLoader loader = new FXMLLoader();

			loader.setLocation(Main.class.getResource("/fxml/UserEdit.fxml"));

			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(this.getStage());
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

}
