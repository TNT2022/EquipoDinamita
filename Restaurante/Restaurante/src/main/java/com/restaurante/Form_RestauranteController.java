/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import com.restaurante.BD.*;
import javafx.event.ActionEvent;
/**
 * FXML Controller class
 *
 * @author ZAPATA RIVAS
 */
public class Form_RestauranteController implements Initializable {

    private String mode;
    private RestauranteDao tools;
    private Item idItem;
    
    public Form_RestauranteController(){
        this.tools = new RestauranteDao();
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setIdItem(int Cuenta) {
        this.idItem = tools.getRestauranteItemsBycodigo(Cuenta);
    }
    
    @FXML
    private TextField txt_Codigo;
    @FXML
    private TextField txt_NombreCli;
    @FXML
    private TextField txt_NombreEmp;
    @FXML
    private TextField txt_RTN;
    @FXML
    private TextField txt_Costo;
    @FXML
    private Button Btn_Guardar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void btnGuardar_onclick(ActionEvent event) {
        if (this.mode == "new") {
            Item item = new Item();
            item.setCodigo(Integer.parseInt(this.txt_Codigo.getText()));
            item.setNombre(this.txt_NombreCli.getText());
            item.setNombre_Emp(this.txt_NombreEmp.getText());
            item.setRtn(this.txt_RTN.getText());
            item.setCosto(Double.parseDouble(this.txt_Costo.getText()));
            
            tools.insertRestauranteItem(item);
            App.closeForm(event);
        }
        
        if (this.mode == "upd") {
            Item item = new Item();
            item.setCodigo(Integer.parseInt(this.txt_Codigo.getText()));
            item.setNombre(this.txt_NombreCli.getText());
            item.setNombre_Emp(this.txt_NombreEmp.getText());
            item.setRtn(this.txt_RTN.getText());
            item.setCosto(Double.parseDouble(this.txt_Costo.getText()));
            
            tools.updateRestauranteItem(item);
            App.closeForm(event);
        }
        if (this.mode == "del") {
            tools.deleteRestauranteItem(this.idItem);
            App.closeForm(event);
        }
    }
    
    private void setDataToFxml(){
        this.txt_Codigo.setText(String.valueOf(this.idItem.getCodigo()));
        this.txt_NombreEmp.setText(this.idItem.getNombre_Emp());
        this.txt_NombreCli.setText(this.idItem.getNombre());
        this.txt_RTN.setText(this.idItem.getRtn());
        this.txt_Costo.setText(String.valueOf(this.idItem.getCosto()));
    }
    
    private void readOnlyFxml(){
        this.txt_Codigo.setEditable(false);
        this.txt_Costo.setEditable(false);
        this.txt_NombreCli.setEditable(false);
        this.txt_NombreEmp.setEditable(false);
        this.txt_RTN.setEditable(false);
    }
    
    public void setVIEW(){
        switch(this.mode){
            case "new":
                this.Btn_Guardar.setText("Crear");
                break;
            case "upd":
                this.Btn_Guardar.setText("Actualizar");
                this.setDataToFxml();
                break;
            case "dsp":
                this.Btn_Guardar.setVisible(false);
                this.setDataToFxml();
                this.readOnlyFxml();
                break;
            case "del":
                this.Btn_Guardar.setText("Eliminar");
                this.setDataToFxml();
                this.readOnlyFxml();
                break;
        }
    }   
}
