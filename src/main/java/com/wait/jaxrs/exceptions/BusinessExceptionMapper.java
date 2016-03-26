package com.wait.jaxrs.exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BusinessExceptionMapper implements ExceptionMapper<SomeBusinessException>{

	@Override
	public Response toResponse(SomeBusinessException e) {
		StringBuilder response = new StringBuilder("<response>");
		response.append("<status>Error</status>");
		response.append("<message>"+e.getMessage()+"</message>");
		response.append("</response>");
		 
		return Response.serverError().entity(response.toString()).type(MediaType.APPLICATION_XML).build();
	}
	

}
