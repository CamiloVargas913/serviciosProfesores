/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.exception.filter;

import co.edu.ucundinamarca.pojo.ErrorWrapper;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author david
 */
@Provider
public class WebApplicationExceptionFilter implements ExceptionMapper<WebApplicationException> {

    @Override
    public Response toResponse(WebApplicationException ex) {
        if (ex.getMessage().contains("404")) {
            ErrorWrapper error = new ErrorWrapper(ex.getMessage(), "404", "NOT_FOUND");
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        } else if (ex.getMessage().contains("405")) {
            ErrorWrapper error = new ErrorWrapper(ex.getMessage(), "405", "METHOD_NOT_ALLOWED");
            return Response.status(Response.Status.METHOD_NOT_ALLOWED).entity(error).build();
        }
        ErrorWrapper error = new ErrorWrapper(ex.getMessage(), "500", "INTERNAL_SERVER_ERROR");
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
    }
}
