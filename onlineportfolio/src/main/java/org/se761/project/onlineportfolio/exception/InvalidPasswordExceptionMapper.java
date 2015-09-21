package org.se761.project.onlineportfolio.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.se761.project.onlineportfolio.model.ErrorMessage;

@Provider
public class InvalidPasswordExceptionMapper implements ExceptionMapper<InvalidPasswordException>{
	
	@Override
	public Response toResponse(InvalidPasswordException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 204);
		return Response.status(Status.UNAUTHORIZED)
				.entity(errorMessage)
				.build();
	}

}
