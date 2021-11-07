/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.BD;

import java.sql.*;

/**
 *
 * @author ZAPATA RIVAS
 */
public class conexion {
    private static Connection _conexion=null;
    
    private conexion(){
        
    }
    
    public static Connection conexion_bd(){
        try{
            if(_conexion == null){
                Class.forName("org.sqlite.JDBC");
                _conexion=DriverManager.getConnection("jdbc:sqlite:FacturaRest.db");
                return _conexion;
            }
            
            else{
                return _conexion;
            }
        }
        catch(Exception ex){
            System.err.println("Error:...."+ex.getMessage());
            return null;
        }
    }
}
