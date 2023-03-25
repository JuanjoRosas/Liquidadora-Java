import java.text.SimpleDateFormat;
import java.util.Date;

public enum Mes {
  //------------------------------------------------
  //Elementos
  //------------------------------------------------
AdministradoraNegocio("ADMINISTRADORA_NEGOCIO", 1500000, 1959999.99 );
Vendedora("VENDEDORA", 1300000, 1483299.99);
Operario("OPERARIO", 1000000, 1200999.99);
Cajero("CAJERO", 1100000, 1199999.99);
LiquidadoraNomina("LIQUIDADORA_NOMINA", 1200000, 1299999.99);

private String nombreCargo;
private double salarioInf;
private double salarioSup;

@param nombreCargo
@param salarioInf
@param salarioSup

private Cargo(String nombreCargo, double salarioInf, double salarioSup){

    this.nombreCargo = nombreCargo;
    this.salarioInf = salarioInf;
    this.salarioSup = salarioS

}

public String getNombre(){
    return nombreCargo;
}
public String getSalarioInf(){
    return salarioInf;
}
public String getSalarioSup(){
    return salarioSup;
}

}
