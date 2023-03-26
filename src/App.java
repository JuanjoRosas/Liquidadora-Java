import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import javax.swing.text.StyledEditorKit.BoldAction;

import java.text.SimpleDateFormat;

public class App {
    public static void main(String[] args) throws Exception {
        Empresa empresa = new Empresa();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean salir = false;

        System.out.println("¡----<Le damos la bienvenida a la liquidadora de NUEVA REALIDAD>----!");
        while (!salir) {
            System.out.println("Seleccione una opcion:"
                    + "\n 1. Agregar empleado(s)."
                    + "\n 2. Generar nomina de un empleado."
                    + "\n 3. Generar nomina a todos los empleado."
                    + "\n 4. Salir.");
            String opcion = reader.readLine();
            switch (opcion) {
                case "1":
                    AgregarEmpleado(reader, empresa);
                    break;
                case "2":
                    CalcularNominaEmpleado(reader, empresa);
                    break;

                case "3":
                    CalcularNominaVariosEmpleados(reader, empresa);
                    break;
                case "4":
                    System.out.println("¡Hasta luego!");
                    salir = true;
                    break;
                default:
                    System.out.println(">ERROR: '" + opcion + "' no es una opción válida.");
            }

        }
    }

    private static void AgregarEmpleado (BufferedReader reader, Empresa empresa)throws IOException{
        SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
        
        while(true){
            System.out.println("-----Puede ingresar 'BACK' en cualquier momento para volver al menú-----");

            //NOMBRE
            System.out.println("Ingrese el nombre del empleado:");
            String nombre = reader.readLine();
            if(nombre.equals("BACK"))
                return;
            
            //ID
            String identificacion;
            while(true){
                System.out.println("Ingrese la identificacion del empleado:");
                identificacion = reader.readLine();
                if(identificacion.equals("BACK"))
                    return;
                if(empresa.buscarEmpleado(identificacion) != null)
                    System.out.println(">ERROR: El empleado con id '" + identificacion +"' ya se encuentra registrado.");
                else{
                    System.out.println(">DONE: Identificación válida.");
                    break;
                } 
            }
            
            //FECHA INGRESO
            Date fecha;
            while(true){
                System.out.println("Ingrese la fecha de ingreso con formato dd/MM/AAAA (ej: '03/02/2012'):");
                String strFecha = reader.readLine();
                if(strFecha.equals("BACK"))
                    return;
                try {
                    fecha=dateF.parse(strFecha);
                    System.out.println(">DONE: formato de fecha correta.");
                    break;
                } catch (Exception e) {
                    System.out.println(">ERROR: El formato de la fecha no es el indicado.");
                }  
            }

            //CARGO
            while(true){
                System.out.println("Ingrese el cargo del empleado {AdministradoraNegocio, Vendedora, Operario, Cajero, LiquidadoraNomina}:");
                String strCargo = reader.readLine();
                if(strCargo.equals("BACK"))
                    return;
                try {
                    empresa.setCargoRevision(strCargo);
                    System.out.println(">DONE: Cargo seleccionado con exito.");             
                    break;
                } catch (CargoException e) {
                    System.out.println(">ERROR: " + e.getMessage());
                }  
            }

            //SALARIO
            double salario;
            while(true){
                salario = 0;
                System.out.println("Ingrese el salario ordinario (mensual) del empleado:");
                String strSalario = reader.readLine();
                if(strSalario.equals("BACK"))
                    return;
                try {
                    salario = Double.parseDouble(strSalario);              
                } catch (Exception e) {
                    System.out.println(">ERROR: El salario '" + strSalario + "' no es un valor válido.");
                }
                if(salario!=0){
                    try {
                        empresa.verificarSalarioCargo(salario);
                        System.out.println(">DONE: Salario registrado");
                        break;
                    } catch (CargoException e) {
                        System.out.println(">ERROR: " + e.getMessage());
                    }  
                }
            }
            empresa.AgregarEmpleado(nombre, identificacion, salario, fecha);
            System.out.println(">DONE: Empleado agregado con éxito");

            //Preguntar si desea agregar otro empleado
            while(true){
                System.out.println("¿Desea agregar otro empleado? (s/n)");
                String mensaje = reader.readLine();
                if(mensaje.equals("BACK") || mensaje.equals("n"))
                    return;
                else if(mensaje.equals("s"))
                    break;
            }
        }
    }

    private static void CalcularNominaEmpleado(BufferedReader reader, Empresa empresa) throws IOException{
        SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("-----Puede ingresar 'BACK' en cualquier momento para volver al menú-----");

        //Buscar ID
        while(true){
            System.out.println("Ingrese la identificacion del empleado al cual desea generar nomina:");
            String mensaje = reader.readLine();
            if(mensaje.equals("BACK"))
                return;
            empresa.setEmpleadoInforme(mensaje);
            if (empresa.getEmpleadoInforme() != null) {
                //MES
                while(true){
                    Boolean calcular = false;
                    System.out.println("Ingrese el nombre del mes en el que desea liquidar la nómina (ENERO-DICIEMBRE):");
                    String strMes = reader.readLine();
                    if(strMes.equals("BACK"))
                        return;
                    try {
                        empresa.setMesNomina(strMes);
                        calcular = true;
                    } catch (FechaException e) {
                        System.out.println(">ERROR: " + e.getMessage());
                    }
                    if(calcular){
                        try {
                            empresa.calcularNominaEmpleado();
                            break;
                        } catch (Exception e) {
                            System.out.println(">ERROR: " + e.getMessage());
                        }
                    }
                }

                //RUTA
                while(true){
                    System.out.println("ingrese la ruta en la que desea que se guarde el informe (ej: C:/Users/user/Downloads):");
                    String strRuta = reader.readLine();
                    if(strRuta.equals("BACK"))
                        return;
                    try {
                        empresa.generarInformeUnico(strRuta);
                        System.out.println(">DONE: Informe generado con éxito");
                        break;
                    } catch (InformeException e) {
                        System.out.println(">ERROR: " + e.getMessage());
                    }
                }
                break;
            } else
                System.out.println(">ERROR: No se encontro al empleado con id '" + mensaje + "'.");
        }
    }

    private static void CalcularNominaVariosEmpleados(BufferedReader reader, Empresa empresa) throws IOException{
        SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("-----Puede ingresar 'BACK' en cualquier momento para volver al menú-----");

        while(true){
            Boolean calcular = false;
            System.out.println("Ingrese el nombre del mes en el que desea liquidar la nómina (ENERO-DICIEMBRE):");
            String strMes = reader.readLine();
            if(strMes.equals("BACK"))
                return;
            try {
                empresa.setMesNomina(strMes);
                break;
            } catch (FechaException e) {
                System.out.println(">ERROR: " + e.getMessage());
            }
        }

        //RUTA
        while(true){
            System.out.println("ingrese la ruta en la que desea que se guarden los informes (ej: C:/Users/user/Downloads):");
            String strRuta = reader.readLine();
            if(strRuta.equals("BACK"))
                return;
            try {
                empresa.generarInformesTotales(strRuta);
                System.out.println(">DONE: Se generaron los infores de los empleados activos en el mes ingresado y un informe total");
                break;
            } catch (Exception e) {
                System.out.println(">ERROR: " + e.getMessage());
            }
        }
    }
}