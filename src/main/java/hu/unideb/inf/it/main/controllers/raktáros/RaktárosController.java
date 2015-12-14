package hu.unideb.inf.it.main.controllers.raktáros;

import java.io.IOException;
import java.util.List;

import hu.unideb.inf.it.main.Main;
import hu.unideb.inf.it.main.Model.StockItem;
import hu.unideb.inf.it.main.controllers.BaseController;
import hu.unideb.inf.it.main.controllers.FormController;
import hu.unideb.inf.it.main.controllers.confirmation.Confirmation;
import hu.unideb.inf.it.main.service.ContextManager;
import hu.unideb.inf.it.main.service.StockItemManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RaktárosController extends BaseController {

	private StockItemManager manager;
	@FXML
	private Button logout;
	@FXML
	private CheckBox machineState;

	@FXML
	private TableColumn<StockItem, String> nameColumn;

	@FXML
	private TableColumn<StockItem, String> stateColumn;

	@FXML
	private Button newUser;

	@FXML
	private Button editUser;

	@FXML
	private Button deleteUser;

	@FXML
	private TableColumn<StockItem, String> typeColumn;

	@FXML
	private TableView<StockItem> table;

	@FXML
	private TableColumn<StockItem, String> rentCountColumn;

	private ObservableList<StockItem> items;
	private ObservableList<StockItem> machines;

	private void reload() {
		items = FXCollections.observableArrayList(manager.getAllItem());
		table.setItems(items);
	}

	@FXML
	void újRaktárElem() {
		this.showEditDialog(null);
		reload();
	}

	@FXML
	void raktárElemMódosítása() {
		StockItem item = table.getSelectionModel().getSelectedItem();
		this.showEditDialog(item);
		reload();
	}

	@FXML
	void raktárelemTörlése() {
		StockItem item = table.getSelectionModel().getSelectedItem();
		if (item != null) {
			Confirmation c = new Confirmation();
			boolean answer = c.showConfirmationWindow(this.getStage(), item.getName(),"Biztosan szeretné törölni?");
			if (answer == true) {
				this.manager.delete(item);
				reload();

			}
		}
	}

	@FXML
	void szervízreSzorulóGépek() {
		machines = FXCollections.observableArrayList();
		if (machineState.isSelected()) {
			for (StockItem i : items) {
				if (i.getRentCount() > 20 && i.getType().equals("gép")) {
					machines.add(i);
				}
			}
			table.setItems(machines);
		}else{
			reload();
		}
		
	}

	public void init() {
		ContextManager cm = new ContextManager();
		manager = cm.getContext().getBean(StockItemManager.class);
		List<StockItem> itemsTemp = manager.getAllItem();

		items = FXCollections.observableArrayList(itemsTemp);

		nameColumn.setCellValueFactory(new PropertyValueFactory<StockItem, String>("name"));

		typeColumn.setCellValueFactory(new PropertyValueFactory<StockItem, String>("type"));

		rentCountColumn.setCellValueFactory(new PropertyValueFactory<StockItem, String>("rentCount"));

		stateColumn.setCellValueFactory(new PropertyValueFactory<StockItem, String>("state"));

		table.setItems(items);
	}
	
	public boolean showEditDialog(Object element) {
		try {
			FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class
						.getResource("/fxml/ItemEdit.fxml"));
			
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(this.getStage());
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			FormController controller = loader.getController();
			controller.init();
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