/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.pojo;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author PROFESIONAL
 */
public class Profesor implements Serializable{
    @NotNull(message = "Campo requerido")
    private int id;
    @NotNull(message = "Campo requerido")   
    private String nombre;
    @NotNull(message = "Campo requerido")
    private String apellido;
    @NotNull(message = "Campo requerido")
    private List<String> listaMateria;
    @NotNull(message = "Campo requerido")
    private String  correo;
    @NotNull(message = "Campo requerido")
    @Size(max = 25)
    private String cedula;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<String> getListaMateria() {
        return listaMateria;
    }

    public void setListaMateria(List<String> listaMateria) {
        this.listaMateria = listaMateria;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
 }
