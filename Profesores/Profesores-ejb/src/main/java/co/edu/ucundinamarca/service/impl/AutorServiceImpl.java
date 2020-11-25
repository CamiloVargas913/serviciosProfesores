/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.service.impl;

import co.edu.ucundinamarca.dto.AutorLectorDto;
import co.edu.ucundinamarca.dto.AutorVistaDto;
import co.edu.ucundinamarca.dto.Autordto;
import co.edu.ucundinamarca.dto.ListarPaginadoDto;
import co.edu.ucundinamarca.entity.Autor;
import co.edu.ucundinamarca.entity.AutorLector;
import co.edu.ucundinamarca.entity.Lector;
import co.edu.ucundinamarca.entity.Libro;
import co.edu.ucundinamarca.entity.ViewAutor;
import co.edu.ucundinamarca.exception.ObjectNotFoundException;
import co.edu.ucundinamarca.exception.ParamRequiredException;
import co.edu.ucundinamarca.repo.IAutorLectorRepo;
import co.edu.ucundinamarca.repo.IAutorRepo;
import co.edu.ucundinamarca.service.IAutorService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.modelmapper.ModelMapper;

/**
 *
 * @author david
 */
@Stateless
public class AutorServiceImpl implements IAutorService {

    @EJB
    private IAutorRepo repo;

    @EJB
    private IAutorLectorRepo repoAutorLector;

    @Override
    public ListarPaginadoDto listar(boolean estado, int page, int size) {
        page = (page) * size;
        ListarPaginadoDto paginado = new ListarPaginadoDto() {
        };
        List<Autor> listaAutor = repo.listar("Autor.listarTodo", page, size);
        if (!estado) {
            for (Autor lista : listaAutor) {
                lista.setLibro(null);
            }
        }
        paginado.setContent(listaAutor);
        paginado.setTotalRegistros(repo.totalRegistros());
        return paginado;
    }

    @Override
    public Autor listarPorId(Integer id) throws ObjectNotFoundException {

        if (repo.validarExisteAutor(id) == 0) {
            throw new ObjectNotFoundException("Autor no existe.");
        } else {
            Autor autor = repo.listarPorId(id);
            return autor;
        }
    }

    @Override
    public Autordto listarPorIdA(Integer id, boolean estado) throws ObjectNotFoundException {
        Autor autor = repo.listarPorId(id);
        Autordto autor2 = new Autordto();

        if (autor == null) {
            throw new ObjectNotFoundException("Autor no existe.");
        } else {
            autor2.setApellido(autor.getApellido());
            autor2.setNombre(autor.getNombre());
            autor2.setFecha(autor.getFecha());
            autor2.setId(autor.getId());
            if (estado) {
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
        if (autor.getDireccion() != null) {
            autor.getDireccion().setAutor(autor);
        }
        repo.guardar(autor);
    }

    @Override
    public void editar(Autor autor) throws ObjectNotFoundException, ParamRequiredException {
        if (autor.getId() == null) {
            throw new ParamRequiredException("Id es requerido para edición");
        } else {
            Autor autorAux = repo.listarPorId(autor.getId());

            if (autor == null) {
                throw new ObjectNotFoundException("Autor no existe.");
            }

            autorAux.setApellido(autor.getApellido());
            autorAux.setNombre(autor.getNombre());
            autorAux.setFecha(autor.getFecha());

            if (autor.getDireccion() != null) {
                autorAux.getDireccion().setBarrio(autor.getDireccion().getBarrio());
                autorAux.getDireccion().setDireccion(autor.getDireccion().getDireccion());
            }
        }

    }

    @Override
    public void eliminar(Integer id) throws ObjectNotFoundException {
        Autor autor = this.listarPorId(id);
        repo.eliminar(autor);
    }

    @Override
    public void cambiarEstado(Integer id) throws ObjectNotFoundException {
        Autor autor = this.listarPorId(id);
        if (autor.isEstado()) {
            repo.cambiarEstado(id, false);
        } else {
            repo.cambiarEstado(id, true);
        }

    }

    @Override
    public void eliminarSinLibros(Integer id) throws Exception {
        Autor autor = this.listarPorId(id);
        if (autor.getLibro().isEmpty()) {
            repo.eliminar(autor);
        } else {
            throw new Exception("Debe eliminar Primero los libros asociados");
        }

    }

    @Override
    public List<ViewAutor> listarVista() {
        List<ViewAutor> ViewAutor = repo.listarVista();
        return ViewAutor;
    }

    @Override
    public ViewAutor listarVistaId(Integer id) throws ObjectNotFoundException {

        if (repo.validarExisteAutor(id) == 0) {
            throw new ObjectNotFoundException("Autor no existe.");
        } else {
            ViewAutor vistaAutor = repo.listarVistaId(id);
            return vistaAutor;
        }

    }

    @Override
    public void asociarAutorLector(AutorLector autorLector) throws ObjectNotFoundException, ParamRequiredException {
        if (autorLector == null || autorLector.getAutor().getId() == null || autorLector.getLector().getId() == null) {
            throw new ParamRequiredException("Id del lector y autoe es requerido para asignacion");
        } else {
            repoAutorLector.guardar(autorLector);
        }

    }

    @Override
    public void desasociarAutorLector(int idAutor, int idLector) throws ObjectNotFoundException {
        if (repoAutorLector.desasociarLector(idAutor, idLector) == 0) {
            throw new ObjectNotFoundException("La Asociacion no exixte.");
        }

    }

    @Override
    public List<AutorLectorDto> listarAutorLector(Integer idAutor) {
        List<AutorLector> listaAutorLector = repoAutorLector.listarAutorLector(idAutor);
        List<AutorLectorDto> lista = new ArrayList<>();
        for (AutorLector lis : listaAutorLector) {
            ModelMapper modelMapper = new ModelMapper();
            AutorLectorDto autorLectorDto = modelMapper.map(lis, AutorLectorDto.class);
            //autorLectorDto.getAutor().setLibro(null);
            autorLectorDto.setAutor(null);
            lista.add(autorLectorDto);
        }
        return lista;
    }

    @Override
    public List<Lector> listarLector() {
        List<Lector> listaLector = repo.listarLector("Autor.listarLectorTodo");
        return listaLector;
    }

    @Override
    public Lector listarLectorId(int id) {
        return repo.listarLectorId(id);
    }

    @Override
    public void guardarLector(Lector lector) {
        repo.guardarLector(lector);
    }

    @Override
    public void editarLector(Lector lector) throws ObjectNotFoundException, ParamRequiredException {
         repo.editarLector(lector);
    }

    @Override
    public void eliminarLector(Integer id) throws ObjectNotFoundException{
        Lector lector = repo.listarLectorId(id);

        System.out.println(lector.getApellido());
        
        if (lector == null) {
            throw new ObjectNotFoundException("Lector no existe.");
        }

        repo.eliminarLector(lector);
    }

}
