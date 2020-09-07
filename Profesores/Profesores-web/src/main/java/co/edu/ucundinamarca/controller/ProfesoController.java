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
import java.util.List;
import javax.validation.Valid;
import javax.ws.rs.PathParam;

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
        try {
            ProfesorService profesoragregar = new ProfesorService();
            profesoragregar.registroProfesor(profesor);
            return Response.status(Response.Status.CREATED).entity("Insercion correcta").build();
        } catch (Exception ex) {
            System.out.println(ex);
            return Response.status(Response.Status.BAD_REQUEST).entity("Error al insertar").build();
        }
    }

    @Path("/editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editarProfesor() {
        return Response.status(Response.Status.OK).build();
    }

    @Path("/eliminar/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarProfesor(@PathParam("id") int id) {
        ProfesorService profesor = new ProfesorService();
        String data = profesor.eliminarProfesor(id);
        if (data.equals("El id no existe")) {
            return Response.status(Response.Status.NO_CONTENT).entity("No existe el id").build();
        } else {
            return Response.status(Response.Status.OK).entity(data).build();
        }

    }

    @Path("/retornarProfesor")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaProfesor() {
        ProfesorService profesor = new ProfesorService();
        List<Profesor> dataProfesor = profesor.retornarProfesores();
        if (dataProfesor != null) {
            return Response.status(Response.Status.OK).entity(dataProfesor).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).entity("No hay registros").build();
        }
    }

    @Path("/retornarProfesorCc/{cedula}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaProfesorCc(@PathParam("cedula") String cedula) {
        ProfesorService profesor = new ProfesorService();
        Profesor dataProfesor = profesor.retornarProfesorCedula(cedula);
        if (dataProfesor != null) {
            return Response.status(Response.Status.OK).entity(dataProfesor).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).entity("No hay registros").build();
        }
    }

    @Path("/retornarProfesorMateria/{materia}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaProfesorMateria(@PathParam("materia") String materia) {
        ProfesorService profesor = new ProfesorService();
        List<Profesor> dataProfesor = profesor.retornarProfesorMateria(materia);
        if (dataProfesor != null) {
            return Response.status(Response.Status.OK).entity(dataProfesor).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).entity("No hay registros").build();
        }
    }

}
