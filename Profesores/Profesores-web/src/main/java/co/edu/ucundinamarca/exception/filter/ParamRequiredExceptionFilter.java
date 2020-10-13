/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.exception.filter;

import co.edu.ucundinamarca.exception.ParamRequiredException;
import co.edu.ucundinamarca.pojo.ErrorWrapper;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author david
 */
public class ParamRequiredExceptionFilter implements ExceptionMapper<ParamRequiredException> {

    @Override
    public Response toResponse(ParamRequiredException ex) {
        System.out.println("Entro a ParamRequiredException");
        ex.printStackTrace();
        ErrorWrapper error = new ErrorWrapper(ex.getMessage(), "400", "BAD_REQUEST");
        return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
    }
}
