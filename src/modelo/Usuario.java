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
public class Usuario {
    private int id;
    private String first_name;
    private String last_name;
    private String usuario;
    private String password;
    private Boolean is_active;
    private Boolean is_superuser;
    
    public Usuario(){
        
    }

    public Usuario(int id, String first_name, String last_name, String usuario, String password, Boolean is_active, Boolean is_superuser) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.usuario = usuario;
        this.password = password;
        this.is_active = is_active;
        this.is_superuser = is_superuser;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }
    

    public Boolean getIs_superuser() {
        return is_superuser;
    }

    public void setIs_superuser(Boolean is_superuser) {
        this.is_superuser = is_superuser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getActivado(){
        if(this.is_active){
            return "Activo";
        }else{
            return "No Activo";
        }
    }
    
    public String getAdmin(){
        if(this.is_superuser){
            return "Si";
        }else{
            return "No";
        }
    }
}
