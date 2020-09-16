/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.pojo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

/**
 *
 * @author david
 */
public class ErrorWrapper implements Serializable {
    private String error;
    
    private String codigo;
    
    private String nombreCodigo;
    
    private LocalDateTime fecha;

    public ErrorWrapper(String error, String codigo, String nombreCodigo) {
        this.error = error;
        this.codigo = codigo;
        this.nombreCodigo = nombreCodigo;
        this.fecha =  LocalDateTime.of(LocalDate.now(), LocalTime.now());
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreCodigo() {
        return nombreCodigo;
    }

    public void setNombreCodigo(String nombreCodigo) {
        this.nombreCodigo = nombreCodigo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
