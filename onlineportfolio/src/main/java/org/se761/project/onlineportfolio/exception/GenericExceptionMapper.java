package org.se761.project.onlineportfolio.exception;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


import org.se761.project.onlineportfolio.model.ErrorMessage;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable ex) {
		if(ex instanceof InvalidPasswordException){
			ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 403);
			return Response.status(Status.UNAUTHORIZED)
					.entity(errorMessage)
					.build();
		}else if(ex instanceof NotActiveException){
			ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 410);
			return Response.status(Status.GONE)
					.entity(errorMessage)
					.build();
		}else if(ex instanceof DatabaseRetrievalException){
			ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 204);
			return Response.status(Status.NO_CONTENT)
					.build();
		}else{
			ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 500);
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(errorMessage)
					.build();
		}
	}

}
