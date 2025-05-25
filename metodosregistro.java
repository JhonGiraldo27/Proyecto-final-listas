import java.util.LinkedList;
import java.util.Scanner;

public class metodosregistro {
    Scanner sc = new Scanner(System.in);
    Validadores v = new Validadores();
    ExportarArchivo exp = new ExportarArchivo();

    private boolean nuevoregistro(String tipoRegistro) {

        String entradatemp = v.validarConRegex(
                "¿Desea ingresar otro " + tipoRegistro + " \n1. Sí \n2. Volver al menú.\nOpcion: ",
                "^[1-2]+$",
                "Error Ingrese un numero valido (1,2)");

        int opcion = Integer.parseInt(entradatemp);

        if (opcion == 1) {
            System.out.println("------- NUEVO REGISTRO ---------");
            return true;
        } else {
            return false;

        }

    }

    public LinkedList<objEst_dis> Registro_EstDiseño(LinkedList<objEst_dis> Est_dis) {

        boolean bandera = true;

        while (bandera) {
            objEst_dis o = new objEst_dis();

            o.setCedula(v.validarSoloNumeros("Ingrese su numero de cedula: "));

            if (!Est_dis.isEmpty()) {
                for (objEst_dis rep : Est_dis) {
                    if (rep.getCedula().equalsIgnoreCase(o.getCedula())) {
                        System.out.println(
                                "Este usuario ya se encuentra registrado, puede modificarlo en la opcion editar usuario");
                        return Est_dis;

                    }
                }
            }
            o.setSerial(null);

            o.setNombre(v.validarSoloLetras("Ingrese su Nombre: "));

            o.setApellido(v.validarSoloLetras("Ingrese su Apellido: "));

            o.setTelefono(v.validarSoloNumeros("ingrese Su numero de Telefono: "));

            String entrada = v.validarConRegex("¿Cuál es su modalidad de estudio?: \n1. Presencial\n2. Virtual: ",
                    "^[1-2]+$", "☣  ️ERROR! Porfavor ingrese una opcion valida");
            int modalidad = Integer.parseInt(entrada);

            if (modalidad == 1) {
                o.setModalidad("Presencial");

            } else {
                o.setModalidad("Virtual");

            }

            int cantAsignaturas;

            do {
                cantAsignaturas = v.validarentero("¿Actualmente Cual es la cantidad de asignaturas que cursa?: ",
                        "porfavor Ingrese un numero entero");
                if (cantAsignaturas > 10) {
                    System.out
                            .println("Error: No puede cursar más de 10 asignaturas en un semestre.");

                }

            } while (cantAsignaturas > 10);
            o.setCantidad_asignaturas(cantAsignaturas);

            Est_dis.add(o);
            exp.exportarArchivoEstDis(Est_dis);

            System.out.println(
                    "Estudiante: " + o.getNombre() + " " + o.getApellido()
                            + " de la carrera Diseño Agregad@ Correctamente...");
            bandera = nuevoregistro("estudiante");
        }

        return Est_dis;

    }

    public LinkedList<objEst_ing> Registro_EstIngenieria(LinkedList<objEst_ing> Est_ing) {

        boolean bandera = true;

        while (bandera) {
            objEst_ing o = new objEst_ing();

            String cedula = v.validarSoloNumeros("ingrese Su numero de cedula: ");
            o.setCedula(cedula);

            if (!Est_ing.isEmpty()) {
                for (objEst_ing rep : Est_ing) {
                    if (rep.getCedula().equalsIgnoreCase(cedula)) {
                        System.out.println(
                                "Este usuario ya se encuentra registrado, puede modificarlo en la opcion editar");
                        return Est_ing;

                    }
                }
            }

            o.setSerial(null);

            o.setNombre(v.validarSoloLetras("Ingrese su Nombre: "));

            o.setApellido(v.validarSoloLetras("Ingrese su Apellido: "));

            o.setTelefono(v.validarSoloNumeros("ingrese su numero de Telefono: "));

            String semestreAct = v.validarConRegex("¿Que semestre se encuentra cursando actualmente?: ",
                    "^(1[0-5]|[1-9])$",
                    "Error! ingrese un semestre que sea valido\n");

            int semestre = Integer.parseInt(semestreAct);

            o.setSemestre_cursado(semestre);

            String promedioacum = v.validarConRegex("¿Actualmente cual es su promedio acumulado?: ",
                    "^(0(\\.\\d+)?|[1-4](\\.\\d+)?|5(\\.0+)?)$",
                    "Error: ingrese un dato valido");
            o.setProm_acum(Float.parseFloat(promedioacum));

            Est_ing.add(o);
            exp.exportarArchivoEstIng(Est_ing);

            System.out.println(
                    "Estudiante: " + o.getNombre() + " " + o.getApellido() + " de Ingenieria Agregado Correctamente");
            bandera = nuevoregistro("Estudiante");

        }

        return Est_ing;

    }

    public LinkedList<ObjComp_portatil> Registrar_portatil(LinkedList<ObjComp_portatil> Computador) {
        Boolean bandera = true;

        while (bandera) {
            ObjComp_portatil o = new ObjComp_portatil();
            o.setSerial(v.validarNoCaracteresEspeciales("ingrese el serial del computador: "));

            if (!Computador.isEmpty()) {
                for (ObjComp_portatil rep : Computador) {
                    if (rep.getSerial().equalsIgnoreCase(o.getSerial())) {
                        System.out.print(
                                "\nEste Computador ya se encuentra registrado. \nSi desea realizar alguna modificacion puede hacerlo desde el menu editar\n");
                        return Computador;

                    }
                }
            }
            o.setDisponible("Disponible"); //pendiente a esta linea

            o.setMarca(v.validarSoloLetras("ingrese la marca del computador: "));

            o.setTamaño(v.validarFloat("Ingrese el Tamaño de su computador en pulgadas: ", "Ingrese un numero valido"));

            o.setPrecio(v.validarFloat("ingrese el precio de su computador: ",
                    "ERROR! VERIFIQUE EL TIPO DE DATO E INTENTE NUEVAMENTE"));
            String entSistemOp = v.validarConRegex(
                    "¿Que sistema operativo tiene el computador " + o.getMarca() + ", con el serial: " + o.getSerial()
                            + "? \n1. Windows 7. \n2. Windows 10. \n3. Windows 11.\n",
                    "^[1-3]+$", "Porfavor ingrese una opcion valida");
            int sistemOp = Integer.parseInt(entSistemOp);
            if (sistemOp == 1) {
                o.setSistem_op("1. Windows 7.");

            } else if (sistemOp == 2) {
                o.setSistem_op("1. Windows 10.");

            } else
                o.setSistem_op("1. Windows 11.");

            String entrada = v.validarConRegex(
                    "¿Que procesador contiene el computador " + o.getMarca() + ", " + o.getSerial()
                            + "? \n1. AMD Ryzen. \n2. Intel® Core™ i5.\nDigite: ",
                    "^[1-2]+$", "\nError! Porfavor ingrese una opcion valida\n");
            int procesador = Integer.parseInt(entrada);
            if (procesador == 1) {
                o.setProcesador("AMD Ryzen.");

            } else
                o.setProcesador("Intel® Core™ i5.");

            Computador.add(o);
            exp.exportarArchivoComp(Computador);

            System.out.println("Computador: " + o.getMarca() + " Con el serial: " + o.getSerial() + "\n" + "Agregado Correctamente");
            System.out.println();
            /*Lo comente porque si esta ingresado computadores o dispositivos, no deberia preguntar acerca del estudiante*/

            bandera = nuevoregistro("computador");

        }

        return Computador;

    }

    public LinkedList<ObjTableta_grafica> registrarTableta(LinkedList<ObjTableta_grafica> tablet) {
        Boolean bandera = true;

        while (bandera) {
            ObjTableta_grafica o = new ObjTableta_grafica();
            o.setSerial(v.validarNoCaracteresEspeciales("ingrese el serial de la tablet: "));

            if (!tablet.isEmpty()) {
                for (ObjTableta_grafica rep : tablet) {
                    if (rep.getSerial().equalsIgnoreCase(o.getSerial())) {
                        System.out.print(
                                "Esta Tablet ya se encuentra registrada. \nSi desea realizar alguna modificacion puede hacerlo desde el menu editar");
                        return tablet;

                    }
                }
            }
            o.setDisponible("Disponible");

            o.setMarca(v.validarSoloLetras("ingrese la marca de la tablet: "));

            o.setTamaño(v.validarFloat("Ingrese el Tamaño de su tablet en pulgadas: ", "Ingrese un numero valido"));

            o.setPrecio(v.validarFloat("ingrese el precio de su tablet: ",

                    "ERROR! VERIFIQUE EL TIPO DE DATO E INTENTE NUEVAMENTE"));

            String almacenamientoTablet = v.validarConRegex(
                    "Cantidad de almacenamiento: " + o.getMarca() + ", " + o.getSerial() //antes preguntaba el sistema operativo
                            + "? \n1. 256 GB. \n2. 512 GB. \n3. 1 TB.\n",
                    "^[1-3]+$", "Porfavor ingrese una opcion valida");
            int almacenamiento = Integer.parseInt(almacenamientoTablet);
            if (almacenamiento == 1) {
                o.setAlmacenamiento("256 GB");

            } else if (almacenamiento == 2) {
                o.setAlmacenamiento("512 GB");

            } else
                o.setAlmacenamiento("1 TB");

            o.setPeso(v.validarFloat("¿Cuanto pesa la tablet? \n (ingrese su peso en kg): ",
                    "ERROR! Ingrese un dato valido\n"));

            tablet.add(o);
            exp.exportarArchivoTablets(tablet);

            bandera = nuevoregistro("tablet");

        }

        return tablet;

    }

}