/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import cbb_reportes.PrecioToLetras;
import java.text.DateFormat;
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
    private String vehiculo_marca;
    private String placa;
    private Boolean extintor;
    private String capacidad;
    private Tipo_Permiso permiso;
    private Clientes clientes;
    private String codigo_permiso;
    private Boolean deleted;
    private String razon_social;
    private String direccion;
    private String actividad_economica;

    public Permiso() {
    }

    public String getCodigo_permiso() {
        return codigo_permiso;
    }

    public void setCodigo_permiso(String codigo_permiso) {
        this.codigo_permiso = codigo_permiso;
    }

    public String getVehiculo_marca() {
        return vehiculo_marca;
    }

    public void setVehiculo_marca(String vehiculo_marca) {
        this.vehiculo_marca = vehiculo_marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Boolean getExtintor() {
        return extintor;
    }

    public void setExtintor(Boolean extintor) {
        this.extintor = extintor;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
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
            SimpleDateFormat dt1 = new SimpleDateFormat("d, MMMM yyyy", new Locale("es", "ES"));
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
            SimpleDateFormat dt1 = new SimpleDateFormat("d, MMMM yyyy", new Locale("es", "ES"));
            return dt1.format(date);
        } catch (ParseException ex) {
            return fecha_expiracion;
        }
    }
    
    public String getFechaExpiracion(){
        return fecha_expiracion;
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

    public String getValor() {
        return this.deleted ? "ANULADO" : String.format("$%1$,.2f", this.permiso.getPrecio());
    }

    public String getVer() {
        return "Ver";
    }

    public String getEditar() {
        return "Editar";
    }

    public String getEliminar() {
        return "Anular";
    }

    public String getNombre() {
        return getCliente().getFullName();
    }

    public String getCedula() {
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getActividad_economica() {
        return actividad_economica;
    }

    public void setActividad_economica(String actividad_economica) {
        this.actividad_economica = actividad_economica;
    }

    public String getFullName() {
        return clientes.getFullName();
    }
    
    public String getFullFechaExpiracion() {
        try {
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dt.parse(this.getFecha_expiracion());
            SimpleDateFormat dt1 = new SimpleDateFormat("d, MMMM yyyy", new Locale("es", "ES"));
            return dt1.format(date);
        } catch (ParseException ex) {
            return this.getFecha_expiracion();
        }
    }

    public String getPrecioEnLetras() {
        double precio = this.getPermiso().getPrecio();
        String entero = PrecioToLetras.cantidadConLetra(String.valueOf(precio));
        double x = precio - (long) precio;
        String decimal = String.format("%.0f/100", x * 100);
        return String.format("%s %s", entero, decimal);
    }

    public String getAnoEmision() {
        try {
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dt.parse(fecha_emision);
            SimpleDateFormat dt1 = new SimpleDateFormat("yyyy", new Locale("es", "ES"));
            return dt1.format(date);
        } catch (ParseException ex) {
            return fecha_emision;
        }
    }

    public String getFullFechaEmision() {
        try {
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dt.parse(this.getFecha_emision());
            SimpleDateFormat dt1 = new SimpleDateFormat("d, MMMM yyyy", new Locale("es", "ES"));
            return dt1.format(date);
        } catch (ParseException ex) {
            return this.getFecha_emision();
        }
    }

    public String getHora() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        return dateFormat.format(date);
    }
    
    public String getSpermiso(){
        return this.getPermiso().getTipo_permiso();
    }
}
