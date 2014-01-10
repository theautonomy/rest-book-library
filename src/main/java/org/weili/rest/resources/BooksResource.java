package org.weili.rest.resources;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.weili.rest.dao.IBookDAO;
import org.weili.rest.dao.InMemoryBookDAO;
import org.weili.rest.domain.Book;

@Path("/books")
public class BooksResource {
	
	@Context 
	UriInfo uriInfo;    

	IBookDAO dao;
	
	public BooksResource() {
		dao = new InMemoryBookDAO();
	}

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Book> getBooks() {
		return dao.findAllBooks(); 
	}

	@Path("{index}/")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public BookResource findBook(@PathParam("index") int index) {
		return new BookResource(uriInfo, index, dao);
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response addBook(Book book) {
		dao.addBook(book);
		URI uri = UriBuilder.fromUri(uriInfo.getAbsolutePath()).path(book.getId()+"").build();
		try {
			System.out.println("uri=" + uri.toURL().toExternalForm());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        return Response.created(uri).entity(book).build();
	}	

}
