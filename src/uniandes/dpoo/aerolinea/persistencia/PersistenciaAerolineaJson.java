package uniandes.dpoo.aerolinea.persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONObject;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Avion;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;

public class PersistenciaAerolineaJson implements IPersistenciaAerolinea {
	
	@Override
	public void cargarAerolinea(String archivo, Aerolinea aerolinea) throws InformacionInconsistenteException {
		// TODO Auto-generated method stub
		String jsonCompleto = null;
		try {
			jsonCompleto = new String( Files.readAllBytes( new File( archivo ).toPath( ) ) );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        JSONObject raiz = new JSONObject( jsonCompleto );
        
        // Cargar aviones
        JSONArray jAviones = raiz.getJSONArray("aviones");
        int lenAviones = jAviones.length();
        
        for (int i = 0; i < lenAviones; i++)
        {
        	JSONObject jAvion = jAviones.getJSONObject(i);
        	String nombre = jAvion.getString("nombre");
        	int capacidad = jAvion.getInt("capacidad");
        	Avion nuevoAvion = new Avion(nombre, capacidad);
        	if (!aerolinea.getAviones().contains(nuevoAvion)) {
        		aerolinea.agregarAvion(nuevoAvion);
        	}
        	else {
        		throw new InformacionInconsistenteException("Hay aviones repetidos dentro del archivo a cargar");
        	}
        }
        
     // Cargar rutas
        JSONArray jRutas = raiz.getJSONArray("rutas");
        int lenRutas = jRutas.length();
        
        for (int i = 0; i < lenRutas; i++)
        {
        	JSONObject jRuta = jRutas.getJSONObject(i);
        	
        	//Obtener los datos de los aeropuertos de origen y de destino
        	JSONObject jOrigen = jRuta.getJSONObject("origen");
        	JSONObject jDestino = jRuta.getJSONObject("destino");
        	Aeropuerto origen = new Aeropuerto(jOrigen.getString("nombre"), jOrigen.getString("codigo"), jOrigen.getString("nombreCiudad"), jOrigen.getDouble("latitud"), jOrigen.getDouble("longitud"));
        	Aeropuerto destino = new Aeropuerto(jDestino.getString("nombre"), jDestino.getString("codigo"), jDestino.getString("nombreCiudad"), jDestino.getDouble("latitud"), jDestino.getDouble("longitud"));
        	
        	String horaSalida = jRuta.getString("horaSalida");
        	String horaLlegada = jRuta.getString("horaLlegada");
        	String codigoRuta = jRuta.getString("codigoRuta");
        	Ruta nuevaRuta = new Ruta(origen, destino, horaSalida, horaLlegada, codigoRuta);
        	if (aerolinea.getRuta(codigoRuta) == null) {
        		aerolinea.agregarRuta(nuevaRuta);
        	}
        	else {
        		throw new InformacionInconsistenteException("Hay rutas identicas repetidas dentro del archivo a cargar");
        	}
        }
        
        // Cargar vuelos
        JSONArray jVuelos = raiz.getJSONArray("vuelos");
        int lenVuelos = jVuelos.length();
        
        for (int i = 0; i < lenVuelos; i++)
        {
        	JSONObject jVuelo = jVuelos.getJSONObject(i);
        	String codigoRuta = jVuelo.getString("codigoRuta");
        	String fecha = jVuelo.getString("fecha");
        	String nombreAvion = jVuelo.getString("nombreAvion");
        	if (aerolinea.getVuelo(codigoRuta, fecha) == null) {
        		try {
					aerolinea.programarVuelo(fecha, codigoRuta, nombreAvion);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        	else {
        		throw new InformacionInconsistenteException("Hay aviones repetidos dentro del archivo a cargar");
        	}
        }
	}
	
	@Override
	public void salvarAerolinea(String archivo, Aerolinea aerolinea) throws IOException {
		// TODO Auto-generated method stub
		JSONObject jObject = new JSONObject();
		
		// Salvar aviones
		JSONArray jAviones = new JSONArray();
		
		for (Avion avion: aerolinea.getAviones())
		{
			JSONObject jAvion = new JSONObject();
			jAvion.put("nombre", avion.getNombre());
			jAvion.put("capacidad", avion.getCapacidad());
			jAviones.put(jAvion);
		}
		
		jObject.put("aviones", jAviones);
		
		// Salvar rutas
		JSONArray jRutas = new JSONArray();
		
		for (Ruta ruta: aerolinea.getRutas())
		{
			JSONObject jRuta = new JSONObject();
			
			// Guardar en JSONObjects los aeropuertos de origen y destino
			JSONObject jOrigen = new JSONObject();
			JSONObject jDestino = new JSONObject();
			
			Aeropuerto origen = ruta.getOrigen();
			jOrigen.put("nombre", origen.getNombre());
			jOrigen.put("codigo", origen.getCodigo());
			jOrigen.put("nombreCiudad", origen.getNombreCiudad());
			jOrigen.put("latitud", origen.getLatitud());
			jOrigen.put("longitud", origen.getLatitud());
			
			Aeropuerto destino = ruta.getDestino();
			jDestino.put("nombre", destino.getNombre());
			jDestino.put("codigo", destino.getCodigo());
			jDestino.put("nombreCiudad", destino.getNombreCiudad());
			jDestino.put("latitud", destino.getLatitud());
			jDestino.put("longitud", destino.getLatitud());
			
			jRuta.put("origen", jOrigen);
			jRuta.put("destino", jDestino);
			jRuta.put("horaSalida", ruta.getHoraSalida());
			jRuta.put("horaLlegada", ruta.getHoraLlegada());
			jRuta.put("codigoRuta", ruta.getCodigoRuta());
			jRutas.put(jRuta);
		}
		
		jObject.put("rutas", jRutas);
		
		// Salvar vuelos
		JSONArray jVuelos = new JSONArray();
		
		for (Vuelo vuelo: aerolinea.getVuelos())
		{
			JSONObject jVuelo = new JSONObject();
			
			jVuelo.put("codigoRuta", vuelo.getRuta().getCodigoRuta());
			jVuelo.put("fecha", vuelo.getFecha());
			
			// Añadir nombre del avion del vuelo
			Avion avionVuelo = vuelo.getAvion();
			jVuelo.put("nombreAvion", avionVuelo.getNombre());
			
			jVuelo.put("tiquetes", vuelo.getTiquetes());
			jVuelos.put(jVuelo);
		}
		
		jObject.put("vuelos", jVuelos);
		
		// Escribir la estructura JSON en un archivo
        PrintWriter pw = new PrintWriter( archivo );
        jObject.write( pw, 2, 0 );
        pw.close( );

	}

}
