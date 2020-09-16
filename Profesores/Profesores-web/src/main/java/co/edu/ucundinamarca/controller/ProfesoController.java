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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.validation.Valid;
import javax.ws.rs.PathParam;

/**
 *
 * @author Camilo Vargas
 */
@Stateless
//@Api(value = "/profesores", description = "Api para hacer las operaciones de los profesores")
@Path("/profesores")
@Api(tags = {"Profesores"})
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
    public Response insertarProfesor(@Valid Profesor profesor) throws IOException, FileNotFoundException, ClassNotFoundException {

        ProfesorService profesoragregar = new ProfesorService();
        profesoragregar.registroProfesor(profesor);
        return Response.status(Response.Status.CREATED).entity("Insercion correcta").build();
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
    public Response editarProfesor(@Valid Profesor profesor) throws IOException, FileNotFoundException, ClassNotFoundException {
        ProfesorService profesoragregar = new ProfesorService();
        profesoragregar.editarProfesor(profesor);
        return Response.status(Response.Status.OK).entity("Modificado correctamente").build();
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
    public Response eliminarProfesor(@PathParam("id") int id) throws ClassNotFoundException, IOException {
        ProfesorService profesor = new ProfesorService();
        profesor.eliminarProfesor(id);
        return Response.status(Response.Status.OK).entity("Eliminado Satisfactoriamente").build();
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
    @ApiOperation(produces = "application/json", value = "Lista Profesores",httpMethod = "GET")
    @ApiResponses(value = {
        @ApiResponse(code = 405, message = "Method Not Allowed"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")})
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
    @ApiOperation(produces = "application/json", value = "Lista Profesor por cedula",httpMethod = "GET")
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 405, message = "Method Not Allowed"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")})
    public Response listaProfesorCc(@PathParam("cedula") String cedula) {
        ProfesorService profesor = new ProfesorService();
        Profesor dataProfesor = profesor.retornarProfesorCedula(cedula);
        return Response.status(Response.Status.OK).entity(dataProfesor).build();

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
    @ApiOperation(produces = "application/json", value = "Lista Profesor por materia",httpMethod = "GET")
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 405, message = "Method Not Allowed"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")})
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
