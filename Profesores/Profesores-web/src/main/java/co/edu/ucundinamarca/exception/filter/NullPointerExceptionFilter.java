/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.exception.filter;

import co.edu.ucundinamarca.pojo.ErrorWrapper;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author david
 */
public class NullPointerExceptionFilter implements ExceptionMapper<NullPointerException> {
    @Override
    public Response toResponse(NullPointerException ex) {        
        ErrorWrapper error = new ErrorWrapper(ex.getMessage(), "500", "INTERNAL_SERVER_ERROR");
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
    }
}
