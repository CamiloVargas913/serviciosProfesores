/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.repo;

import co.edu.ucundinamarca.entity.Profesor;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PROFESIONAL
 */
@Local
public interface IProfesorRepo {
    public List<Profesor> listar();
    public void insertar(Profesor profesor);
    public Profesor listarID(Integer id);
    public void modificar(Profesor profesor);
    public void eliminar(Profesor profesor);
    public Integer validarCedula(String cedula, Integer id);
    
    public Integer validarCorreo(String correo, Integer id);
    
    public Integer validarCedulaInsert(String cedula);
    
    public Integer validarCorreoInsert(String correo);
}
