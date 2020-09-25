/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.service;

import co.edu.ucundinamarca.dto.Profesor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.ObjectNotFoundException;

/**
 *
 * @author PROFESIONAL
 */
@Local
public interface IProfesorService {

    /**
     * metodo que recibe un objeto profesor y lo agrega a la lista de profesores
     *
     * @param profesor
     */
    public void registroProfesor(Profesor profesor);

    public void llenarProfesor();

    public List<Profesor> leer();

    public List<Profesor> retornarProfesores();

    public Profesor retornarProfesorCedula(String cedula) throws ObjectNotFoundException;

    public void eliminarProfesor(int id) throws FileNotFoundException;

    public void editarProfesor(Profesor profesor) throws IOException, FileNotFoundException, ClassNotFoundException;

    public List<Profesor> retornarProfesorMateria(String materia) throws IOException, FileNotFoundException, ClassNotFoundException;
}
