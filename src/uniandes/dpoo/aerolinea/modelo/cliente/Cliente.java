package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public abstract class Cliente {
	
	private List<Tiquete> tiquetesSinUsar;
	private List<Tiquete> tiquetesUsados;
	
	public Cliente() {
		this.tiquetesSinUsar = new ArrayList<Tiquete>();
		this.tiquetesUsados = new ArrayList<Tiquete>();
	}
	
	public abstract String getTipoCliente();
	
	public abstract String getIdentificador();
	
	
	/**
	 * Agrega un nuevo tiquete a la lista de tiquetes (sin usar) que ha comprado el cleinte
	 * @param tiquete El nuevo tiquete que se va a agregar
	 */
	public void agregarTiquete​(Tiquete tiquete) {
		this.tiquetesSinUsar.add(tiquete);
	}
	
	/**
	 * Calcula el valor total de los tiquetes que ha comprado un client
	 * @return El valor de todos los tiquetes de un cliente
	 */
	public int calcularValorTotalTiquetes() {
		//TODO
		int total = 0;
		Tiquete tiquete;
		for (Iterator<Tiquete> iterador = this.tiquetesSinUsar.iterator(); iterador.hasNext();) {
			tiquete = iterador.next();
			total += tiquete.getTarifa();
		}
		
		for (Iterator<Tiquete> iterador = this.tiquetesUsados.iterator(); iterador.hasNext();) {
			tiquete = iterador.next();
			total += tiquete.getTarifa();
		}
		return total;
	}
	
	
	/**
	 * Marca como usados todos los tiquetes del cliente qus se hayan realizado en el vuelo que llega por parámetro, moviéndolos de la lista de tiquetes sin usar a la lista de tiquetes usados
	 */
	public void usarTiquetes​(Vuelo vuelo) {
		//TODO
		Collection<Tiquete> tiquetesVuelo = vuelo.getTiquetes();
		Tiquete tiquete;
		for (Iterator<Tiquete> iterador = tiquetesVuelo.iterator(); iterador.hasNext();) {
			tiquete = iterador.next();
			if (this.tiquetesSinUsar.contains(tiquete))
			{
				this.tiquetesSinUsar.remove(tiquete);
				this.tiquetesUsados.add(tiquete);
			}
		}
	}
}
