package org.se761.project.onlineportfolio.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import org.se761.project.onlineportfolio.model.ErrorMessage;

public class NotActiveExceptionMapper implements ExceptionMapper<NotActiveException>{
	
	@Override
	public Response toResponse(NotActiveException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 500);
		return Response.status(Status.GONE)
				.entity(errorMessage)
				.build();
	}

}
