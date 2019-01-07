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
public class Permiso {
    private int id;
    private String descripcion;
    private String fecha_emision;
    private String fecha_expiracion;
    private String ruta_pdf;
    private String modo_permiso;
    private String numero_deposito;
    private String fecha_documento;
    private Tipo_Permiso permiso;
    private Clientes clientes;

    public Permiso() {
    }

    public String getModo_permiso() {
        return modo_permiso;
    }

    public void setModo_permiso(String modo_permiso) {
        this.modo_permiso = modo_permiso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha_emision() {
        try {
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dt.parse(fecha_emision);
            SimpleDateFormat dt1 = new SimpleDateFormat("d, MMMM yyyy", new Locale("es","ES"));
            return dt1.format(date);
        } catch (ParseException ex) {
            return fecha_emision;
        }
    }

    public void setFecha_emision(String fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public String getFecha_expiracion() {
        try {
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dt.parse(fecha_expiracion);
            SimpleDateFormat dt1 = new SimpleDateFormat("d, MMMM yyyy", new Locale("es","ES"));
            return dt1.format(date);
        } catch (ParseException ex) {
            return fecha_expiracion;
        }
    }

    public void setFecha_expiracion(String fecha_expiracion) {
        this.fecha_expiracion = fecha_expiracion;
    }

    public String getRuta_pdf() {
        return ruta_pdf;
    }

    public void setRuta_pdf(String ruta_pdf) {
        this.ruta_pdf = ruta_pdf;
    }

    public Tipo_Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Tipo_Permiso permiso) {
        this.permiso = permiso;
    }
    
    public String getValor(){
        return String.format("$%1$,.2f", this.permiso.getPrecio());
    }
    
    public String getVer(){
        return "Ver";
    }
    
    public String getEditar(){
        return "Editar";
    }
    
    public String getEliminar(){
        return "Eliminar";
    }
    
    public String getNombre(){
        return getCliente().getFullName();
    }
    
    public String getCedula(){
        return getCliente().getCedula();
    }

    public Clientes getCliente() {
        return clientes;
    }

    public void setCliente(Clientes cliente) {
        this.clientes = cliente;
    }

    public String getNumero_deposito() {
        return numero_deposito;
    }

    public void setNumero_deposito(String numero_deposito) {
        this.numero_deposito = numero_deposito;
    }

    public String getFecha_documento() {
        return fecha_documento;
    }

    public void setFecha_documento(String fecha_documento) {
        this.fecha_documento = fecha_documento;
    }
    
    public String getFullName(){
        return clientes.getFullName();
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
    
    public String getFullFechaExpiracion(){
        try {
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dt.parse(this.getFecha_expiracion());
            SimpleDateFormat dt1 = new SimpleDateFormat("d, MMMM yyyy", new Locale("es","ES"));
            return dt1.format(date);
        } catch (ParseException ex) {
            return this.getFecha_expiracion();
        }
    }
    
    public String getFullFechaEmision(){
        try {
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dt.parse(this.getFecha_emision());
            SimpleDateFormat dt1 = new SimpleDateFormat("d, MMMM yyyy", new Locale("es","ES"));
            return dt1.format(date);
        } catch (ParseException ex) {
            return this.getFecha_emision();
        }
    }
}
