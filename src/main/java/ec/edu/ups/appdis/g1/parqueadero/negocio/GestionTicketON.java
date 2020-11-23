package ec.edu.ups.appdis.g1.parqueadero.negocio;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.appdis.g1.parqueadero.dao.ClienteDAO;
import ec.edu.ups.appdis.g1.parqueadero.modelo.Cliente;
import ec.edu.ups.appdis.g1.parqueadero.modelo.Ticket;
import ec.edu.ups.appdis.g1.parqueadero.modelo.Vehiculo;

@Stateless
public class GestionTicketON {
	
	@Inject
	private ClienteDAO daoCliente;

	public boolean registrarTicket(Ticket ticket) {
		return true;
	}
	
	public double calcularTiempo() {
		return 0;
	}
	
	public Vehiculo buscarVehiculo(String placa) {
		return null;
	}
	
	public boolean registrarCliente(Cliente cliente) {
		
		return true;
	}
	
}
