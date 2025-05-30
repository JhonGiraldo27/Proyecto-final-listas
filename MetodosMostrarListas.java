import java.util.LinkedList;

public class MetodosMostrarListas {

    public static void mostrarComputadores(LinkedList<ObjComp_portatil> listaC) {
        if (listaC.isEmpty()) {
            System.out.println("No hay ningun registro de computadores aún");
        } else {
            System.out.println("---------COMPUTADORES REGISTRADOS-------");
            System.out.println("-----------------------------------------");
            for (ObjComp_portatil o : listaC) {
                System.out.println(o);
                System.out.println("------------------------------------------------");

            }

        }

    }

    public static void mostrarTablets(LinkedList<ObjTableta_grafica> listaT) {
        if (listaT.isEmpty()) {
            System.out.println("No hay ningun registro de Tablets aún");
        } else {
            System.out.println("------------TABLETS REGISTRADAS----------");
            System.out.println("-----------------------------------------");
            for (ObjTableta_grafica o : listaT) {
                System.out.println(o);
                System.out.println("------------------------------------------------");

            }

        }

    }

    public static void mostrarEstudiantesDis(LinkedList<objEst_dis> listaE) {
        if (listaE.isEmpty()) {
            System.out.println("No hay ningun registro de estudiantes de diseño aún");
        } else {
            System.out.println("---------ESTUDIANTES DE DISEÑO REGISTRADOS-------");
            System.out.println("-------------------------------------------------");
            for (objEst_dis o : listaE) {
                System.out.println(o);
                System.out.println("------------------------------------------------");

            }

        }

    }

    public static void mostrarEstudiantesIng(LinkedList<objEst_ing> listaE) {
        if (listaE.isEmpty()) {
            System.out.println("No hay ningun registro de estudiantes de ingenieria aún");
        } else {
            System.out.println("---------ESTUDIANTES DE INGENIERIA REGISTRADOS-------");
            System.out.println("------------------------------------------------------");
            for (objEst_ing o : listaE) {
                System.out.println(o);
                System.out.println("------------------------------------------------");

            }

        }

    }
}
