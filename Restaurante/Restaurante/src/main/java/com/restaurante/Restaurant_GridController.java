/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante;

import com.restaurante.BD.*;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.*;
/**
 * FXML Controller class
 *
 * @author ZAPATA RIVAS
 */
public class Restaurant_GridController implements Initializable {


    @FXML
    private TableView<Item> Grid;
    @FXML
    private TableColumn Clm_Codigo;
    @FXML
    private TableColumn Clm_NomCli;
    @FXML
    private TableColumn Clm_NomEmp;
    @FXML
    private TableColumn Clm_Rtn;
    @FXML
    private TableColumn Clm_Costo;
    @FXML
    private Button Btn_Eliminar;
    @FXML
    private Button btn_Mostrar;
    @FXML
    private Button btn_Editar;
    @FXML
    private Button btn_Nuevo;
    
    private RestauranteDao tool;
    private ObservableList<Item> dataset;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.Clm_Codigo.setCellValueFactory(
                new PropertyValueFactory<>("codigo")
        );
        this.Clm_Costo.setCellValueFactory(
                new PropertyValueFactory<>("costo")
        );
        this.Clm_NomCli.setCellValueFactory(
                new PropertyValueFactory<>("nombre")
        );  
        this.Clm_NomEmp.setCellValueFactory(
                new PropertyValueFactory<>("nombre_Emp")
        );
        this.Clm_Rtn.setCellValueFactory(
                new PropertyValueFactory<>("rtn")
        );
        
        this.tool=new RestauranteDao();
        this.tool.tableInitialize();
        
        this.dataset=FXCollections.observableArrayList(this.tool.getRestauranteItems(true));
        this.Grid.getItems().addAll(dataset);
    }
    
    //Sin terminar
    @FXML
    private void nuevo_dato(ActionEvent event){
        try{
            FXMLLoader fxmlloader=App.getFXMLLoader("Form_Restaurante");
            Parent scene=fxmlloader.load();
            Form_RestauranteController sceneController=(Form_RestauranteController) fxmlloader.getController();
            
            sceneController.setMode("new");
            sceneController.setVIEW();
            App.loadFXMLModal(scene);
            this.updTable();
        }
        catch(IOException ex){
            System.err.println(ex.getMessage());
        }
    }
    
    private void Actualizar_Dato(String mode){
        Item newItem = Grid.getSelectionModel().getSelectedItem();
        int cuenta = newItem.getCodigo();
        try{
            FXMLLoader fxmlloader=App.getFXMLLoader("Form_Restaurante");
            Parent scene=fxmlloader.load();
            Form_RestauranteController sceneController=(Form_RestauranteController) fxmlloader.getController();
            
            sceneController.setMode(mode);
            sceneController.setIdItem(cuenta);
            sceneController.setVIEW();
            
            App.loadFXMLModal(scene);
            this.updTable();
        }
        catch(IOException ex){
            System.err.println(ex.getMessage());
        }
    }
    
    @FXML
    private void edit_onclick(ActionEvent event) {
        this.Actualizar_Dato("upd");
    }

    @FXML
    private void display_onclick(ActionEvent event) {
        this.Actualizar_Dato("dsp");
    }

    @FXML
    private void delete_onclick(ActionEvent event) {
        this.Actualizar_Dato("del");
    }
    
    private void updTable(){
        this.Grid.getItems().clear();
        this.dataset=FXCollections.observableArrayList(this.tool.getRestauranteItems(true));
        this.Grid.getItems().addAll(dataset);
    }
    
}
