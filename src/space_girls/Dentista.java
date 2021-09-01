package space_girls;


/**
 * Dentist class
 * @author a_hognose_snake
 * @author dorime_a1
 *
 */
public class Dentista {
	private String nombre;
	private String apellido;
	private String rut;
	private String contrasena;
	private int sueldo;//sueldo base
	private int ganancia;//comisiones
	private String ciudad;
	private int experiencia;
	private ListaCitas lista_citas;
	public Dentista(String nombre, String apellido, String rut,String contrasena, int sueldo, int ganancia, String ciudad,
			int experiencia, int max) {
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.rut = rut;
		this.contrasena=contrasena;
		this.sueldo = sueldo;
		this.ganancia = ganancia;
		this.ciudad = ciudad;
		this.experiencia = experiencia;
		lista_citas = new ListaCitas(max);
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public int getSueldo() {
		return sueldo;
	}
	public void setSueldo(int sueldo) {
		this.sueldo = sueldo;
	}
	public int getGanancia() {
		return ganancia;
	}
	public void setGanancia(int ganancia) {
		this.ganancia = ganancia;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public int getExperiencia() {
		return experiencia;
	}
	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}
	public ListaCitas getLista_citas() {
		return lista_citas;
	}
	
	@Override
	public String toString() {
		return "NOMBRE: " + nombre + ", APELLIDO: " + apellido + ", RUT: " + rut + ", SUELDOS: " + sueldo
				+ ", GANANCIA: " + ganancia + ", CIUDAD: " + ciudad + ", AÑOS DE EXPERIENCIA: "+experiencia+"\n";
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public void sumarComision(double cant) {
		this.ganancia+=cant;
	}
}
