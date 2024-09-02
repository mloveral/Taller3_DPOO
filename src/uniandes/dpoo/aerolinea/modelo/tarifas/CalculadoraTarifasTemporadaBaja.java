package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas {

	protected static final int COSTO_POR_KM_NATURAL = 600;
	protected static final int COSTO_POR_KM_CORPORATIVO = 900;
	protected static final double DESCUENTO_PEQ = 0.02;
	protected static final double DESCUENTO_MEDIANAS = 0.1;
	protected static final double DESCUENTO_GRANDES = 0.2;
	
	/**
	 * Calcula el costo base como COSTO_POR_KM x distancia.
	 */
	@Override
	protected int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
		// TODO Auto-generated method stub
		String tipoCliente = cliente.getTipoCliente();
		Ruta rutaVuelo = vuelo.getRuta();
		int distancia = this.calcularDistanciaVuelo(rutaVuelo);
		int costoBase = 0;
		if (tipoCliente == "Natural")
		{
			costoBase = distancia * COSTO_POR_KM_NATURAL;
		}
		else if (tipoCliente == "Corporativo")
		{
			costoBase = distancia * COSTO_POR_KM_CORPORATIVO;
		}
		return costoBase;
	}
	
	/**
	 * Calcula el porcentaje de descuento que se le debería dar a un cliente dado su tipo y/o su historia. El método retorna un número entre 0 y 1: 0 significa que no hay descuento, y 1 significa que el descuento es del 100%.
	 */
	@Override
	protected double calcularPorcentajeDescuento(Cliente cliente) {
		// TODO Auto-generated method stub
		double descuento = 0;
		String tipoCliente = cliente.getTipoCliente();
		if (tipoCliente == "Corporativo")
		{
			ClienteCorporativo c = (ClienteCorporativo) cliente;
			if (c.getTamanoEmpresa() >= ClienteCorporativo.GRANDE)
			{
				descuento = DESCUENTO_GRANDES;
			}
			else if (c.getTamanoEmpresa() >= ClienteCorporativo.MEDIANA)
			{
				descuento = DESCUENTO_MEDIANAS;
			}
			else 
			{
				descuento = DESCUENTO_PEQ;
			}
		}
		return descuento;
	}

}
