package crm.controller;

import crm.CrmApplication;
import crm.service.PersonCustomerRegistration;
import crm.service.RegisterPersonForm;
import crm.util.HibernateUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class StartViewController {

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField ssNumber;

    @FXML
    private Text customerId;

    @FXML
    private Text errorMessage;

    private final PersonCustomerRegistration registration;
    private SceneLoader sceneLoader;

    public StartViewController() {
        this.registration = new PersonCustomerRegistration(HibernateUtil.getSessionFactory());
        this.sceneLoader = new SceneLoader();
    }

    @FXML
    public void onPersonSave(ActionEvent event) {
        customerId.setText("");
        //take data from fields
        final var firstNameText = firstName.getText();
        final var lastNameText = lastName.getText();
        final var ssNumberText = ssNumber.getText();

        //build object from data
        final RegisterPersonForm form = new RegisterPersonForm(firstNameText, lastNameText, ssNumberText);

        //register person
        try {
            final var registeredCustomerId = registration.registerPerson(form);

            //return and show user ID
            customerId.setText(registeredCustomerId.getId().toString());
            errorMessage.setText("");

        } catch (Exception e) {
            errorMessage.setText(e.getMessage());
        }


    }

    @FXML
    public void displayCustomerList(ActionEvent event) throws IOException {
        sceneLoader.loadOnEvent("customer_list.fxml", event);
    }


}
