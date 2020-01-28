/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schroniskoapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
                        + schronisko.SchroniskoNazwa + " Mijescowosc " + schronisko.SchroniskoMiejscowosc + " Ulica" + schronisko.SchroniskoUlica + " NrLokalu" + schronisko.SchroniskoNumerLokalu);
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

    public ObservableList<Schroniska> getRestrictedList(Connection conn, String schroniskoName) {
        ObservableList<Schroniska> listaSchronisk = FXCollections.observableArrayList();
        String sql = "SELECT Id_Schroniska,Nazwa,Miejscowosc,Ulica, Nr_Lokalu "
                + "from Schroniska where upper(Nazwa) like ? order by Id_Schroniska";
        PreparedStatement stmt;
        ResultSet rs;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,schroniskoName.toUpperCase()+"%");
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Schroniska schronisko = new Schroniska();
                schronisko.SchroniskoId = rs.getInt(1);
                schronisko.SchroniskoNazwa = rs.getString(2);
                schronisko.SchroniskoMiejscowosc = rs.getString(3);
                schronisko.SchroniskoUlica = rs.getString(4);
                schronisko.SchroniskoNumerLokalu = rs.getInt(5);
                System.out.println("SchroniskoID: " + schronisko.SchroniskoId.toString() + " Nazwa Schroniska: "
                        + schronisko.SchroniskoNazwa + " Mijescowosc " + schronisko.SchroniskoMiejscowosc + " Ulica" + schronisko.SchroniskoUlica + " NrLokalu" + schronisko.SchroniskoNumerLokalu);
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
    
    public int insertSchronisko(Connection conn, String nazwa,String miejscowosc,String ulica,Integer nrLokalu){
        String sql = "INSERT INTO Schroniska(Nazwa,Miejscowosc,Ulica, Nr_Lokalu) values (%,%,%,%)";
        PreparedStatement stmt;
        Integer res=1;
        
        try{
            stmt= conn.prepareStatement(sql);
            stmt.setString(1,nazwa);
            stmt.setString(2,miejscowosc);
            stmt.setString(3,ulica);
            stmt.setInt(4,nrLokalu);
            
            res =stmt.executeUpdate();
            System.out.println(res);
        } catch(SQLException exc){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errots with data access");
            alert.setContentText("Details: " + exc.getMessage());
            alert.showAndWait();
        
        }
        
        return res;
    }
}
