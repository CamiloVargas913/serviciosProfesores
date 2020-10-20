/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.service;

import co.edu.ucundinamarca.dto.Autordto;
import co.edu.ucundinamarca.entity.Autor;
import co.edu.ucundinamarca.exception.ObjectNotFoundException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author david
 */
@Local
public interface IAutorService {

    public List<Autordto> listar(int estado);

    public Autor listarPorId(Integer id) throws ObjectNotFoundException;

    public void guardar(Autor autor);

    public void editar(Autor autor);

    public void eliminar(Integer id) throws ObjectNotFoundException;

    public Autordto listarPorIdA(Integer id, int estado) throws ObjectNotFoundException;
}
