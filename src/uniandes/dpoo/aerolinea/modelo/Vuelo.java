package uniandes.dpoo.aerolinea.modelo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class Vuelo {
	
	private Ruta ruta;
	private String fecha;
	private Avion avion;
	private Map<String, Tiquete> tiquetes;
	
	public Vuelo(Ruta ruta, String fecha, Avion avion) {
		super();
		this.ruta = ruta;
		this.fecha = fecha;
		this.avion = avion;
		this.tiquetes = new HashMap<String, Tiquete>();
	}

	public Ruta getRuta() {
		return ruta;
	}

	public String getFecha() {
		return fecha;
	}

	public Avion getAvion() {
		return avion;
	}
	
	public Collection<Tiquete> getTiquetes()
	{
		Collection<Tiquete> tiquetesVuelo = this.tiquetes.values();
		return tiquetesVuelo;
	}
	
	/**
	 * Vende una determinada cantidad de tiquetes para el vuelo y los deja registrados en el mapa de tiquetes
	 * @param cliente El cliente al cual se le venden los tiquetes
	 * @param calculadora La calculadora de tarifas que debe usarse para saber el precio por tiquete
	 * @param cantidad La cantidad de tiquetes que se quieren comprar
	 * @return El valor total de los tiquetes vendidos
	 * @throws VueloSobrevendidoException Se lanza esta excepción si no hay suficiente espacio en el vuelo para todos los pasajeros
	 */
	public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad) throws VueloSobrevendidoException {
		if (this.avion.getCapacidad() > this.tiquetes.size() + cantidad) {
			throw new VueloSobrevendidoException(this);
		}
		int valorTotal = 0;
		int tarifaXTiquete = calculadora.calcularTarifa(this, cliente);
		Tiquete tiquete;
		for (int i = 0; i < cantidad; i++)
		{
			tiquete = GeneradorTiquetes.generarTiquete(this, cliente, tarifaXTiquete);
			this.tiquetes.put(tiquete.getCodigo(), tiquete);
			cliente.agregarTiquete​(tiquete);
			valorTotal += tarifaXTiquete;
		}
		return valorTotal;
	}
	
	@Override
	public boolean equals(Object obj) {
		Vuelo vueloCompare = (Vuelo) obj;
		boolean igual = false;
		if (this.avion.getNombre().equals(vueloCompare.avion.getNombre()))
		{
			if (this.fecha.equals(vueloCompare.fecha))
			{
				Ruta r = this.getRuta();
				Ruta rC = vueloCompare.getRuta();
				if (r.getOrigen().equals(rC.getOrigen()) && r.getDestino().equals(rC.getDestino())) 
				{
					igual = true;
				}
			}
		}
		return igual;
	}
}
