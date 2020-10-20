/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.controller;

import co.edu.ucundinamarca.dto.Autordto;
import co.edu.ucundinamarca.entity.Autor;
import co.edu.ucundinamarca.exception.ObjectNotFoundException;
import co.edu.ucundinamarca.service.IAutorService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

    @Path("/listar/{estado}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listr(@PathParam("estado") Integer estado) {
        List<Autordto> listarAutor = service.listar(estado);
        return Response.status(Response.Status.OK).entity(listarAutor).build();
    }

    @Path("/retornarPorId/{id}/{estado}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retornarPorId(@PathParam("id") Integer id, @PathParam("estado") Integer estado) throws ObjectNotFoundException {
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
}
