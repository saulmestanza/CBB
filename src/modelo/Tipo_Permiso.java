/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author saulmestanza
 */
public class Tipo_Permiso {
    private int id;
    private String tipo_permiso;
    private Double precio;
    private boolean is_active;

    public Tipo_Permiso() {
    }

    public Tipo_Permiso(int id, String tipo_permiso, Double precio, boolean is_active) {
        this.id = id;
        this.tipo_permiso = tipo_permiso;
        this.precio = precio;
        this.is_active = is_active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo_permiso() {
        return tipo_permiso;
    }

    public void setTipo_permiso(String tipo_permiso) {
        this.tipo_permiso = tipo_permiso;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }
    
    public String getActivado(){
        if(this.is_active){
            return "Activo";
        }else{
            return "No Activo";
        }
    }
    
    @Override
    public String toString(){
        return this.getTipo_permiso();
    }

    public String toSimple(){
        if(this.getTipo_permiso().contains("-")){
            String split[] = this.getTipo_permiso().split("-");
            return split[0];
        }else{
            return this.getTipo_permiso();
        }
    }
}
