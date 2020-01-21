/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schroniskoapp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author Macius-laptop
 */
public class Schroniska {

    private Integer SchroniskoId, SchroniskoNumerLokalu;
    private String SchroniskoNazwa, SchroniskoMiejscowosc, SchroniskoUlica;

    public Integer getSchroniskoId() {
        return SchroniskoId;
    }

    public void setSchroniskoId(Integer SchroniskoId) {
        this.SchroniskoId = SchroniskoId;
    }

    public Integer getSchroniskoNumerLokalu() {
        return SchroniskoNumerLokalu;
    }

    public void setSchroniskoNumerLokalu(Integer SchroniskoNumerLokalu) {
        this.SchroniskoNumerLokalu = SchroniskoNumerLokalu;
    }

    public String getSchroniskoNazwa() {
        return SchroniskoNazwa;
    }

    public void setSchroniskoNazwa(String SchroniskoNazwa) {
        this.SchroniskoNazwa = SchroniskoNazwa;
    }

    public String getSchroniskoMiejscowosc() {
        return SchroniskoMiejscowosc;
    }

    public void setSchroniskoMiejscowosc(String SchroniskoMiejscowosc) {
        this.SchroniskoMiejscowosc = SchroniskoMiejscowosc;
    }

    public String getSchroniskoUlica() {
        return SchroniskoUlica;
    }

    public void setSchroniskoUlica(String SchroniskoUlica) {
        this.SchroniskoUlica = SchroniskoUlica;
    }

    public ObservableList<Schroniska> getAll(Connection conn) {
        ObservableList<Schroniska> listaSchronisk = FXCollections.observableArrayList();
        String sql = "SELECT Id_Schroniska,Nazwa,Miejscowosc,Ulica, Nr_Lokalu from Schroniska order by Id_Schroniska";
        Statement stmt;
        ResultSet rs;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Schroniska schronisko = new Schroniska();
                schronisko.SchroniskoId = rs.getInt(1);
                schronisko.SchroniskoNazwa = rs.getString(2);
                schronisko.SchroniskoMiejscowosc = rs.getString(3);
                schronisko.SchroniskoUlica = rs.getString(4);
                schronisko.SchroniskoNumerLokalu = rs.getInt(5);
                System.out.println("SchroniskoID: " + schronisko.SchroniskoId.toString() + " Nazwa Schroniska: "
                        + schronisko.SchroniskoNazwa);
                listaSchronisk.add(schronisko);
            }
        } catch (SQLException exc) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errots with data access");
            alert.setContentText("Details: " + exc.getMessage());
            alert.showAndWait();
        }
        return listaSchronisk;
    }
}
