import java.util.Date;

/*
 * Esta clase representa a un empleado de la empresa. En este caso se asume que el empleado
 * no tiene ha tenido ascensos y no da la posibilidad de otorgarle uno tampoco.
 */
public class Empleado {
  //---------------------------------------------------------
  //ATRIBUTOS
  //---------------------------------------------------------
  /*
   * Nombre y apellidos de empleado.
   */
  private String nombre;

  /*
   * Cadena de texto con el código de identificación del empleado.
   */
  private String identificacion;

  /*
   * Cargo del empleado.
   */
  private Cargo cargo;

  /*
   * Salario mensual del empleado.
   */
  private double salarioOrdinario;

  /*
   * Fecha en la que ingreso a la compañia
   */
  private Date fechaIngreso;

  //---------------------------------------------------------
  //CONSTRUCTOR
  //---------------------------------------------------------
  /**
   * Crea una instancia de la clase Empleado con los valores pasados por parametro.
   * @param pNombre Cadena de texto que se va a asignar al nombre del empleado.
   * @param pId Cadena de texto que se va a asignar a la identificacion del empleado.
   * @param pCargo Cargo del empleado.
   * @param pSalario Valor numerico que se va a asignar al salario mensual del empleado.
   * @param pFechaIngreso Fecha que se va a asignar a la fecha de ingreso del empleado.
   */
  public Empleado(String pNombre, String pId, Cargo pCargo, double pSalario, Date pFechaIngreso){
    nombre = pNombre;
    identificacion = pId;
    cargo = pCargo;
    salarioOrdinario = pSalario;
    fechaIngreso = pFechaIngreso;
  }

  //---------------------------------------------------------
  //METODOS
  //---------------------------------------------------------
  /**
   * Retorna el nombre del empleado.
   * @return Cadena de texto con el nombre del empleado.
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * Retorna la identificación del empleado.
   * @return Cadena de texto con la identificación del empleado.
   */
  public String getId() {
    return identificacion;
  }

  /**
   * Retorna el cargo del empleado.
   * @return Cargo del empleado.
   */
  public Cargo getCargo() {
    return cargo;
  }

/**
 * Retorna el valor del salario mensual del empleado.
 * @return double con el valor del salario mensual del empleado.
 */
  public double getSalarioOrdinario() {
    return salarioOrdinario;
  }

  /**
   * Retorna la fecha de ingreso del empleado.
   * @return Date con la fecha de ingreso del empleado.
   */
  public Date getFechaIngreso() {
    return fechaIngreso;
  }

}
