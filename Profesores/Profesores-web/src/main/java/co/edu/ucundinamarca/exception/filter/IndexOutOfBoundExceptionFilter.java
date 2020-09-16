/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.exception.filter;

import co.edu.ucundinamarca.pojo.ErrorWrapper;
import java.io.FileNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author david
 */
public class IndexOutOfBoundExceptionFilter implements ExceptionMapper<IndexOutOfBoundsException>{
    @Override
    public Response toResponse(IndexOutOfBoundsException ex) {        
        ErrorWrapper error = new ErrorWrapper(ex.getMessage(), "500", "INTERNAL_SERVER_ERROR");
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
    }
}
