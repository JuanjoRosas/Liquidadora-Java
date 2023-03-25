import java.util.ArrayList;

public class Empresa {
  //---------------------------------------------------------
  //ATRIBUTOS
  //---------------------------------------------------------
  /**
   * Lista de los empleados de la empresa
   */
  private ArrayList<Empleado> empleados;

  /**
   * Calculadora de nomina de la empresa
   */
  private Nomina nomina;

  /**
   * Cargo auxiliar para realizar validaciones
   */

  //---------------------------------------------------------
  //CONSTRUCTOR
  //---------------------------------------------------------
  /**
   * Crea la instancia de la clase Empresa y 
   */
  public Empresa(){
    empleados = new ArrayList<Empleado>();
    nomina = new Nomina();
  }
  
  //---------------------------------------------------------
  //METODOS
  //---------------------------------------------------------
}
