/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author saulmestanza
 */
public class Cantidad {
    
    private int cantidad;
    private String nombre;
    private String cedula;
    private String razonsocial;
    private String fechaemision;
    private String fechaexpiracion;
    private int id;
    private double precio;
    private String permiso;
    
    public Cantidad(){
        
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    
    public String getPermiso() {
        return permiso;
    }

    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getFechaemision() {
        try {
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dt.parse(fechaemision);
            SimpleDateFormat dt1 = new SimpleDateFormat("d, MMMM yyyy", new Locale("es","ES"));
            return dt1.format(date);
        } catch (ParseException ex) {
            return fechaemision;
        }
    }

    public void setFechaemision(String fechaemision) {
        this.fechaemision = fechaemision;
    }

    public String getFechaexpiracion() {
        try {
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dt.parse(fechaexpiracion);
            SimpleDateFormat dt1 = new SimpleDateFormat("d, MMMM yyyy", new Locale("es","ES"));
            return dt1.format(date);
        } catch (ParseException ex) {
            return fechaexpiracion;
        }
    }

    public void setFechaexpiracion(String fechaexpiracion) {
        this.fechaexpiracion = fechaexpiracion;
    }

    
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    public String getFullCode(){
        if(this.getId() < 10){
            return String.format("0000%d", this.getId());
        }else if(this.getId() >= 10 && this.getId() < 100){
            return String.format("000%d", this.getId());
        }else if(this.getId() >= 100 && this.getId() < 1000){
            return String.format("00%d", this.getId());
        }else if(this.getId() >= 1000 && this.getId() < 10000){
            return String.format("00%d", this.getId());
        }else{
            return String.format("%d", this.getId());
        }
    }
    
}
