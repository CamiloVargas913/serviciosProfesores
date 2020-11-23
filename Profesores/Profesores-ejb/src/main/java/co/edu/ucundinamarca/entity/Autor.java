/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author PROFESIONAL
 */
@Entity
@Table(name = "autor")
@NamedQueries({
    @NamedQuery(name = "Autor.listarTodo", query = "SELECT a FROM Autor a GROUP BY a.id"),
    @NamedQuery(name = "Autor.listarSoloAutor", query = "SELECT a.id, a.nombre, a.apellido, a.fecha, a.estado FROM Autor a"),
    @NamedQuery(name = "Autor.validarAutorId", query = "SELECT COUNT(p.id)  FROM Autor p WHERE p.id = :id"),
    @NamedQuery(name = "Autor.cambiarEstado", query = "UPDATE Autor a SET a.estado= :estado WHERE a.id = :id"),
    @NamedQuery(name = "Autor.actualizarAutor", query = "UPDATE Autor a SET a.nombre= :nombre, a.apellido= :apellido WHERE a.id = :id"),
    @NamedQuery(name = "Autor.obtenerTotal", query = "SELECT COUNT(p.id)  FROM Autor p ")
})
public class Autor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 25)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 25)
    private String apellido;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(name = "estado", nullable = false)
    private boolean estado;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Libro> libro;

    @OneToOne(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Direccion direccion;

    public Autor() {

    }

    public Autor(Integer id, String nombre, String apellido, Date fecha, boolean estado, List<Libro> libro, Direccion direccion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha = fecha;
        this.estado = estado;
        this.libro = libro;
        this.direccion = direccion;
    }
    
    
    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Libro> getLibro() {
        return libro;
    }

    public void setLibro(List<Libro> libro) {
        this.libro = libro;
    }
}
