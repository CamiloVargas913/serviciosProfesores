/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Camilo Vargas
 */
@Entity
@Table(name = "profesor")
@NamedQueries({
    @NamedQuery(name = "Profesor.listarTodo", query = "SELECT p FROM Profesor p"),
    @NamedQuery(name = "Profesor.validarCedula", query = "SELECT COUNT(p.cedula)  FROM Profesor p WHERE p.cedula = :cedula AND p.id <> :id"),
    @NamedQuery(name = "Profesor.validarCorreo", query = "SELECT COUNT(p.correo)  FROM Profesor p WHERE p.correo = :correo AND p.id <> :id"),
    @NamedQuery(name = "Profesor.validarCedulaInsert", query = "SELECT COUNT(p.cedula)  FROM Profesor p WHERE p.cedula = :cedula"),
    @NamedQuery(name = "Profesor.validarCorreoInsert", query = "SELECT COUNT(p.correo)  FROM Profesor p WHERE p.correo = :correo")
})
public class Profesor implements Serializable {

    /**
     * id que identifica al profesor
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * nombre del profesor
     */
    @Column(name = "nombre_profesor", nullable = false, length = 25)
    @Size(min = 3, max = 25, message = "Nombre maximo 25 caracteres y minimo 3 caracteres ")
    @NotNull(message = "Nombre requerido")
    private String nombre;
    /**
     * apellido del profesor
     */
    @Column(name = "apellido_profesor", nullable = false, length = 25)
    @Size(min = 3, max = 25, message = "Apellido maximo 25 caracteres y minimo 3 caracteres ")
    @NotNull(message = "Apellido requerido")
    private String apellido;
    /**
     * correo personal del profesor
     */
    @Column(name = "correo_profesor", length = 60, nullable = false, unique = true)
    @NotNull(message = "Correo requerido")
    @Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message = "Correo invalido ")
    private String correo;
    /**
     * cedula del profesor
     */
    @Column(name = "cedula_profesor", length = 10, nullable = false, unique = true)
    @NotNull(message = "Cedula requerida")
    @Size(min = 7, max = 10, message = "cedula maximo 10 caracteres y minimo 7 caracteres ")
    private String cedula;

    @Column(name = "edad_profesor")
    @Min(value = 18, message = "Edad minima 18")
    @NotNull(message = "Edad requerida")
    private Integer edad;

    @Column(name = "fecha_nacimiento")
    @NotNull(message = "Fecha de nacimiento requerida")
    private Date fechaNacimiento;

    public Profesor(Integer id, String nombre, String apellido, String correo, String cedula, Integer edad, Date fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.cedula = cedula;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Profesor() {
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    /**
     * metodo para obtener el id del profesor
     *
     * @return int
     */
    public Integer getId() {
        return id;
    }

    /**
     * metodo para setear la valiable id
     *
     * @param id variable para guardar el id del profesor
     */
    public void setId(Integer id) {
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
