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

        computadores = imp.importarArchivoComputadores();
        Est_dis = imp.importarArchivoEstDis();
        Est_ing = imp.importarArchivoEstIng();
        Tabl_graf = imp.importarArchivoTablets();

        int optmenu;

        do {
            System.out.println("---------------------------UNIVERSIDAD SAN JUAN DE DIOS------------------------ \n"
                    + "\n------------------SISTEMA DE GESTION PARA PRESTAMOS DE DISPOSITIVOS------------ \n"
                    + "\n----------------------------------MENÚ PRINCIPAL------------------------------- \n");
            String menu = "\n1. ESTUDIANTES DE INGENIERIA."
                    + "\n2. ESTUDIANTES DE DISEÑO."
                    + "\n3. MOSTRAR INVENTARIO"
                    + "\n4. REGISTRAR USUARIO/DISPOSITIVO"
                    + "\n5. Salir Del Sistema\n";
            String entrada = v.validarConRegex(menu + "\nDigite una opcion: ", "^[1-5]+$",
                    "entrada invalida ingrese una opcion existente");
            optmenu = Integer.parseInt(entrada);

            switch (optmenu) {
                case 1:
                    boolean banderamenu = true;
                    while (banderamenu) {

                        System.out.println("-MENU PARA ESTUDIANTES DE INGENIERIA-\n"
                                + "1. REALIZAR PRESTAMO DE EQUIPO\n"
                                + "2. MODIFICAR PRESTAMO DE EQUIPO\n"
                                + "3. DEVOLUCION EQUIPO\n"
                                + "4. BUSCAR EQUIPO\n"
                                + "5. VOLVER AL MENÚ PRINCIPAL\n");
                        while (!sc.hasNextInt()) {
                            System.out.println("Ingrese una opción válida");
                            sc.next();
                        }
                        int c = sc.nextInt();
                        sc.nextLine();
                        if (c < 1 || c > 5) {
                            System.out.println("Por favor Ingrese una opcion valida");
                            continue;
                        }

                        switch (c) {
                            case 1:

                                break;

                            default:
                                System.out.println("Regresando al menú Principal");
                                banderamenu = false;
                                break;
                        }

                    }

                    break;

                default:
                    System.out
                            .println("Volviendo al menú principal...");
                    break;

                case 2:
                    boolean banderamenu2 = true;
                    while (banderamenu2) {
                        System.out.println("-MENU PARA ESTUDIANTES DE DISEÑO-\n"
                                + "1. REALIZAR PRESTAMO DE EQUIPO\n"
                                + "2. MODIFICAR PRESTAMO DE EQUIPO\n"
                                + "3. DEVOLUCION EQUIPO\n"
                                + "4. BUSCAR EQUIPO\n"
                                + "5. VOLVER AL MENÚ PRINCIPAL\n");
                        while (!sc.hasNextInt()) {
                            System.out.println("Ingrese una opción válida");
                            sc.next();
                        }
                        int c = sc.nextInt();
                        sc.nextLine();
                        if (c < 1 || c > 5) {
                            System.out.println("Por favor Ingrese una opcion valida");
                            continue;
                        }

                        switch (c) {
                            case 1:

                                break;

                            default:
                                System.out.println("Regresando al menú Principal");
                                banderamenu2 = false;
                                break;
                        }

                    }

                    break;
                case 3:
                    boolean banderamenu3 = true;
                    while (banderamenu3) {
                        System.out.println("-REGISTRAR-\n"
                                + "1. REGISTRAR USUARIO\n"
                                + "2. REGISTRAR DISPOSITIVO\n");
                        while (!sc.hasNextInt()) {
                            System.out.println("Ingrese una opción válida");
                            sc.next();
                        }
                        int c = sc.nextInt();
                        sc.nextLine();
                        if (c < 1 || c > 5) {
                            System.out.println("Por favor Ingrese una opcion valida");
                            continue;
                        }

                        switch (c) {
                            case 1:

                                break;

                            default:
                                System.out.println("Regresando al menú Principal");
                                banderamenu3 = false;
                                break;
                        }

                    }

                    break;
                case 4:
                    boolean banderamenu4 = true;
                    while (banderamenu4) {
                        System.out.println("\n-------REGISTRAR------\n"
                                + "1. REGISTRAR ESTUDIANTE\n"
                                + "2. REGISTRAR DISPOSITIVO\n"
                                + "3. EDITAR\n"
                                + "4. VER USUARIOS REGISTRADOS\n"
                                + "5. VOLVER AL MENU PRINCIPAL\n");
                        String menu4 = v.validarConRegex("Digite una opcion: ", "^[1-4]+$",
                                "entrada invalida ingrese una opcion existente");
                        int c = Integer.parseInt(menu4);
                        switch (c) {
                            case 1:
                                System.out.println("\n1.DISEÑO\n"
                                        + "2.INGENIERIA\n"
                                        + "3.VOLVER\n");

                                String menu4_1 = v.validarConRegex("Digite una opcion: ", "^[1-4]+$",
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
                                String menu4_2 = v.validarConRegex("Digite una opcion: \n", "^[1-4]+$",
                                        "entrada invalida ingrese una opcion existente");
                                int opcion2 = Integer.parseInt(menu4_2);
                                if (opcion2 == 1) {
                                    m.Registrar_portatil(computadores);
                                    break;
                                } else if (opcion2 == 2) {
                                    m.registrarTableta(Tabl_graf);
                                    break;
                                }

                                System.out.println("Volviendo...");
                                break;
                            case 3:
                                System.out.println("pagina en mantenimiento");

                                break;

                            default:
                                System.out.println("Regresando al menú Principal");
                                banderamenu4 = false;
                                break;
                        }

                    }

                    break;

            }

        } while (optmenu != 5);

        sc.close();
    }
}