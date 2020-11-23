/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.service;


import co.edu.ucundinamarca.dto.LibroDto;
import co.edu.ucundinamarca.dto.ListarPaginadoDto;
import co.edu.ucundinamarca.entity.Libro;
import co.edu.ucundinamarca.exception.ObjectNotFoundException;
import co.edu.ucundinamarca.exception.ParamRequiredException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author david
 */
@Local
public interface ILibroService {
    
    public ListarPaginadoDto listar(int page,int size);
        
    public LibroDto listarPorId(Integer id) throws ObjectNotFoundException;
    
    public void guardar(Libro libro)throws ObjectNotFoundException, ParamRequiredException;
    
    public void editar(Libro libro) throws ParamRequiredException, ObjectNotFoundException ;
   
    public void eliminar(Integer idLibro) throws  ObjectNotFoundException  ;
    
}
