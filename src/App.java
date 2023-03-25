import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Date;
import java.text.SimpleDateFormat;

public class App {
    public static void main(String[] args) throws Exception {
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
                    AgregarEmpleado(reader);
                    break;
                case "2":

                    break;

                case "3":
                    //liquidar todos los empleados
                    Empleado empleado = null//aqui va el metodo de empresa donde se hace la busqueda del empleado
                    try {
                    if (empleado != null) {
                        System.out.println("Ingrese el mes correspondiente (ENERO-DICIEMBRE):"
                                + "\n 1.Enero."
                                + "\n 2.Febrero."
                                + "\n 3.Marzo."
                                + "\n 4.Abril."
                                + "\n 5.Mayo."
                                + "\n 6.Junio."
                                + "\n 7.Julio."
                                + "\n 8.Agosto."
                                + "\n 9.Septiembre."
                                + "\n 10.Octubre."
                                + "\n 11.Noviembre."
                                + "\n 12.Diciembre. ");
                        String mes = reader.readLine();
                        }   

                
                    }   catch (Exception e) {
                            System.out.println(e.getMessage());
                        } 
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

    private static void AgregarEmpleado (BufferedReader reader)throws IOException{
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
                if(false)//TODO: Agregar en la condición la función de la clase Empresa que busca empleado por ID.
                    System.out.println(">ERROR: El empleado con id '" + identificacion +"' ya se encuentra registrado.");
                else
                    System.out.println(">DONE: Identificación válida.");
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
                System.out.println("Ingrese el cargo del empleado:");
                String strCargo = reader.readLine();
                if(strCargo.equals("BACK"))
                    return;
                try {
                    //TODO: Funcion para cambiar el cargo de verificación de la empresa
                    System.out.println(">DONE: Cargo seleccionado con exito.");             
                    break;
                } catch (Exception e) {//TODO: Cambiar por la clase específica de la exception.
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
                        //TODO: Agregar función de verificar salario de la clase empresa
                        System.out.println(">DONE: Salario registrado");
                        break;
                    } catch (Exception e) {//TODO: Cambiar por la clase específica de la exception.
                        System.out.println(">ERROR: " + e.getMessage());
                    }  
                }
            }
            //TODO: Agregar función de agregar empleado;
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

    private static void CalcularNominaEmpleado(BufferedReader reader) throws IOException{ //TODO: AQUI FALTA
        System.out.println("-----Puede ingresar 'BACK' en cualquier momento para volver al menú-----");

        //Buscar ID
        while(true){
            System.out.println("Ingrese la identificacion del empleado al cual desea generar nomina:");
            String mensaje = reader.readLine();
            if(mensaje.equals("BACK"))
                return;
            Empleado empleado = null;//TODO: Agregar funcion de buscar empleado de la clase Empresa.
            if (empleado != null) {
                //RUTA
                String ruta;
                while(true){
                    System.out.println("Ingrese la ruta de salida del resumen de nómina:");
                    ruta = reader.readLine();
                    if(ruta.equals("BACK"))
                        return;
                    try {
                        //TODO: Agregar función de conectar escritor de la clase Empresa.
                        System.out.println(">DONE: Ruta registrada con éxito");
                        break;
                    } catch (Exception e) {//TODO: Cambiar por la exception personalizada correspondiente
                        System.out.println(">ERROR: " + e.getMessage());
                    }
                }

                //MES
                String mes;
                while(true){
                    System.out.println("Ingrese el nombre de el mes en el que desea liquidar la nómina (ENERO-DICIEMBRE):");
                    String mes = reader.readLine();
                    if(mes.equals("BACK"))
                        return;
                break;
            } else
                System.out.println(">ERROR: No se encontro al empleado con id '" + mensaje + "'.");
        }
    }
}