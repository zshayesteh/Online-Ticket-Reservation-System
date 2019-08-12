package ticketPackage;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;

public class TicketDao {//ticket pojo
	Statement stmt;

	public TicketDao() {//constructor of the class which initialize mysql connector parameters
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://127.0.0.1/airplane_ticket_reservation?user=root&password=";
			Connection conn = DriverManager.getConnection(url);
			if (conn == null) {
				System.out.println("errrrrrrrrrror connection");
			}
			stmt = conn.createStatement();
		} catch (Exception e) {
			System.out.println("exception " + e);
		}
	}

	public int create(Ticket t) throws SQLException {// create a ticket which returns its ID
		stmt.executeUpdate(MessageFormat.format("insert into ticket (ownerName,source,destination,flightDate,flightNumber) values(''{0}'',''{1}'',''{2}'',''{3}'',{4});",
				t.getOwnerName(), t.getSource(), t.getDestination(), t.getFlightDate(), t.getFlightNumber()));
		ResultSet rs=stmt.executeQuery(MessageFormat.format("select ticketID from ticket where ownerName=''{0}'' and source=''{1}'' and destination=''{2}'' and flightDate=''{3}'' and flightNumber= {4};",t.getOwnerName(), t.getSource(), t.getDestination(), t.getFlightDate(), t.getFlightNumber()));
		while(rs.next())
			return rs.getInt(1);
		return -1;//if ticket does not create successfully
	}

	public Ticket read(int ticketID) throws SQLException {// read one ticket by its ID and returns its object
		int ID, flightNumber;
		String ownerName, source, destination;
		String flightDate;
		Ticket t;
		ResultSet rs = stmt.executeQuery("select * from ticket where ticketID=" + ticketID + ";");
		while (rs.next()) {
			ID = rs.getInt(1);
			ownerName = rs.getString(2);
			source = rs.getString(3);
			destination = rs.getString(4);
			flightDate = rs.getString(5);
			flightNumber = rs.getInt(6);
			t = new Ticket(ID, ownerName, source, destination, flightDate, flightNumber);
			return t;//returning the ticket object
		}
		return new Ticket();//if ticket does not found it will create a empty object of ticket
	}

	public ArrayList<Ticket> readAll() throws SQLException// read all tickets and returns their list
	{
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		int ID, flightNumber;
		String ownerName, source, destination;
		String flightDate;
		Ticket t;
		ResultSet rs = stmt.executeQuery("select * from ticket;");
		while (rs.next()) {
			ID = rs.getInt(1);
			ownerName = rs.getString(2);
			source = rs.getString(3);
			destination = rs.getString(4);
			flightDate = rs.getString(5);
			flightNumber = rs.getInt(6);
			t = new Ticket(ID, ownerName, source, destination, flightDate, flightNumber);
			tickets.add(t);
		}
		return tickets;//returning the list of tickets
	}

	public void update(Ticket t) throws SQLException {// update a ticket
		stmt.executeUpdate("update ticket set ownerName='" + t.getOwnerName() + "' ,source='" + t.getSource()+ "' ,destination='" + t.getDestination() + "' ,flightDate='" + t.getFlightDate() + "' ,flightNumber="+ t.getFlightNumber() + " where ticketID=" + t.getTicketID() + ";");
	}

	public void delete(int ticketID) throws SQLException {// delete a ticket with its ID
		stmt.executeUpdate("delete from Ticket where ticketID=" + ticketID + ";");
	}

	public void deleteAll() throws SQLException {// delete all tickets
		stmt.executeUpdate("truncate table ticket;");
	}
}