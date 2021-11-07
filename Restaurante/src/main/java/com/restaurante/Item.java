/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ZAPATA RIVAS
 */
public class Item {

    public Item() {
        this.codigo = new SimpleIntegerProperty(0);
        this.nombre = new SimpleStringProperty("");
        this.nombre_Emp = new SimpleStringProperty("");
        this.rtn = new SimpleStringProperty("");
        this.costo = new SimpleDoubleProperty(0);
    }
    
    private SimpleIntegerProperty codigo;
    private SimpleStringProperty nombre;
    private SimpleStringProperty nombre_Emp;
    private SimpleStringProperty rtn;
    private SimpleDoubleProperty costo;

    public Integer getCodigo() {
        return codigo.get();
    }

    public void setCodigo(Integer codigo) {
        this.codigo.set(codigo);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getNombre_Emp() {
        return nombre_Emp.get();
    }

    public void setNombre_Emp(String nombre_Emp) {
        this.nombre_Emp.set(nombre_Emp);
    }

    public String getRtn() {
        return rtn.get();
    }

    public void setRtn(String rtn) {
        this.rtn.set(rtn);
    }

    public Double getCosto() {
        return costo.get();
    }

    public void setCosto(Double costo) {
        this.costo.set(costo);
    }

    public Item(SimpleIntegerProperty codigo, SimpleStringProperty nombre, SimpleStringProperty nombre_Emp, SimpleStringProperty rtn, SimpleDoubleProperty costo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.nombre_Emp = nombre_Emp;
        this.rtn = rtn;
        this.costo = costo;
    }
}
