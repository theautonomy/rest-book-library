package org.weili.rest.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.weili.rest.dao.IBookDAO;
import org.weili.rest.domain.Book;

public class BookResource {

	IBookDAO dao;
	UriInfo uriInfo;
	int index;
	
	public BookResource(UriInfo uriInfo, int index, IBookDAO dao) {
		this.uriInfo = uriInfo;
		this.index = index;
		this.dao = dao;
	}

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response findBook() {
		Book book = dao.findBook(index);
		if (book != null) {
			return  Response.status(Status.OK).entity(dao.findBook(index)).build();
		} else {
			// See Status here: http://jsr311.java.net/nonav/releases/1.1/javax/ws/rs/core/Response.Status.html
			// Notice the difference between Status.NOT_FOUND and Status.NO_CONTENT
			return Response.status(Status.NOT_FOUND).build();
		}

	}
	
	@PUT 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Book update(Book book) {
		return dao.updateBook(book);
	}
	
	@DELETE 
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void remove() {
		dao.removeBook(index);
	}

}
