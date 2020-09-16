/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.exception.filter;

import co.edu.ucundinamarca.pojo.ErrorWrapper;
import javax.el.MethodNotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author david
 */
public class IllegalArgumentExceptionFilter implements ExceptionMapper<IllegalArgumentException> {
    
    @Override
    public Response toResponse(IllegalArgumentException ex) {        
        ErrorWrapper error = new ErrorWrapper(ex.getMessage(), "400", "BAD_REQUEST");
        return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
    }
}
