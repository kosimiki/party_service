package hu.unideb.inf.it.main.controllers.vezető;

import java.util.ArrayList;
import java.util.List;

import hu.unideb.inf.it.main.Model.PartyEvent;
import hu.unideb.inf.it.main.Model.StockItem;
import hu.unideb.inf.it.main.controllers.FormController;
import hu.unideb.inf.it.main.service.ContextManager;
import hu.unideb.inf.it.main.service.PartyEventManager;
import hu.unideb.inf.it.main.service.StockItemManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PartyEventFormController extends FormController {

	private PartyEventManager partyManager;
	private StockItemManager stockItemManager;

	@FXML
	private TextField rendezvényNeve;

	@FXML
	private TableColumn<StockItem, String> típus;

	@FXML
	private TableColumn<StockItem, String> id;

	@FXML
	private TextField rendezvényÁra;

	@FXML
	private TableView<StockItem> eszközökTábla;

	@FXML
	private ChoiceBox<StockItem> gépekLista;

	@FXML
	private TableColumn<StockItem, String> megnevezés;

	@FXML
	private ChoiceBox<StockItem> textíliákLista;

	@FXML
	private ChoiceBox<StockItem> helyszínekLista;

	private PartyEvent party;

	private ObservableList<StockItem> cloth;
	private ObservableList<StockItem> machine;
	private ObservableList<StockItem> place;
	private ObservableList<StockItem> itemsTable;

	List<StockItem> machines = new ArrayList<>();
	List<StockItem> cloths = new ArrayList<>();
	List<StockItem> places = new ArrayList<>();

	private StockItem aktuálisHelyszín = null;

	@FXML
	void hozzáadGép() {
		StockItem kiválasztott = gépekLista.getSelectionModel().getSelectedItem();
		if (!itemsTable.contains(kiválasztott)) {
			itemsTable.add(kiválasztott);
			reload();
		}
	}

	private void reload() {
		megnevezés.setCellValueFactory(new PropertyValueFactory<StockItem, String>("name"));
		típus.setCellValueFactory(new PropertyValueFactory<StockItem, String>("type"));
		id.setCellValueFactory(new PropertyValueFactory<StockItem, String>("id"));
		eszközökTábla.setItems(itemsTable);

	}

	@FXML
	void hozzáadTextília() {
		StockItem kiválasztott = textíliákLista.getSelectionModel().getSelectedItem();
		if (!itemsTable.contains(kiválasztott)) {

			itemsTable.add(textíliákLista.getSelectionModel().getSelectedItem());
			reload();
		}
	}

	@FXML
	void hozzáadHelyszínek() {
		StockItem kiválasztott = helyszínekLista.getSelectionModel().getSelectedItem();
		boolean van = false;
		if (kiválasztott.getType().equals("helyszín")) {
			for (StockItem item : itemsTable) {
				if (item.getType().equals("helyszín")) {
					van = true;
				}
			}

			if (!van) {
				aktuálisHelyszín = kiválasztott;
				itemsTable.add(kiválasztott);
				reload();

			}
		} else {
			itemsTable.add(kiválasztott);
			reload();

		}

	}

	@FXML
	void mégse() {
		this.getDialogStage().close();
	}

	@FXML
	void rendezvényMentése() {
		if (isInputValid()) {
			party.setName(rendezvényNeve.getText());
			party.setPrice(Integer.parseInt(rendezvényÁra.getText()));

			party.setPlaceID(aktuálisHelyszín.getId());
			party.setItems(itemsTable);
			partyManager.save(party);
			this.setOk(true);
			this.getDialogStage().close();
		}

	}

	@FXML
	void elemEltávolítása(ActionEvent event) {
		itemsTable.remove(eszközökTábla.getSelectionModel().getSelectedItem());
		reload();
	}

	public void init() {
		ContextManager cm = new ContextManager();

		partyManager = cm.getContext().getBean(PartyEventManager.class);
		stockItemManager = cm.getContext().getBean(StockItemManager.class);

		List<StockItem> items = stockItemManager.getAllItem();

		for (StockItem item : items) {

			if (item.getType().equals("textília")) {
				cloths.add(item);
			}
			if (item.getType().equals("gép")) {
				machines.add(item);
			}
			if (item.getType().equals("helyszín")) {
				places.add(item);
			}
		}

		cloth = FXCollections.observableArrayList(cloths);

		place = FXCollections.observableArrayList(places);

		machine = FXCollections.observableArrayList(machines);

		gépekLista.setItems(machine);
		textíliákLista.setItems(cloth);
		helyszínekLista.setItems(place);

		itemsTable = FXCollections.observableArrayList();
		if (this.getElement() == null) {
			party = new PartyEvent();
		} else {
			party = (PartyEvent) this.getElement();
		}

	}

	public void populateForm() {
		if (this.getElement() == null) {
			party = new PartyEvent();
		} else {
			party = (PartyEvent) this.getElement();
		}
		rendezvényNeve.setText(party.getName());
		rendezvényÁra.setText(party.getPrice().toString());

		itemsTable = FXCollections.observableArrayList(party.getItems());
		megnevezés.setCellValueFactory(new PropertyValueFactory<StockItem, String>("name"));
		típus.setCellValueFactory(new PropertyValueFactory<StockItem, String>("type"));
		id.setCellValueFactory(new PropertyValueFactory<StockItem, String>("id"));
		eszközökTábla.setItems(itemsTable);
	}

	public boolean isInputValid() {
		String errorMsg = "";

		if (rendezvényNeve.getText().isEmpty() || rendezvényNeve.getText() == null) {
			errorMsg += "Nem lehet üres rendezvényt megadni.";
		}

		try {
			Integer.parseInt(rendezvényÁra.getText());
		} catch (NumberFormatException e) {
			errorMsg += "Az ár csak számokat tartalmazhat.";
		}

		if (aktuálisHelyszín == null) {
			errorMsg += "Nincs helyszín kiválasztva";
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