package uniandes.dpoo.aerolinea.tiquetes;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public class Tiquete {
	
	private String codigo;
	private Vuelo vuelo;
	private Cliente cliente;
	private int tarifa;
	private boolean usado;
	
	public Tiquete(String codigo, Vuelo vuelo, Cliente cliente, int tarifa) {
		super();
		this.codigo = codigo;
		this.vuelo = vuelo;
		this.cliente = cliente;
		this.tarifa = tarifa;
		this.usado = false;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public Vuelo getVuelo() {
		return vuelo;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public int getTarifa() {
		return tarifa;
	}
	
	/**
	 * Cambia el estado del tiquete para marcarlo como usado
	 */
	public void marcarComoUsado() {
		this.usado = true;
	}
	
	/**
	 * Indica si el tiquete ya fue usado
	 * @return true si ya fue usado, false de lo contrario
	 */
	public boolean esUsado() {
		return this.usado;
	}
}
