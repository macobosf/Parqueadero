package ec.edu.ups.appdis.g1.parqueadero.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.appdis.g1.parqueadero.modelo.Factura;

@Stateless
public class FacturaDAO {
	
	@Inject
	private Connection con;
	
	public FacturaDAO () {
		
	}
	public boolean insert (Factura factura) throws SQLException {
		String sql = "INSERT INTO Factura (cliente, fechaEmision, numeroFactura, ticket, valorTotal)"
				+ "VALUES (?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(3, factura.getCliente().getDni());
		ps.setDate(2, (Date) factura.getFechaEmision());
		ps.setLong(1,factura.getNumero() );
		ps.setInt(5, factura.getTicket().getCodigo());
		ps.setDouble(4, factura.getValorTotal());
		
		ps.executeUpdate();
		ps.close();
		
		return true;
	}
	public boolean update (Factura factura, String codigo) throws SQLException {
		String sql = "UPDATE FACTURA"
				 + " SET `factura`.`FACTURA_NUMEROFACTURA` = " + factura.getNumero() + " ' " + ","
				 + "`factura`.`FACTURA_FECHAEMISION` = " + factura.getFechaEmision() + " ' " + ","
				 + " `factura`.`FACTURA_CLIENTE` = " + factura.getCliente().getDni() + " ' " + ","
				 + "`factura`.`FACTURA_VALORTOTAL` = " + factura.getValorTotal() + " ' " + ","
				 + "`factura`.`FACTURA_CODIGO_TICKET` = " + factura.getTicket().getCodigo() + " ' " + ","
				 + "WHERE numeroFactura =" + " ' " + codigo + " ' ";
		
		PreparedStatement ps = con.prepareStatement(sql);
			ps.executeUpdate();
			
		return false;
	}
	
	public Factura read(int codigo) throws SQLException {
		Factura nuevaFactura = null ;
		String sql = "SELECT `factura`.`FACTURA_NUMEROFACTURA"
				 + "`factura`.`FACTURA_FECHAEMISION` " 
				 + "`factura`.`FACTURA_CLIENTE` " 
				 + "`factura`.`FACTURA_VALORTOTAL`"
				 + "`factura`.`FACTURA_CODIGO_TICKET`"
				 + " FROM  `parqueadero`.`factura`" 
				 + "WHERE  `factura`.`FACTURA_NUMEROFACTURA`\"  =" + " ' " + codigo + " ' ";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet resultado = ps.executeQuery();
			nuevaFactura.setNumero(resultado.getInt("FACTURA_NUMEROFACTURA".trim()));
			nuevaFactura.setFechaEmision(resultado.getDate("FACTURA_FECHAEMISION".trim()));
			ClienteDAO clienteDo = null;
			int buscarCliente = resultado.getInt("FACTURA_CLIENTE".trim());
			nuevaFactura.setCliente(clienteDo.read(buscarCliente));
			nuevaFactura.setValorTotal(resultado.getDouble("FACTURA_VALORTOTAL".trim()));
			TicketDAO ticketFactura = null;
			int buscarTicket = resultado.getInt("FACTURA_CODIGO_TICKET".trim());
			nuevaFactura.setTicket(ticketFactura.read(buscarTicket));
			ps.executeUpdate();
			ps.close();
		return null;
	}
	public boolean delete (String codigo) throws SQLException {
		String sql = "DELETE FROM FACTURA"
                + " WHERE numeroFactura = " + "'" + codigo + "'";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.executeUpdate();
		ps.close();
		return true;
	}


	
}
