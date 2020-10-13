/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.controller;

import co.edu.ucundinamarca.entity.Profesor;
import co.edu.ucundinamarca.exception.ObjectNotFoundException;
import co.edu.ucundinamarca.service.IProfesorServiceEntity;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.ws.rs.Consumes;
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
 * @author PROFESIONAL
 */
@Stateless
//@Api(value = "/profesores", description = "Api para hacer las operaciones de los profesores")
@Path("/profesores2")
public class ProfesoEntityController {

    @EJB
    public IProfesorServiceEntity service;

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
        this.service.insertar(profesor);
        return Response.status(Response.Status.CREATED).entity("Insercion correcta").build();
    }

    @Path("/listarId/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(produces = "application/json", value = "Insertar un profesor", consumes = "aplication/json",
            httpMethod = "GET")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 422, message = "Invalid data"),
        @ApiResponse(code = 405, message = "Method Not Allowed"),
        @ApiResponse(code = 500, message = "Internal Server Error")})
    public Response listarId(@PathParam("id") @Min(1) int id) {
        Profesor dataProfesor = this.service.listarID(id);
        return Response.status(Response.Status.OK).entity(dataProfesor).build();
    }

    @Path("/listar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(produces = "application/json", value = "Insertar un profesor", consumes = "aplication/json",
            httpMethod = "GET")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 422, message = "Invalid data"),
        @ApiResponse(code = 405, message = "Method Not Allowed"),
        @ApiResponse(code = 500, message = "Internal Server Error")})
    public Response listar() {
        List<Profesor> dataProfesor = this.service.listar();
        return Response.status(Response.Status.OK).entity(dataProfesor).build();
    }

    @Path("/editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(produces = "application/json", value = "Insertar un profesor", consumes = "aplication/json",
            httpMethod = "put")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 422, message = "Invalid data"),
        @ApiResponse(code = 405, message = "Method Not Allowed"),
        @ApiResponse(code = 500, message = "Internal Server Error")})
    public Response editar(@Valid Profesor profesor) {
        this.service.modificar(profesor);
        return Response.status(Response.Status.OK).entity("Modificado correctamente").build();
    }
    
    @Path("/eliminar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(produces = "application/json", value = "Insertar un profesor", consumes = "aplication/json",
            httpMethod = "put")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 422, message = "Invalid data"),
        @ApiResponse(code = 405, message = "Method Not Allowed"),
        @ApiResponse(code = 500, message = "Internal Server Error")})
    public Response eliminar(@Valid Profesor profesor) {
        this.service.eliminar(profesor);
        return Response.status(Response.Status.OK).entity("Eliminado correctamente").build();
    }
}
