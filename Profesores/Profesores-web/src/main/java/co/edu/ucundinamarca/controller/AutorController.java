/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.controller;

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
import co.edu.ucundinamarca.service.IAutorService;
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
@Path("/autores")
public class AutorController {

    @EJB
    private IAutorService service;

    @Path("/listar/{estado}/page={page}/size={size}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listr(@PathParam("estado") boolean estado, @PathParam("page") int page, @PathParam("size") int size) {
        ListarPaginadoDto listarAutor = service.listar(estado, page, size);
        return Response.status(Response.Status.OK).entity(listarAutor).build();
    }

    @Path("/retornarPorId/{id}/{estado}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retornarPorId(@PathParam("id") Integer id, @PathParam("estado") boolean estado) throws ObjectNotFoundException {
        Autordto autor = service.listarPorIdA(id, estado);
        return Response.status(Response.Status.OK).entity(autor).build();
    }

    @Path("/guardar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardar(@Valid Autor autor) {
        service.guardar(autor);
        return Response.status(Response.Status.CREATED).build();
    }

    @Path("/eliminar/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("id") Integer id) throws ObjectNotFoundException {
        service.eliminar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Path("/eliminar2/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarOpcion2(@PathParam("id") Integer id) throws Exception {
        service.eliminarSinLibros(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Path("/cambiarEstado/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response cambiaEstado(@PathParam("id") Integer id) throws ObjectNotFoundException {
        service.cambiarEstado(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Path("/editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response cambiaEstado(Autor autor) throws ObjectNotFoundException, ParamRequiredException {
        service.editar(autor);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Path("/listarVista")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarVista() {
        List<ViewAutor> listarAutor = service.listarVista();
        return Response.status(Response.Status.OK).entity(listarAutor).build();
    }

    @Path("/retornarPorIdV/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retornarPorId(@PathParam("id") Integer id) throws ObjectNotFoundException {
        ViewAutor autor = service.listarVistaId(id);
        return Response.status(Response.Status.OK).entity(autor).build();
    }

    @Path("/asociarLector")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response asoicarLector(AutorLector autorLector) throws ObjectNotFoundException, ParamRequiredException {
        service.asociarAutorLector(autorLector);
        return Response.status(Response.Status.CREATED).build();
    }

    @Path("/desasociarLector/idAutor={idAutor}/idLector={idLector}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response desasociarLector(@PathParam("idAutor") Integer idAutor, @PathParam("idLector") Integer idLector) throws ObjectNotFoundException {
        service.desasociarAutorLector(idAutor, idLector);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Path("/listarLector/{idAutor}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarLector(@PathParam("idAutor") Integer id) {
        List<AutorLectorDto> lista = service.listarAutorLector(id);
        return Response.status(Response.Status.OK).entity(lista).build();
    }

    @Path("/listarLector/page={page}/size={size}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listr(@PathParam("page") int page, @PathParam("size") int size) {
        List<Lector> listarAutor = service.listarLector();
        return Response.status(Response.Status.OK).entity(listarAutor).build();
    }
    
    @Path("/listarLectorId/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarLectorId(@PathParam("id") Integer id) throws ObjectNotFoundException  {
        Lector autor = service.listarLectorId(id);
        return Response.status(Response.Status.OK).entity(autor).build();
    }
    
    @Path("/editarLector")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response editarLector(Lector lector) throws ObjectNotFoundException, ParamRequiredException {
        service.editarLector(lector);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
        @Path("/guardarLector")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardarLector(@Valid Lector lector) {
        service.guardarLector(lector);
        return Response.status(Response.Status.CREATED).build();
    }

}
