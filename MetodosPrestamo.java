import java.util.LinkedList;

public class MetodosPrestamo {
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
            if (t.isDisponible()) {
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

        if (!tabletElegida.isDisponible()) {
            System.out.println("Esa tablet no está disponible.");
            return;
        }

        tabletElegida.setDisponible(false);
        estudiante.setSerial(tabletElegida.getSerial());

        System.out.println("Tablet asignada exitosamente al estudiante " + estudiante.getNombre() + " "
                + estudiante.getApellido());

        exp.exportarArchivoTablets(tablets);
        exp.exportarArchivoEstDis(estudiantes);
    }

}
