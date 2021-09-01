package space_girls;


/**
 * Appointmet class
 * @author a_hognose_snake
 * @author dorime_a1
 */
public class Cita {
  private String codigo;
  private String procedimiento;
  private int precioP;
  private String fecha;
  private Clinica clinica;
  private Dentista dentista;
  private Cliente cliente;

  public Cita(String codigo, String procedimiento, int precioP, String fecha, Clinica clinica, Dentista dentista,
      Cliente cliente) {

    this.codigo = codigo;
    this.procedimiento = procedimiento;
    this.precioP = precioP;
    this.fecha = fecha;
    this.clinica = clinica;
    this.dentista = dentista;
    this.cliente = cliente;
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getProcedimiento() {
    return procedimiento;
  }

  public void setProcedimiento(String procedimiento) {
    this.procedimiento = procedimiento;
  }

  public int getPrecioP() {
    return precioP;
  }

  public void setPrecioP(int precioP) {
    this.precioP = precioP;
  }

  public String getFecha() {
    return fecha;
  }

  public void setFecha(String fecha) {
    this.fecha = fecha;
  }

  public Clinica getClinica() {
    return clinica;
  }

  public void setClinica(Clinica clinica) {
    this.clinica = clinica;
  }

  public Dentista getDentista() {
    return dentista;
  }

  public void setDentista(Dentista dentista) {
    this.dentista = dentista;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  @Override
  public String toString() {	
		return "FECHA: "+fecha+", CODIGO: "+codigo+" PROCEDIMIENTO: "+procedimiento+", VALOR: $"+precioP+"\n";
	}
	
}
