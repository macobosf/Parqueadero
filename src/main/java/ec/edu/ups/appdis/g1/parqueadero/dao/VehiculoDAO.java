package ec.edu.ups.appdis.g1.parqueadero.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Stateful;
import javax.inject.Inject;

import ec.edu.ups.appdis.g1.parqueadero.modelo.Vehiculo;

@Stateful
public class VehiculoDAO {
	
	@Inject
	private Connection con;
	
	public VehiculoDAO() {
		
	}
	
	public boolean insert(Vehiculo vehiculo) throws SQLException {
		String sql = "NSERT INTO Vehiculo (placa, marca, color)"
				+"VALUES (?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, vehiculo.getPlaca());
		ps.setString(2, vehiculo.getMarca());
		ps.setString(3, vehiculo.getColor());
		ps.execute();
		ps.close();
		return true;
	}
	
	public boolean update(Vehiculo vehiculo) throws SQLException {
		String sql = "UPDATE_VEHICULO"
				+"SET 'vehiculo'.'VEHICULO_PLACA' = " +
		vehiculo.getPlaca() + " ' " + ","
				+"SET 'vehiculo'.'VEHICULO_MARCA' = " +
		vehiculo.getMarca() + " ' " + ","
				+"SET 'vehiculo'.'VEHICULO_COLOR' = " +
		vehiculo.getColor() + " ' " + ",";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.executeUpdate();
		return true;
	}
	
	public Vehiculo read(int id) throws SQLException {
		Vehiculo nuevoVehiculo = null ;
		String sql = "SELECT `vehiculo`.`VEHICULO_PLACA"
				 + "`vehiculo`.`VEHICULO_MARCA` " 
				 + "`vehiculo`.`VEHICULO_COLOR` ";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet resultado = ps.executeQuery();
			nuevoVehiculo.setPlaca(resultado.getString("VEHICULO_PLACA".trim()));
			nuevoVehiculo.setMarca(resultado.getString("VEHICULO_MARCA".trim()));
			nuevoVehiculo.setColor(resultado.getString("VEHICULO_COLOR".trim()));
			ps.executeUpdate();
			ps.close();
		return null;
	}
	
	public boolean delete(String placa) throws SQLException {
		String sql = "DELETE FROM VEHICULO"
				+ "WHERE VEHICULO_PLACA = " + "'" + placa +"'";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.executeUpdate();
		ps.close();
		return true;
	}

}
