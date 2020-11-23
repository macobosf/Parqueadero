package ec.edu.ups.appdis.g1.parqueadero.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.appdis.g1.parqueadero.modelo.Cliente;


@Stateless
public class ClienteDAO {
	
	@Inject
	private Connection con;
	
	public ClienteDAO() {
		
	}
	public boolean insert (Cliente cliente) throws SQLException {
		String sql = "INSERT INTO Cliente (dni, email, nombre, tipoDocumento)"
				+ "VALUES (?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, cliente.getDni());
		ps.setString(2, cliente.getNombre());
		ps.setString(3, cliente.getEmail());
		ps.setInt(4, cliente.getTipoDocumento());
		
		ps.executeUpdate();
		ps.close();
		
		return true;
		
	}
	public boolean update (Cliente client, String dni) throws SQLException {
		String sql = "UPDATE CLIENTE"
				 + " SET `cliente`.`CLIENTE_ID` = " + client.getDni() + " ' " + ","
				 + "`cliente`.`CLIENTE_NOMBRE` = " + client.getNombre() + " ' " + ","
				 + "`cliente`.`CLIENTE_CORREO` = " + client.getEmail() + " ' " + ","
				 + "`cliente`.`CLIENTE_TIPODOCUMENTO` = " + client.getTipoDocumento() + " ' " + ","
				 + "WHERE dni =" + " ' " + dni + " ' ";
		
		PreparedStatement ps = con.prepareStatement(sql);
			ps.executeUpdate();
			
		return true;
	}
	
	public Cliente read(int codigo) throws SQLException {
		Cliente nuevoCliente = null ;
		String sql = "SELECT `cliente`.`CLIENTE_DNI"
				 + "`cliente`.`CLIENTE_NOMBRE` " 
				 + "`cliente`.`CLIENTE_CORREO` " 
				 + "`cliente`.`CLIENTE_TIPODOCUMENTO`"
				 + " FROM  `parqueadero`.`cliente`" 
				 + "WHERE  `cliente`.`CLIENTE_DNI`\"  =" + " ' " + codigo + " ' ";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet resultado = ps.executeQuery();
			nuevoCliente.setDni(resultado.getString("CLIENTE_DNI".trim()));
			nuevoCliente.setNombre(resultado.getString("CLIENTE_NOMBRE".trim()));
			nuevoCliente.setEmail(resultado.getString("CLIENTE_CORREO".trim()));
			nuevoCliente.setTipoDocumento(resultado.getInt("CLIENTE_TIPODOCUMENTO".trim()));
		
			ps.executeUpdate();
			ps.close();	
		return null;
	}
	public boolean delete (int codigo) throws SQLException {
		String sql = "DELETE FROM CLIENTE"
                + " WHERE CLIENTE_DNI = " + "'" + codigo + "'";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.executeUpdate();
		ps.close();
		return true;

}
	
}
