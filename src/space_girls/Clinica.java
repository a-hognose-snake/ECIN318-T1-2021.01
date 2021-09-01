package space_girls;

/**
 * Clinic class
 * @author a_hognose_snake
 * @author dorime_a1
 *
 */
public class Clinica {
	private String ciudad;
	private int ganancia;
	private String estado;
	private ListaDentistas ld;
	private ListaCitas lista_citas;
	private ListaClientes lc;
	
	public Clinica(String ciudad, int ganancia, String estado,int max) {
		
		this.ciudad = ciudad;
		this.ganancia = ganancia;
		this.estado = estado;
		ld = new ListaDentistas(max);
		lista_citas = new ListaCitas(max);
		lc = new ListaClientes(max);
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
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public ListaDentistas getLd() {
		return ld;
	}
	public void setLd(ListaDentistas ld) {
		this.ld = ld;
	}
	public ListaCitas getLista_citas() {
		return lista_citas;
	}
	public void setLista_citas(ListaCitas lista_citas) {
		this.lista_citas = lista_citas;
	}
	public ListaClientes getLc() {
		return lc;
	}
	public void setLc(ListaClientes lc) {
		this.lc = lc;
	}
	public void sumarGanancia(double cant) {
		this.ganancia+=cant;
	}
	public String toString() {	
		return "CIUDAD: "+ciudad+", ESTADO: "+estado+" GANANCIAS: $"+ganancia+"\n";
	}
}

