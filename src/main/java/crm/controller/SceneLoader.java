package crm.controller;

import crm.CrmApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneLoader {


    public void loadOnEvent(String view, ActionEvent event) throws IOException {
        final var stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        final var fxmlLoader = new FXMLLoader(CrmApplication.class
                .getClassLoader()
                .getResource(view));
        final var scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}
