/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.BD;


import com.restaurante.Item;
import java.util.ArrayList;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author edith
 */
public class RestauranteDao {
     private ArrayList _RestauranteItems;
    
    public RestauranteDao(){
        this._RestauranteItems = new ArrayList<Item>();
    }
    
    public ArrayList<Item> getRestauranteItems(){
        return this.getRestauranteItems(false);
       
    }
    
    public void tableInitialize(){
        String sqlCreate = "CREATE TABLE IF NOT EXISTS RESTAURANTE"
                        + "(CODIGO INTEGER PRIMARY KEY NOT NULL,"
                        + " NOMBRE TEXT NOT NULL,"
                        + " NOMBRE_EMP TEXT NOT NULL,"
                        + " RTN TEXT NOT NULL,"
                        + " COSTO DOUBLE NOT NULL"
                        + ")";
       
        try {
           
            Statement comando =  conexion.conexion_bd().createStatement();
            int resultado = comando.executeUpdate(sqlCreate);
            comando.close();
        } catch( Exception ex){
            System.err.println(ex.getMessage());
        }
    }
    
    public ArrayList<Item> getRestauranteItems(boolean forceLoad){
        try {
           if (forceLoad) {
                Statement comando =   conexion.conexion_bd().createStatement();
                ResultSet misRegistro = comando.executeQuery("SELECT * from RESTAURANTE;");
                this._RestauranteItems.clear();
                while (misRegistro.next()) {
                    Item Factura = new Item();
                    Factura.setCodigo(misRegistro.getInt("CODIGO"));
                    Factura.setNombre(misRegistro.getString("NOMBRE"));
                    Factura.setNombre_Emp(misRegistro.getString("NOMBRE_EMP"));
                    Factura.setRtn(misRegistro.getString("RTN"));
                    Factura.setCosto(misRegistro.getDouble("COSTO"));
                    
                    this._RestauranteItems.add(Factura);
                }
                comando.close();
           }
           return this._RestauranteItems;
        } catch(Exception ex){
            System.err.println(ex.getMessage());
            return this._RestauranteItems;
        }   
    }
    
    public Item getRestauranteItemsBycodigo(int codigo){
        try {
            String SQLGetByID = "SELECT * FROM RESTAURANTE WHERE CODIGO = ?;";
            PreparedStatement comando = conexion.conexion_bd().prepareStatement(SQLGetByID);
            comando.setInt(1, codigo);
            ResultSet misRegistro = comando.executeQuery();
            Item Factura = new Item();
            while (misRegistro.next()) {
                Factura.setCodigo(misRegistro.getInt("CODIGO"));
                Factura.setNombre(misRegistro.getString("NOMBRE"));
                Factura.setNombre_Emp(misRegistro.getString("NOMBRE_EMP"));
                Factura.setRtn(misRegistro.getString("RTN"));
                Factura.setCosto(misRegistro.getDouble("COSTO"));
                break;
            }
            comando.close();

            return Factura;
        } catch(Exception ex){
            System.err.println(ex.getMessage());
            return null;
        }   
    }
    
    public int updateRestauranteItem(Item ItemToUpdate) {
        try {
            String SQLUpdate = "UPDATE RESTAURANTE set NOMBRE=?, NOMBRE_EMP=?, RTN=?, COSTO=? where CODIGO=?;";
            PreparedStatement comando = conexion.conexion_bd().prepareStatement(SQLUpdate);
            
            comando.setString(1, ItemToUpdate.getNombre());
            comando.setString(2, ItemToUpdate.getNombre_Emp());
            comando.setString(3, ItemToUpdate.getRtn());
            comando.setDouble(4, ItemToUpdate.getCosto());
            comando.setInt (5, ItemToUpdate.getCodigo());
            
            int registrAfectado = comando.executeUpdate();
            comando.close();
            return registrAfectado;
            
        } catch( Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
     public int insertRestauranteItem(Item ItemToInsert) {
        try {
            //                                                                      1, 2, 3
            String SQLInsert = "INSERT INTO RESTAURANTE (CODIGO, NOMBRE, NOMBRE_EMP, RTN, COSTO) values (?, ?, ?, ?, ?);";
            PreparedStatement comando = conexion.conexion_bd().prepareStatement(SQLInsert);
            
            comando.setInt(1, ItemToInsert.getCodigo()); // ? 
            comando.setString(2, ItemToInsert.getNombre());
            comando.setString(3, ItemToInsert.getNombre_Emp());
            comando.setString(4, ItemToInsert.getRtn());
            comando.setDouble(5, ItemToInsert.getCosto());
            
            int registrAfectado = comando.executeUpdate();
            comando.close();
            return registrAfectado;
            
        } catch( Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
     
    public int deleteRestauranteItem(Item ItemToDelete) {
        try {
            String SQLDelete = "DELETE FROM RESTAURANTE WHERE CODIGO = ?;";
            PreparedStatement comando = conexion.conexion_bd().prepareStatement(SQLDelete);
            
            comando.setInt(1, ItemToDelete.getCodigo());
            
            int registrAfectado = comando.executeUpdate();
            comando.close();
            return registrAfectado;
            
        } catch( Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
    
}
