package ec.edu.ups.appdis.g1.parqueadero.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Stateful;
import javax.inject.Inject;

import ec.edu.ups.appdis.g1.parqueadero.modelo.Ticket;

@Stateful
public class TicketDAO {

	@Inject
	private Connection con;
	
	public  TicketDAO() {
		
	}
	
	public boolean insert (Ticket ticket) throws SQLException {
		String sql = "INSERT INTO Ticket (codigo, fechaIngreso, fechaSalida, tiempo, valor, vehiculo)"
				+ "VALUES (?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, ticket.getCodigo());
		ps.setDate(2, (Date) ticket.getFechaIngreso());
		ps.setDate(3,(Date) ticket.getFechaSalida());
		ps.setInt(4, ticket.getTiempo());
		ps.setDouble(5, ticket.getValor());
		ps.setString(6, ticket.getVehiculo().getPlaca());

		ps.executeUpdate();
		ps.close();
		
		return true;
		
	}
	public boolean update (Ticket ticket, int codigo) throws SQLException {
		String sql = "UPDATE Ticket"
				 + " SET `ticket`.`TICKET_CODIGO` = " + ticket.getCodigo() + " ' " + ","
				 + "`ticket`.`TICKET_FECHA_INGRESO` = " + ticket.getFechaIngreso() + " ' " + ","
				 + " `ticket`.`TICKET_FECHA_SALIDA` = " + ticket.getFechaSalida() + " ' " + ","
				 + "`ticket`.`TICKET_TIEMPO` = " + ticket.getTiempo() + " ' " + ","
				 + "`ticket`.`TICKET_VALOR` = " + ticket.getValor() + " ' " + ","
				 + "`ticket`.`TICKET_VEHICULO` = " + ticket.getVehiculo().getPlaca() + " ' " + ","
				 + "WHERE codigo =" + " ' " + codigo + " ' ";
		
		PreparedStatement ps = con.prepareStatement(sql);
			ps.executeUpdate();
			
		return false;
	}
	
	public Ticket read(int codigo) throws SQLException {
		Ticket ticketNuevo = null ;
		String sql = "SELECT `ticket`.`TICKET_CODIGO"
				 + "`ticket`.`TICKET_FECHA_INGRESO` " 
				 + "`ticket`.`TICKET_FECHA_SALIDA` " 
				 + "`ticket`.`TICKET_TIEMPO`"
				 + "`ticket`.`TICKET_VALOR`"
				 + "`ticket`.`TICKET_VEHICULO`"
				 + " FROM  `parqueadero`.`ticket`" 
				 + "WHERE  `ticket`.`TICKET_CODIGO`\"  =" + " ' " + codigo + " ' ";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet resultado = ps.executeQuery();
			ticketNuevo.setCodigo(resultado.getInt("TICKET_CODIGO".trim()));
			ticketNuevo.setFechaIngreso(resultado.getDate("TICKET_FECHA_INGRESO".trim()));
			ticketNuevo.setFechaSalida(resultado.getDate("TICKET_FECHA_SALIDA".trim()));
			ticketNuevo.setTiempo(resultado.getInt("TICKET_FECHA_SALIDA".trim()));
			ticketNuevo.setValor(resultado.getDouble("TICKET_VALOR".trim()));
			VehiculoDAO vehiculoDo = null;
			int buscarVehiculo = resultado.getInt("TICKET_VEHICULO".trim());
			ticketNuevo.setVehiculo(vehiculoDo.read(buscarVehiculo));
			
			ps.executeUpdate();
			ps.close();
		return null;
	}
	
	public boolean delete (int codigo) throws SQLException {
		String sql = "DELETE FROM Ticket"
                + " WHERE codigo = " + "'" + codigo + "'";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.executeUpdate();
		ps.close();
		return true;
	}

	
}
