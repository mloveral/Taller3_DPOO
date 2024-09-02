package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public abstract class CalculadoraTarifas {
	
	public static final double IMPUESTO = 0.28;
	
	/**
	 *Este método calcula cuál debe ser la tarifa total para un vuelo, dado el vuelo y el cliente. La tarifa total está constituída por un costo base, un descuento que podría aplicarse sobre el costo base, y un impuesto que se aplica sobre el costo base menos el descuento. Este método utiliza los métodos calcularCostoBase y calcularPorcentajeDescuento para calcular la tarifa total
	 *@param vuelo El vuelo para el que se quiere calcular la tarifa
	 *@param cliente El cliente para el que se quiere calcular la tarifa
	 *
	 *@return El valor completo de la tarifa
	 */
	public int calcularTarifa(Vuelo vuelo, Cliente cliente) {
		int base = this.calcularCostoBase(vuelo, cliente);
		double descuento = this.calcularPorcentajeDescuento(cliente);
		base = (int) (base - base*descuento);
		int impuestos = this.calcularValorImpuestos(base);
		int total = base + impuestos;
		return total;
	}
	
	
	/**
	 * Este método calcula cuál debe ser el costo base dado el vuelo y el cliente.
	 * @param vuelo El vuelo para el que se quiere calcular la tarifa
	 * @param cliente El cliente para el que se quiere calcular la tarifa
	 * 
	 * @return El valor base de la tarifa
	 */
	protected abstract int calcularCostoBase(Vuelo vuelo, Cliente cliente);
	
	
	/**
	 * Calcula el porcentaje de descuento que se le debería dar a un cliente dado su tipo y/o su historia. El método retorna un número entre 0 y 1: 0 significa que no hay descuento, y 1 significa que el descuento es del 100%.
	 * @param cliente El cliente para el que se quiere conocer el descuento
	 * @return Un porcentaje de descuento, entre 0 y 1.
	 */
	protected abstract double calcularPorcentajeDescuento(Cliente cliente);
	
	
	/**
	 * Calcula la distancia aproximada en kilómetros para una ruta
	 * @param ruta
	 * @return Una aproximación de la distancia
	 */
	protected int calcularDistanciaVuelo(Ruta ruta) {
		Aeropuerto a1 = ruta.getOrigen();
		Aeropuerto a2 = ruta.getDestino();
		int distancia = Aeropuerto.calcularDistancia(a1, a2);
		return distancia;
	}
	
	
	/**
	 * Calcula el valor de los impuestos para un tiquete, dado el costo base. Los impuestos se calculan como un porcentaje sobre el costo base, usando la constante IMPUESTO
	 * @param costoBase El valor base del tiquete
	 * @return El valor correspondiente a los impuestos
	 */
	protected int calcularValorImpuestos(int costoBase) {
		int impuesto = (int) (costoBase * IMPUESTO);
		return impuesto;
	}
}
