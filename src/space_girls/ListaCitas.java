package space_girls;


/**
 * Container of the appointments
 * @author a_hognose_snake
 * @author dorime_a1
 *
 */
public class ListaCitas {
 private int max;
 private int cantidad;
 private Cita [] lista;
 
public ListaCitas(int max) {
	
	this.max = max;
	cantidad = 0;
	lista= new Cita[max];
	
}
 public boolean agregarCita( Cita c) {
	if (cantidad< max) {
		lista[cantidad]=c;
		cantidad++;
		return true;
	}else {
	return false;
	}
 }
 public boolean borrarCitas() {
	 if(cantidad!=0) {
	this.lista = new Cita[max];
	return true;
	}
	 return false;
	 
 }
 public boolean eliminarCita(String codigo){
	int i; 
	for(i=0; i< cantidad; i++){
		if(lista[i].getCodigo().equals(codigo)) {
			break;
		}
	}
	if(i == cantidad){
			return false;
	}
	else { 
		for(int j=i; j<cantidad - 1;j++){
			lista[j] = lista[j+1]; 
		}
		cantidad--; 
		return true;
	}
}
 public Cita buscarCita(String codigo) {
	 for (int i =0; i<cantidad;i++) {
		 if(lista[i].getCodigo().equals(codigo)) {
			 return lista[i];
		 }
	 }
	 return null;
 }
 
@Override
public String toString() {
	String text = "";
	for (int i = 0; i < cantidad; i++){
		Cita x = lista[i];
		text += x.toString()+"\n";	
	}
return text;
}

public int getCant() {
	return cantidad;
}
public Cita getElemtol( int index) {
	return lista[index];
}

}
