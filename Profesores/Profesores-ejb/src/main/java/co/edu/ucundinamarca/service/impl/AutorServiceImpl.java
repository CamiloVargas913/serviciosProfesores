/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.service.impl;

import co.edu.ucundinamarca.entity.Autor;
import co.edu.ucundinamarca.entity.Libro;
import co.edu.ucundinamarca.exception.ObjectNotFoundException;
import co.edu.ucundinamarca.repo.IAutorRepo;
import co.edu.ucundinamarca.service.IAutorService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author david
 */
@Stateless
public class AutorServiceImpl implements IAutorService{
    @EJB
    private IAutorRepo repo;
    
    @Override
    public List<Autor> listar() {
        return repo.listar();        
    }

    @Override
    public Autor listarPorId(Integer id) throws  ObjectNotFoundException{
        Autor autor = repo.listarPorId(id);
        if(autor == null)
            throw new ObjectNotFoundException("Autor no existe.");
        else
            return autor;
    }

    @Override
    public void guardar(Autor autor) {
        if(autor.getLibro() != null) {
            for(Libro libro: autor.getLibro()) {
                libro.setAutor(autor);
            }
        }
        repo.guardar(autor);
    }

    @Override
    public void editar(Autor autor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Integer id) throws  ObjectNotFoundException {
        Autor autor = this.listarPorId(id);
        repo.eliminar(autor);
    }
}
