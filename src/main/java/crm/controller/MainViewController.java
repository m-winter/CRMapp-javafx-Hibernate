package crm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainViewController {

    @FXML
    private Button addPersonButton;
    @FXML
    private Button addAddressButton;
    @FXML
    private Button addCompanyButton;
    @FXML
    private Button addPremiumButton;
    @FXML
    private Button addContactButton;
    @FXML
    private Button addVerifiedButton;
    @FXML
    private Button showAllButton;
    @FXML
    private Button showByIdButton;

    private SceneLoader sceneLoader;

    public MainViewController() {
        this.sceneLoader = new SceneLoader();
    }

    @FXML
    public void displayAddPersonView(ActionEvent event) throws IOException {
        sceneLoader.loadOnEvent("addPerson_view.fxml", event);
    }

    @FXML
    public void displayAddCompanyView(ActionEvent event) throws IOException {
        sceneLoader.loadOnEvent("addCompany_view.fxml", event);
    }

    @FXML
    public void displayAll(ActionEvent event) throws IOException {
        sceneLoader.loadOnEvent("showAll_view.fxml", event);
    }
}
