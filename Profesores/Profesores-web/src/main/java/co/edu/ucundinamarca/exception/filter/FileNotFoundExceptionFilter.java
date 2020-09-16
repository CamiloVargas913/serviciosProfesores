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
import javax.ws.rs.ext.Provider;

/**
 *
 * @author david
 */
@Provider
public class FileNotFoundExceptionFilter implements ExceptionMapper<FileNotFoundException>{
    @Override
    public Response toResponse(FileNotFoundException ex) {        
        ErrorWrapper error = new ErrorWrapper(ex.getMessage(), "404", "NOT_FOUND");
        return Response.status(Response.Status.NOT_FOUND).entity(error).build();
    }
}
