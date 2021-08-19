package crm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class CustomerListViewController {

    private SceneLoader sceneLoader;

    public CustomerListViewController() {
        this.sceneLoader = new SceneLoader();
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        sceneLoader.loadOnEvent("start-view.fxml", event);
    }
}

