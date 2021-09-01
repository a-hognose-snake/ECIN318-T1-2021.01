package space_girls;


/**
 * Client class
 * @author a_hognose_snake
 * @author dorime_a1
 *
 */
public class Cliente {
	private String nombre;
	private String apellido;
	private String rut;
	private String contrasena;
	private String isapre;
	private String ciudad;
	private ListaCitas lista_citas;
	public Cliente(String nombre, String apellido, String rut, String contrasena, String isapre, String ciudad, int max) {
	
		this.nombre = nombre;
		this.apellido = apellido;
		this.rut = rut;
		this.contrasena = contrasena;
		this.isapre = isapre;
		this.ciudad = ciudad;
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
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getIsapre() {
		return isapre;
	}
	public void setIsapre(String isapre) {
		this.isapre = isapre;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public ListaCitas getLista_citas() {
		return lista_citas;
	}
	public void setLista_citas(ListaCitas lista_citas) {
		this.lista_citas = lista_citas;
	}
	public String toString() {	
		return "NOMBRE: "+nombre+", APELLIDO: "+apellido+" RUT: "+rut+", CIUDAD: "+ciudad+", SISTEMA DE SALUD: "+isapre+"\n";
	}
	
}
