package space_girls;

/**
 * 
 * Interface
 * @author a_hognose_snake
 * @author dorime_a1
 *
 */
public interface ClinicaDentistaSonRisas {
	
	/**
	 * 
	 * Checks if the rut input belongs to a client in the system.
	 * @param rutInput
	 * @returns a boolean (true if it belongs, false if it doesn't)
	 * 
	 */
	public boolean validarCliente(String rutInput); 
	/**
	 * 
	 * Checks if the rut input belongs to a dentist in the system.
	 * @param rutInput
	 * @returns a boolean (true if it belongs, false if it doesn't)
	 * 
	 */
	public boolean validarDentista(String rutInput);
	/**
	 * 
	 * Checks if the input password of the client designated by rutInput is the same as the one in the system.
	 * @param rutCliente
	 * @param contrasena
	 * @returns a boolean (true if it checks out, false if it doesn't)
	 */
	public boolean validarAccesoCliente(String rutCliente, String contrasena); 
	/**
	 * 
	 * Checks if the input password of the dentist designated by rutInput is the same as the one in the system.
	 * @param rutDentista
	 * @param contrasena
	 * @returns a boolean (true if it checks out, false if it doesn't)
	 * 
	 */
	public boolean validarAccesoDentista(String rutDentista, String contrasena);
	/**
	 * 
	 * Registers a clinic to the system.
	 * @param ciudad
	 * @param totalGanancia
	 * @param estado
	 * @returns a boolean (true if the registration is a success, false if it isn't)
	 * 
	 */
	public boolean ingresarClinica(String ciudad, int totalGanancia, String estado); 
	/**
	 * 
	 * registers a dentist to the system.
	 * @param nombre
	 * @param apellido
	 * @param rutDentista
	 * @param contrasena
	 * @param sueldoBase
	 * @param totalComisiones
	 * @param ciudad
	 * @param anosExperiencia
	 * @returns a boolean (true if the registration is a success, false if it isn't)
	 * 
	 */
	public boolean ingresarDentista(String nombre, String apellido, String rutDentista, String contrasena,
	int sueldoBase, int totalComisiones,String ciudad, int anosExperiencia);
	/**
	 * 
	 * Registers a client to the system.
	 * @param nombre
	 * @param apellido
	 * @param rutCliente
	 * @param contrasena
	 * @param sistemaSalud
	 * @param ciudad
	 * @returns a boolean (true if the registration is a success, false if it isn't)
	 * 
	 */
	public boolean ingresarCliente(String nombre, String apellido, String rutCliente, String contrasena,
	String sistemaSalud, String ciudad); 
	/**
	 * 
	 * Registers an appointment to the system. It also links the appointment to the client and a dentist.
	 * @param rutCliente
	 * @param operacion
	 * @param precio
	 * @param ciudad
	 * @param rutDentista
	 * @param fecha
	 * @param codigoUnico
	 * @returns a boolean (true if the registration and linking is a success, false if it isn't)
	 * 
	 */
	public boolean agendarCita(String rutCliente, String operacion ,int precio, String ciudad, String rutDentista, String fecha,String codigoUnico); 
	/**
	 * 
	 * Obtains all the appointments that the client linked to the id card number (input) has.
	 * @param rut
	 * @returns a string with the data
	 * 
	 */
	public String obtenerDatosCitasAgendadas(String rut);
	/**
	 * 
	 * Obtains all the appointments that the dentist linked to the id card number (input) has.
	 * @param rut
	 * @returns a string with the data
	 */
	public String obtenerDatosCitasAgendadasDentista(String rut);
	/**
	 * 
	 * Pays the clinic and the dentist linked to the apointment linked to the appointment code.
	 * @param codigoUnico
	 * @returns a boolean (true if the payment is a success, false if it isn't)
	 * 
	 */
	public boolean pagarCita(String codigoUnico);
	/**
	 * 
	 * Deletes the appointment linked to the appointment code from the system and any container that it may contain it.
	 * @param codigoUnico
	 * @returns a boolean (true if the deletion is a success, false if it isn't)
	 * 
	 */
	public boolean borrarCita(String codigoUnico);
	/**
	 * 
	 * Obtains the final pay of a dentist linked to the id card number (input).
	 * @param rutDentista
	 * @returns an int, which is the final pay of that dentist
	 * 
	 */
	public int obtenerSueldoFinalDentista(String rutDentista);
	/**
	 * 
	 * Changes the state of a clinic (the one linked to the input city) from clean to dirty.
	 * @param ciudad
	 * @returns a boolean (true if the change of state was a success, false if it wasn't)
	 * 
	 */
	public boolean detectarClienteCovid(String ciudad);
	/**
	 * 
	 * Obtains the total monetary gain per clinic in the system.
	 * @return a string with the monetary gains per clinic in the system
	 * 
	 */
	public String obtenerGananciasTotalesClinicas();
	/**
	 * 
	 * Obtains data from each clinic that is clean.
	 * @return returns data from clean clinics
	 * 
	 */
	public String obtenerClinicasHigienizadas();
	/**
	 * 
	 * Changes the state of a clinic (the one linked to the input city) from dirty to clean.
	 * @param ciudad
	 * @returns a boolean (true if the change of state was a success, false if it wasn't)
	 * 
	 */
	public boolean higienizarClinica (String ciudad); 
	/**
	 * 
	 * Obtains the final pay per dentist in the system.
	 * @returns a string with the final pay of each dentist in the system
	 * 
	 */
	public String obtenerSueldosFinalesDentistas();
	/**
	 * 
	 * Obtains data from each clinic that is dirty.
	 * @returns returns data from dirty clinics
	 */
	public String obtenerClinicasInsalubres();
	/**
	 * Obtains data from all clients in the system.
	 * @returns a string with all the data of the clients in the system
	 */
	public String obtenerTxtClientes();
	/**
	 * 
	 * Obtains the data of all the appointments in the system.
	 * @returns a string with all the data of the appointments in the system
	 * 
	 */
	public String obtenerTxtCitas();
	/**
	 * 
	 * Obtains the data of all the dentists in the system.
	 * @return a string with all the data of the dentists in the system
	 * 
	 */
	public String obtenerTxtDentistas();
	/**
	 * 
	 * Obtains the data of all the clinics in the system.
	 * @return a string with all the data of the clinics in the system
	 * 
	 */
	public String obtenerTxtClinicas();
	
}
