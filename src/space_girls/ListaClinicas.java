package space_girls;

/**
 * Container of the clinics
 * @author a_hognose_snake
 * @author dorime_a1
 * 
 */
public class ListaClinicas {
	private int max;
	private int cantidad;
	private Clinica[] lista;
	public ListaClinicas(int max) {
		
		this.max = max;
		cantidad = 0;
		lista = new Clinica[max] ;
	}
	
	public boolean agregarClinica(Clinica clinica) {
		if (cantidad< max) {
			lista[cantidad]=clinica;
			cantidad++;
			return true;
		}else {
		return false;
		}
	 }

	public Clinica buscarClinica(String ciudad) {
	 for (int i =0; i<cantidad;i++) {
		 if(lista[i].getCiudad().equals(ciudad)) {
			 return lista[i];
		 }
	 }
	 return null;
 }

	@Override
	public String toString() {
		String text = "";
		for (int i = 0; i < cantidad; i++){
			Clinica x = lista[i];
			text += x.toString()+"\n";	
		}
		return text;
	}

	public int getCant() {
		return cantidad;
	}
	public Clinica getElemtol( int index) {
		return lista[index];
	}
}
