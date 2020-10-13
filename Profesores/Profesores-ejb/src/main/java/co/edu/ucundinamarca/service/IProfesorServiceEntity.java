/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.service;

import co.edu.ucundinamarca.entity.Profesor;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PROFESIONAL
 */
@Local
public interface IProfesorServiceEntity {
    public List<Profesor> listar();
    public void insertar(Profesor profesor);
    public Profesor listarID(int id);
    public void modificar(Profesor profesor);
    public void eliminar(Profesor profesor);
}
