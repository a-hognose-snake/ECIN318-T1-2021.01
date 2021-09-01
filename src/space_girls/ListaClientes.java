package space_girls;


/**
 * Container of the clients
 * @author a_hognose_snake
 * @author dorime_a1
 *
 */
public class ListaClientes {
	private int max;
	private int cantidad;
	private Cliente [] lista;

	public ListaClientes(int max) {
		this.max = max;
		cantidad = 0;
		lista = new Cliente[max];
	}
	
	public boolean agregarCliente( Cliente x) {
		if (cantidad< max) {
			lista[cantidad]=x;
			cantidad++;
			return true;
		}
		else {
		return false;
		}
	 }
	public Cliente buscarCliente(String rutCliente) {
		 for (int i =0; i<cantidad;i++) {
			 if(lista[i].getRut().equals(rutCliente)) {
				 return lista[i];
			 }
		 }
		 return null;
	 }
	public int getCant() {
		return cantidad;
	}
	public Cliente getElemtol( int index) {
		return lista[index];
	}

	@Override
	public String toString() {
	String text = "";
	for (int i = 0; i < cantidad; i++){
		Cliente x = lista[i];
		text += x.toString()+"\n";	
	}
	return text;
	}
	
	}
	
	


