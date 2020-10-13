/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.exception.filter;

import co.edu.ucundinamarca.exception.ParamUsedException;
import co.edu.ucundinamarca.pojo.ErrorWrapper;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author david
 */
public class ParamUsedExceptionFilter implements ExceptionMapper<ParamUsedException> {

    @Override
    public Response toResponse(ParamUsedException ex) {
        System.out.println("Entro a ParamUsedException");
        ex.printStackTrace();
        ErrorWrapper error = new ErrorWrapper(ex.getMessage(), "400", "BAD_REQUEST");
        return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
    }
}
