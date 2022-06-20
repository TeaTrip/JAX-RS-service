package com.wishmaster.ifmo.ws.jaxrs.server;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IllegalParameterExceptionMapper  implements ExceptionMapper<IllegalParameterException> {
    @Override
    public Response toResponse(IllegalParameterException e)  {
        return
                Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}
