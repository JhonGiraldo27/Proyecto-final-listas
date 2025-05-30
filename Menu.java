import java.util.LinkedList;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        LinkedList<ObjComp_portatil> computadores = new LinkedList<>();
        LinkedList<objEst_dis> Est_dis = new LinkedList<>();
        LinkedList<objEst_ing> Est_ing = new LinkedList<>();
        LinkedList<ObjTableta_grafica> Tabl_graf = new LinkedList<>();

        metodosregistro m = new metodosregistro();
        Scanner sc = new Scanner(System.in);
        Validadores v = new Validadores();
        ImportarArchivo imp = new ImportarArchivo();
        MetodosEditarRegistros mEditar = new MetodosEditarRegistros();
        ExportarArchivo exp = new ExportarArchivo();
        MetodosPrestamo mPrst = new MetodosPrestamo();

        computadores = imp.importarArchivoComputadores();
        Est_dis = imp.importarArchivoEstDis();
        Est_ing = imp.importarArchivoEstIng();
        Tabl_graf = imp.importarArchivoTablets();

        int optmenu;
        System.out.println("--------------------------UNIVERSIDAD SAN JUAN DE DIOS------------------------ \n"
                + "\n------------------SISTEMA DE GESTION PARA PRESTAMOS DE DISPOSITIVOS------------");

        do {
            System.out.println("\n----------------------------------MENÚ PRINCIPAL-------------------------------");
            System.out.println("\n1. ESTUDIANTES DE INGENIERIA."
                    + "\n2. ESTUDIANTES DE DISEÑO."
                    + "\n3. MOSTRAR INVENTARIO"
                    + "\n4. REGISTRAR USUARIO/DISPOSITIVO"
                    + "\n5. Salir Del Sistema\n");
            String entrada = v.validarConRegex("Digite una opcion: ", "^[1-5]$",
                    "Entrada no nvalida ingrese una opcion existente");
            optmenu = Integer.parseInt(entrada);

            switch (optmenu) {
                case 1:
                    menuEstudiantesIngenieria(exp, Est_ing, computadores, sc, v, mPrst);
                    ;
                    break;

                case 2:
                    menuEstudiantesDis(exp, Est_dis, Tabl_graf, sc, v, mPrst);
                    ;

                    break;

                case 3:
                    MetodosMostrarListas.mostrarComputadores(computadores);
                    System.out.println("\n================================================\n"
                            + "================================================\n");
                    MetodosMostrarListas.mostrarTablets(Tabl_graf);
                    break;
                case 4:
                    menuRegistrar(sc, computadores, Est_dis, Est_ing, Tabl_graf, v, m, mEditar);
                    break;

                default:
                    System.out
                            .println("SALIENDO DEL SISTEMA...");
                    break;

            }

        } while (optmenu != 5);

        sc.close();
    }

    public static void menuEstudiantesIngenieria(ExportarArchivo exp, LinkedList<objEst_ing> estIng,
            LinkedList<ObjComp_portatil> computador, Scanner sc, Validadores v, MetodosPrestamo mPrst) {

        boolean banderamenu = true;
        while (banderamenu) {
            System.out.println("\n-MENU PARA ESTUDIANTES DE INGENIERIA-\n"
                    + "1. REALIZAR PRESTAMO DE EQUIPO\n"
                    + "2. MODIFICAR PRESTAMO DE EQUIPO\n"
                    + "3. DEVOLUCION EQUIPO\n"
                    + "4. BUSCAR EQUIPO\n"
                    + "5. VOLVER AL MENÚ PRINCIPAL\n");
            String optmenuing = v.validarConRegex("Opcion: ", "^[1-5]$", "Ingrese una opcion valida.");
            int c = Integer.parseInt(optmenuing);

            switch (c) {
                case 1:
                    mPrst.prestarComputador(computador, estIng, v, exp);
                    break;
                case 2:
                    mPrst.modificarPrestamoComputador(estIng, computador, v, exp, sc);
                    break;
                case 3:
                    mPrst.devolverComputador(estIng, computador, v, exp);
                    break;
                case 4:
                    mPrst.buscarComputador(computador, estIng, sc,v);
                    
                    break;

                default:
                    System.out.println("Regresando al menú Principal");
                    banderamenu = false;
                    break;
            }
        }
    }

    public static void menuRegistrar(Scanner sc, LinkedList<ObjComp_portatil> computadores,
            LinkedList<objEst_dis> Est_dis, LinkedList<objEst_ing> Est_ing,
            LinkedList<ObjTableta_grafica> Tabl_graf, Validadores v, metodosregistro m,
            MetodosEditarRegistros mEditar) {
        boolean banderamenu4 = true;
        while (banderamenu4) {
            System.out.println("\n-------REGISTRAR------\n"
                    + "1. REGISTRAR ESTUDIANTE\n"
                    + "2. REGISTRAR DISPOSITIVO\n"
                    + "3. EDITAR/ELIMINAR\n"
                    + "4. VER USUARIOS REGISTRADOS\n"
                    + "5. VOLVER AL MENU PRINCIPAL\n");
            String menu4 = v.validarConRegex("Digite una opcion: ", "^[1-5]$",
                    "entrada invalida ingrese una opcion existente");
            int c = Integer.parseInt(menu4);
            switch (c) {
                case 1:
                    System.out.println("\n1.DISEÑO\n"
                            + "2.INGENIERIA\n"
                            + "3.VOLVER\n");

                    String menu4_1 = v.validarConRegex("Digite una opcion: ", "^[1-3]$",
                            "entrada invalida ingrese una opcion existente");
                    int opcion = Integer.parseInt(menu4_1);

                    if (opcion == 1) {
                        m.Registro_EstDiseño(Est_dis);
                        break;
                    } else if (opcion == 2) {
                        m.Registro_EstIngenieria(Est_ing);
                        break;
                    }

                    System.out.println("Volviendo...");
                    break;

                case 2:
                    System.out.println("\n1.REGISTRAR COMPUTADOR\n"
                            + "2.REGISTRAR TABLET\n"
                            + "3.VOLVER\n");
                    String menu4_2 = v.validarConRegex("Digite una opcion: ", "^[1-3]$",
                            "entrada invalida ingrese una opcion existente");
                    int opcion2 = Integer.parseInt(menu4_2);
                    if (opcion2 == 1) {
                        m.RegistrarComputador(computadores);
                        break;
                    } else if (opcion2 == 2) {
                        m.registrarTableta(Tabl_graf);
                        break;
                    }

                    System.out.println("Volviendo...");
                    break;
                case 3:

                    System.out.println("------MENU EDITAR----" +
                            "\n1.EDITAR EDTUDIANTE DISEÑO\n" +
                            "2.EDITAR EDTUDIANTE INGENIERIA\n" +
                            "3.EDITAR  TABLET\n" +
                            "4.EDITAR  COMPUTADOR\n" +
                            "5.ELIMINAR EDTUDIANTE DISEÑO\n" +
                            "6.ELIMINAR EDTUDIANTE INGENIERIA\n" +
                            "7.ELIMINAR  TABLET\n" +
                            "8.ELIMINAR  COMPUTADOR\n" +
                            "9.VOLVER\n");
                    String entrada = v.validarConRegex(
                            "Opcion: ", "^[1-9]$", "INGRESE UNA OPCION DEL 1 AL 5");
                    int opcionEditar = Integer.parseInt(entrada);
                    switch (opcionEditar) {
                        case 1:
                            mEditar.editarEstudianteDis(Est_dis);

                            break;
                        case 2:
                            mEditar.editarEstudianteIng(Est_ing);
                            break;
                        case 3:
                            mEditar.editarTableta(Tabl_graf);
                            break;
                        case 4:
                            mEditar.editarComp(computadores);
                            break;
                        case 5:
                            mEditar.eliminarEstudianteDis(Est_dis, Tabl_graf);
                            break;
                        case 6:
                            mEditar.eliminarEstudianteIng(Est_ing, computadores);
                            break;
                        case 7:
                            mEditar.eliminarTableta(Tabl_graf, Est_dis);
                            break;
                        case 8:
                            mEditar.eliminarComp(computadores, Est_ing);
                            break;

                        default:
                            System.out.println("Regresando al menu registrar...");
                            break;
                    }

                    break;
                case 4:
                    MetodosMostrarListas.mostrarEstudiantesDis(Est_dis);
                    System.out.println("\n================================================\n"
                            + "================================================\n");
                    MetodosMostrarListas.mostrarEstudiantesIng(Est_ing);
                    break;

                default:
                    System.out.println("Regresando al menú Principal");
                    banderamenu4 = false;
                    break;
            }

        }

    }

    public static void menuEstudiantesDis(ExportarArchivo exp,
            LinkedList<objEst_dis> Est_dis,
            LinkedList<ObjTableta_grafica> Tablets,
            Scanner sc,
            Validadores v,
            MetodosPrestamo mPrst) {
        boolean banderamenu2 = true;
        while (banderamenu2) {
            System.out.println("\n-MENU PARA ESTUDIANTES DE DISEÑO-\n"
                    + "1. REALIZAR PRESTAMO DE EQUIPO\n"
                    + "2. MODIFICAR PRESTAMO DE EQUIPO\n"
                    + "3. DEVOLUCION EQUIPO\n"
                    + "4. BUSCAR EQUIPO\n"
                    + "5. VOLVER AL MENÚ PRINCIPAL");
            String opcionEstDis = v.validarConRegex("\nOpcion: ", "^[1-5]$", "Ingrese una opcion valida");
            int c = Integer.parseInt(opcionEstDis);

            switch (c) {
                case 1:
                    mPrst.prestarTablet(Tablets, Est_dis, v, exp);
                    break;
                case 2:
                    mPrst.modificarPrestamoTablet(Est_dis, Tablets, v, exp, sc);
                    break;
                case 3:
                    mPrst.devolverTablet(Est_dis, Tablets, v, exp);
                    break;
                case 4:
                    mPrst.buscarTablet(Tablets, Est_dis, sc, v);
                    break;
                default:
                    System.out.println("Regresando al menú Principal");
                    banderamenu2 = false;
                    break;
            }
        }
    }
}