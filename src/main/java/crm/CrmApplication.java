package crm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CrmApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        final var fxmlLoader = new FXMLLoader(CrmApplication.class
                .getClassLoader()
                .getResource("start-view.fxml"));

        final var scene = new Scene(fxmlLoader.load());

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setTitle("CRM Application");
        stage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
