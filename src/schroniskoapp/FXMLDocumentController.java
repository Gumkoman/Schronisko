/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schroniskoapp;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;



/**
 *
 * @author Macius-laptop
 */
public class FXMLDocumentController implements Initializable {
    private Connection conn;
    private ObservableList<Schroniska> listaSchronisk = FXCollections.observableArrayList();
     @FXML
    private Label label;

    @FXML
    private TableView<?> mainTable;

    @FXML
    private TableColumn<?, ?> idTable;

    @FXML
    private TableColumn<?, ?> nameTabel;

    @FXML
    private TableColumn<?, ?> cityTable;

    @FXML
    private TableColumn<?, ?> streetTable;

    @FXML
    private TableColumn<?, ?> numerTable;

    @FXML
    private Button searchButton;

    @FXML
    private Label titleLabel;

    @FXML
    private Label searchParamLabel;

    @FXML
    private TextArea textInput;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = DBConnection.getConnection();
        
        listaSchronisk = new Schroniska().getAll(conn);
    }    
    
}
