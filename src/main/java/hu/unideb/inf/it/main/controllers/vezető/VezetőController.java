package hu.unideb.inf.it.main.controllers.vezető;

import hu.unideb.inf.it.main.controllers.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class VezetőController extends BaseController {

    @FXML
    private TableColumn<?, ?> vevőEmail;

    @FXML
    private TableColumn<?, ?> megrendelőNeve;

    @FXML
    private TableColumn<?, ?> megrendelőEmail;

    @FXML
    private TableColumn<?, ?> rendezvényÁra;

    @FXML
    private TableColumn<?, ?> vevőTelefonszáma;

    @FXML
    private TableColumn<?, ?> ajánlatSzövege;

    @FXML
    private TableColumn<?, ?> rendezvényHelyszíne;

    @FXML
    private TableView<?> rendezvények;

    @FXML
    private TableView<?> megrendelések;

    @FXML
    private TableColumn<?, ?> kellékNeve;

    @FXML
    private TableColumn<?, ?> kellékTípusa;

    @FXML
    private TableColumn<?, ?> rendezvényNeve;

    @FXML
    private TableColumn<?, ?> ajánlatLétrehozó;

    @FXML
    private TableView<?> kellékek;

    @FXML
    private TableColumn<?, ?> megrendeltRendezényNeve;

    @FXML
    private TableColumn<?, ?> kellékDarabaszám;

    @FXML
    private TableView<?> ajánlatok;

    @FXML
    private TextArea ajánlatText;

    @FXML
    private TableColumn<?, ?> megrendelőTelefonszáma;

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


}
