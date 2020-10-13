/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.service;

import co.edu.ucundinamarca.dto.Profesor;
import co.edu.ucundinamarca.dto.ProfesorBD;
import co.edu.ucundinamarca.exception.ObjectNotFoundException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PROFESIONAL
 */
@Local
public interface IProfesorService {
    public void insertarProfesor(ProfesorBD profesorInsertar) throws Exception;
    public void traerCedula(int cedula);
    public void traerUltimoID() throws SQLException;
    public void listaMateriasProfesor() throws SQLException;

    public void listarProfesor() throws ObjectNotFoundException;
    
    public void registroProfesor(Profesor profesor) throws ObjectNotFoundException ;

    public void llenarProfesor() throws ObjectNotFoundException ;

    public List<Profesor> leer() throws ObjectNotFoundException;

    public List<Profesor> retornarProfesores()throws ObjectNotFoundException;

    public Profesor retornarProfesorCedula(String cedula) throws ObjectNotFoundException;

    public void eliminarProfesor(int id) throws FileNotFoundException, ObjectNotFoundException ;

    public void editarProfesor(Profesor profesor) throws IOException, FileNotFoundException, ClassNotFoundException, ObjectNotFoundException ;

    public List<ProfesorBD> getListaProfesor()throws ObjectNotFoundException;
    
    public List<Profesor> retornarProfesorMateria(String materia) throws IOException, FileNotFoundException, ClassNotFoundException,ObjectNotFoundException;
}
