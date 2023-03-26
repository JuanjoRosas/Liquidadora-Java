import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
  private Cargo cargoRevision;

  /**
   * Empleado al cual se le va a generar el informe de nómina
   */
  private Empleado empleadoInforme;

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

  /**
   * Devuelve el objeto Empleado que representa al empleado sobre el que se va a realizar el informe de nómina
   * @return Objeto Empleado que representa al empleado sobre el que se va a realizar el informe.
   */
  public Empleado getEmpleadoInforme() {
    return empleadoInforme;
  }
  
  /**
   * Asgina al atributo cargoVerificación el cargo con el valor pasado por parámetro.
   * @param pCargo Valor del cargo buscado.
   * @throws CargoException Cuando no se encontró el cargo con el valor pasado por parametro.
   */
  public void setCargoRevision(String pCargo) throws CargoException{
    try {
      cargoRevision = Cargo.valueOf(Cargo.class, pCargo);
    } catch (Exception e) {
      throw new CargoException("No se pudo encontrar el cargo '" + pCargo + "'.");
    }
  }

  /**
   * Asigna el valor del atributo interno empleadoInforme por aquel que se encuentra dentro de la lista de empleados y posea la identificación pasada por parametro.
   * El atributo tendra valor null si no se encuentra ningun empleado con identificación pId.
   * @param pId Identificación del empleado buscado para ser asignado al atributo.
   */
  public void setEmpleadoInforme(String pId){
    empleadoInforme = buscarEmpleado(pId);
  }

  /**
   * Asigna el mes de la nómina
   * @param pMes Mes deseado
   * @return El mes asignado
   * @throws FechaException Si no es un mes válido
   */
  public void setMesNomina(String pMes) throws FechaException{
    nomina.setMesLiquidacion(pMes);
  }


  /**
   * Verifica que el salario pasado por parámetro se encuentre dentro de los rangos del cargo de verificación
   * @param pSalario Salario que se desea verificar.
   * @throws CargoException Cuando el salario no se encuentra en los rangos.
   */
  public void verificarSalarioCargo(double pSalario) throws CargoException{
    DecimalFormat f = new DecimalFormat("$###,###,###.00 COP");
    double inferior = cargoRevision.getSalarioInf();
    double superior = cargoRevision.getSalarioSup();
    if(pSalario < inferior || pSalario > superior)
      throw new CargoException("El salario " + f.format(pSalario) + " no se encuentra dentro del rango de salario válido para el cargo de " + cargoRevision.getNombre() + " ( "+ f.format(inferior) + " a "+ f.format(superior) + " ).");
  }

  /**
   * Agrega un empleado con los atributos pasados por parametro a la lista de empleados
   * @param pNombre Nombre del empleado.
   * @param pId Identificación del empleado
   * @param pSalario Salario del empleado.
   * @param pFechaIngreso Fecha de ingreso del empleado.
   */
  public void AgregarEmpleado(String pNombre, String pId, double pSalario, Date pFechaIngreso){
      Empleado agregar = new Empleado(pNombre, pId, cargoRevision, pSalario, pFechaIngreso);
      empleados.add(agregar);
  }

  /**
   * Busca un empleado por Id
   * @param pId Id de busqueda
   * @return Empleado buscado, null si no lo encuentra.
   */
  public Empleado buscarEmpleado(String pId) {
    Empleado retorno = null;
    for (int i = 0; i < empleados.size() && retorno == null; i++) {
      if(empleados.get(i).getId().equals(pId))
        retorno = empleados.get(i);
    }
    return retorno;
  }

  /**
   * Realiza el calculo de la nómina para el empleado informe segun los atributos estipulados de nómina.
   * @throws FechaException Si aún no se ha escogido fecha de liquidación o si la fecha de ingreso del empleado es posterior al mes de liquidación.
   * @throws Exception Si no se ha inicializado el empleado informe.
   */
  public void calcularNominaEmpleado()throws FechaException, Exception{
    SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
    Mes mes = nomina.getMesLiquidacion();
    if(empleadoInforme == null)
      throw new Exception("No se pudó calcular la nomina pues no se ha seleccinado un empleado");

    if(mes == null)
      throw new FechaException("No se pudó calcular la nomina pues no se ha seleccionado un mes para el cálculo.");

    if(!verificarFechaEmpleadoInforme())
      throw new FechaException("El empleado '" + empleadoInforme.getNombre() +"' tiene una fecha de ingreso ("+ dateF.format(empleadoInforme.getFechaIngreso()) +") posterior al mes que se desea liquidar (" + dateF.format(mes.getFinalMes()) +").");
    
    nomina.calcularLiquidacion(empleadoInforme.getFechaIngreso(), empleadoInforme.getSalarioOrdinario());
  }

  
  public void generarInformeUnico(String pRuta) throws InformeException{
    SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat f = new DecimalFormat("$###,###,###.00 COP");
    Mes mes = nomina.getMesLiquidacion();

    String nombreArchivo = "Liq" + mes.getNombreMes() +"-" + empleadoInforme.getNombre() + "_" + empleadoInforme.getId() + ".txt";
    String rutaCompleata = pRuta + "/" + nombreArchivo;

    try {
      FileWriter out = new FileWriter(rutaCompleata);
      PrintWriter log = new PrintWriter(out, true);
      String msg = "DATOS PERSONALES:"
      + "\n Nombre " + empleadoInforme.getNombre()
      + "\n Id: " + empleadoInforme.getId()
      + "\n Cargo:" + empleadoInforme.getCargo().getNombre()
      + "\n Salario ordinal: " + f.format(empleadoInforme.getSalarioOrdinario())
      + "\n Fecha ingreso: " + dateF.format(empleadoInforme.getFechaIngreso())
      + "\n"
      + "\nDATOS LIQUIDACIÓN:"
      + "\n Salario pagado: " + f.format(nomina.getSalario())
      + "\n Auxilio transporte: " + f.format(nomina.getAuxTransporte())
      + "\n Aporte salud: " + f.format(nomina.getAporteSalud())
      + "\n Aporte pensión: " + f.format(nomina.getAportePension())
      + "\n Cesantias: " + f.format(nomina.getCesantias())
      + "\n Intereses cesantias: " + f.format(nomina.getInteresCesantias())
      + "\n Prima: " + f.format(nomina.getPrima())
      + "\n TOTAL LIQUIDACIÓN: " + f.format(nomina.getLiquidacion())
      + "OSO ME LO RECONTRA MAMA BIEN PERO BIEN RICO HP MALPARIDO OJALA DARLE POR ESE CULITO PELUDO";
      
      log.print(msg);
      log.close( );
      out.close( );
    } catch (Exception e) {
      throw new InformeException("Ocurrio un error tratando de crear el archivo - '" + e.getMessage());
    }
  }

  public void generarInformesTotales(String pRuta) throws InformeException, Exception{
    SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat f = new DecimalFormat("$###,###,###.00 COP");
    Mes mes = nomina.getMesLiquidacion();

    int totalEmpleadosActivos = 0;
    double totalSalarios = 0;
    double totalAuxiliosT = 0;
    double totalAportesSalud = 0;
    double totalAportesPension = 0;
    double totalCesantias = 0;
    double totalInteresesCesantias = 0;
    double totalPrimas = 0;
    double totalLiquidaciones = 0;


    for (Empleado empleado : empleados) {
      empleadoInforme = empleado;
      try {
        calcularNominaEmpleado();
        
        double salarioPagado = nomina.getSalario();
        double auxilioT = nomina.getAuxTransporte();
        double aporteS = nomina.getAporteSalud();
        double aporteP = nomina.getAportePension();
        double cesantia = nomina.getCesantias();
        double interesCesantia = nomina.getInteresCesantias();
        double prima = nomina.getPrima();
        double liquidacion = nomina.getLiquidacion();

        totalEmpleadosActivos++;
        totalSalarios += salarioPagado;
        totalAuxiliosT += auxilioT;
        totalAportesSalud += aporteS;
        totalAportesPension += aporteP;
        totalCesantias += cesantia;
        totalInteresesCesantias += interesCesantia;
        totalPrimas += prima;
        totalLiquidaciones += liquidacion;

        String nombreArchivo = "Liq" + mes.getNombreMes() +"-" + empleadoInforme.getNombre() + "_" + empleadoInforme.getId() + ".txt";
        String rutaCompleata = pRuta + "/" + nombreArchivo;

        FileWriter out = new FileWriter(rutaCompleata);
        PrintWriter log = new PrintWriter(out, true);

        String msg = "DATOS PERSONALES:"
        + "\n Nombre " + empleadoInforme.getNombre()
        + "\n Id: " + empleadoInforme.getId()
        + "\n Cargo:" + empleadoInforme.getCargo().getNombre()
        + "\n Salario ordinal: " + f.format(empleadoInforme.getSalarioOrdinario())
        + "\n Fecha ingreso: " + dateF.format(empleadoInforme.getFechaIngreso())
        + "\n"
        + "\nDATOS LIQUIDACIÓN:"
        + "\n Salario pagado: " + f.format(salarioPagado)
        + "\n Auxilio transporte: " + f.format(auxilioT)
        + "\n Aporte salud: " + f.format(aporteS)
        + "\n Aporte pensión: " + f.format(aporteP)
        + "\n Cesantias: " + f.format(cesantia)
        + "\n Intereses cesantias: " + f.format(interesCesantia)
        + "\n Prima: " + f.format(prima)
        + "\n TOTAL LIQUIDACIÓN: " + f.format(liquidacion);

        log.print(msg);
        log.close( );
        out.close( );

      } catch (FechaException e) {
        //No hay que hacer nada solo no se genera el informe de esa persona.
      } catch (IOException e){
        throw new InformeException("Ocurrio un error tratando de crear el archivo - '" + e.getMessage());
      }      
    }

    String nombreArchivo = "Liq" + mes.getNombreMes() +"-TOTAL.txt";
    String rutaCompleata = pRuta + "/" + nombreArchivo;

    FileWriter out = new FileWriter(rutaCompleata);
    PrintWriter log = new PrintWriter(out, true);

    String msg = "DATOS GENERALES:"
    + "\n Total empleados activos: " + totalEmpleadosActivos
    + "\n"
    + "\nDATOS LIQUIDACIÓN:"
    + "\n Total salarios pagados: " + f.format(totalSalarios)
    + "\n Total auxilios transporte: " + f.format(totalAuxiliosT)
    + "\n Total aportes salud: " + f.format(totalAportesSalud)
    + "\n Total aportes pensión: " + f.format(totalAportesPension)
    + "\n Total cesantias: " + f.format(totalCesantias)
    + "\n Total intereses cesantias: " + f.format(totalInteresesCesantias)
    + "\n Total prima: " + f.format(totalPrimas)
    + "\n TOTAL LIQUIDACIONES: " + f.format(totalLiquidaciones);

    log.print(msg);
    log.close( );
    out.close( );
  }

  /**
   * Verifica que la fecha de ingreso del empleado informe no se encuentre despues que la fecha de calculo de la nómina.
   * empleadoInforme y el mes de la nómina deben estar ya inicializados ( != null).
   * @return False si la fecha ingreso es despues y true si no.
   */
  private boolean verificarFechaEmpleadoInforme(){
    if(nomina.getMesLiquidacion().getFinalMes().before(empleadoInforme.getFechaIngreso()))
      return false;
    return true;
  }
}
