package space_girls;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 * Space Girls: Taller 01
 * @author a_hognose_snake
 * @author dorime_a1
 *
 */
class Main {

	/**
	 * 
	 * Executes the program
	 * @param args
	 * @throws IOException
	 * 
	 */
	public static void main(String[] args) throws IOException {
		ClinicaDentistaSonRisas system = new ClinicaDentistaSonRisasImpl();
		lectura(system);
		iniciarSesion(system);
	}
	
	/**
	 * 
	 * Reads all files and registers data to the system.
	 * @param system
	 * @throws IOException
	 * 
	 */
	private static void lectura (ClinicaDentistaSonRisas system ) throws IOException {
		File file_clinicas = new File("clinicas.txt");
		Scanner  scan_clinicas = new Scanner(file_clinicas);
		System.out.println("************************************************************");
		System.out.println("LECTURA DE ARCHIVOS");
		System.out.println("************************************************************\n");
		while (scan_clinicas.hasNextLine()) {
			String banana = scan_clinicas.nextLine();
			String [] partes = banana.split(",");
			String ciudad = partes[0];
			int gananciaT_clinica = Integer.parseInt(partes[1]);
			String estado = partes[2];
			try {
				if (system.ingresarClinica(ciudad, gananciaT_clinica, estado)) {
					System.out.println("Se ingreso correctamente la clinica.");
				} 
				else {
					System.out.println("[ERROR] : No se pudo ingresar la clinica.");
				}
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		scan_clinicas.close();

		File file_dentistas = new File("dentistas.txt");
		Scanner  scan_dentistas = new Scanner(file_dentistas);
		while (scan_dentistas.hasNextLine()) {
			String banana = scan_dentistas.nextLine();
			String [] partes = banana.split(",");
			String nombre = partes[0];
			String apellido = partes[1];
			String inputRut = partes[2];
			String rut_dentista = cambiarFormato(inputRut);
			String contrasena = partes[3];
			int sueldoBase = Integer.parseInt(partes[4]);
			int comision = Integer.parseInt(partes[5]);
			String ciudad_dentista = partes[6];
			//años de experiencia del dentista
			int experiencia = Integer.parseInt(partes[7]);
			try {
				if (system.ingresarDentista(nombre, apellido, rut_dentista, contrasena, sueldoBase, comision, ciudad_dentista, experiencia)) {
					System.out.println("Se ingreso correctamente al dentista.");
				} 
				else {
					System.out.println("[ERROR] : No se pudo ingresar al dentista.");
				}
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		scan_dentistas.close();
		
		File file_clientes = new File("clientes.txt");
		Scanner scan_clientes = new Scanner(file_clientes);
		while (scan_clientes.hasNextLine()) {
			String banana = scan_clientes.nextLine();
			String [] partes = banana.split(",");
			String nombre = partes[0];
			String apellido = partes[1];
			String rutInput = partes[2];
			String rut_cliente = cambiarFormato(rutInput);
			String contrasena = partes[3];
			String sistemaSalud = partes[4];
			String ciudad_cliente = partes[5];
			try {
				if (system.ingresarCliente(nombre, apellido, rut_cliente, contrasena, sistemaSalud, ciudad_cliente)) {
					System.out.println("Se ingreso correctamente al cliente.");
				} 
				else {
					System.out.println("[ERROR] : No se pudo ingresar al cliente.");
				}
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		scan_clientes.close();
		
		File file_citas = new File("citas.txt");
		Scanner scan_citas = new Scanner(file_citas);
		while (scan_citas.hasNextLine()) {
			String banana = scan_citas.nextLine();
			String [] partes = banana.split(",");
			String rutinput = partes[0];
			String rut_cliente = cambiarFormato(rutinput);
			String procedimiento = partes[1];
			String ciudad_cita = partes[2];
			String rutInput2 = partes[3];
			String rut_dentista = cambiarFormato(rutInput2);
			String fecha_cita = partes[4];
			String codigo_cita = partes[5];
			int precio_procedimiento = estimarProcedimiento(procedimiento);
			try {
				if (system.agendarCita(rut_cliente, procedimiento, precio_procedimiento, ciudad_cita, rut_dentista, fecha_cita, codigo_cita)) {
					System.out.println("Se ingreso correctamente la cita.");
				} 
				else {
					System.out.println("[ERROR] : No se pudo ingresar la cita.");
				}
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		scan_citas.close();
	}
	
	/**
	 * Obtains the monetary value of a input procedure.
	 * @param procedimiento
	 * @returns an in with the monetary value of a procedure (input)
	 * 
	 */
	private static int estimarProcedimiento(String procedimiento) {
		if (procedimiento.equals("Blanqueamiento laser")) {
			return 136000;
		}
		else {
			if (procedimiento.equals("Exodoncia")) {
				return 35000;
			} 
			else {
				if (procedimiento.equals("Limpieza")) {
					return 63000;
				} 
				else {
					if (procedimiento.equals("Radiografia")) {
						return 15000;
					} 
					else {
						if (procedimiento.equals("Fluoracion")) {
							return 55000;	
						} 
						else {
							return 0;
						}

					}

				}

			}

		}
		
	}

	/**
	 * 
	 * Allows the user to log in and obtain information according to its role (client, dentist or admin) or to try again o close the system.
	 * @param system
	 * @throws IOException
	 * 
	 */
	private static void iniciarSesion(ClinicaDentistaSonRisas system ) throws IOException {
		// [1] Cliente, [2] Dentista, [3] Admin, [4] Volver a intentar, [0] Cerrar sistema
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		Random random = new Random();
		int upperBoundRandom = 100000;
		String rutInput = ingresarRut();
		int tipoLogIn = LogIn(system, rutInput);
		while (tipoLogIn == 4) {
			String rut = ingresarRut();
			tipoLogIn = LogIn(system, rut);	
		}
		if (tipoLogIn != 0) {
			if (tipoLogIn == 1) {
				// Menu Cliente
				desplegarMenuCliente();
				System.out.println("[ ] : ");
				while (true) {
					try {
						int operacion = Integer.parseInt(scan.nextLine());
						
						if (operacion == 1) {
							// [1] Ver citas agendadas
							System.out.println(system.obtenerDatosCitasAgendadas(rutInput));
							throw new NullPointerException("");
						}
						
						if (operacion == 2) {
							// [2] Agendar cita
							String rut_cliente = rutInput;
							System.out.print("CIUDAD: ");
				            String ciudad_cliente = scan.nextLine();
				            System.out.print("PROCEDIMIENTO: ");
				            String procedimiento = scan.nextLine();
				            System.out.print("RUT DENTISTA: ");
				            String rut_dentista = cambiarFormato(scan.nextLine());
				            System.out.print("FECHA: ");
				            String fecha = scan.nextLine();
				            int precio = estimarProcedimiento(procedimiento);
				            String codigo_cita = Integer.toString(random.nextInt(upperBoundRandom));
				            system.agendarCita(rut_cliente, procedimiento, precio, ciudad_cliente, rut_dentista, fecha, codigo_cita);	
				            throw new NullPointerException("");
						}
						
						if (operacion == 3) {
							// [3] Completar cita
							System.out.println(system.obtenerDatosCitasAgendadas(rutInput));
							System.out.println("CODIGO DE CITA A PAGAR: ");
							String codigo_cita = scan.nextLine();
							if (system.pagarCita(codigo_cita)) {
								System.out.println("La cita ha sido pagada");
								if (system.borrarCita(codigo_cita)) {
									System.out.println("Se borro la cita del sistema.\n");
								} 
								else {
									System.out.println("No se pudo borrar la cita del sistema.\n");
								}	
							} 
							else {
								System.out.println("La cita no se pudo pagar.");
							}
							throw new NullPointerException("");
						}
						
						if (operacion == 4) {
							// [4] Cancelar cita
						System.out.println(system.obtenerDatosCitasAgendadas(rutInput));
						System.out.println("CODIGO DE LA CITA A CANCELAR: ");
						String codigo_cita = scan.nextLine();
						if (system.borrarCita(codigo_cita)) {
							System.out.println("Cita Cancelada. Se borro la cita del sistema.\n");
						} 
						else {
							System.out.println("No se pudo borrar la cita del sistema.\n");
						}
						throw new NullPointerException("");	
						}
						
						if (operacion == 0) {
							// 0] Cerrar Sistema
							reEscribirTxt(system);
							System.out.println("\n************************************************************");
							System.out.println("SISTEMA CERRADO");
							System.out.println("************************************************************");
							System.exit(0);
						} 
						
						else {
							throw new NumberFormatException();
						}
					} 
					
					catch (NumberFormatException exception) {
						System.out.print("Ingrese una opcion valida.\n");
						System.out.println("[ ] : ");
					}
					
					catch (NullPointerException e) {
			            System.out.print(e.getMessage());
			            desplegarMenuCliente();
			            System.out.println("[ ] : ");
			        }
				}
			}
			
			if (tipoLogIn == 2) {
				// [2] Dentista
				desplegarMenuDentista();
				System.out.println("[ ] : ");
				while (true) {
					try {
						int operacion = Integer.parseInt(scan.nextLine());
						
						if (operacion == 1) {
							// [1] Ver sueldo final
							System.out.println("Sueldo Final: $"+system.obtenerSueldoFinalDentista(rutInput));
							throw new NullPointerException("");	
						}
						
						if (operacion == 2) {
							// [2] Cancelar cita
							System.out.println(system.obtenerDatosCitasAgendadasDentista(rutInput));
							System.out.println("CODIGO DE LA CITA A CANCELAR: ");
							String codigo_cita = scan.nextLine();
							if (system.borrarCita(codigo_cita)) {
								System.out.println("Cita Cancelada por Dentista. Se borro la cita del sistema.\n");
							} 
							else {
								System.out.println("No se pudo borrar la cita del sistema.\n");
							}	
							throw new NullPointerException("");
						}
						
						if (operacion == 0) {
							// [0] Cerrar Sistema
							reEscribirTxt(system);
							System.out.println("\n************************************************************");
							System.out.println("SISTEMA CERRADO");
							System.out.println("************************************************************");
							System.exit(0);
							
						} 
						
						else {
							throw new NumberFormatException();
						}
					} 
					
					catch (NumberFormatException exception) {
						System.out.print("Ingrese una opcion valida.\n");
						System.out.println("[ ] : ");
					}
					
					catch (NullPointerException e) {
						System.out.println(e.getMessage() + "\n");
			            desplegarMenuDentista();
			            System.out.println("[ ] : ");
					}
				}
			}
			
			if (tipoLogIn == 3) {
				// [3] Admin
				desplegarMenuAdmin();
				System.out.println("[ ] : ");
				while (true) {
					try {
						int operacion = Integer.parseInt(scan.nextLine());
						
						if (operacion == 1) {
							// [1] Cliente COVID detectado
							System.out.println(system.obtenerClinicasHigienizadas());
							System.out.println("CIUDAD DEL COVID DETECTADO: ");
							String ciudad = scan.nextLine();
							if (system.detectarClienteCovid(ciudad)) {
								System.out.println("La clinica en "+ciudad+" ha sido clausurada, ya que se ha detectado COVID (estado = Insalubre).");
							} 
							throw new NullPointerException("");	
						}
						
						if (operacion == 2) {
							// [2] Ver sueldo final por dentista
							System.out.println(system.obtenerSueldosFinalesDentistas());
							throw new NullPointerException("");
						}
						
						if (operacion == 3) {
							// 3] Ver info de ganacias por clinica
							System.out.println(system.obtenerGananciasTotalesClinicas());
							throw new NullPointerException("");
						}
						
						if (operacion == 4) {
							// 4] Higienizar clinica
							System.out.println(system.obtenerClinicasInsalubres());
							System.out.println("CIUDAD DE CLINICA A HIGIENIZAR:");
							String ciudad = scan.nextLine();
							if (system.higienizarClinica(ciudad)) {
								System.out.println("La clinica en "+ciudad+" fue exitozamente higienizada.");
							}
							else {
								System.out.println("La clinica en "+ciudad+" no pudo ser higienizada.");
							}
							throw new NullPointerException("");
						}
						
						if (operacion == 5) {
							// [5] Contratar dentista
							System.out.println("************************************************************");
							System.out.println("CONTRATO DEL DENTISTA");
							System.out.println("************************************************************\n");
							System.out.println("NOMBRE DEL DENTISTA: ");
							String nombre = scan.nextLine();
							System.out.println("APELLIDO DEL DENTISTA: ");
							String apellido = scan.nextLine();
							System.out.println("RUT DEL DENTISTA: ");
							String inputRut = scan.nextLine();
							String rut_dentista = cambiarFormato(inputRut);
							System.out.println("CONTRASEÑA DEL DENTISTA: ");
							String contrasena = scan.nextLine();
							System.out.println("SUELDO BASE DEL DENTISTA: ");
							int sueldoBase = Integer.parseInt(scan.nextLine());
							int comision = 0;
							System.out.println("CIUDAD DEL TRABAJO: ");
							String ciudad_dentista = scan.nextLine();
							//años de experiencia del dentista
							System.out.println("AÑOS DE EXPERIENCIA DEL DENTISTA: ");
							int experiencia = Integer.parseInt(scan.nextLine());
							if (system.ingresarDentista(nombre, apellido, rut_dentista, contrasena, sueldoBase, comision, ciudad_dentista, experiencia)) {
								System.out.println("Dentista fue contratado y ingresado al sistema de forma exitosa.\n");
							} 
							else {
								System.out.println("El dentista no fue contratado, ni ingresado al sistema.\n");
							}
							throw new NullPointerException("");
						}
						
						if (operacion == 0) {
							// [0] Cerrar Sistema
							reEscribirTxt(system);
							System.out.println("\n************************************************************");
							System.out.println("SISTEMA CERRADO");
							System.out.println("************************************************************");
							System.exit(0);
						} 
						
						else {
							throw new NumberFormatException();
						}
						
					} 
					catch (NumberFormatException exception) {
						System.out.print("Ingrese una opcion valida.\n");
						System.out.print("[ ] : ");
					}
					catch (NullPointerException e) {
						System.out.println(e.getMessage() + "\n");
			            desplegarMenuAdmin();
			            System.out.println("[ ] : ");
					}
				}
			}
			
			else {
				// [0] Cerrar sistema
				reEscribirTxt(system);
				System.out.println("\n************************************************************");
				System.out.println("SISTEMA CERRADO");
				System.out.println("************************************************************");
				System.exit(0);
			}
		}
	}
	

	/**
	 * Displays the admin menu of options.
	 */
	private static void desplegarMenuAdmin() {
		System.out.println("\n************************************************************");
		System.out.println("MENU ADMIN");
		System.out.println("************************************************************\n");
	    System.out.println("[1] Cliente COVID detectado");
	    System.out.println("[2] Ver sueldo final por dentista");
	    System.out.println("[3] Ver info de ganacias por clinica ");
	    System.out.println("[4] Higienizar clinica");
	    System.out.println("[5] Contratar dentista");
	    System.out.println("[0] Cerrar Sistema");
	}

	/**
	 * Display the dentist menu of options.
	 */
	private static void desplegarMenuDentista() {
		System.out.println("************************************************************");
		System.out.println("MENU DENTISTA");
		System.out.println("************************************************************\n");
	    System.out.println("[1] Ver sueldo final");
	    System.out.println("[2] Cancelar cita");
	    System.out.println("[0] Cerrar Sistema");
	}

	/**
	 * Displays the client menu of options.
	 */
	private static void desplegarMenuCliente() {
		System.out.println("************************************************************");
		System.out.println("MENU CLIENTE");
		System.out.println("************************************************************\n");
	    System.out.println("[1] Ver citas agendadas");
	    System.out.println("[2] Agendar cita");
	    System.out.println("[3] Completar cita");
	    System.out.println("[4] Cancelar cita");
	    System.out.println("[0] Cerrar Sistema");
	}

	
	/**
	 * 
	 * Asks for id card number.
	 * @returns a string with the formated card number
	 * 
	 */
	private static String ingresarRut() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("\n************************************************************");
		System.out.println("INICIAR SESIÓN");
		System.out.println("************************************************************\n");
		System.out.println("RUT: ");
		String rutInput = scan.nextLine();
		rutInput = cambiarFormato(rutInput);
		return rutInput;
	}

	
	/**
	 * 
	 * Changes the card number input format to a universal format.
	 * @param rut
	 * @returns a string with the formated input parameter
	 * 
	 */
	private static String cambiarFormato(String rut) {
		String[] lista = rut.split("");
	    String retorno = "";
	    for (int i = 0; i < lista.length; i++) {
	      if (lista[i].equals(".") || lista[i].equals("-")) {
	      } else {
	        if (lista[i].equalsIgnoreCase("k")) {
	          retorno += "k";
	        } else {
	          retorno += lista[i];
	        }
	      }
	    }
	    return retorno;
	}

	/**
	 * 
	 * Allows to know what type of user is trying to log in and it validates its access to the system or it lets the system know the next action to take.
	 * @param system
	 * @param rutInput
	 * @return an int, that lets us know the type of user ([1] client, [2] dentist, [3] admin, [4] try again, [0] close system)
	 * 
	 */
	private static int LogIn(ClinicaDentistaSonRisas system, String rutInput) {
		// [1] Cliente, [2] Dentista, [3] Admin, [4] Volver a intentar, [0] Cerrar sistema
		int tipoLogIn;
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		boolean accesoInvalido = false;
		
		if (system.validarCliente(rutInput)) {
			// [1] : Cliente
			System.out.println("CONTRASEÑA: ");
			String contrasena = scan.nextLine();
			tipoLogIn = 1;
			if (system.validarAccesoCliente(rutInput, contrasena)) {
				return tipoLogIn;
			} 
			else {
				accesoInvalido = true;
			}
		}
		
		if (system.validarDentista(rutInput)) {
			// [2] Dentista
			System.out.println("CONTRASEÑA: ");
			String contrasena = scan.nextLine();
			tipoLogIn = 2;
			if (system.validarAccesoDentista(rutInput,contrasena)) {
				return tipoLogIn;
			} 
			else {
				accesoInvalido = true;
			}
		}
		
		if (rutInput.equals("ADMIN")) {
			// [3] Admin
			System.out.println("CONTRASEÑA: ");
			String contrasena = scan.nextLine();
			if (!contrasena.equals("ADMIN")) {
				accesoInvalido = true;
			} 
			else {
				tipoLogIn = 3;
				return tipoLogIn;
			}
		}
		
		if (accesoInvalido) {
			System.out.println("[ERROR]: Clave Incorrecta");
		    System.out.println("[4] Volver a intentarlo");
		    System.out.println("[0] Salir del sistema");
		    System.out.println("[ ]:  ");
		    String opcion = scan.nextLine();
		    while (!opcion.equals("4") && !opcion.equals("0")) {
		    	System.out.println("[ERROR]: Opcion incorrecta.");
		    	System.out.println("[4] Volver a intentarlo");
		        System.out.println("[0] Salir del sistema");
		        System.out.println("[ ]:  ");
		        opcion = scan.nextLine();
			}
		    if (opcion.equals("4")) {
		        String rut = ingresarRut();
		        return LogIn(system, rut);
		      } 
		    else {
		        return 0;
		      }	
		}
		
		else {
			String rutIn = ingresarRut();
			return LogIn(system, rutIn);
		}	
	}
	
	/**
	 * 
	 * Obtains data from the system and updates the .txt files.
	 * @param system
	 * @throws IOException
	 * 
	 */
	private static void reEscribirTxt(ClinicaDentistaSonRisas system) throws IOException{
		FileWriter file_clientes = new FileWriter("clientes.txt");
        PrintWriter escritura_clientes = new PrintWriter(file_clientes);
        escritura_clientes.print(system.obtenerTxtClientes());
        escritura_clientes.close();
        file_clientes.close();
        
        FileWriter file_citas = new FileWriter("citas.txt");
        PrintWriter escritura_citas = new PrintWriter(file_citas);
        escritura_citas.print(system.obtenerTxtCitas());
        escritura_citas.close();
        file_citas.close();
        
        FileWriter file_clinicas = new FileWriter("clinicas.txt");
        PrintWriter escritura_clinicas = new PrintWriter(file_clinicas);
        escritura_clinicas.print(system.obtenerTxtClinicas());
        escritura_clinicas.close();
        file_clinicas.close();
        
        FileWriter file_dentistas = new FileWriter("dentistas.txt");
        PrintWriter escritura_dentistas = new PrintWriter(file_dentistas);
        escritura_dentistas.print(system.obtenerTxtDentistas());
        escritura_dentistas.close();
        file_dentistas.close();
	}
}
