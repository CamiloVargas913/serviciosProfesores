/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.service.impl;

import co.edu.ucundinamarca.dto.LibroDto;
import co.edu.ucundinamarca.dto.ListarPaginadoDto;
import co.edu.ucundinamarca.exception.ObjectNotFoundException;
import co.edu.ucundinamarca.repo.ILibroRepo;
import co.edu.ucundinamarca.service.ILibroService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.modelmapper.ModelMapper;
import co.edu.ucundinamarca.entity.Libro;
import co.edu.ucundinamarca.exception.ParamRequiredException;

/**
 *
 * @author david
 */
@Stateless
public class LibroServiceImpl implements ILibroService {

    @EJB
    private ILibroRepo repo;

    @Override
    public ListarPaginadoDto listar(int page, int size) {
        page = (page) * size;
        ListarPaginadoDto paginado = new ListarPaginadoDto() {
        };
        List<Libro> listaLibro = repo.listar("Libro.listarTodo", page, size);
        List<LibroDto> libroDto = new ArrayList<>();

        for (Libro libro : listaLibro) {
            ModelMapper modelMapper = new ModelMapper();
            LibroDto dto = modelMapper.map(libro, LibroDto.class);
            libroDto.add(dto);
        }

        for (LibroDto dto : libroDto) {
            //dto.setAutor(null);
            dto.getAutor().getLibro().clear();
        }

        paginado.setContent(listaLibro);
        paginado.setTotalRegistros(repo.totalRegistros());
        return paginado;
    }

    @Override
    public LibroDto listarPorId(Integer id) throws ObjectNotFoundException {
        Libro libro = repo.listarPorId(id);

        if (libro == null) {
            throw new ObjectNotFoundException("Libro no existe.");
        }

        ModelMapper modelMapper = new ModelMapper();
        LibroDto dto = modelMapper.map(libro, LibroDto.class);

        dto.setAutor(null);
        //dto.getAutor().getLibro().clear();

        return dto;
    }

    @Override
    public void guardar(Libro libro) throws ParamRequiredException {
        System.out.println(libro.getAutor().getId());
        if (libro.getAutor() == null || libro.getAutor().getId() == null) {
            throw new ParamRequiredException("IdAutor es requerido para guardar");
        } else {
            //libro.getAutor().getLibro().add(libro);
            repo.guardar(libro);
        }
    }

    @Override
    public void editar(Libro libro) throws ParamRequiredException, ObjectNotFoundException {
        if (libro.getId() == null) {
            throw new ParamRequiredException("Id es requerido para edición");
        }

        Libro libroAux = repo.listarPorId(libro.getId());

        if (libroAux == null) {
            throw new ObjectNotFoundException("Libro no existe.");
        }

        libroAux.setEditorial(libro.getEditorial());
        libroAux.setNombre(libro.getNombre());

        //libroAux.setAutor(libro.getAutor());  No funciona
        repo.editar(libroAux);
    }

    @Override
    public void eliminar(Integer idLibro) throws ObjectNotFoundException {
        Libro libroAux = repo.listarPorId(idLibro);

        if (libroAux == null) {
            throw new ObjectNotFoundException("Libro no existe.");
        }

        repo.eliminar(libroAux);
    }
}
