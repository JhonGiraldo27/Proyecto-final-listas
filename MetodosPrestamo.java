import java.util.LinkedList;
import java.util.Scanner;

public class MetodosPrestamo {

    public void prestarTablet(
            LinkedList<ObjTableta_grafica> tablets,
            LinkedList<objEst_dis> estudiantes,
            Validadores v,
            ExportarArchivo exp) {

        if (tablets == null || tablets.isEmpty()) {
            System.out.println("No hay tablets registradas actualmente.");
            return;
        }

        if (estudiantes == null || estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados actualmente.");
            return;
        }

        String cedula = v.validarCedula("Ingrese su número de cédula: ");

        objEst_dis estudiante = null;
        for (objEst_dis e : estudiantes) {
            if (e.getCedula().equalsIgnoreCase(cedula)) {
                estudiante = e;
                break;
            }
        }

        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        if (estudiante.getSerial() != null && !estudiante.getSerial().equalsIgnoreCase("null")) {
            System.out.println("El estudiante ya tiene una tablet asignada con serial: " + estudiante.getSerial());
            return;
        }

        System.out.println("\n--- Tablets Disponibles ---");
        boolean hayDisponibles = false;

        for (ObjTableta_grafica t : tablets) {
            if (t.isDisponible()) {
                System.out.println(t);
                System.out.println("-----------------------------------");
                hayDisponibles = true;
            }
        }

        if (!hayDisponibles) {
            System.out.println("No hay tablets disponibles en este momento.");
            return;
        }

        String serialElegido = v.validarNoCaracteresEspeciales("Ingrese el serial de la tablet que desea prestar: ");

        for (ObjTableta_grafica o : tablets) {
            if (o.getSerial().equalsIgnoreCase(serialElegido)) {
                if (!o.isDisponible()) {
                    System.out.println("La tablet no está disponible.");
                    return;
                }

                o.setDisponible(false);
                estudiante.setSerial(o.getSerial());

                System.out.println(
                        "Tablet prestada exitosamente a: "
                                + estudiante.getNombre() + " "
                                + estudiante.getApellido());

                exp.exportarArchivoEstDis(estudiantes);
                exp.exportarArchivoTablets(tablets);
                return;
            }
        }

        System.out.println("No se encontró ninguna tablet con ese serial.");
    }

    public void modificarPrestamoTablet(LinkedList<objEst_dis> estudiantes, LinkedList<ObjTableta_grafica> tablets,
            Validadores v, ExportarArchivo exp, Scanner sc) {

        String cedula = v.validarCedula("Ingrese la cédula del estudiante: ");
        objEst_dis estudiante = null;

        for (objEst_dis e : estudiantes) {
            if (e.getCedula().equalsIgnoreCase(cedula)) {
                estudiante = e;
                break;
            }
        }

        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        if (estudiante.getSerial() == null || estudiante.getSerial().equalsIgnoreCase("null")
                || estudiante.getSerial().isEmpty()) {
            System.out.println("El estudiante no tiene una tablet asignada.");
            return;
        }

        System.out.println("Estudiante: " + estudiante.getNombre() + " " + estudiante.getApellido());
        System.out.println("Tablet asignada actualmente: " + estudiante.getSerial());

        System.out.println("\n----------Tablets disponibles:------------\n------------------------------------------");
        int disponibles = 0;
        for (ObjTableta_grafica t : tablets) {
            if (t.isDisponible()) {
                System.out.println(t.toString() + "\n------------------------------------------");
                disponibles++;
            }
        }

        if (disponibles == 0) {
            System.out.println("No hay tablets disponibles en este momento.");
            return;
        }

        String nuevoSerial = v.validarNoCaracteresEspeciales("Ingrese el serial de la nueva tablet: ");
        ObjTableta_grafica nuevaTablet = null;

        for (ObjTableta_grafica o : tablets) {
            if (o.getSerial().equalsIgnoreCase(nuevoSerial) && o.isDisponible()) {
                nuevaTablet = o;
                break;
            }
        }

        if (nuevaTablet == null) {
            System.out.println("El serial ingresado no corresponde a una tablet disponible.");
            return;
        }

        for (ObjTableta_grafica t : tablets) {
            if (t.getSerial().equalsIgnoreCase(estudiante.getSerial())) {
                t.setDisponible(true);
                break;
            }
        }

        nuevaTablet.setDisponible(false);
        estudiante.setSerial(nuevaTablet.getSerial());

        System.out.println("Cambio de tablet realizado con éxito.");

        exp.exportarArchivoEstDis(estudiantes);
        exp.exportarArchivoTablets(tablets);
    }

    public void devolverTablet(LinkedList<objEst_dis> estudiantes,
            LinkedList<ObjTableta_grafica> tablets,
            Validadores v,
            ExportarArchivo exp) {

        String cedula = v.validarCedula("Ingrese la cédula del estudiante: ");
        objEst_dis estudiante = null;

        for (objEst_dis o : estudiantes) {
            if (o.getCedula().equalsIgnoreCase(cedula)) {
                estudiante = o;
                break;
            }
        }

        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        if (estudiante.getSerial() == null || estudiante.getSerial().equalsIgnoreCase("null")
                || estudiante.getSerial().isEmpty()) {
            System.out.println("El estudiante no tiene una tablet asignada.");
            return;
        }

        String serialDevuelto = estudiante.getSerial();
        boolean encontrado = false;

        for (ObjTableta_grafica o : tablets) {
            if (o.getSerial().equalsIgnoreCase(serialDevuelto)) {
                o.setDisponible(true);
                encontrado = true;
                System.out.println("Devolucion realizada correctamente");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró la tablet en el inventario.");
            return;
        }

        estudiante.setSerial(null);

        exp.exportarArchivoEstDis(estudiantes);
        exp.exportarArchivoTablets(tablets);

    }

    public void buscarTablet(
            LinkedList<ObjTableta_grafica> tablets,
            LinkedList<objEst_dis> Est_dis,
            Scanner sc, Validadores v) {
        String serialBuscado = v.validarNoCaracteresEspeciales("Ingrese el serial de la tablet que desea buscar: ");

        boolean tabletEncontrada = false;

        for (ObjTableta_grafica tablet : tablets) {
            if (tablet.getSerial().equalsIgnoreCase(serialBuscado)) {
                tabletEncontrada = true;
                System.out.println(tablet.toString());

                boolean estudianteEncontrado = false;

                for (objEst_dis estudiante : Est_dis) {
                    if (estudiante.getSerial() != null &&
                            estudiante.getSerial().equalsIgnoreCase(serialBuscado)) {

                        System.out.println("\nEsta tableta la tiene prestada el estudiante:" + estudiante.getNombre() +
                                "\n------------------Datos-----------------");
                        System.out.println(estudiante.toString());
                        estudianteEncontrado = true;
                        System.out.println("--------------------------------------");
                        break;
                    }
                }

                if (!estudianteEncontrado) {
                    System.out.println("\nLa tableta no está asignada a ningún estudiante.");
                }

                break;
            }
        }

        if (!tabletEncontrada) {
            System.out.println("No se encontró ninguna tableta con ese serial.");
        }
    }

    public void prestarComputador(
            LinkedList<ObjComp_portatil> computadores,
            LinkedList<objEst_ing> estudiantes,
            Validadores v,
            ExportarArchivo exp) {

        if (computadores == null || computadores.isEmpty()) {
            System.out.println("No hay computadores registrados actualmente.");
            return;
        }

        if (estudiantes == null || estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados actualmente.");
            return;
        }

        String cedula = v.validarCedula("Ingrese su número de cédula: ");
        objEst_ing estudiante = null;
        for (objEst_ing e : estudiantes) {
            if (e.getCedula().equalsIgnoreCase(cedula)) {
                estudiante = e;
                break;
            }
        }

        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        if (estudiante.getSerial() != null && !estudiante.getSerial().equalsIgnoreCase("null")) {
            System.out.println("El estudiante ya tiene un computador asignado con serial: " + estudiante.getSerial());
            return;
        }

        System.out.println("\n--- Computadores Disponibles ---");
        boolean hayDisponibles = false;
        for (ObjComp_portatil c : computadores) {
            if (c.getDisponible()) {
                System.out.println(c);
                System.out.println("-----------------------------------");
                hayDisponibles = true;
            }
        }

        if (!hayDisponibles) {
            System.out.println("No hay computadores disponibles en este momento.");
            return;
        }

        String serialElegido = v.validarNoCaracteresEspeciales("Ingrese el serial del computador que desea prestar: ");

        for (ObjComp_portatil c : computadores) {
            if (c.getSerial().equalsIgnoreCase(serialElegido)) {
                if (!c.getDisponible()) {
                    System.out.println("El computador no está disponible.");
                    return;
                }

                c.setDisponible(false);
                estudiante.setSerial(c.getSerial());

                System.out.println(
                        "Computador prestado exitosamente a: "
                                + estudiante.getNombre() + " "
                                + estudiante.getApellido());

                exp.exportarArchivoEstIng(estudiantes);
                exp.exportarArchivoComp(computadores);
                return;
            }
        }

        System.out.println("No se encontró ningún computador con ese serial.");
    }

    public void modificarPrestamoComputador(
            LinkedList<objEst_ing> estudiantes, LinkedList<ObjComp_portatil> computadores,
            Validadores v, ExportarArchivo exp, Scanner sc) {

        String cedula = v.validarCedula("Ingrese la cédula del estudiante: ");
        objEst_ing estudiante = null;

        for (objEst_ing e : estudiantes) {
            if (e.getCedula().equalsIgnoreCase(cedula)) {
                estudiante = e;
                break;
            }
        }

        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        if (estudiante.getSerial() == null || estudiante.getSerial().equalsIgnoreCase("null")
                || estudiante.getSerial().isEmpty()) {
            System.out.println("El estudiante no tiene un computador asignado.");
            return;
        }

        System.out.println("Estudiante: " + estudiante.getNombre() + " " + estudiante.getApellido());
        System.out.println("Computador asignado actualmente: " + estudiante.getSerial());

        System.out.println(
                "\n---------- Computadores disponibles ---------\n---------------------------------------------");
        int disponibles = 0;
        for (ObjComp_portatil c : computadores) {
            if (c.getDisponible()) {
                System.out.println(c.toString() + "\n------------------------------------------");
                disponibles++;
            }
        }

        if (disponibles == 0) {
            System.out.println("No hay computadores disponibles en este momento.");
            return;
        }

        String nuevoSerial = v.validarConRegex("Ingrese el serial del nuevo computador: ", "^[A-Za-z0-9-]+$",
                "Ingrese un serial válido.");
        ObjComp_portatil nuevoComputador = null;

        for (ObjComp_portatil o : computadores) {
            if (o.getSerial().equalsIgnoreCase(nuevoSerial) && o.getDisponible()) {
                nuevoComputador = o;
                break;
            }
        }

        if (nuevoComputador == null) {
            System.out.println("El serial ingresado no corresponde a un computador disponible.");
            return;
        }

        for (ObjComp_portatil c : computadores) {
            if (c.getSerial().equalsIgnoreCase(estudiante.getSerial())) {
                c.setDisponible(true);
                break;
            }
        }

        nuevoComputador.setDisponible(false);
        estudiante.setSerial(nuevoComputador.getSerial());

        System.out.println("Cambio de computador realizado con éxito.");

        exp.exportarArchivoEstIng(estudiantes);
        exp.exportarArchivoComp(computadores);
    }

    public void devolverComputador(
            LinkedList<objEst_ing> estudiantes,
            LinkedList<ObjComp_portatil> computadores,
            Validadores v,
            ExportarArchivo exp) {

        String cedula = v.validarCedula("Ingrese la cédula del estudiante: ");
        objEst_ing estudiante = null;

        for (objEst_ing e : estudiantes) {
            if (e.getCedula().equalsIgnoreCase(cedula)) {
                estudiante = e;
                break;
            }
        }

        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        if (estudiante.getSerial() == null || estudiante.getSerial().equalsIgnoreCase("null")
                || estudiante.getSerial().isEmpty()) {
            System.out.println("El estudiante no tiene un computador asignado.");
            return;
        }

        String serialDevuelto = estudiante.getSerial();
        boolean encontrado = false;

        for (ObjComp_portatil c : computadores) {
            if (c.getSerial().equalsIgnoreCase(serialDevuelto)) {
                c.setDisponible(true);
                encontrado = true;
                System.out.println("Devolución realizada correctamente.");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró el computador en el inventario.");
            return;
        }

        estudiante.setSerial(null);

        exp.exportarArchivoEstIng(estudiantes);
        exp.exportarArchivoComp(computadores);
    }

    public void buscarComputador(
            LinkedList<ObjComp_portatil> computadores,
            LinkedList<objEst_ing> Est_ing,
            Scanner sc, Validadores v) {
        String serialBuscado = v.validarNoCaracteresEspeciales("Ingrese el serial del computador que desea buscar: ");

        boolean computadorEncontrado = false;

        for (ObjComp_portatil comp : computadores) {
            if (comp.getSerial().equalsIgnoreCase(serialBuscado)) {
                computadorEncontrado = true;
                System.out.println(comp.toString());

                boolean estudianteEncontrado = false;

                for (objEst_ing estudiante : Est_ing) {
                    if (estudiante.getSerial() != null &&
                            estudiante.getSerial().equalsIgnoreCase(serialBuscado)) {

                        System.out.println(
                                "\nEste computador se encuentra prestado por el estudiante:" + estudiante.getNombre() +
                                        "\n------------------Datos-----------------");
                        System.out.println(estudiante.toString());
                        estudianteEncontrado = true;
                        System.out.println("--------------------------------------");
                        estudianteEncontrado = true;
                        break;
                    }
                }

                if (!estudianteEncontrado) {
                    System.out.println("\nEl computador no está asignado a ningún estudiante.");
                }

                break;
            }
        }

        if (!computadorEncontrado) {
            System.out.println("No se encontró ningún computador con ese serial.");
        }
    }

}