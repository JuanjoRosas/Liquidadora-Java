import java.text.SimpleDateFormat;
import java.util.Date;

public enum Mes {

  //------------------------------------------------
  //Elementos
  //------------------------------------------------
  ENERO("01/01/2023", "01/01/2023", "31/01/2023", 21, 122), 
  FEBRERO("01/02/2023", "01/01/2023", "28/02/2023", 20, 122), 
  MARZO("01/03/2023", "01/01/2023", "31/03/2023", 22, 122), 
  ABRIL("01/04/2023", "01/01/2023", "30/04/2023", 18, 122), 
  MAYO("01/05/2023", "01/01/2023", "31/05/2023", 21, 122), 
  JUNIO("01/06/2023", "01/01/2023", "30/06/2023", 20, 122), 
  JULIO("01/07/2023", "01/07/2023", "31/07/2023", 19, 121), 
  AGOSTO("01/08/2023", "01/07/2023", "31/08/2023", 21, 121), 
  SEPTIEMBRE("01/09/2023", "01/07/2023", "30/09/2023", 21, 121), 
  OCTUBRE("01/10/2023", "01/07/2023", "31/10/2023", 21, 121), 
  NOVIEMBRE("01/11/2023", "01/07/2023", "30/11/2023", 20, 121), 
  DICIEMBRE("01/12/2023", "01/07/2023", "31/12/2023", 19, 121);

  //------------------------------------------------
  //Atributos
  //------------------------------------------------
  /**
   * Fecha de inicio del mes.
   */
  private Date fechaInicioMes;

  /**
   * Fecha de inicio del semestre.
   */
  private Date fechaInicioSemestre;

  /**
   * Fecha de final del mes.
   */
  private Date fechaFinalMes;

  /**
   * Dias laborables del mes
   */
  private int diasLaborablesMes;

  /**
   * Dias laborables del semestre;
   */
  private int diasLaborablesSemestre;


  //------------------------------------------------
  //Constuctor
  //------------------------------------------------
  /**
   * Inicializa la enumeración con los valores pasados por parámetro.
   * @param pFechaInicioMes Date que será asignada a la fecha de inicio del mes.
   * @param pFechaInicioSemestre Date que será asignada a la fecha de inicio del semestre.
   * @param pFechaFinalMes Date que será asignada a la fecha de final de mes.
   * @param pDiasLaborablesMes Entero que será asignado a los días laborables del mes.
   * @param pDiasLaborablesSemestre Entero que será asignado a los días laborables del semestre.
   */
  private Mes(String pFechaInicioMes, String pFechaInicioSemestre, String pFechaFinalMes, int pDiasLaborablesMes, int pDiasLaborablesSemestre){
    SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");

    try {
      this.fechaInicioMes = dateF.parse(pFechaInicioMes);
    } catch (Exception e) {
      System.out.println("ERROR CREANDO FECHA INICIO MES");
      this.fechaInicioMes = new Date();
    }

    try {
      this.fechaInicioSemestre = dateF.parse(pFechaInicioSemestre);
    } catch (Exception e) {
      System.out.println("ERROR CREANDO FECHA INICIO SEMESTRE");
      this.fechaInicioSemestre = new Date();
    }
    
    try {
      this.fechaFinalMes = dateF.parse(pFechaFinalMes);
    } catch (Exception e) {
      System.out.println("ERROR CREANDO FECHA FIN MES");
      this.fechaFinalMes = new Date();
    }

    this.diasLaborablesMes = pDiasLaborablesMes;
    this.diasLaborablesSemestre = pDiasLaborablesSemestre;
  }

  //------------------------------------------------
  //Metodos
  //------------------------------------------------
  /**
   * Devuelve la fecha de inicio del mes.
   * @return Date con la fecha de inicio del mes
   */
  public Date getInicioMes() {
    return fechaInicioMes;
  }

  /**
   * Devuelve la fecha de inicio del semestre.
   * @return Date con la fecha de inicio de semestre
   */
  public Date getInicioSemestre() {
    return fechaInicioSemestre;
  }

  /**
   * Devuelve la fecha de final de mes.
   * @return Date con la fehca final de mes.
   */
  public Date getFinalMes() {
    return fechaFinalMes;
  }

  /**
   * Devuelve los días laborables del mes.
   * @return Entero con los días laborables del mes.
   */
  public int getDiasLaborablesMes() {
    return diasLaborablesMes;
  }

  /**
   * Devuelve los días laborables del semestre.
   * @return Entero con los días laborables del semestre.
   */
  public int getDiasLaborablesSemestre() {
    return diasLaborablesSemestre;
  }

}
