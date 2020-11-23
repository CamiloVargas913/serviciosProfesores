/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.repo;

import co.edu.ucundinamarca.dto.AutorVistaDto;
import co.edu.ucundinamarca.dto.Autordto;
import co.edu.ucundinamarca.entity.Autor;
import co.edu.ucundinamarca.entity.ViewAutor;
import co.edu.ucundinamarca.exception.ObjectNotFoundException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author david
 */
@Local
public interface IAutorRepo {

    public List<Autor> listar(String consulta,int page,int size);

    public List<ViewAutor> listarVista();

    public Autor listarPorId(Integer id);

    public Integer validarExisteAutor(Integer id);

    public void guardar(Autor autor);

    public void editar(Autor autor);

    public void eliminar(Autor autor);

    public void cambiarEstado(Integer id, boolean estado);

    public ViewAutor listarVistaId(Integer id);
    
    public Integer totalRegistros();

}
