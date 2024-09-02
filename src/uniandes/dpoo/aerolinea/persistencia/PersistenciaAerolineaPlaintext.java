package uniandes.dpoo.aerolinea.persistencia;

import java.io.IOException;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;

/**
 * Esta clase no está implementada - y no debería implementarse como parte del taller.
 * 
 * Su objetivo es sólo ilustrar que podría haber varias implementaciones de la misma interfaz, y que durante la ejecución alguien podría decidir cuál de estas implementaciones
 * usar.
 */
public class PersistenciaAerolineaPlaintext implements IPersistenciaAerolinea
{

	@Override
	public void cargarAerolinea(String archivo, Aerolinea aerolinea) throws InformacionInconsistenteException {
		// No está implementado (y no es necesario para el taller)
		
	}

	@Override
	public void salvarAerolinea(String archivo, Aerolinea aerolinea) throws IOException {
		// No está implementado (y no es necesario para el taller)
		
	}
    

}
