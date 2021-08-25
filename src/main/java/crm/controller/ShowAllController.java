package crm.controller;

import crm.entity.*;
import crm.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.UUID;

public class ShowAllController {

    @FXML
    private TableView<Customer> tableView;

    @FXML
    private TableColumn<Customer, UUID> idColumn;
    @FXML
    private TableColumn<Customer, String> customerTypeColumn;
    @FXML
    private TableColumn<Customer, UUID> premiumStatusColumn;
    @FXML
    private TableColumn<Customer, UUID> verifyStatusColumn;
    @FXML
    private TableColumn<Customer, UUID> contactListColumn;
    @FXML
    private TableColumn<Customer, String> firstNameColumn;
    @FXML
    private TableColumn<Customer, String> lastNameColumn;
    @FXML
    private TableColumn<Customer, SsNumber> peselColumn;
    @FXML
    private TableColumn<Customer, String> companyNameColumn;
    @FXML
    private TableColumn<Customer, NipNumber> nipColumn;
    @FXML
    private TableColumn<Customer, UUID> addressListColumn;

    private SceneLoader sceneLoader;

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private Session session;
    private Transaction transaction;


    public ShowAllController() {

        this.session = sessionFactory.openSession();
        this.sceneLoader = new SceneLoader();
        this.transaction = session.beginTransaction();
    }

    public void updateTable() {

        //setColumnNames

        idColumn.setCellValueFactory(new PropertyValueFactory<Customer, UUID>("id"));
        customerTypeColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("CUSTOMERTYPE"));
        premiumStatusColumn.setCellValueFactory(new PropertyValueFactory<Customer, UUID>("premiumStatus"));
        verifyStatusColumn.setCellValueFactory(new PropertyValueFactory<Customer, UUID>("verificationStatus"));
        contactListColumn.setCellValueFactory(new PropertyValueFactory<Customer, UUID>("contactList"));
        addressListColumn.setCellValueFactory(new PropertyValueFactory<Customer, UUID>("addresses"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
        peselColumn.setCellValueFactory(new PropertyValueFactory<Customer, SsNumber>("ssNumber".toString()));

        companyNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("companyName"));
        nipColumn.setCellValueFactory(new PropertyValueFactory<Customer, NipNumber>("nipNumber".toString()));


        //load data to table
        tableView.setItems(getData());

    }

    public ObservableList<Customer> getData() {
        List customers = FXCollections.observableArrayList();
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();

        try {
            customers = session.createCriteria(Customer.class).list();

            transaction.commit();
            customerObservableList.addAll(customers);

        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return customerObservableList;
    }


    @FXML
    public void goBack(ActionEvent event) throws IOException {
        sceneLoader.loadOnEvent("main_view.fxml", event);
    }

}
