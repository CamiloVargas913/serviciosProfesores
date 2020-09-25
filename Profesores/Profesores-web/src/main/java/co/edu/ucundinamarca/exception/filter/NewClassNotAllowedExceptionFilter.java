/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.exception.filter;
import co.edu.ucundinamarca.pojo.ErrorWrapper;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author PROFESIONAL
 */
@Provider
public class NewClassNotAllowedExceptionFilter implements ExceptionMapper<NotAllowedException> {

    @Override
    public Response toResponse(NotAllowedException ex) {
        System.out.println("Entro a NotAllowedExceptionFilter");
        ex.printStackTrace();                        
        ErrorWrapper error = new ErrorWrapper(ex.getMessage(), "405", "METHOD_NOT_ALLOWED");
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).entity(error).build();
    }
}
