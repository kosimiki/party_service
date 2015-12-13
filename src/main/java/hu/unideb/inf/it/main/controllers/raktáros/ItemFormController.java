package hu.unideb.inf.it.main.controllers.raktáros;

import hu.unideb.inf.it.main.Model.StockItem;
import hu.unideb.inf.it.main.controllers.FormController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class ItemFormController extends FormController {

	@FXML
	private TextField name;


    @FXML
    private ChoiceBox<String> state;

    @FXML
    private ChoiceBox<String> type;

	@FXML
	private TextField rentcount;

	

	private ObservableList<String> types;

	private ObservableList<String> states;

	public ItemFormController() {
		types = FXCollections.observableArrayList();
		states = FXCollections.observableArrayList();
		types.add("textília");
		types.add("gép");
		types.add("helyszín");
		states.add("offline");
		states.add("online");
	}
	
	@FXML
	void resetValue() {

	}
	
	@FXML
	void handleOkClicked() {
		if(isInputValid()){
			
			StockItem item =(StockItem)this.getElement(); 
			if(item==null){
				item = new StockItem();
			}
			item.setName(name.getText());
			item.setState(state.getValue());
			item.setType(type.getValue());
			item.setRentCount(Integer.parseInt(rentcount.getText()));
			this.getItemManager().saveStockItem(item);
			this.getDialogStage().close();
		}
	}

	@FXML
	void handleCancel() {
		this.getDialogStage().close();

	}
	
	public void init(){
		state.setItems(states);
		type.setItems(types);
	}
	
	public void populateForm(){
		if(this.getElement()!=null){
		StockItem item = (StockItem)this.getElement();
		name.setText(item.getName());
		type.setValue(item.getType());
		state.setValue(item.getState());
		rentcount.setText(item.getRentCount().toString());
		}
	}
	
	public boolean isInputValid() {
		String errorMsg = "";
		
		
		if (name.getText().isEmpty() || name.getText() == null) {
			errorMsg += "Nem lehet üres megnevezést megadni.";
		}
		
		
		try{
			Integer.parseInt(rentcount.getText());
		}catch(NumberFormatException e){
			errorMsg += "A telefonszám csakis számokat tartalmazhat.";
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
