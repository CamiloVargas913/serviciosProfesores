/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.controller;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import co.edu.ucundinamarca.logica.ProfesorService;
import co.edu.ucundinamarca.pojo.Profesor;
import javax.validation.Valid;

/**
 *
 * @author PROFESIONAL
 */
@Stateless
@Path("/profesores")
public class ProfesoController {

    @Path("/insertar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertarProfesor(@Valid Profesor profesor) {    
        System.out.println("info");
        /*try {
            ProfesorService profesoragregar = new ProfesorService();
            profesoragregar.registroProfesor(profesor);
            return Response.status(Response.Status.CREATED).entity("Insercion correcta").build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error al insertar").build();
        }*/
        return Response.status(Response.Status.CREATED).build();
    }

    @Path("/editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editarProfesor() {
        return Response.status(Response.Status.OK).build();
    }

    @Path("/eliminar")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response elimiarProfesor() {
        return Response.status(Response.Status.OK).build();
    }

    @Path("/retornarProfesor")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaProfesor() {
        return Response.status(Response.Status.OK).build();
    }

    @Path("/retornarProfesorCc")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaProfesorCc() {
        return Response.status(Response.Status.OK).build();
    }

    @Path("/retornarProfesorMateria")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaProfesorMateria() {
        return Response.status(Response.Status.OK).build();
    }

}
