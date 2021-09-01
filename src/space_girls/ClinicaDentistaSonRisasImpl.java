package space_girls;


/**
 * Implementation of the interface
 * @author a_hognose_snake
 * @author dorime_a1
 *
 */
public class ClinicaDentistaSonRisasImpl implements ClinicaDentistaSonRisas {
	private ListaClinicas lista_clinicas;
	private ListaCitas lista_citas;
	private ListaDentistas ld;
	private ListaClientes lc;
	
	public ClinicaDentistaSonRisasImpl(){
		lista_clinicas = new ListaClinicas(1000);
		lista_citas = new ListaCitas(1000);
		ld = new ListaDentistas(1000);
		lc = new ListaClientes(1000);
	}
	
	
	@Override
	public boolean validarCliente(String rutInput) {
		Cliente c = lc.buscarCliente(rutInput);
		if (c != null) {
				return true;}
			
	return false ;
	}
					

	@Override
	public boolean validarDentista(String rutInput) {
		Dentista d= ld.buscarDentista(rutInput);
		if(d != null){
		
			return true;
		}
	return false;
	 
	}

	@Override
	public boolean validarAccesoCliente(String rutCliente, String contrasena) {//revisar
		Cliente c=lc.buscarCliente(rutCliente);
		if(c!=null) {
			String pass= c.getContrasena();
			if(pass.equals(contrasena)) {
				System.out.println("ingreso correcto");
				return true;
			}else {
				System.out.println("contrase�a incorrecta");
				return false;
			}
		}else 
			if(rutCliente.equals("ADMIN")) {//si es ADMIN
				if(contrasena.equals("ADMIN")) {
			System.out.println("INGRESO CORRECTO DE ADMIN");
			return true;
			}
			}
		return false;
	}

	@Override
	public boolean validarAccesoDentista(String rutDentista, String contrasena) {
		Dentista d=ld.buscarDentista(rutDentista);
		if(d!=null) {
			String pass= d.getContrasena();
			if(pass.equals(contrasena)) {
				System.out.println("ingreso correcto");
				return true;
			}else {
				System.out.println("contrasena incorrecta");
				return false;
			}
		}else 
			if(rutDentista.equals("ADMIN")) {//si es ADMIN
				if(contrasena.equals("ADMIN")) {
			System.out.println("INGRESO CORRECTO DE ADMIN");
			return true;
			}
			}
		return false;
	}

	@Override
	public boolean ingresarClinica(String ciudad, int totalGanancia, String estado) {
		Clinica c= lista_clinicas.buscarClinica(ciudad);
		
		if(c==null) {
			Clinica clinica= new Clinica(ciudad, totalGanancia, estado, 1000);//revisar max
			
			return lista_clinicas.agregarClinica(clinica);
		}
		return false;
	}

	@Override
	public boolean ingresarDentista(String nombre, String apellido, String rutDentista, String contrasena,
			int sueldoBase, int totalComisiones, String ciudad, int anosExperiencia) {
		Clinica c= lista_clinicas.buscarClinica(ciudad);
		if(c!=null && c.getLd().getCant()<10 ) {
			Dentista d= new Dentista(nombre, apellido, rutDentista, contrasena, sueldoBase, totalComisiones, ciudad, anosExperiencia, 10000);
			ld.agregarDentista(d);
			c.getLd().agregarDentista(d);
			return true;
		}else {
			throw new NullPointerException("no existe la clinica asociada o se supera el limite maximo de dentistas");
		}
	}

	@Override
	public boolean ingresarCliente(String nombre, String apellido, String rutCliente, String contrasena,
			String sistemaSalud, String ciudad) {
		Cliente c = new Cliente(nombre, apellido, rutCliente, contrasena, sistemaSalud, ciudad, 10000);
		lc.agregarCliente(c);
		return true;
			
	}

	public boolean agendarCita(String rutCliente, String operacion, int precio, String ciudad, String rutDentista, String fecha,String codigoUnico) {
		Cliente c= lc.buscarCliente(rutCliente);
		Dentista d = ld.buscarDentista(rutDentista);
		Clinica clinica= lista_clinicas.buscarClinica(ciudad);
		if(c!=null && d!=null && clinica!=null) {
			if(c.getCiudad().equals(ciudad) && d.getCiudad().equals(ciudad)) {
				if(clinica.getEstado().equals("Higienizada")) {
					Cita cita = new Cita(codigoUnico, operacion, precio, fecha, clinica, d, c) ;
					if (lista_citas.agregarCita(cita) && clinica.getLista_citas().agregarCita(cita) && d.getLista_citas().agregarCita(cita) && c.getLista_citas().agregarCita(cita)) {
						return true;
					} 
					else {
						return false;

					}
				}
			}
		}
		else {
			throw new NullPointerException("No se pudo agendar la cita. No esta registrada la clinica, el cliente o el dentista o no se encuentra higienizada la clinica.");
		}
		return false;
		
	}

	@Override
	public String obtenerDatosCitasAgendadas(String rut) {
		String text = "";
		Cliente c = lc.buscarCliente(rut);
		if(c != null) {
			text += "CITAS DE "+c.getNombre()+" "+c.getApellido()+": \n\n";
			text +=	c.getLista_citas().toString();
			int cant = c.getLista_citas().getCant();
			if (cant == 0) {
				throw new NullPointerException("No hay citas agendadas por el cliente.\n\n");
			} 
			else {
				return text;
			}
		}
		else {
			throw new NullPointerException("No exite cliente con ese rut.");
		}
	}
	@Override
	public String obtenerDatosCitasAgendadasDentista(String rut) {
		String text = "";
		Dentista d = ld.buscarDentista(rut);
		if(d != null) {
			text += "CITAS DE DR. "+d.getNombre()+" "+d.getApellido()+": \n\n";
			text +=	d.getLista_citas().toString();
			int cant = d.getLista_citas().getCant();
			if (cant == 0) {
				throw new NullPointerException("No hay citas en la agenda del dentista.\n\n");
			} 
			else {
				return text;
			}
		}
		else {
			throw new NullPointerException("No exite dentista con ese rut.");
		}
	}
	
	

	@Override
	public boolean pagarCita(String codigoUnico) {
		Cita c= lista_citas.buscarCita(codigoUnico);
		if(c!= null) {
			if(c.getCliente().getIsapre().equals("Fonasa")) {
				int operacion = c.getPrecioP();
				int pago = (int) (operacion*0.8);
				int pagoDentista = (int) (pago*0.1);
				int pagoFonasa = pago-pagoDentista;
				c.getClinica().sumarGanancia(pagoFonasa);
				c.getDentista().sumarComision(pagoDentista);

				return true;
			}
			else {
				if(!c.getCliente().getIsapre().equals("Fonasa")) {
					int operacion = c.getPrecioP();
					int pago = (int) (operacion*0.7);
					int pagoDentista = (int) (pago*0.1);
					int pagoIsapre = pago-pagoDentista;
					c.getClinica().sumarGanancia(pagoIsapre);
					c.getDentista().sumarComision(pagoDentista);
					
					return true;
				}
			}
		}
		else {
			throw new NullPointerException("No existe cita registrada.");
		}
		
		
		return false;
	}

	@Override
	public boolean borrarCita(String codigoUnico) {
		Cita c= lista_citas.buscarCita(codigoUnico);//buscar cita
		if(c!=null) {
			lista_citas.eliminarCita(codigoUnico);
			if (c.getClinica().getLista_citas().eliminarCita(codigoUnico) && c.getDentista().getLista_citas().eliminarCita(codigoUnico) && c.getCliente().getLista_citas().eliminarCita(codigoUnico)) {
				return true;
			} 
			else {
				return false;
			}
		}
		else {
			throw new NullPointerException("No existe la cita.");
		}
	}

	@Override
	public int obtenerSueldoFinalDentista(String rutDentista) {
		Dentista dentista=ld.buscarDentista(rutDentista);
		if(dentista!=null) {
			
			int sueldoFinal=(int) ((dentista.getSueldo()*(dentista.getExperiencia()*1.1))+dentista.getGanancia());
			return sueldoFinal;
		}
		else {
			throw new NullPointerException("El dentista no se encuentra en los registros");
		}
	}
	
	public String obtenerClinicasHigienizadas() {
		String text = "";
		int cantidad = lista_clinicas.getCant();
		for (int i = 0; i < cantidad; i++) {
			Clinica clinica = lista_clinicas.getElemtol(i);
			if (clinica.getEstado().equals("Higienizada")) {
				text += clinica.toString()+"\n";
			} 
		}
		if (text.equals("")) {
			text =" No hay clinicas higienizadas.";
			return text;
		} 
		else {
			return text;
		}
		
	}

	@Override
	public boolean detectarClienteCovid(String ciudad) {
		Clinica c= lista_clinicas.buscarClinica(ciudad);
		if(c!=null && c.getEstado().equals("Higienizada")) {
			c.setEstado("Insalubre");
			return c.getLista_citas().borrarCitas();
		}else {
			throw new NullPointerException("No esta registrada la clinica");
		}
	}

	@Override
	public String obtenerGananciasTotalesClinicas() {
		String text = "";
		for(int i = 0; i<lista_clinicas.getCant();i++) {
			Clinica c = lista_clinicas.getElemtol(i);
			String ciudad=lista_clinicas.getElemtol(i).getCiudad();
			text += "La clinica en "+ciudad+" ha recaudado $"+c.getGanancia()+"\n";
		}
		return text;
	}
	public String obtenerSueldosFinalesDentistas() {
		String text = "";
		int cantidad = ld.getCant();
		for (int i = 0; i < cantidad; i++) {
			Dentista d = ld.getElemtol(i);
			String rut = d.getRut();
			int sueldoFinal = (int) obtenerSueldoFinalDentista(rut);
			text += "NOMBRE: "+d.getNombre()+" "+d.getApellido()+" RUT: "+d.getRut()+" SUELDO FINAL: $"+sueldoFinal+"\n";	
		}
		if (text.equals("")) {
			text = "No hay dentistas.\n";
			return text;
		} 
		else {
			return text;
		}
	}

	@Override
	public boolean higienizarClinica(String ciudad) {
		Clinica c = lista_clinicas.buscarClinica(ciudad);
		if(c!= null && c.getEstado().equals("Insalubre")) {
			c.setEstado("Higienizada");
			return true;
		}else {
			throw new NullPointerException("No existe la clinica en los registros o la clinica esta higienizada");
		}
		
	}
	public String obtenerClinicasInsalubres() {
		String text = "";
		int cantidad = lista_clinicas.getCant();
		for (int i = 0; i < cantidad; i++) {
			Clinica clinica = lista_clinicas.getElemtol(i);
			if (clinica.getEstado().equals("Insalubre")) {
				text += clinica.toString()+"\n";
			} 
		}
		if (text.equals("")) {
			throw new NullPointerException("No hay clinicas Insalubres.");
		} 
		else {
		return text;
		}
	}

	@Override
	public String obtenerTxtClientes() {
		int cantidad = lc.getCant();
		String text = "";
		for (int i = 0; i < cantidad; i++) {
			Cliente cliente = lc.getElemtol(i);
			if (i == cantidad - 1) {
				text += cliente.getNombre()+","+cliente.getApellido()+","+cliente.getRut()+","+cliente.getContrasena()+","+cliente.getIsapre()+","+cliente.getCiudad();
			} 
			else {
				text += cliente.getNombre()+","+cliente.getApellido()+","+cliente.getRut()+","+cliente.getContrasena()+","+cliente.getIsapre()+","+cliente.getCiudad()+"\n";
			}
		}
		return text;
	}

	@Override
	public String obtenerTxtCitas() {
		int cantidad = lista_citas.getCant();
		String text = "";
		for (int i = 0; i < cantidad; i++) {
			Cita cita = lista_citas.getElemtol(i);
			if (i == cantidad - 1) {
				text += cita.getCliente().getRut()+","+cita.getProcedimiento()+","+cita.getClinica().getCiudad()+","+cita.getDentista().getRut()+","+cita.getFecha()+","+cita.getCodigo();
			} 
			else {
				text += cita.getCliente().getRut()+","+cita.getProcedimiento()+","+cita.getClinica().getCiudad()+","+cita.getDentista().getRut()+","+cita.getFecha()+","+cita.getCodigo()+"\n";
			}
		}
		return text;
	}


	@Override
	public String obtenerTxtDentistas() {
		int cantidad = ld.getCant();
		String text = "";
		for (int i = 0; i < cantidad; i++) {
			Dentista d = ld.getElemtol(i);
			if (i == cantidad - 1) {
				text += d.getNombre()+","+d.getApellido()+","+d.getRut()+","+d.getContrasena()+","+d.getSueldo()+","+d.getGanancia()+","+d.getCiudad()+","+d.getExperiencia();
			} 
			else {
				text += d.getNombre()+","+d.getApellido()+","+d.getRut()+","+d.getContrasena()+","+d.getSueldo()+","+d.getGanancia()+","+d.getCiudad()+","+d.getExperiencia()+"\n";
			}
		}
		return text;
	}


	@Override
	public String obtenerTxtClinicas() {
		int cantidad = lista_clinicas.getCant();
		String text = "";
		for (int i = 0; i < cantidad; i++) {
			Clinica c = lista_clinicas.getElemtol(i);
			if (i == cantidad - 1) {
				text += c.getCiudad()+","+c.getGanancia()+","+c.getEstado();
			} 
			else {
				text += c.getCiudad()+","+c.getGanancia()+","+c.getEstado()+"\n";
			}
		}
		return text;
	}
}


