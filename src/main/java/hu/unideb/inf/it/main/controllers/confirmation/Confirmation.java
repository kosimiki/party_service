package hu.unideb.inf.it.main.controllers.confirmation;

import java.io.IOException;

import hu.unideb.inf.it.main.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Confirmation {
	
	
	 public boolean showConfirmationWindow(Stage stage, String name) {
			try {
				FXMLLoader loader = new FXMLLoader();
				
					loader.setLocation(Main.class
							.getResource("/fxml/Comfirmation.fxml"));
				
				
				AnchorPane page = (AnchorPane) loader.load();
				Stage dialogStage = new Stage();
				dialogStage.initModality(Modality.WINDOW_MODAL);
				dialogStage.initOwner(stage);
				Scene scene = new Scene(page);
				dialogStage.setScene(scene);
				ConfirmationController controller = loader.getController();
				controller.setName(name);
				controller.setStage(dialogStage);
				dialogStage.showAndWait();
				return controller.isOk();
			} catch (IOException e) {
				return false;
			}
		}
}
