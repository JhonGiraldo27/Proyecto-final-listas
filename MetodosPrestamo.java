import java.util.LinkedList;
import java.util.Scanner;

public class MetodosPrestamo {
    // Añado propiedades necesarias
    private Validadores validador = new Validadores();
    private ImportarArchivo importar = new ImportarArchivo();
    private ExportarArchivo exportar = new ExportarArchivo();
    private Scanner sc = new Scanner(System.in);
    
    public void prestarTabletADiseno(LinkedList<ObjTableta_grafica> tablets, LinkedList<objEst_dis> estudiantes,
            Validadores v, ExportarArchivo exp) {
        String cedula = v.validarNoCaracteresEspeciales("Ingrese la cédula del estudiante: ");

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
        
        if (estudiante.getSerial() != null && !estudiante.getSerial().equalsIgnoreCase("null")
                && !estudiante.getSerial().isEmpty()) {
            System.out.println("El estudiante "+estudiante.getNombre()+" ya tiene asignada la tablet con serial: " + estudiante.getSerial());
            return;
        }

        System.out.println("\n--- Tablets Disponibles ---");
        int disponibles = 0;
        for (ObjTableta_grafica t : tablets) {
            if (t.getDisponible()) {
                System.out.println(t.toString()
                        + "\n------------------------");
                disponibles++;
            }
        }

        if (disponibles == 0) {
            System.out.println("No hay tablets disponibles en este momento.");
            return;
        }

        String serialTablet = v.validarNoCaracteresEspeciales("Ingrese el serial de la tablet que desea prestar: ");
        ObjTableta_grafica tabletElegida = null;
        for (ObjTableta_grafica t : tablets) {
            if (t.getSerial().equalsIgnoreCase(serialTablet)) {
                tabletElegida = t;
                break;
            }
        }

        if (tabletElegida == null) {
            System.out.println("No se encontró una tablet con ese serial.");
            return;
        }

        if (!tabletElegida.getDisponible()) { //antes era isDisponible
            System.out.println("Esa tablet no está disponible.");
            return;
        }

        tabletElegida.setDisponible("Prestado");
        estudiante.setSerial(tabletElegida.getSerial());

        System.out.println("Tablet asignada exitosamente al estudiante " + estudiante.getNombre() + " "
                + estudiante.getApellido());

        exp.exportarArchivoTablets(tablets);
        exp.exportarArchivoEstDis(estudiantes);
    }

    public void modificarPrestamoComputador(String documento) {
        LinkedList<objEst_ing> estudiantes = importar.importarArchivoEstIng();
        LinkedList<ObjComp_portatil> computadores = importar.importarArchivoComputadores();
        boolean encontrado = false;
        
        for (objEst_ing estudiante : estudiantes) {
            if (estudiante.getCedula().equals(documento) && estudiante.getSerial() != null) {
                encontrado = true;
                System.out.println("Estudiante: " + estudiante.getNombre() + " " + estudiante.getApellido());
                System.out.println("Computador prestado actualmente: " + estudiante.getSerial());
                
                System.out.println("\n¿Qué desea modificar?");
                System.out.println("1. Cambiar por otro computador");
                System.out.println("2. Modificar fecha de préstamo");
                System.out.println("3. Cancelar");
                
                int opcion = validador.validarentero("Ingrese una opción: ", "Error! Ingrese una opción válida.");
                
                switch (opcion) {
                    case 1:
                        // Primero liberar el computador actual
                        String serialActual = estudiante.getSerial();
                        for (ObjComp_portatil comp : computadores) {
                            if (comp.getSerial().equals(serialActual)) {
                                System.out.println("ESTDO DISPONIBLE");
                                //LO ideal aqui es establecer un setter que establezca en el objeto "disponible"
                                break;
                            }
                        }
                        
                        // Mostrar computadores disponibles
                        System.out.println("\nComputadores disponibles:");
                        boolean hayDisponibles = false;
                        for (ObjComp_portatil comp : computadores) {
                            if (comp.getDisponible()) { //verifica si esta disponible
                                System.out.println(comp);
                                hayDisponibles = true;
                            }
                        }
                        
                        if (!hayDisponibles) {
                            System.out.println("No hay computadores disponibles para cambio.");
                            estudiante.setSerial(serialActual); // Mantener el mismo
                            break;
                        }
                        
                        // Seleccionar nuevo computador
                        String nuevoSerial = validador.validarConRegex("Ingrese el serial del nuevo computador: ", 
                                "^[A-Za-z0-9-]+$", "Error! Ingrese un serial válido.");
                        
                        boolean serialValido = false;
                        for (ObjComp_portatil comp : computadores) {
                            if (comp.getSerial().equals(nuevoSerial) && comp.getDisponible()) {
                                comp.setDisponible("Prestado");
                                estudiante.setSerial(nuevoSerial);
                                serialValido = true;
                                System.out.println("Cambio de computador realizado con éxito.");
                                break;
                            }
                        }
                        
                        if (!serialValido) {
                            System.out.println("El serial ingresado no corresponde a un computador disponible.");
                            estudiante.setSerial(serialActual); // Mantener el mismo
                        }
                        break;
                    
                    case 2:
                        // Modificar fecha de préstamo si es necesario
                        System.out.println("Función no implementada aún.");
                        break;
                    
                    default:
                        System.out.println("Operación cancelada.");
                        break;
                }
                
                // Guardar cambios
                exportar.exportarArchivoEstIng(estudiantes);
                exportar.exportarArchivoComp(computadores);
                break;
            }
        }
        
        if (!encontrado) {
            System.out.println("El estudiante no tiene ningún computador prestado actualmente.");
        }
    }

    public void modificarPrestamoTablet(String documento) {
        LinkedList<objEst_ing> estudiantes = importar.importarArchivoEstIng();
        LinkedList<ObjTableta_grafica> tablets = importar.importarArchivoTablets();
        boolean encontrado = false;
        
        for (objEst_ing estudiante : estudiantes) {
            if (estudiante.getCedula().equals(documento) && estudiante.getSerial() != null) {
                encontrado = true;
                System.out.println("Estudiante: " + estudiante.getNombre() + " " + estudiante.getApellido());
                System.out.println("Tablet prestada actualmente: " + estudiante.getSerial());
                
                System.out.println("\n¿Qué desea modificar?");
                System.out.println("1. Cambiar por otra tablet");
                System.out.println("2. Modificar fecha de préstamo");
                System.out.println("3. Cancelar");
                
                int opcion = validador.validarentero("Ingrese una opción: ", "Error! Ingrese una opción válida.");
                
                switch (opcion) {
                    case 1:
                        // Primero liberar la tablet actual
                        String serialActual = estudiante.getSerial();
                        for (ObjTableta_grafica tablet : tablets) {
                            if (tablet.getSerial().equals(serialActual)) {
                                tablet.setDisponible("Disponible");
                                break;
                            }
                        }
                        
                        // Mostrar tablets disponibles
                        System.out.println("\nTablets disponibles:");
                        boolean hayDisponibles = false;
                        for (ObjTableta_grafica tablet : tablets) {
                            if (tablet.getDisponible()) { //si getDisponible es true
                                System.out.println(tablet);
                                hayDisponibles = true;
                            }
                        }
                        
                        if (!hayDisponibles) {
                            System.out.println("No hay tablets disponibles para cambio.");
                            estudiante.setSerial(serialActual); // Mantener el mismo
                            break;
                        }
                        
                        // Seleccionar nueva tablet
                        String nuevoSerial = validador.validarConRegex("Ingrese el serial de la nueva tablet: ", 
                                "^[A-Za-z0-9-]+$", "Error! Ingrese un serial válido.");
                        
                        boolean serialValido = false;
                        for (ObjTableta_grafica tablet : tablets) {
                            if (tablet.getSerial().equals(nuevoSerial) && tablet.getDisponible()) { //tablet.getEstado().equalsIgnoreCase("Disponible"
                                //tablet.setEstado("Prestado");
                                tablet.setDisponible("Prestado");
                                estudiante.setSerial(nuevoSerial);
                                serialValido = true;
                                System.out.println("Cambio de tablet realizado con éxito.");
                                break;
                            }
                        }
                        
                        if (!serialValido) {
                            System.out.println("El serial ingresado no corresponde a una tablet disponible.");
                            estudiante.setSerial(serialActual); // Mantener el mismo
                        }
                        break;
                    
                    case 2:
                        // Modificar fecha de préstamo si es necesario
                        System.out.println("Función no implementada aún.");
                        break;
                    
                    default:
                        System.out.println("Operación cancelada.");
                        break;
                }
                
                // Guardar cambios
                exportar.exportarArchivoEstIng(estudiantes);
                exportar.exportarArchivoTablets(tablets);
                break;
            }
        }
        
        if (!encontrado) {
            System.out.println("El estudiante no tiene ninguna tablet prestada actualmente.");
        }
    }

    public void devolverComputador(String documento, String serial) {
        LinkedList<objEst_ing> estudiantes = importar.importarArchivoEstIng();
        LinkedList<ObjComp_portatil> computadores = importar.importarArchivoComputadores();
        boolean encontrado = false;
        
        for (objEst_ing estudiante : estudiantes) {
            if (estudiante.getCedula().equals(documento) && estudiante.getSerial() != null) {
                if (estudiante.getSerial().equals(serial)) {
                    encontrado = true;
                    // Actualizar estado del estudiante
                    estudiante.setSerial(null);
                    
                    // Actualizar estado del computador
                    for (ObjComp_portatil comp : computadores) {
                        if (comp.getSerial().equals(serial)) {
                            //comp.setEstado("Disponible");
                            comp.setDisponible("Disponible");
                            System.out.println("Computador con serial " + serial + " devuelto exitosamente.");
                            break;
                        }
                    }
                    
                    // Guardar cambios
                    exportar.exportarArchivoEstIng(estudiantes);
                    exportar.exportarArchivoComp(computadores);
                    break;
                } else {
                    System.out.println("El serial ingresado no coincide con el computador prestado al estudiante.");
                    encontrado = true;
                    break;
                }
            }
        }
        
        if (!encontrado) {
            System.out.println("El estudiante no tiene ningún computador prestado con ese serial.");
        }
    }

    public void devolverTablet(String documento, String serial) {
        LinkedList<objEst_ing> estudiantes = importar.importarArchivoEstIng();
        LinkedList<ObjTableta_grafica> tablets = importar.importarArchivoTablets();
        boolean encontrado = false;
        
        for (objEst_ing estudiante : estudiantes) {
            if (estudiante.getCedula().equals(documento) && estudiante.getSerial() != null) {
                if (estudiante.getSerial().equals(serial)) {
                    encontrado = true;
                    // Actualizar estado del estudiante
                    estudiante.setSerial(null);
                    
                    // Actualizar estado de la tablet
                    for (ObjTableta_grafica tablet : tablets) {
                        if (tablet.getSerial().equals(serial)) {
                            tablet.setDisponible("Disponible");
                            System.out.println("Tablet con serial " + serial + " devuelta exitosamente.");
                            break;
                        }
                    }
                    
                    // Guardar cambios
                    exportar.exportarArchivoEstIng(estudiantes);
                    exportar.exportarArchivoTablets(tablets);
                    break;
                } else {
                    System.out.println("El serial ingresado no coincide con la tablet prestada al estudiante.");
                    encontrado = true;
                    break;
                }
            }
        }
        
        if (!encontrado) {
            System.out.println("El estudiante no tiene ninguna tablet prestada con ese serial.");
        }
    }

    public void mostrarComputadoresDisponibles() {
        LinkedList<ObjComp_portatil> computadores = importar.importarArchivoComputadores();
        boolean hayDisponibles = false;
        
        System.out.println("\n----- COMPUTADORES DISPONIBLES -----");
        for (ObjComp_portatil comp : computadores) {
            if (comp.getDisponible()) { //comp.getEstado().equalsIgnoreCase("Disponible")
                System.out.println(comp);
                System.out.println("----------------------------------------");
                hayDisponibles = true;
            }
        }
        
        if (!hayDisponibles) {
            System.out.println("No hay computadores disponibles actualmente.");
        }
    }

    public void mostrarTabletsDisponibles() {
        LinkedList<ObjTableta_grafica> tablets = importar.importarArchivoTablets();
        boolean hayDisponibles = false;
        
        System.out.println("\n----- TABLETS DISPONIBLES -----");
        for (ObjTableta_grafica tablet : tablets) {
            if (tablet.getDisponible()) { //tablet.getEstado().equalsIgnoreCase("Disponible")
                System.out.println(tablet);
                System.out.println("----------------------------------------");
                hayDisponibles = true;
            }
        }
        
        if (!hayDisponibles) {
            System.out.println("No hay tablets disponibles actualmente.");
        }
    }

    public void mostrarEquiposPrestados() {
        LinkedList<objEst_ing> estudiantes = importar.importarArchivoEstIng();
        LinkedList<ObjComp_portatil> computadores = importar.importarArchivoComputadores();
        LinkedList<ObjTableta_grafica> tablets = importar.importarArchivoTablets();
        boolean hayPrestados = false;
        
        System.out.println("\n----- EQUIPOS PRESTADOS A ESTUDIANTES DE INGENIERÍA -----");
        for (objEst_ing estudiante : estudiantes) {
            if (estudiante.getSerial() != null) {
                System.out.println("Estudiante: " + estudiante.getNombre() + " " + estudiante.getApellido());
                System.out.println("Documento: " + estudiante.getCedula());
                System.out.println("Serial del equipo: " + estudiante.getSerial());
                
                // Buscar si es computador o tablet
                boolean encontrado = false;
                for (ObjComp_portatil comp : computadores) {
                    if (comp.getSerial().equals(estudiante.getSerial())) {
                        System.out.println("Tipo de equipo: Computador");
                        System.out.println("Marca: " + comp.getMarca());
                       // System.out.println("Modelo: " + comp.getModelo());
                        encontrado = true;
                        break;
                    }
                }
                
                if (!encontrado) {
                    for (ObjTableta_grafica tablet : tablets) {
                        if (tablet.getSerial().equals(estudiante.getSerial())) {
                            System.out.println("Tipo de equipo: Tablet");
                            System.out.println("Marca: " + tablet.getMarca());
                            //System.out.println("Modelo: " + tablet.get);
                            break;
                        }
                    }
                }
                
                System.out.println("----------------------------------------");
                hayPrestados = true;
            }
        }
        
        if (!hayPrestados) {
            System.out.println("No hay equipos prestados actualmente.");
        }
    }

    public void buscarEquipoPorSerial(String serial) {
        LinkedList<ObjComp_portatil> computadores = importar.importarArchivoComputadores();
        LinkedList<ObjTableta_grafica> tablets = importar.importarArchivoTablets();
        LinkedList<objEst_ing> estudiantesIng = importar.importarArchivoEstIng();
        LinkedList<objEst_dis> estudiantesDis = importar.importarArchivoEstDis();
        boolean encontrado = false;
        
        // Buscar en computadores
        for (ObjComp_portatil comp : computadores) {
            if (comp.getSerial().equals(serial)) {
                System.out.println("\n----- RESULTADO DE BÚSQUEDA -----");
                System.out.println("Tipo de equipo: Computador");
                System.out.println(comp);
                
                if (comp.getDisponible()) { //comp.getEstado().equalsIgnoreCase("Prestado"
                    // Buscar a qué estudiante está prestado
                    for (objEst_ing estudiante : estudiantesIng) {
                        if (estudiante.getSerial() != null && estudiante.getSerial().equals(serial)) {
                            System.out.println("Prestado a: " + estudiante.getNombre() + " " + estudiante.getApellido());
                            System.out.println("Documento: " + estudiante.getCedula());
                            System.out.println("Carrera: Ingeniería");
                            break;
                        }
                    }
                }
                
                encontrado = true;
                break;
            }
        }
        
        // Buscar en tablets si no se encontró en computadores
        if (!encontrado) {
            for (ObjTableta_grafica tablet : tablets) {
                if (tablet.getSerial().equals(serial)) {
                    System.out.println("\n----- RESULTADO DE BÚSQUEDA -----");
                    System.out.println("Tipo de equipo: Tablet");
                    System.out.println(tablet);
                    
                    if (tablet.getDisponible()) {
                        // Buscar a qué estudiante está prestado
                        boolean prestadoEncontrado = false;
                        
                        // Buscar en estudiantes de ingeniería
                        for (objEst_ing estudiante : estudiantesIng) {
                            if (estudiante.getSerial() != null && estudiante.getSerial().equals(serial)) {
                                System.out.println("Prestado a: " + estudiante.getNombre() + " " + estudiante.getApellido());
                                System.out.println("Documento: " + estudiante.getCedula());
                                System.out.println("Carrera: Ingeniería");
                                prestadoEncontrado = true;
                                break;
                            }
                        }
                        
                        // Si no está en ingeniería, buscar en diseño
                        if (!prestadoEncontrado) {
                            for (objEst_dis estudiante : estudiantesDis) {
                                if (estudiante.getSerial() != null && estudiante.getSerial().equals(serial)) {
                                    System.out.println("Prestado a: " + estudiante.getNombre() + " " + estudiante.getApellido());
                                    System.out.println("Documento: " + estudiante.getCedula());
                                    System.out.println("Carrera: Diseño");
                                    break;
                                }
                            }
                        }
                    }
                    
                    encontrado = true;
                    break;
                }
            }
        }
        
        if (!encontrado) {
            System.out.println("No se encontró ningún equipo con el serial " + serial);
        }
    }

    public void prestarComputadorAIngenieria(LinkedList<ObjComp_portatil> computadoresList, String documento, String serialComputador) {
        boolean computadorEncontrado = false;
        boolean estudianteConPrestamo = false;
        
        // Verificar si el estudiante ya tiene un equipo prestado
        LinkedList<objEst_ing> estudiantes = importar.importarArchivoEstIng();
        for (objEst_ing estudiante : estudiantes) {
            if (estudiante.getCedula().equals(documento)) {
                if (estudiante.getSerial() != null) {
                    System.out.println("El estudiante ya tiene un equipo prestado con serial: " + estudiante.getSerial());
                    System.out.println("Debe devolver el equipo actual antes de solicitar uno nuevo.");
                    estudianteConPrestamo = true;
                    return;
                }
                break;
            }
        }
        
        if (!estudianteConPrestamo) {
            // Buscar el computador por su serial
            for (ObjComp_portatil computador : computadoresList) {
                if (computador.getSerial().equals(serialComputador)) {
                    computadorEncontrado = true;
                    
                    // Verificar si el computador está disponible
                    if (computador.getDisponible()) {
                        // Actualizar estado del computador
                        //computador.setEstado("Prestado");
                        computador.setDisponible("Prestado");
                        
                        // Actualizar información del estudiante
                        for (objEst_ing estudiante : estudiantes) {
                            if (estudiante.getCedula().equals(documento)) {
                                estudiante.setSerial(serialComputador);
                                break;
                            }
                        }
                        
                        // Guardar cambios
                        exportar.exportarArchivoComp(computadoresList);
                        exportar.exportarArchivoEstIng(estudiantes);
                        
                        System.out.println("Préstamo realizado con éxito.");
                        System.out.println("Computador con serial " + serialComputador + " prestado al estudiante con documento " + documento);
                    } else {
                        System.out.println("El computador con serial " + serialComputador + " no está disponible para préstamo.");
                    }
                    break;
                }
            }
            
            if (!computadorEncontrado) {
                System.out.println("No se encontró ningún computador con el serial " + serialComputador);
            }
        }
    }

    public void prestarTabletAIngenieria(LinkedList<ObjTableta_grafica> tabletsList, String documento, String serialTablet) {
        boolean tabletEncontrada = false;
        boolean estudianteConPrestamo = false;
        
        // Verificar si el estudiante ya tiene un equipo prestado
        LinkedList<objEst_ing> estudiantes = importar.importarArchivoEstIng();
        for (objEst_ing estudiante : estudiantes) {
            if (estudiante.getCedula().equals(documento)) {
                if (estudiante.getSerial() != null) {
                    System.out.println("El estudiante ya tiene un equipo prestado con serial: " + estudiante.getSerial());
                    System.out.println("Debe devolver el equipo actual antes de solicitar uno nuevo.");
                    estudianteConPrestamo = true;
                    return;
                }
                break;
            }
        }
        
        if (!estudianteConPrestamo) {
            // Buscar la tablet por su serial
            for (ObjTableta_grafica tablet : tabletsList) {
                if (tablet.getSerial().equals(serialTablet)) {
                    tabletEncontrada = true;
                    
                    // Verificar si la tablet está disponible
                    if (tablet.getDisponible()) {
                        // Actualizar estado de la tablet
                        tablet.setDisponible("Prestado");
                        
                        // Actualizar información del estudiante
                        for (objEst_ing estudiante : estudiantes) {
                            if (estudiante.getCedula().equals(documento)) {
                                estudiante.setSerial(serialTablet);
                                break;
                            }
                        }
                        
                        // Guardar cambios
                        exportar.exportarArchivoTablets(tabletsList);
                        exportar.exportarArchivoEstIng(estudiantes);
                        
                        System.out.println("Préstamo realizado con éxito.");
                        System.out.println("Tablet con serial " + serialTablet + " prestada al estudiante con documento " + documento);
                    } else {
                        System.out.println("La tablet con serial " + serialTablet + " no está disponible para préstamo.");
                    }
                    break;
                }
            }
            
            if (!tabletEncontrada) {
                System.out.println("No se encontró ninguna tablet con el serial " + serialTablet);
            }
        }
    }
}