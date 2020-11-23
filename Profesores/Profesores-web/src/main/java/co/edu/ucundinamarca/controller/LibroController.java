/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.controller;


import co.edu.ucundinamarca.dto.LibroDto;
import co.edu.ucundinamarca.dto.ListarPaginadoDto;
import co.edu.ucundinamarca.entity.Autor;
import co.edu.ucundinamarca.entity.Libro;
import co.edu.ucundinamarca.exception.ObjectNotFoundException;
import co.edu.ucundinamarca.exception.ParamRequiredException;
import co.edu.ucundinamarca.service.ILibroService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author david
 */
@Stateless
@Path("/libros")
public class LibroController {

    @EJB
    private ILibroService service;
    
    @Path("/listar/page={page}/size={size}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar(@PathParam("page") int page,@PathParam("size") int size)  {
        ListarPaginadoDto listarLibro = service.listar(page,size);
        return Response.status(Response.Status.OK).entity(listarLibro).build();       
    }        

    @Path("/retornarPorId/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retornarPorId(@PathParam("id" ) Integer id) throws ObjectNotFoundException {
        LibroDto libro = service.listarPorId(id);
        return Response.status(Response.Status.OK).entity(libro).build();       
    }
    
    @Path("/guardar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardar(@Valid Libro libro) throws ParamRequiredException, ObjectNotFoundException {
        service.guardar(libro);
        return Response.status(Response.Status.CREATED).build();
    } 
     
    @Path("/editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response cambiaEstado(Libro libro) throws ObjectNotFoundException, ParamRequiredException {
        service.editar(libro);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @Path("/eliminar/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("id") Integer id) throws ObjectNotFoundException {
        service.eliminar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
