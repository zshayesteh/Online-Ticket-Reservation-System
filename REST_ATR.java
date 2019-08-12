package REST;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("tickets")
public class REST_ATR {
	ticketPackage.TicketDao TDAO = new ticketPackage.TicketDao();
	String regex="^[1-4]\\d{3}\\/((0?[1-6]\\/((3[0-1])|([1-2][0-9])|(0?[1-9])))|((1[0-2]|(0?[7-9]))\\/(30|([1-2][0-9])|(0?[1-9]))))$";//regex pattern for checking the Date

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTicket(ticketPackage.Ticket t) throws SQLException {//create a ticket
		if (t.getFlightDate().matches(regex)) {//checking validation of the Date format 
			if (t.getFlightNumber() > 99 && t.getFlightNumber() < 1000) {//checking validation of the flight number
				int id = TDAO.create(t);// creating a ticket & returning its id
				return Response.status(200).entity("Ticket bought successfully with ticketNumber= " + id).build();
			} else//if the flight number is not valid
				return Response.status(200).entity("Flight number is not valid!").build();
		} else//if the flight Date is not in valid format
			return Response.status(200).entity("Flight Date is not in the valid format!").build();
	}

	@GET
//	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ticketPackage.Ticket> showTickets() throws SQLException {// read all tickets 
		return TDAO.readAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ticketPackage.Ticket showTicket(@PathParam("id") int id) throws SQLException {//read one ticket by its ID
		return TDAO.read(id);
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editTicket(ticketPackage.Ticket t) throws SQLException {//updating a ticket with its ID
		if (t.getFlightDate().matches(regex)) {//regex pattern of the Date
			if (t.getFlightNumber() > 99 && t.getFlightNumber() < 1000) {//checking the flight number validation
				TDAO.update(t);
				return Response.status(200).entity("Ticket updated successfully!").build();//if the ticket create successfully
			} else//if the flight number is not valid
				return Response.status(200).entity("Flight number is not valid!").build();
		} else//if the flight date is not in valid format of it
			return Response.status(200).entity("Flight Date is not in the valid format!").build();
	}

	@DELETE
	@Path("/{id}")
	public Response canselTicket(@PathParam("id") int id) throws SQLException {//deleting a ticket with its ID
		TDAO.delete(id);
		return Response.status(200).entity("Ticket canseled successfully!").build();
	}

	@DELETE
	public Response canselTickets() throws SQLException {//deleting all tickets
		TDAO.deleteAll();
		return Response.status(200).entity("All tickets canselled successfully!").build();
	}
}
