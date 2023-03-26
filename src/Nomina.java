import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Clase que representa a un calculo de Nomina que
 * recibe ciertos parametros para poder ser ejecutado.
 */
public class Nomina {
  //---------------------------------------------------------
  //CONSTANTES
  //---------------------------------------------------------
  /**
   * Todos los días laborales de 2023.
   */
  public final static int DIAS_LAB_2023 = 243;

  /**
   * Salario mínimo para colombia en 2023
   */
  public final static double SALARIO_MINIMO_2023 = 1160000;

  /**
   * Auxilio de transporte en 2023
   */
  public final static double AUXILIO_TRANSPORTE = 140606;

  /**
   * Porcentaje de aporte a salud en 2023
   */
  public final static double PORCENTAJE_SALUD = 0.04;

  /**
   * Porcentaje de aporte a pensión en 2023
   */
  public final static double PORCENTAJE_PENSION = 0.04;

  /**
   * Interes generado sobre las cesantias en 2023
   */
  public final static double INTERES_CESANTIAS = 0.12;

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
  private int diasLaboradosSemestre;

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

  /**
   * Lista de fechas festivas
   */
  private ArrayList<Date> festivos;

  //---------------------------------------------------------
  //CONSTRUCTOR
  //---------------------------------------------------------
  /**
   * Crea una instancia de la clase e inicializa sus valores con los pasados por parametro.
   * @param pMesLiquidacion Mes que se va a asignar al mes de registro.
   */
  public Nomina(){
    festivos = new ArrayList<Date>();
    crearFestivos();
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
  public int getDiasLaboradosSemestre() {
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

  /**
   * Cambia el valor del mes de liquidación por el equivalente al string pasado por parametro.
   * @param pMesLiquidacion String que representa al mes de liquidación.
   */
  public void setMesLiquidacion(String strMes) throws FechaException{
    try {
      mesLiquidacion = Mes.valueOf(Mes.class, strMes.toUpperCase());
    } catch (Exception e) {
      throw new FechaException("El mes '" + strMes + "' no es un valor válido.");
    }
  }

  public void calcularLiquidacion(Date pFechaIngreso, double pSalario){
    calcularDiasLab(pFechaIngreso);
    calcularSalario(pSalario);
    calcularAuxilioT();
    calcularSalud();
    calcularPension();
    calcularCesantias();
    calcularInteresCesantia();
    calcularPrima();
    liquidacion = salario + auxilioTransporte + cesantias + interesCesantias + prima - aporteSalud - aportePension;
  }

  /**
   * Agrega todos los festivos de 2023 a la lista de fechas de festivos.
   */
  private void crearFestivos(){
    ArrayList<String> festivosStr = new ArrayList<String>();
    festivosStr.add("01/01/2023");
    festivosStr.add("09/01/2023");
    festivosStr.add("20/03/2023");
    festivosStr.add("02/04/2023");
    festivosStr.add("06/04/2023");
    festivosStr.add("07/04/2023");
    festivosStr.add("09/04/2023");
    festivosStr.add("01/05/2023");
    festivosStr.add("22/05/2023");
    festivosStr.add("12/06/2023");
    festivosStr.add("19/06/2023");
    festivosStr.add("03/07/2023");
    festivosStr.add("20/07/2023");
    festivosStr.add("07/07/2023");
    festivosStr.add("21/08/2023");
    festivosStr.add("16/10/2023");
    festivosStr.add("06/11/2023");
    festivosStr.add("13/11/2023");
    festivosStr.add("08/12/2023");
    festivosStr.add("25/12/2023");

    SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
    try {
      for (String strDate : festivosStr) {
        festivos.add(dateF.parse(strDate));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Busca la fecha pasada por parametro dentro del arraylist de festivos.
   * @param pDate Fecha que se desea buscar dentro del array.
   * @return True si está dentro del array, False si no.
   */
  private boolean buscarFestivo(Date pDate){
    boolean retorno = false;

    for (int i = 0; i < festivos.size() && !retorno; i++) {
      if(festivos.get(i).compareTo(pDate) == 0)
        retorno = true;
    }

    return retorno;
  }

  /**
   * Calcula los dias laborales entre 2 fechas pasadas por parametro.
   * Cuenta la fecha de inicio y la fecha de final como dias válidos.
   * @param pDateStart Fecha de inicio.
   * @param pDateEnd Fecha de final.
   * @return Días laborales entre las 2 fechas.
   */
  private int diasLabIntl(Date pDateStart, Date pDateEnd){
    Calendar startCal = Calendar.getInstance();
    startCal.setTime(pDateStart);        

    Calendar endCal = Calendar.getInstance();
    endCal.setTime(pDateEnd);

    int diasLab = 0;
    long lastTimeInMilis = endCal.getTimeInMillis();
    Date auxDate = null;

    while (startCal.getTimeInMillis() <= lastTimeInMilis){
      auxDate = startCal.getTime();
      if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && !buscarFestivo(auxDate))
        diasLab++;
      startCal.add(Calendar.DAY_OF_MONTH, 1);
    }

    return diasLab;
  }
  
  /**
   * Inicializa los valores de los atributos de los días laborados en el semestre y en el mes.
   * @param pFechaIngreso Fecha en la que ingreso el empleado sobre el que se va a realizar el calculo a la compañia.
   */
  private void calcularDiasLab(Date pFechaIngreso){
    diasLaboradosSemestre = 0;
    diasLaboradosMes = 0;

    //Calcula dias laborados en semestre solo sí es junio o diciembre
    if(mesLiquidacion ==  Mes.JUNIO || mesLiquidacion == Mes.DICIEMBRE){
      if(pFechaIngreso.before(mesLiquidacion.getInicioSemestre()))
        diasLaboradosSemestre = mesLiquidacion.getDiasLaborablesSemestre();
      else
        diasLaboradosSemestre = diasLabIntl(pFechaIngreso, mesLiquidacion.getFinalMes());
    }

    //Calcula dias laborados en el mes
    if(pFechaIngreso.before(mesLiquidacion.getInicioMes()))
      diasLaboradosMes = mesLiquidacion.getDiasLaborablesMes();
    else
      diasLaboradosMes = diasLabIntl(pFechaIngreso, mesLiquidacion.getFinalMes());
  }

  /**
   * Inicializa el valor del salario a pagar.
   * @param pSalarioMes Salario ordinario de la persona sobre la que se calcula la liquidación.
   */
  private void calcularSalario(double pSalarioMes){
    salario = pSalarioMes * diasLaboradosMes / mesLiquidacion.getDiasLaborablesMes();
  }

  /**
   * Inicializa el valor del auxilio de transporte.
   */
  private void calcularAuxilioT(){
    double salarioMes = salario * mesLiquidacion.getDiasLaborablesMes()/diasLaboradosMes;
    auxilioTransporte = 0;
    if(salarioMes <= 2*SALARIO_MINIMO_2023)
      auxilioTransporte = AUXILIO_TRANSPORTE * diasLaboradosMes / mesLiquidacion.getDiasLaborablesMes();
  }

  /**
   * Inicializa el valor del aporte a la salud.
   */
  private void calcularSalud(){
    aporteSalud = salario * PORCENTAJE_SALUD;
  }

  /**
   * Inicializa el valor del aporte a la salud.
   */
  private void calcularPension(){
    aportePension = salario * PORCENTAJE_PENSION;
  }

  /**
   * Inicializa el valor de las cesantias equivalentes a los dias trabajados.
   */
  private void calcularCesantias(){
    double auxilioMes = auxilioTransporte * mesLiquidacion.getDiasLaborablesMes()/diasLaboradosMes;
    double salarioMes = salario * mesLiquidacion.getDiasLaborablesMes()/diasLaboradosMes;
    cesantias = (auxilioMes + salarioMes) * diasLaboradosMes / DIAS_LAB_2023;
  }

  /**
   * Inicializa el valor del interes generado por las cesantias
   */
  private void calcularInteresCesantia(){
    interesCesantias = cesantias * INTERES_CESANTIAS;
  }

  /**
   * Inicializa el valor de la prima.
   */
  private void calcularPrima(){
    double salarioMes = salario * mesLiquidacion.getDiasLaborablesMes()/diasLaboradosMes;
    prima = salarioMes * diasLaboradosSemestre / DIAS_LAB_2023;
  }

}
