public enum Cargo{
    //------------------------------------------------
    //ELEMENTOS
    //------------------------------------------------
    AdministradoraNegocio("Administrador de negocio", 1500000, 1959999.99),
    Vendedora("Vendedora", 1300000, 1483299.99),
    Operario("Operario", 1000000, 1200999.99),
    Cajero("Cajero", 1100000, 1199999.99),
    LiquidadoraNomina("Liquidarora de nómina", 1200000, 1299999.99);

    //------------------------------------------------
    //ATRIBUTOS
    //------------------------------------------------
    private String nombreCargo;
    private double salarioInf;
    private double salarioSup;
    

    //------------------------------------------------
    //CONSTRUCTOR
    //------------------------------------------------
    private Cargo(String nombreCargo, double salarioInf, double salarioSup){
        this.nombreCargo = nombreCargo;
        this.salarioInf = salarioInf;
        this.salarioSup = salarioSup;
    }

    //------------------------------------------------
    //METODOS
    //------------------------------------------------
    public String getNombre(){
        return nombreCargo;
    }
    public double getSalarioInf(){
        return salarioInf;
    }
    public double getSalarioSup(){
        return salarioSup;
    }
}
