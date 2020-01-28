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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;



/**
 *
 * @author Macius-laptop
 */
public class FXMLDocumentController implements Initializable {
    private Connection conn;
    private ObservableList<Schroniska> listaSchronisk = FXCollections.observableArrayList();
    private Schroniska schroniska;
    @FXML
    private Label label;

    @FXML
    private TableView<Schroniska> tableSchroniska;

    @FXML
    private TableColumn<Schroniska, Integer> tableColumnIdSchroniska;

    @FXML
    private TableColumn<Schroniska, String> tableColumnNazwa;

    @FXML
    private TableColumn<Schroniska, String> tableColumnMiejscowosc;

    @FXML
    private TableColumn<Schroniska, String> tableColumnUlica;

    @FXML
    private TableColumn<Schroniska, Integer> tableColumnNrLokalu;

    @FXML
    private Button searchButton;

    @FXML
    private Label titleLabel;

    @FXML
    private Label searchParamLabel;

    @FXML
    private TextField textInput;

    @FXML
    private TextField TextFieldNazwaSchroniska;

    @FXML
    private TextField TextFieldMiejscowoscSchroniska;

    @FXML
    private Button addSchroniskoButton;

    @FXML
    private TextField TextFieldUlicaSchroniska;

    @FXML
    private TextField TextFieldNrLokaluSchroniska;

    
    
    @FXML
    void searchButtonOnAction(ActionEvent event) {
        System.out.println("asdasd");
        conn = DBConnection.getConnection();
        System.out.println(textInput.getText().trim());
        listaSchronisk = new Schroniska().getRestrictedList(conn,textInput.getText().trim());
        
        System.out.println("Baba: "+tableColumnNazwa.getText().trim());
        settableViewSchroniska(listaSchronisk);
    }
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @FXML
    private void buttonAddSchroniskoOnAction(ActionEvent event){
    String nazwa,miejscowosc,ulica;
    Integer nrLokalu;
    Integer result;
    
    
    nazwa = TextFieldNazwaSchroniska.getText().trim();
    miejscowosc = TextFieldMiejscowoscSchroniska.getText().trim();
    ulica = TextFieldUlicaSchroniska.getText().trim();
    
    nrLokalu = Integer.parseInt(TextFieldNrLokaluSchroniska.getText().trim());
    
    try{
        conn = DBConnection.getConnection();
        result = new Schroniska().insertSchronisko(conn,nazwa,miejscowosc,ulica,nrLokalu);
        System.out.println("wyslano");
    }catch(NumberFormatException exc){
        System.out.println("zly format textu");
    }
    
    
    
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = DBConnection.getConnection();
        
        schroniska = new Schroniska();
        
        listaSchronisk = schroniska.getAll(conn);
 
        tableColumnIdSchroniska.setCellValueFactory(new PropertyValueFactory<>("SchroniskoId"));
        tableColumnNazwa.setCellValueFactory(new PropertyValueFactory<>("SchroniskoNazwa"));
        tableColumnMiejscowosc.setCellValueFactory(new PropertyValueFactory<>("SchroniskoMiejscowosc"));
        tableColumnUlica.setCellValueFactory(new PropertyValueFactory<>("SchroniskoUlica"));
        tableColumnNrLokalu.setCellValueFactory(new PropertyValueFactory<>("SchroniskoNumerLokalu"));
        
        settableViewSchroniska(listaSchronisk);
        
    }    
    private void settableViewSchroniska(ObservableList<Schroniska> listaSchronisk){
        tableColumnIdSchroniska.setCellValueFactory(new PropertyValueFactory<>("SchroniskoId"));
        tableColumnNazwa.setCellValueFactory(new PropertyValueFactory<>("SchroniskoNazwa"));
        tableColumnMiejscowosc.setCellValueFactory(new PropertyValueFactory<>("SchroniskoMiejscowosc"));
        tableColumnUlica.setCellValueFactory(new PropertyValueFactory<>("SchroniskoUlica"));
        tableColumnNrLokalu.setCellValueFactory(new PropertyValueFactory<>("SchroniskoNumerLokalu"));
        
        tableSchroniska.setItems(listaSchronisk);
    }
}
