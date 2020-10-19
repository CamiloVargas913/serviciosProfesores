/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.repo;

import co.edu.ucundinamarca.entity.Autor;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author david
 */
@Local
public interface IAutorRepo {
    public List<Autor> listar();
    
    public Autor listarPorId(Integer id);
    
    public void guardar(Autor autor);
    
    public void editar(Autor autor);
   
    public void eliminar(Autor autor);
}
