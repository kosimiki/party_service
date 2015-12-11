package hu.unideb.inf.it.main.service;

import javafx.fxml.FXMLLoader;

public class LoaderFactory {

	public FXMLLoader getLoginLoader() {
		return new FXMLLoader(getClass().getResource("/fxml/Belepes.fxml"));
	}

	public FXMLLoader getVezetőLoader() {
		return new FXMLLoader(getClass().getResource("/fxml/VezetőFelület.fxml"));
	}

	public FXMLLoader getRaktárosLoader() {
		return new FXMLLoader(getClass().getResource("/fxml/RaktárosFelület.fxml"));
	}

	public FXMLLoader getAdminisztrátorLoader() {
		return new FXMLLoader(getClass().getResource("/fxml/AdminisztrátorFelület.fxml"));
	}
}
