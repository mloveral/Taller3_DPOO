package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public class CalculadoraTarifasTemporadaAlta extends CalculadoraTarifas {
	
	protected static final int COSTO_POR_KM = 1000;
	
	/**
	 * Calcula el costo base como COSTO_POR_KM x distancia.
	 */
	@Override
	public int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
		Ruta ruta = vuelo.getRuta();
		int costoBase = COSTO_POR_KM * this.calcularDistanciaVuelo(ruta);
		return costoBase;
	}
	
	/**
	 * Calcula el porcentaje de descuento que se le debería dar a un cliente dado su tipo y/o su historia. El método retorna un número entre 0 y 1: 0 significa que no hay descuento, y 1 significa que el descuento es del 100%.
	 */
	@Override
	public double calcularPorcentajeDescuento(Cliente cliente) {
		
		return 0;
	}

}
