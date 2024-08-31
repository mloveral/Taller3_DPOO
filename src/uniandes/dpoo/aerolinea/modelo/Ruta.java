package uniandes.dpoo.aerolinea.modelo;

/**
 * Esta clase tiene la información de una ruta entre dos aeropuertos que cubre una aerolínea.
 */
public class Ruta
{
	private Aeropuerto origen;
    private Aeropuerto destino;
	private String horaSalida;
    private String horaLlegada;
    private String codigoRuta;
    
    
	public Ruta( Aeropuerto origen, Aeropuerto destino, String horaSalida, String horaLlegada, String codigoRuta) {
		super();
		this.horaSalida = horaSalida;
		this.horaLlegada = horaLlegada;
		this.codigoRuta = codigoRuta;
		this.origen = origen;
		this.destino = destino;
	}


	public String getCodigoRuta() {
		return codigoRuta;
	}
	
	public Aeropuerto getOrigen() {
		return origen;
	}


	public Aeropuerto getDestino() {
		return destino;
	}


	public String getHoraSalida() {
		return horaSalida;
	}


	public String getHoraLlegada() {
		return horaLlegada;
	}
	
	/**
	 * Calcula la duración esperada del vuelo en minutos
	 * @return la duración del vuelo
	 * */
	public int getDuracion() {
		int duracion = 0;
		int horasDuracion = (Ruta.getHoras(this.horaLlegada) - Ruta.getHoras(this.horaSalida)) % 24;
		int minutosDuracion = (Ruta.getMinutos(this.horaLlegada) - Ruta.getMinutos(this.horaSalida)) % 60;
		duracion = minutosDuracion + horasDuracion * 60;
		return duracion;
	}

	/**
     * Dada una cadena con una hora y minutos, retorna los minutos.
     * 
     * Por ejemplo, para la cadena '715' retorna 15.
     * @param horaCompleta Una cadena con una hora, donde los minutos siempre ocupan los dos últimos caracteres
     * @return Una cantidad de minutos entre 0 y 59
     */
    public static int getMinutos( String horaCompleta )
    {
        int minutos = Integer.parseInt( horaCompleta ) % 100;
        return minutos;
    }

    /**
     * Dada una cadena con una hora y minutos, retorna las horas.
     * 
     * Por ejemplo, para la cadena '715' retorna 7.
     * @param horaCompleta Una cadena con una hora, donde los minutos siempre ocupan los dos últimos caracteres
     * @return Una cantidad de horas entre 0 y 23
     */
    public static int getHoras( String horaCompleta )
    {
        int horas = Integer.parseInt( horaCompleta ) / 100;
        return horas;
    }

    
}
