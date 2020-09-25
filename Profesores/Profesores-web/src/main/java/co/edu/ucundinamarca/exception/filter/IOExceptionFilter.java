 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.exception.filter;

import co.edu.ucundinamarca.pojo.ErrorWrapper;
import java.io.IOException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author david
 */
@Provider
public class IOExceptionFilter implements ExceptionMapper<IOException> {
    @Override
    public Response toResponse(IOException ex) {        
        ErrorWrapper error = new ErrorWrapper(ex.getMessage(), "500", "loca");
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
    }
}
