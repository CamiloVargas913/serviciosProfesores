/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.service;

import co.edu.ucundinamarca.dto.AutorLectorDto;
import co.edu.ucundinamarca.dto.AutorVistaDto;
import co.edu.ucundinamarca.dto.Autordto;
import co.edu.ucundinamarca.dto.ListarPaginadoDto;
import co.edu.ucundinamarca.entity.Autor;
import co.edu.ucundinamarca.entity.AutorLector;
import co.edu.ucundinamarca.entity.Lector;
import co.edu.ucundinamarca.entity.ViewAutor;
import co.edu.ucundinamarca.exception.ObjectNotFoundException;
import co.edu.ucundinamarca.exception.ParamRequiredException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author david
 */
@Local
public interface IAutorService {

    public ListarPaginadoDto listar(boolean estado, int page, int size);

    public Autor listarPorId(Integer id) throws ObjectNotFoundException;

    public void guardar(Autor autor);

    public void guardarLector(Lector lector);

    public void editar(Autor autor) throws ObjectNotFoundException, ParamRequiredException;

    public void editarLector(Lector lector) throws ObjectNotFoundException, ParamRequiredException;

    public void eliminar(Integer id) throws ObjectNotFoundException;

    public Autordto listarPorIdA(Integer id, boolean estado) throws ObjectNotFoundException;

    public void eliminarSinLibros(Integer id) throws Exception;

    public void cambiarEstado(Integer id) throws ObjectNotFoundException;

    public List<ViewAutor> listarVista();

    public ViewAutor listarVistaId(Integer id) throws ObjectNotFoundException;

    public void asociarAutorLector(AutorLector autorLector) throws ObjectNotFoundException, ParamRequiredException;

    public List<AutorLectorDto> listarAutorLector(Integer idAutor);

    public void desasociarAutorLector(int idAutor, int idLector) throws ObjectNotFoundException;

    public List<Lector> listarLector();

    public Lector listarLectorId(int id);
}
