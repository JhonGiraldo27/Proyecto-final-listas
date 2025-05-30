import java.util.LinkedList;

public class MetodosEditarRegistros {
    Validadores v = new Validadores();
    ExportarArchivo exp = new ExportarArchivo();

    public LinkedList<ObjTableta_grafica> editarTableta(LinkedList<ObjTableta_grafica> tablet) {
        String serial = v.validarNoCaracteresEspeciales("Ingrese el serial de la tablet que desea editar: ");

        for (ObjTableta_grafica t : tablet) {
            if (t.getSerial().equalsIgnoreCase(serial)) {
                System.out.println("Tablet encontrada. Ingrese los nuevos datos:");

                t.setMarca(v.validarSoloLetras("Ingrese la nueva marca de la tablet: "));

                t.setTamaño(Float.parseFloat(v.validarConRegex("EDITAR tamaño en pulgadas (" + t.getTamaño() + "): ",
                        "^(?:1[0-7](?:\\.\\d{1,2})?|[7-9](?:\\.\\d{1,2})?)$",
                        "Ingrese un numero valido (Desde 7 Hasta 17 pulgadas)")));

                t.setPrecio(Float.parseFloat(v.validarConRegex("EDITAR precio (" + t.getPrecio() + "): ",
                        "^(1[5-9][0-9]{4}|[2-9][0-9]{5}|1[0-9]{6}|2[0-4][0-9]{6}|25000000)$",
                        "Ingrese una opcion valida, (LIMITES: $1 - $25'000,000)")));

                String almacenamientoTablet = v.validarConRegex(
                        "¿Qué almacenamiento tiene ahora la tablet " + t.getMarca() + ", " + t.getSerial()
                                + "? \n1. 256 GB. \n2. 512 GB. \n3. 1 TB.\n",
                        "^[1-3]+$", "Por favor, ingrese una opción válida");
                int almacenamiento = Integer.parseInt(almacenamientoTablet);
                if (almacenamiento == 1) {
                    t.setAlmacenamiento("256 GB");
                } else if (almacenamiento == 2) {
                    t.setAlmacenamiento("512 GB");
                } else {
                    t.setAlmacenamiento("1 TB");
                }

                t.setPeso(
                        Float.parseFloat(v.validarConRegex("¿Cuánto pesa la tablet ahora? \n (ingrese su peso en kg): ",
                                "^(?:[0-3](\\.[0-9]{1,2})?|4(\\.00)?)$",
                                "Ingrese un dato valido (Desde cero hasta 4kg)\n")));

                exp.exportarArchivoTablets(tablet);
                System.out.println("Tablet actualizada correctamente.");
                return tablet;
            }
        }

        System.out.println("No se encontró una tablet con ese serial.");
        return tablet;
    }

    public LinkedList<ObjComp_portatil> editarComp(LinkedList<ObjComp_portatil> Computador) {

        if (Computador.isEmpty()) {
            System.out.println("No hay computadores registrados para editar.");
            return Computador;
        }

        String serialBuscar = v.validarNoCaracteresEspeciales("Ingrese el serial del computador que desea editar: ");
        boolean encontrado = false;

        for (ObjComp_portatil o : Computador) {
            if (o.getSerial().equalsIgnoreCase(serialBuscar)) {
                encontrado = true;

                System.out.println("Computador encontrado:");
                System.out.println(o);

                o.setMarca(v.validarSoloLetras("EDITAR marca (" + o.getMarca() + "): "));

                o.setTamaño(Float.parseFloat(v.validarConRegex("EDITAR tamaño en pulgadas (" + o.getTamaño() + "): ",
                        "^(?:1[0-7](?:\\.\\d{1,2})?|[7-9](?:\\.\\d{1,2})?)$",
                        "Ingrese un numero valido (Desde 7 Hasta 17 pulgadas)")));

                o.setPrecio(Float.parseFloat(v.validarConRegex("EDITAR precio (" + o.getPrecio() + "): ",
                        "^(1[5-9][0-9]{4}|[2-9][0-9]{5}|1[0-9]{6}|2[0-4][0-9]{6}|25000000)$",
                        "Ingrese una opcion valida, (LIMITES: $1 - $25'000,000)")));

                String entSistemOp = v.validarConRegex(
                        "Nuevo sistema operativo:\n1. Windows 7.\n2. Windows 10.\n3. Windows 11.\nOpción: ",
                        "^[1-3]+$", "Ingrese una opción válida");
                int sistemOp = Integer.parseInt(entSistemOp);
                if (sistemOp == 1)
                    o.setSistem_op("Windows 7.");
                else if (sistemOp == 2)
                    o.setSistem_op("Windows 10.");
                else
                    o.setSistem_op("Windows 11.");

                String entrada = v.validarConRegex("Nuevo procesador:\n1. AMD Ryzen.\n2. Intel® Core™ i5.\nOpción: ",
                        "^[1-2]+$", "Ingrese una opción válida");
                int procesador = Integer.parseInt(entrada);
                if (procesador == 1)
                    o.setProcesador("AMD Ryzen.");
                else
                    o.setProcesador("Intel® Core™ i5.");

                System.out.println("Computador editado correctamente.");
                System.out.println(o);
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró ningún computador con ese serial.");
        } else {
            exp.exportarArchivoComp(Computador);
        }

        return Computador;
    }

    public LinkedList<objEst_ing> editarEstudianteIng(LinkedList<objEst_ing> Est_ing) {

        if (Est_ing.isEmpty()) {
            System.out.println("No hay estudiantes registrados para editar.");
            return Est_ing;
        }

        String cedulaBuscar = v.validarCedula("Ingrese la cédula del estudiante que desea editar: ");
        boolean encontrado = false;

        for (objEst_ing o : Est_ing) {
            if (o.getCedula().equalsIgnoreCase(cedulaBuscar)) {
                encontrado = true;

                System.out.println("----------------------\nEstudiante encontrado:\n----------------------");
                System.out.println(o);

                o.setNombre(v.validarSoloLetras("\nEDITAR nombre (" + o.getNombre() + "): "));
                o.setApellido(v.validarSoloLetras("EDITAR apellido (" + o.getApellido() + "): "));
                o.setTelefono(v.validarNumeroTel("Nuevo número de teléfono (" + o.getTelefono() + "): "));

                String semestreAct = v.validarConRegex("Nuevo semestre que cursa: ",
                        "^(1[0-5]|[1-5])$", "Ingrese un semestre que sea válido (1-15)\n");
                o.setSemestre_cursado(Integer.parseInt(semestreAct));

                String promedioacum = v.validarConRegex("Nuevo promedio acumulado: ",
                        "^(0(\\.\\d+)?|[1-4](\\.\\d+)?|5(\\.0+)?)$", "Ingrese un dato válido");
                o.setProm_acum(Float.parseFloat(promedioacum));

                System.out.println("Estudiante editado correctamente.");
                System.out.println(o);
                break;
            }
        }

        if (!encontrado) {
            System.out.println(" No se encontró ningún estudiante con esa cédula.");
        } else {
            exp.exportarArchivoEstIng(Est_ing);
        }

        return Est_ing;
    }

    public LinkedList<objEst_dis> editarEstudianteDis(LinkedList<objEst_dis> Est_dis) {

        if (Est_dis.isEmpty()) {
            System.out.println("No hay estudiantes registrados para editar.");
            return Est_dis;
        }
        // las cedulas son de tipo numerico
        String cedulaBuscar = v.validarCedula("Ingrese la cédula del estudiante que desea editar: ");
        boolean encontrado = false;

        for (objEst_dis o : Est_dis) {
            if (o.getCedula().equalsIgnoreCase(cedulaBuscar)) {
                encontrado = true;

                System.out.println("----------------------\nEstudiante encontrado:\n----------------------");
                System.out.println(o);

                o.setNombre(v.validarSoloLetras("\nEDITAR nombre (" + o.getNombre() + "): "));

                o.setApellido(v.validarSoloLetras("EDITAR apellido (" + o.getApellido() + "): "));

                o.setTelefono(v.validarNumeroTel("Nuevo número de teléfono (" + o.getTelefono() + "): "));

                String entrada = v.validarConRegex("Nueva modalidad:\n1. Presencial\n2. Virtual\nDigite una opción: ",
                        "^[1-2]+$", "--- Ingrese una opción valida ---");
                int modalidad = Integer.parseInt(entrada);
                if (modalidad == 1) {
                    o.setModalidad("Presencial");
                } else {
                    o.setModalidad("Virtual");
                }

                int cantAsignaturas;
                do {
                    cantAsignaturas = v.validarentero("¿Nueva cantidad de asignaturas que cursa?: ",
                            "Por favor ingrese un número entero válido.");
                    if (cantAsignaturas > 10) {
                        System.out.println("No puede cursar más de 10 asignaturas en un semestre.");
                    }
                } while (cantAsignaturas > 10);
                o.setCantidad_asignaturas(cantAsignaturas);

                System.out.println("Estudiante editado correctamente.");
                System.out.println(o);
                break;
            }
        }

        if (!encontrado) {
            System.out.println(" No se encontró ningún estudiante con esa cedula.");
        } else {
            exp.exportarArchivoEstDis(Est_dis);
        }

        return Est_dis;
    }

    public LinkedList<ObjTableta_grafica> eliminarTableta(LinkedList<ObjTableta_grafica> tabletas,
            LinkedList<objEst_dis> estudiantes) {
        String serial = v.validarNoCaracteresEspeciales("Ingrese el serial de la tablet que desea eliminar: ");
        boolean encontrado = false;

        for (ObjTableta_grafica t : tabletas) {
            if (t.getSerial().equalsIgnoreCase(serial)) {
                for (objEst_dis e : estudiantes) {
                    if (e.getSerial() != null && e.getSerial().equalsIgnoreCase(serial)) {
                        e.setSerial(null);
                        System.out.println("La tableta ha sido desasignada del estudiante.");
                        break;
                    }
                }

                tabletas.remove(t);
                encontrado = true;
                System.out.println("Tableta eliminada correctamente.");
                exp.exportarArchivoTablets(tabletas);
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró ninguna tableta con ese serial.");
        }
        return tabletas;
    }

    public LinkedList<ObjComp_portatil> eliminarComp(LinkedList<ObjComp_portatil> computadores,
            LinkedList<objEst_ing> estudiantes) {
        String serialBuscar = v.validarNoCaracteresEspeciales("Ingrese el serial del computador que desea eliminar: ");
        boolean encontrado = false;

        for (ObjComp_portatil c : computadores) {
            if (c.getSerial().equalsIgnoreCase(serialBuscar)) {
                for (objEst_ing e : estudiantes) {
                    if (e.getSerial() != null && e.getSerial().equalsIgnoreCase(serialBuscar)) {
                        e.setSerial(null);
                        System.out.println("El computador ha sido desasignado del estudiante.");
                        break;
                    }
                }

                computadores.remove(c);
                encontrado = true;
                System.out.println("Computador eliminado correctamente.");
                exp.exportarArchivoComp(computadores);
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró ningún computador con ese serial.");
        }
        return computadores;
    }

    public LinkedList<objEst_ing> eliminarEstudianteIng(LinkedList<objEst_ing> estudiantes,
            LinkedList<ObjComp_portatil> computadores) {
        String cedulaBuscar = v.validarCedula("Ingrese la cédula del estudiante de ingeniería que desea eliminar: ");
        boolean encontrado = false;

        for (objEst_ing e : estudiantes) {
            if (e.getCedula().equalsIgnoreCase(cedulaBuscar)) {
                encontrado = true;

                String serialAsignado = e.getSerial();

                if (serialAsignado == null || serialAsignado.equalsIgnoreCase("null") || serialAsignado.isEmpty()) {
                    estudiantes.remove(e);
                    exp.exportarArchivoEstIng(estudiantes);
                    System.out.println("Estudiante eliminado correctamente.");
                } else {
                    System.out.println(
                            "Este estudiante tiene un computador asignado. Debe devolverlo antes de ser eliminado.");
                }
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró ningún estudiante con esa cédula.");
        }

        return estudiantes;
    }

    public LinkedList<objEst_dis> eliminarEstudianteDis(LinkedList<objEst_dis> estudiantes,
            LinkedList<ObjTableta_grafica> tablets) {
        String cedulaBuscar = v.validarCedula("Ingrese la cédula del estudiante que desea eliminar: ");
        boolean encontrado = false;

        for (objEst_dis o : estudiantes) {
            if (o.getCedula().equalsIgnoreCase(cedulaBuscar)) {
                encontrado = true;

                String serialAsignado = o.getSerial();

                if (serialAsignado == null || serialAsignado.equalsIgnoreCase("null") || serialAsignado.isEmpty()) {
                    estudiantes.remove(o);
                    exp.exportarArchivoEstDis(estudiantes);
                    System.out.println("Estudiante de diseño eliminado.");
                } else {
                    System.out.println(
                            "Este estudiante tiene una tableta asignada. Debe devolverla antes de ser eliminado.");
                }
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró ningun estudiante con esa cédula.");
        }

        return estudiantes;
    }

}
