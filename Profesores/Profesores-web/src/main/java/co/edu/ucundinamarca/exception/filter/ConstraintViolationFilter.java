/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.exception.filter;

import co.edu.ucundinamarca.pojo.ErrorWrapper;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author PROFESIONAL
 */
@Provider
public class ConstraintViolationFilter implements ExceptionMapper<ConstraintViolationException>{


    @Override
    public Response toResponse(ConstraintViolationException ex) {
        System.out.println("Entro a ConstraintViolationFilter");     
        ErrorWrapper error = new ErrorWrapper(prepareMessage(ex), "400", "BAD_REQUEST");
        return Response.status(Response.Status.BAD_REQUEST).entity(error).build();        
    }
    
     private String prepareMessage(ConstraintViolationException exception) {
        String msg = "";
        for (ConstraintViolation<?> cv : exception.getConstraintViolations()) {
            msg = cv.getMessage();
        }
        return msg;
    }    
     
}
