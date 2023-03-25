import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.text.SimpleDateFormat;

public class App {
    public static void main(String[] args) throws Exception {
        //ArrayList<Empleado> listaEmpleados = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean agregarEmpleado = true;
        SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
        
        while(agregarEmpleado){
            System.out.println("Ingrese el nombre del empleado:");
            String nombre = reader.readLine();   
            System.out.println("Ingrese la identificacion del empleado:");
            String identificacion = reader.readLine();  
            Date fecha;
            while(true){
                System.out.println("");
                System.out.println("Ingrese la fecha de ingreso formato dd/MM/AAAA (ej: '03/02/2012'):");
                String fechaIngreso = reader.readLine();
                
                try {
                    fecha=dateF.parse(fechaIngreso);
                    System.out.println(">formato de fecha correta!.");
                    break;
                } catch (Exception e) {
                    System.out.println(">El formato de la fecha no es el indicado.");
                }  
            }
            while(true){
                System.out.println("Ingrese el cargo del empleado:");
                String cargos = reader.readLine();
                try {
                    Cargo cargo = null;//reemplazar por la funcion de empresa                  
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }  
            }

            while(true){
                double salario = 0;
                try {
                    System.out.println("Ingrese el salario basico del empleado:");
                    String aux = reader.readLine();
                    salario = Double.parseDouble(aux);                  
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                if(salario!=0){
                    System.out.println("Salario admitido");
                }else{
                    try {
                        //llamar funcion de verificacion salario por cargo              
                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }  
                }
            }
            //agregar empleado
            System.out.println("¿Desea agregar otro empleado? (s/n)");
            String mensaje = reader.readLine();
            agregarEmpleado = mensaje.equalsIgnoreCase("s");
        }
        boolean salir = false;
        while (!salir) {
            
            System.out.println("Seleccione una opcion:"
                    + "\n 1.Generar nomina de un empleado."
                    + "\n 2.Generar nomina a todos los empleado."
                    + "\n 3.Salir.");
            int opcion = reader.readInt();
            switch (opcion) {
                case "1":
                    System.out.println("Ingrese la identificacion del empleado al cual desea generar nomina:");
                    String mensaje = reader.readLine();
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

                case "2":
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
                case "3":
                    salir = true;
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }

        }
    }
}