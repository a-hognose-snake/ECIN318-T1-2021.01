package space_girls;


/**
 * Container of the dentists
 * @author a_hognose_snake
 * @author dorime_a1
 */
public class ListaDentistas {
	private int max;
	private int cantidad;
	private Dentista [] lista;
	public ListaDentistas(int max) {
		this.max = max;
		cantidad = 0;
		lista = new Dentista[max];
	}
	
	public boolean agregarDentista( Dentista d) {
		if (cantidad< max) {
			lista[cantidad]=d;
			cantidad++;
			return true;
		}else {
		return false;
		}
	 }
	public Dentista buscarDentista(String rutDentista) {
		 for (int i =0; i<cantidad;i++) {
			 if(lista[i].getRut().equals(rutDentista)) {
				 return lista[i];
			 }
		 }
		 return null;
	 }

	@Override
	public String toString() {
		String text = "";
		for (int i = 0; i < cantidad; i++){
			Dentista x = lista[i];
			text += x.toString()+"\n";	
		}
		return text;
	}

	public int getCant() {
		return cantidad;
	}
	public Dentista getElemtol( int index) {
		return lista[index];
		}
	
	
}
