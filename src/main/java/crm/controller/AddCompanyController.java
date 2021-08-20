package crm.controller;

import crm.service.CompanyCustomerRegistration;
import crm.service.RegisterCompanyForm;
import crm.util.HibernateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javafx.event.ActionEvent;
import java.io.IOException;

public class AddCompanyController {

    @FXML
    private TextField companyName;

    @FXML
    private TextField NIP;

    @FXML
    private Text customerId;

    @FXML
    private Text errorMessage;

    private final CompanyCustomerRegistration registration;
    private SceneLoader sceneLoader;

    public AddCompanyController() {
        this.registration = new CompanyCustomerRegistration(HibernateUtil.getSessionFactory());
        this.sceneLoader = new SceneLoader();
    }


    @FXML
    public void goBack(ActionEvent event) throws IOException {
        sceneLoader.loadOnEvent("main_view.fxml", event);

    }

    @FXML
    public void onCompanySave(ActionEvent event) {
        customerId.setText("");

        final var companyNameText = companyName.getText();
        final var companyNIPText = NIP.getText();

        final RegisterCompanyForm form = new RegisterCompanyForm(companyNameText,companyNIPText);

        try{
            final var registeredCompanyId = registration.registeredCompany(form);

            customerId.setText(registeredCompanyId.toString());

            errorMessage.setText("");
        }
        catch (Exception e){
            errorMessage.setText(e.getMessage());
        }
    }
}
