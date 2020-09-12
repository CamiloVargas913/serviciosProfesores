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
import io.swagger.annotations.*;
import java.util.List;
import javax.validation.Valid;
import javax.ws.rs.PathParam;

/**
 *
 * @author Camilo Vargas
 */
@Stateless
@Api(value = "/profesores", description = "Api para hacer las operaciones de los profesores")
@Path("/profesores")
public class ProfesoController {

    /**
     * metodo que recibe la peticion http e insterta un profesor
     *
     * @param profesor Variable que trae los datos del profesor
     * @return Response codigo http
     */
    @Path("/insertar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(produces = "application/json", value = "Insertar un profesor", consumes = "aplication/json",
            httpMethod = "POST")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 422, message = "Invalid data"),
        @ApiResponse(code = 405, message = "Method Not Allowed"),
        @ApiResponse(code = 500, message = "Internal Server Error")})
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

    /**
     * metodo que recibe la peticion http y modifica un profesor
     *
     * @return Response codigo http
     */
    @Path("/editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(produces = "application/json", value = "Edita un profesor", consumes = "aplication/json",
            httpMethod = "PUT")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 422, message = "Invalid data"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 405, message = "Method Not Allowed"),
        @ApiResponse(code = 500, message = "Internal Server Error")})
    public Response editarProfesor(@Valid Profesor profesor) {
        ProfesorService profesoragregar = new ProfesorService();
        if (profesoragregar.editarProfesor(profesor)) {
            return Response.status(Response.Status.OK).entity("Modificado correctamente").build();
        } else {
            return Response.status(Response.Status.NOT_MODIFIED).entity("Error al modificar").build();
        }

    }

    /**
     * Metodo que recibe la peticion HTTP y elimina un profesor dependiendo el
     * ID
     *
     * @param id variable para saber el profesor a eliminar
     * @return Response codigo HTTP
     */
    
    @ApiOperation(produces = "application/json", value = "Elimina un profesor", consumes = "aplication/json",
            httpMethod = "DELETE")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 405, message = "Method Not Allowed"),
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @Path("/eliminar/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarProfesor(@PathParam("id") int id) {
        ProfesorService profesor = new ProfesorService();
        int data = profesor.eliminarProfesor(id);
        if (data == 2) {
            return Response.status(Response.Status.NOT_FOUND).entity("No existe el id").build();
        } else if (data == 1) {
            return Response.status(Response.Status.OK).entity("Eliminado Satisfactoriamente").build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).entity("No existe fichero").build();
        }
    }

    /**
     * Metodo que recibe la peticion HTTP y retorna todos los profesores
     * registrados
     *
     * @return Response codigo HTTP
     */
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

    /**
     * Metodo que recibe la peticion HTTP y retorna un profesor dependiendo la
     * cedula
     *
     * @param cedula variable para saber el profesor que se debe retornar
     * @return Response codigo HTTP
     */
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

    /**
     * Metodo que recibe la peticion HTTP y retorna un profesor dependiendo la
     * materia que dicta
     *
     * @param materia Variable para saber que por que materia filtrar
     * @return Response codigo HTTP
     */
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
