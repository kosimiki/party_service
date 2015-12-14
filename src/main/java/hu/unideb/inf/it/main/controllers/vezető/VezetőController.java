package hu.unideb.inf.it.main.controllers.vezető;

import java.util.ArrayList;
import java.util.List;

import hu.unideb.inf.it.main.Model.PartyOrder;
import hu.unideb.inf.it.main.Model.PartyEvent;
import hu.unideb.inf.it.main.Model.Request;
import hu.unideb.inf.it.main.Model.StockItem;
import hu.unideb.inf.it.main.Model.User;
import hu.unideb.inf.it.main.controllers.BaseController;
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
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
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
    private TableColumn<PartyEventTable,String> rendezvényHelyszíne;

    @FXML
    private TableView<PartyEventTable> rendezvények;

    @FXML
    private TableView<OrderTable> megrendelések;

    @FXML
    private TableColumn<StockItem, String> kellékNeve;

    @FXML
    private TableColumn<StockItem, String> kellékTípusa;

    @FXML
    private TableColumn<PartyEventTable, String> rendezvényNeve;

    @FXML
    private TableColumn<RequestTable, String> ajánlatLétrehozó;
    
    @FXML
    private TableColumn<RequestTable, String> ajánlatÁllapota;


    @FXML
    private TableView<StockItem> kellékek;

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

    }

    @FXML
    void ajánlatElfogadása() {

    }

    @FXML
    void ajánlatElutasítása() {

    }

    @FXML
    void megrendelésTeljesítve() {

    }

    public void init() {
		ContextManager cm = new ContextManager();
		orderManager = cm.getContext().getBean(OrderManager.class);
		partyManager = cm.getContext().getBean(PartyEventManager.class);
		requestManager = cm.getContext().getBean(RequestManager.class);
		stockItemManager = cm.getContext().getBean(StockItemManager.class);
		userManager = cm.getContext().getBean(UserManager.class);
		
		List<PartyEvent> events = partyManager.getAllPartyEvent();
		List<PartyEventTable> eventTable = new ArrayList<>();
		for(PartyEvent event: events){
			PartyEventTable row = new PartyEventTable();
			row.setName(event.getName());
			StockItem place = stockItemManager.findOne(event.getPlaceID());
			row.setPlaceName(place.getName());
			row.setPrice(event.getPrice());
			eventTable.add(row);
		}
		
		rendezvényNeve.setCellValueFactory(new PropertyValueFactory<PartyEventTable, String>("name"));
		rendezvényHelyszíne.setCellValueFactory(new PropertyValueFactory<PartyEventTable, String>("placeName"));
		rendezvényÁra.setCellValueFactory(new PropertyValueFactory<PartyEventTable, Integer>("price"));
		
		rendezvények.setItems(FXCollections.observableArrayList(eventTable));
	
		List<PartyOrder> orders = orderManager.getAllOrder();
		List<OrderTable> ordersTable = new ArrayList<>();
		for(PartyOrder order : orders){
			OrderTable row = new OrderTable();
			row.setPartyName( partyManager.findOne(order.getPartyID()).getName());
			User user = userManager.findOne(order.getUserID());
			row.setUserName(user.getFullname());
			row.setUserEmail(user.getEmail());
			row.setUserPhone(user.getPhonenumber());
			ordersTable.add(row);
		}
		
		megrendeltRendezényNeve.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("partyName"));
		megrendelőNeve.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("userName"));
		megrendelőEmail.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("userPhone"));
		megrendelőTelefonszáma.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("userEmail"));
		megrendelések.setItems(FXCollections.observableArrayList(ordersTable));



		List<Request> requests = requestManager.findAllRequest();
		List<RequestTable> requestTable = new ArrayList<>();
		for(Request request: requests){
			RequestTable row = new RequestTable();
			User user = userManager.findOne(request.getUserID());
			row.setRequest(request.getRequest());
			row.setState(request.getState());
			row.setUserName(user.getFullname());
			row.setUserEmail(user.getEmail());
			row.setUserPhone(user.getPhonenumber());
			requestTable.add(row);
		}
		
		ajánlatLétrehozó.setCellValueFactory(new PropertyValueFactory<RequestTable, String>("userName"));
		ajánlatSzövege.setCellValueFactory(new PropertyValueFactory<RequestTable, String>("request"));
		vevőTelefonszáma.setCellValueFactory(new PropertyValueFactory<RequestTable, String>("userPhone"));
		vevőEmail.setCellValueFactory(new PropertyValueFactory<RequestTable, String>("userEmail"));
		ajánlatÁllapota.setCellValueFactory(new PropertyValueFactory<RequestTable, String>("state"));
		
		ajánlatok.setItems(FXCollections.observableArrayList(requestTable));
		
    }
	

}
