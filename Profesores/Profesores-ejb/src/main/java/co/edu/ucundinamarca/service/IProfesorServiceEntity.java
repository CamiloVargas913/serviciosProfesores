/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.service;

import co.edu.ucundinamarca.entity.Profesor;
import co.edu.ucundinamarca.exception.ObjectNotFoundException;
import co.edu.ucundinamarca.exception.ParamRequiredException;
import co.edu.ucundinamarca.exception.ParamUsedException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PROFESIONAL
 */
@Local
public interface IProfesorServiceEntity {
    public List<Profesor> listar();
    public void insertar(Profesor profesor) throws ParamUsedException;
    public Profesor listarID(Integer id) throws ObjectNotFoundException;
    public void modificar(Profesor profesor) throws ParamRequiredException, ObjectNotFoundException, ParamUsedException;
    public void eliminar(Integer id) throws ObjectNotFoundException;
}
