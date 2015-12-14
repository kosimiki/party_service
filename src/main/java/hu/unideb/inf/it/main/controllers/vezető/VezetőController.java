package hu.unideb.inf.it.main.controllers.vezető;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import hu.unideb.inf.it.main.Model.PartyEvent;
import hu.unideb.inf.it.main.Model.PartyOrder;
import hu.unideb.inf.it.main.Model.Request;
import hu.unideb.inf.it.main.Model.StockItem;
import hu.unideb.inf.it.main.Model.User;
import hu.unideb.inf.it.main.controllers.BaseController;
import hu.unideb.inf.it.main.controllers.confirmation.Confirmation;
import hu.unideb.inf.it.main.service.ContextManager;
import hu.unideb.inf.it.main.service.OrderManager;
import hu.unideb.inf.it.main.service.PartyEventManager;
import hu.unideb.inf.it.main.service.RequestManager;
import hu.unideb.inf.it.main.service.StockItemManager;
import hu.unideb.inf.it.main.service.UserManager;
import hu.unideb.inf.it.main.service.table.OrderTable;
import hu.unideb.inf.it.main.service.table.PartyEventTable;
import hu.unideb.inf.it.main.service.table.RequestTable;
import hu.unideb.inf.it.main.service.table.StockItemTable;
import hu.unideb.inf.it.main.service.table.TypeAndCount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class VezetőController extends BaseController {

	private OrderManager orderManager;
	private PartyEventManager partyManager;
	private UserManager userManager;
	private StockItemManager stockItemManager;
	private RequestManager requestManager;

	@FXML
	private TableColumn<RequestTable, String> vevőEmail;

	@FXML
	private TableColumn<OrderTable, String> megrendelőNeve;

	@FXML
	private TableColumn<OrderTable, String> megrendelőEmail;

	@FXML
	private TableColumn<PartyEventTable, Integer> rendezvényÁra;

	@FXML
	private TableColumn<RequestTable, String> vevőTelefonszáma;

	@FXML
	private TableColumn<RequestTable, String> ajánlatSzövege;

	@FXML
	private TableColumn<PartyEventTable, String> rendezvényHelyszíne;

	@FXML
	private TableView<PartyEventTable> rendezvények;

	@FXML
	private TableView<OrderTable> megrendelések;

	@FXML
	private TableColumn<StockItemTable, String> kellékNeve;

	@FXML
	private TableColumn<StockItemTable, String> kellékTípusa;

	@FXML
	private TableColumn<PartyEventTable, String> rendezvényNeve;

	@FXML
	private TableColumn<RequestTable, String> ajánlatLétrehozó;

	@FXML
	private TableColumn<RequestTable, String> ajánlatÁllapota;

	@FXML
	private TableView<StockItemTable> kellékek;

	@FXML
	private TableColumn<OrderTable, String> megrendeltRendezényNeve;

	@FXML
	private TableColumn<StockItemTable, Integer> kellékDarabaszám;

	@FXML
	private TableView<RequestTable> ajánlatok;

	@FXML
	private TextArea ajánlatText;

	@FXML
	private TableColumn<OrderTable, String> megrendelőTelefonszáma;

	private ObservableList<OrderTable> ordersTable;
	private ObservableList<RequestTable> requestsTable;
	private ObservableList<PartyEventTable> partysTable;
	private ObservableList<StockItemTable> itemsTable;
	
	@FXML
	void újRedendezvény() {

	}

	@FXML
	void rendezvényMódosítása() {

	}

	@FXML
	void rendezvényTörlése() {

	}

	@FXML
	void ajánlatSzövegénekMegjelenítése() {
		RequestTable rt = ajánlatok.getSelectionModel().getSelectedItem();
		ajánlatText.setText(rt.getRequest());

	}

	@FXML
	void ajánlatElfogadása() {
		RequestTable rt = new RequestTable();
		rt = ajánlatok.getSelectionModel().getSelectedItem();
		if(rt==null){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.initOwner(this.getStage());
			alert.setTitle("Oops!");
			alert.setHeaderText("Nincs ajánlat kiválasztva.");
			alert.showAndWait();
			}else{
				Request request = requestManager.findOne(rt.getRequestID());
				request.setState("Elfogadva");
				
				requestManager.accept(request);
				reloadRequests();
			}

	}

	@FXML
	void ajánlatElutasítása() {
		RequestTable rt = new RequestTable();
		rt = ajánlatok.getSelectionModel().getSelectedItem();
		if(rt==null){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.initOwner(this.getStage());
			alert.setTitle("Oops!");
			alert.setHeaderText("Nincs ajánlat kiválasztva.");
			alert.showAndWait();
			}else{
				Request request = requestManager.findOne(rt.getRequestID());
				request.setState("Elutasítva");
				requestManager.accept(request);
				reloadRequests();
			}

	}

	@FXML
	void megrendelésTeljesítve() {
		OrderTable ot = new OrderTable();
		ot = megrendelések.getSelectionModel().getSelectedItem();
		if (ot == null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.initOwner(this.getStage());
			alert.setTitle("Oops!");
			alert.setHeaderText("Nincs rendelés kiválasztva.");
			alert.showAndWait();
		} else {
			Confirmation cm = new Confirmation();
			boolean válasz = cm.showConfirmationWindow(this.getStage(), "", "Biztosan szeretné \n teljesítetté tenni?");
			if(válasz==true){
				orderManager.delete(ot.getOrderID());
				ordersTable.remove(ot);
				reloadOrders();
			}
		}
	}

	public void init() {
		ContextManager cm = new ContextManager();

		orderManager = cm.getContext().getBean(OrderManager.class);
		partyManager = cm.getContext().getBean(PartyEventManager.class);
		requestManager = cm.getContext().getBean(RequestManager.class);
		stockItemManager = cm.getContext().getBean(StockItemManager.class);
		userManager = cm.getContext().getBean(UserManager.class);
		
		reloadOrders();
		reloadParties();
		reloadRequests();
	}
	

	private void reloadParties(){
		try{
			List<PartyEvent> events = partyManager.getAllPartyEvent();
			List<PartyEventTable> eventTable = new ArrayList<>();
			for (PartyEvent event : events) {
				PartyEventTable row = new PartyEventTable();
				row.setName(event.getName());
				StockItem place = stockItemManager.findOne(event.getPlaceID());
				row.setPlaceName(place.getName());
				row.setPrice(event.getPrice());
				row.setPartyID(event.getId());
				eventTable.add(row);
			}
			
			partysTable = FXCollections.observableArrayList(eventTable);
			rendezvényNeve.setCellValueFactory(new PropertyValueFactory<PartyEventTable, String>("name"));
			rendezvényHelyszíne.setCellValueFactory(new PropertyValueFactory<PartyEventTable, String>("placeName"));
			rendezvényÁra.setCellValueFactory(new PropertyValueFactory<PartyEventTable, Integer>("price"));

			rendezvények.setItems(partysTable);

		}catch(Exception e){
			
		}
	}
	private void reloadOrders(){

		try {
			List<PartyOrder> orders = orderManager.getAllOrder();
			List<OrderTable> orderTable = new ArrayList<>();
			for (PartyOrder order : orders) {
				OrderTable row = new OrderTable();
				row.setPartyName(partyManager.findOne(order.getPartyID()).getName());
				User user = userManager.findOne(order.getUserID());
				row.setUserName(user.getFullname());
				row.setUserEmail(user.getEmail());
				row.setOrderID(order.getId());
				row.setUserPhone(user.getPhonenumber());
				orderTable.add(row);
			}
				
			ordersTable = FXCollections.observableArrayList(orderTable);
			megrendeltRendezényNeve.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("partyName"));
			megrendelőNeve.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("userName"));
			megrendelőEmail.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("userEmail"));
			megrendelőTelefonszáma.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("userPhone"));
			megrendelések.setItems(ordersTable);
		} catch (Exception e) {
		}

	}
	private void reloadRequests(){
		try{
		List<Request> requests = requestManager.findAllRequest();
		List<RequestTable> requestTable = new ArrayList<>();
		for (Request request : requests) {
			RequestTable row = new RequestTable();
			User user = userManager.findOne(request.getUserID());
			row.setRequest(request.getRequest());
			row.setState(request.getState());
			row.setUserName(user.getFullname());
			row.setUserEmail(user.getEmail());
			row.setUserPhone(user.getPhonenumber());
			row.setRequestID(request.getId());
			requestTable.add(row);
		}

	 requestsTable = FXCollections.observableArrayList(requestTable);
		ajánlatLétrehozó.setCellValueFactory(new PropertyValueFactory<RequestTable, String>("userName"));
		ajánlatSzövege.setCellValueFactory(new PropertyValueFactory<RequestTable, String>("request"));
		vevőTelefonszáma.setCellValueFactory(new PropertyValueFactory<RequestTable, String>("userPhone"));
		vevőEmail.setCellValueFactory(new PropertyValueFactory<RequestTable, String>("userEmail"));
		ajánlatÁllapota.setCellValueFactory(new PropertyValueFactory<RequestTable, String>("state"));

		ajánlatok.setItems(requestsTable);
		}catch(Exception e){
			
		}
	
	}
	

    @FXML
    void rendezvényRészletei() {
    	try {
    		PartyEventTable party = rendezvények.getSelectionModel().getSelectedItem();
    		Long id = party.getPartyID();
    		
			List<StockItem> items = partyManager.getItems(id);
			System.out.println("hány elem" + items.size());
			
			List<StockItemTable> itemTable = new ArrayList<>();
			Map<String, TypeAndCount> names = new HashMap<>();
			for (StockItem item : items) {
				System.out.println(item.getName());
				if(names.containsKey(item.getName())){
					names.put(item.getName(),new TypeAndCount(item.getType() ,  names.get(item.getName()).getCount()+1));
				}else{
					if(!item.getType().equals("helyszín")){
						names.put(item.getName(), new TypeAndCount(item.getType(), 1));

					}
								}
			}
			for( Entry<String, TypeAndCount> item: names.entrySet()){
				StockItemTable row = new StockItemTable();
				row.setName(item.getKey());
				row.setType(item.getValue().getType());
				row.setCount(item.getValue().getCount());
				itemTable.add(row);
			}
			
				
			itemsTable = FXCollections.observableArrayList(itemTable);
			kellékNeve.setCellValueFactory(new PropertyValueFactory<StockItemTable, String>("name"));
			kellékDarabaszám.setCellValueFactory(new PropertyValueFactory<StockItemTable, Integer>("count"));
			kellékTípusa.setCellValueFactory(new PropertyValueFactory<StockItemTable, String>("type"));
			
			kellékek.setItems(itemsTable);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
    }
}
