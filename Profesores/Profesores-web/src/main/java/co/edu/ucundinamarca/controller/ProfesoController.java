/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.controller;

import co.edu.ucundinamarca.dto.Profesor;
import co.edu.ucundinamarca.dto.ProfesorBD;
import co.edu.ucundinamarca.exception.ObjectNotFoundException;
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
import co.edu.ucundinamarca.service.IProfesorService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.PathParam;

/**
 *
 * @author Camilo Vargas
 */
@Stateless
//@Api(value = "/profesores", description = "Api para hacer las operaciones de los profesores")
@Path("/profesores")
@Api(value = "/profesores")
public class ProfesoController {

    @EJB
    public IProfesorService service;

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
    public Response insertarProfesor(@Valid Profesor profesor) throws IOException, FileNotFoundException, ClassNotFoundException, ObjectNotFoundException {
        service.registroProfesor(profesor);
        return Response.status(Response.Status.CREATED).entity("Insercion correcta").build();
    }

    @Path("/insertarBD")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(produces = "application/json", value = "Insertar un al profesor con su correspondientes datos", consumes = "aplication/json",
            httpMethod = "POST")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 422, message = "Invalid data"),
        @ApiResponse(code = 405, message = "Method Not Allowed"), 
        @ApiResponse(code = 500, message = "Internal Server Error")})
    public Response insertarProfe(@Valid ProfesorBD profesor) throws Exception {
        service.insertarProfesor(profesor);
        return Response.status(Response.Status.CREATED).build();
    }

    @Path("/retornarBD")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(produces = "application/json", value = "Retorna la lista de todos los profesores", consumes = "aplication/json",
            httpMethod = "GET")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Internal Server Error")})
    public Response retornarProfesor() throws SQLException, ObjectNotFoundException {
        service.listarProfesor();
        return Response.status(Response.Status.OK).entity(service.getListaProfesor()).build();
    }

    /**
     * metodo que recibe la peticion http y modifica un profesor
     *
     * @param profesor
     * @return Response codigo http
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     * @throws java.io.FileNotFoundException
     */
    @Path("/editar")
    @DELETE
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
    public Response editarProfesor(@Valid Profesor profesor) throws EJBException, IOException, FileNotFoundException, ClassNotFoundException, ObjectNotFoundException {
        service.editarProfesor(profesor);
        return Response.status(Response.Status.OK).entity("Modificado correctamente").build();
    }

    /**
     * Metodo que recibe la peticion HTTP y elimina un profesor dependiendo el
     * ID
     *
     * @param id variable para saber el profesor a eliminar
     * @return Response codigo HTTP
     * @throws java.io.FileNotFoundException
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
    public Response eliminarProfesor(@PathParam("id") @Min(1) int id) throws FileNotFoundException, ObjectNotFoundException {
        service.eliminarProfesor(id);
        return Response.status(Response.Status.OK).entity("Eliminado Satisfactoriamente").build();
    }

    /**
     * Metodo que recibe la peticion HTTP y retorna todos los profesores
     * registrados
     *
     * @return Response codigo HTTP
     * @throws javax.ejb.ObjectNotFoundException
     */
    @Path("/retornar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(produces = "application/json", value = "Lista Profesores", httpMethod = "GET")
    @ApiResponses(value = {
        @ApiResponse(code = 405, message = "Method Not Allowed"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")})
    public Response listaProfesor() throws EJBException, ObjectNotFoundException {
        List<Profesor> dataProfesor = service.retornarProfesores();
        return Response.status(Response.Status.OK).entity(dataProfesor).build();
    }

    /**
     * Metodo que recibe la peticion HTTP y retorna un profesor dependiendo la
     * cedula
     *
     * @param cedula variable para saber el profesor que se debe retornar
     * @return Response codigo HTTP
     * @throws javax.ejb.ObjectNotFoundException
     */
    @Path("/retornarCc/{cedula}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(produces = "application/json", value = "Lista Profesor por cedula", httpMethod = "GET")
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 405, message = "Method Not Allowed"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")})
    public Response listaProfesorCc(@PathParam("cedula") @Size(min = 7, max = 10, message = "minimo 7 max 10") String cedula) throws ObjectNotFoundException {
        Profesor dataProfesor = service.retornarProfesorCedula(cedula);
        return Response.status(Response.Status.OK).entity(dataProfesor).build();

    }

    /**
     * Metodo que recibe la peticion HTTP y retorna un profesor dependiendo la
     * materia que dicta
     *
     * @param materia Variable para saber que por que materia filtrar
     * @return Response codigo HTTP
     */
    @Path("/retornarMateria/{materia}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(produces = "application/json", value = "Lista Profesor por materia", httpMethod = "GET")
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 405, message = "Method Not Allowed"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")})
    public Response listaProfesorMateria(@PathParam("materia") @Size(min = 5, max = 20, message = "El nombre de la materia deber tener minimo 5 caracteres y maximo 20") String materia) throws IOException, FileNotFoundException, ClassNotFoundException, ObjectNotFoundException {
        List<Profesor> dataProfesor = service.retornarProfesorMateria(materia);
        return Response.status(Response.Status.OK).entity(dataProfesor).build();
    }

}
