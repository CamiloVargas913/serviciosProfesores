/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.service.impl;

import co.edu.ucundinamarca.dto.Autordto;
import co.edu.ucundinamarca.entity.Autor;
import co.edu.ucundinamarca.entity.Libro;
import co.edu.ucundinamarca.exception.ObjectNotFoundException;
import co.edu.ucundinamarca.repo.IAutorRepo;
import co.edu.ucundinamarca.service.IAutorService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author david
 */
@Stateless
public class AutorServiceImpl implements IAutorService {

    @EJB
    private IAutorRepo repo;

    @Override
    public List<Autordto> listar(int estado) {
        List<Autordto> autor = new ArrayList<>();
        List<Autor> autor2 = repo.listar();
        for (Autor aur : autor2) {
            List<Libro> libro = new ArrayList<>();
            Autordto pojo = new Autordto();
            pojo.setId(aur.getId());
            pojo.setApellido(aur.getApellido());
            pojo.setFecha(aur.getFecha());
            pojo.setNombre(aur.getNombre());
            for (Libro libros : aur.getLibro()) {
                Libro libr = new Libro();
                libr.setId(libros.getId());
                libr.setAutor(libros.getAutor());
                libr.setEditorial(libros.getEditorial());
                libro.add(libr);
            }
            if (estado == 1) {
                pojo.setLibro(libro);
            }
            autor.add(pojo);
        }

        return autor;
    }

    @Override
    public Autor listarPorId(Integer id) throws ObjectNotFoundException {
        Autor autor = repo.listarPorId(id);
        if (autor == null) {
            throw new ObjectNotFoundException("Autor no existe.");
        } else {
            return autor;
        }
    }

    @Override
    public Autordto listarPorIdA(Integer id, int estado) throws ObjectNotFoundException {
        Autor autor = repo.listarPorId(id);
        Autordto autor2 = new Autordto();

        if (autor == null) {
            throw new ObjectNotFoundException("Autor no existe.");
        } else {
            autor2.setApellido(autor.getApellido());
            autor2.setNombre(autor.getNombre());
            autor2.setFecha(autor.getFecha());
            autor2.setId(autor.getId());
            if (estado == 1) {
                autor2.setLibro(autor.getLibro());
                return autor2;
            } else {
                return autor2;
            }
        }
    }

    @Override
    public void guardar(Autor autor) {
        if (autor.getLibro() != null) {
            for (Libro libro : autor.getLibro()) {
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
    public void eliminar(Integer id) throws ObjectNotFoundException {
        Autor autor = this.listarPorId(id);
        repo.eliminar(autor);
    }
}
