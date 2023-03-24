import java.util.Date;

/**
 * Clase que representa a un calculo de Nomina que
 * recibe ciertos parametros para poder ser ejecutado.
 */
public class Nomina {
  //---------------------------------------------------------
  //ATRIBUTOS
  //---------------------------------------------------------
  /**
   * Mes en el que se está realizando el cálculo.
   */
  private Mes mesLiquidacion;

  /**
   * Dias laborados del mes en el que se realiza el cálculo.
   */
  private int diasLaboradosMes;

  /**
   * Dias laborados del semestre en el que se realiza el cálculo.
   * Solo son pertienentes para los calculos de las primas en junio y diciembre.
   */
  private Date diasLaboradosSemestre;

  /**
   * Salario correspondiente a los días laborados del mes en el que se realiza el cálculo.
   */
  private double salario;

  /**
   * Subsidio de transporte correspondiente al salario nominal.
   */
  private double auxilioTransporte;

  /**
   * Valor de las cesantias correspondientes a los días laborados del mes en el que se realiza el cálculo.
   */
  private double cesantias;

  /**
   * Valor del aporte a salud correspondientes al salario pagado.
   */
  private double aporteSalud;

  /**
   * Valor del aporte a pensión correspondiente al salario pagado.
   */
  private double aportePension;

  /**
   * Valor del interes generado por las cesantias pagadas.
   */
  private double interesCesantias;

  /**
   * Valor correspondiente a la prima.
   */
  private double prima;

  /**
   * Valor total de lo que se debe ser pagado por la empresa
   */
  private double liquidacion;

  //---------------------------------------------------------
  //CONSTRUCTOR
  //---------------------------------------------------------
  /**
   * Crea una instancia de la clase e inicializa sus valores con los pasados por parametro.
   * @param pMesLiquidacion Mes que se va a asignar al mes de registro.
   */
  public Nomina(Mes pMesLiquidacion){
    mesLiquidacion = pMesLiquidacion;
  }

  //---------------------------------------------------------
  //METODOS
  //---------------------------------------------------------
  
  /**
   * Devuelve el mes en el que se realiza la liquidación.
   * @return Mes que representa el mes de la liquidación.
   */
  public Mes getMesLiquidacion() {
    return mesLiquidacion;
  }

  /**
   * Devuelve los días laborados del mes.
   * @return Días laborados en el mes.
   */
  public int getDiasLaboradosMes() {
    return diasLaboradosMes;
  }

  /**
   * Devuelve los días laborados en el semestre.
   * @return Días laborados en el semestre.
   */
  public Date getDiasLaboradosSemestre() {
    return diasLaboradosSemestre;
  }

  /**
   * Devuelve el salario pagado correspondiente a los dias laborados del mes.
   * @return Salario correspondiente por los dias laborados.
   */
  public double getSalario() {
    return salario;
  }

  /**
   * Devuelve el auxilio de transporte correspondiente a los días del mes trabajados.
   * @return Valor del auxilio de transporte correspondiente a los días trabajados.
   */
  public double getAuxTransporte() {
    return auxilioTransporte;
  }

  /**
   * Devuelve el valor de las cesantias correspondientes a los días del mes trabajados.
   * @return Valor de las cesantias correspondientes a los días del mes trabajados.
   */
  public double getCesantias() {
    return cesantias;
  }

  /**
   * Devuelve el valor del aporte a salud correspondiente al salario pagado.
   * @return Valor del aporte a salud correspondiente al salario pagado.
   */
  public double getAporteSalud() {
    return aporteSalud;
  }

  /**
   * Devuelve el valor del aporte a pensión correspondiente al salario pagado.
   * @return Valor del aporte a pensión correspondiente al salario pagado.
   */
  public double getAportePension() {
    return aportePension;
  }

  /**
   * Devuelve el valor de los intereses generados sobre las cesantias pagadas.
   * @return Valor de los intereses generados sobre las cesantias pagadas.
   */
  public double getInteresCesantias() {
    return interesCesantias;
  }

  /**
   * Devuelve el valor de la prima correspondiente a los días laborados del semestre.
   * @return Valor de la prima correspondiente a los días laborados del semestre.
   */
  public double getPrima() {
    return prima;
  }

  /**
   * Devuelve el valor que debe liquidar la empresa.
   * @return Valor que debe liquidar la empresa.
   */
  public double getLiquidacion() {
    return liquidacion;
  }

}
