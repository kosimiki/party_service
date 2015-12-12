package hu.unideb.inf.it.main.controllers;

import java.util.List;

import hu.unideb.inf.it.main.Model.User;
import hu.unideb.inf.it.main.service.ContextManager;
import hu.unideb.inf.it.main.service.UserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminisztrátorController extends BaseController{
	
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
    
    private  ObservableList<User> users;
    
    private UserManager userManager;
    
    public void init(){
    ContextManager cm = new ContextManager();
    userManager = cm.getContext().getBean(UserManager.class);
    List<User> userList = userManager.getAllUser();
    for(User u: userList){
    	System.out.println(u.getUsername());
    }
    users = FXCollections.observableArrayList(userList);
    usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
    fullnameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("fullname"));
    emailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
    rankColumn.setCellValueFactory(new PropertyValueFactory<User,String>("rank"));
    phoneColumn.setCellValueFactory(new PropertyValueFactory<User, String>("phonenumber"));
    table.setItems(users);
    }

    @FXML
    void újFelhasználó() {

    }

    @FXML
    void felhasználóMódosítása() {

    }

    @FXML
    void felhasználóTörlése() {

    }


}
