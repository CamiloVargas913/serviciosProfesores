/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.dto;

/**
 *
 * @author PROFESIONAL
 */
public class AutorLectorDto {

    private Autordto autor;

    private LectorDto lector;

    private String infoAdicional;

    public Autordto getAutor() {
        return autor;
    }

    public void setAutor(Autordto autor) {
        this.autor = autor;
    }

    public LectorDto getLector() {
        return lector;
    }

    public void setLector(LectorDto lector) {
        this.lector = lector;
    }

    public String getInfoAdicional() {
        return infoAdicional;
    }

    public void setInfoAdicional(String infoAdicional) {
        this.infoAdicional = infoAdicional;
    }
}
