/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Camilo Vargas
 */
@Entity
@Table(name = "profesor")
public class Profesor implements Serializable {

    /**
     * id que identifica al profesor
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * nombre del profesor
     */
    @Column(name = "nombre_profesor")
    //@NotNull(message = "Nombre requerido")
    private String nombre;
    /**
     * apellido del profesor
     */
    @Column(name = "apellido_profesor")
    @NotNull(message = "Apellido requerido")
    private String apellido;
    /**
     * correo personal del profesor
     */
    @Column(name = "correo_profesor")
    @NotNull(message = "Correp requerido")
    private String correo;
    /**
     * cedula del profesor
     */
    @Column(name = "cedula_profesor")
    @NotNull(message = "Cedula requerida")
    @Size(min = 7, max = 10, message = "cedula maximo 10 caracteres y minimo 7 caracteres ")
    private String cedula;

    @Column(name = "edad_profesor")
    @NotNull(message = "Edad requerida")
    private int edad;

    public Profesor(int id, String nombre, String apellido, String correo, String cedula, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.cedula = cedula;
        this.edad = edad;
    }

    public Profesor() {
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * metodo para obtener el id del profesor
     *
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * metodo para setear la valiable id
     *
     * @param id variable para guardar el id del profesor
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * metodo para obtener el nombre del profesor
     *
     * @return String
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * metodo para setear el nombre
     *
     * @param nombre variable para guardar el nombre del profesor
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * metod para obtener el apellido del profesor
     *
     * @return String
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * metodo para setear el valor del apellido
     *
     * @param apellido variable para guardar el apellido del profesor
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * metodo para obtener el correo del profesor
     *
     * @return String
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * metodo para setear el valor del correo
     *
     * @param correo variable para guardar el correo del profesor
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * metodo para obtener la cedula del profesor
     *
     * @return String
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * metodo pars setear la cedula del profesor
     *
     * @param cedula variable para guardar la cedula del profesor
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
}
