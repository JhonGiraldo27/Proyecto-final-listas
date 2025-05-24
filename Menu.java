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

        computadores = imp.importarArchivoComputadores();
        Est_dis = imp.importarArchivoEstDis();
        Est_ing = imp.importarArchivoEstIng();
        Tabl_graf = imp.importarArchivoTablets();
        MetodosPrestamo mPrst = new MetodosPrestamo();

        int optmenu;
        System.out.println("---------------------------UNIVERSIDAD SAN JUAN DE DIOS------------------------ \n"
                + "\n------------------SISTEMA DE GESTION PARA PRESTAMOS DE DISPOSITIVOS------------");

        do {
            System.out.println("\n----------------------------------MENÚ PRINCIPAL-------------------------------");
            System.out.println("\n1. ESTUDIANTES DE INGENIERIA."
                    + "\n2. ESTUDIANTES DE DISEÑO."
                    + "\n3. MOSTRAR INVENTARIO"
                    + "\n4. REGISTRAR USUARIO/DISPOSITIVO"
                    + "\n5. Salir Del Sistema\n");
            String entrada = v.validarConRegex("Digite una opcion: ", "^[1-5]$",
                    "Error entrada no nvalida ingrese una opcion existente");
            optmenu = Integer.parseInt(entrada);

            switch (optmenu) {
                case 1:
                    menuEstudiantesIngenieria(sc, v, mPrst);
                    break;

                case 2:
                    menuEstudiantesDis(exp, Est_dis, Tabl_graf, sc, v, mPrst);;

                    break;

                case 3:
                    MetodosMostrarListas.mostrarComputadores(computadores);
                    System.out.println("\n================================================\n"
                            + "================================================\n");
                    MetodosMostrarListas.mostrarTablets(Tabl_graf);
                    break;
                case 4:
                    menuRegistrar(sc, computadores, Est_dis, Est_ing, Tabl_graf, v, m, mEditar); //aqui tambien

                default:
                    System.out
                            .println("SALIENDO DEL SISTEMA...");
                    break;

            }

        } while (optmenu != 5);

        sc.close();
    }

    public static void menuEstudiantesIngenieria(Scanner sc, Validadores v, MetodosPrestamo mPrst) {

        boolean banderamenu = true;
        while (banderamenu) {
            System.out.println("\n-MENU PARA ESTUDIANTES DE INGENIERIA-\n"
                    + "1. REALIZAR PRESTAMO DE EQUIPO\n"
                    + "2. MODIFICAR PRESTAMO DE EQUIPO\n"
                    + "3. DEVOLUCION EQUIPO\n"
                    + "4. BUSCAR EQUIPO\n"
                    + "5. VOLVER AL MENÚ PRINCIPAL\n");
            String optmenuing = v.validarConRegex("Opcion: ", "^[1-5]$", "Error! Ingrese una opcion valida.");
            int c = Integer.parseInt(optmenuing);

            switch (c) {
                case 1:
                    //Ok procedo a preguntar los datos como CC y nombre, curso
                    Validadores validador = new Validadores();
                    Integer documento = validador.validarentero("Ingrese el número de documento", "Error, Ingrese solamente Números");
                    while(documento.toString().length() > 10){
                        System.out.println("El documento Solamente debe contener maximo 10 caracteres. \n ingrese nuevamente el Número de documento ");
                        documento = validador.validarentero("Ingrese el número de documento", "Error, Ingrese solamente Números");
                    }
                    //ahora debo verificar si el estudiante existe en la base de dato
                    boolean existe = validador.verificarDocumentoEstudianteIngenieria(documento.toString());
                    if(existe){
                        int opt = validador.validarentero("Que Equipo desea prestar? " + " \n " + "[1] Computador \n [2] Tablet \n ", "Eleccion Incorrecta");
                        while (opt > 2){
                            System.out.println("Eleccion Incorrecta");
                            opt = validador.validarentero("Que Equipo desea prestar? " + " \n " + "[1] Computador \n [2] Tablet \n ", "Eleccion Incorrecta");
                        }
                        if (opt == 1){
                            // Implementar el préstamo de computador
                            ImportarArchivo importarArchivo = new ImportarArchivo();
                            LinkedList<ObjComp_portatil> computadores_list = importarArchivo.importarArchivoComputadores();
                            if (!computadores_list.isEmpty()){
                                System.out.println("COMPUTADORES DISPONIBLES:");
                                for (ObjComp_portatil o : computadores_list) {
                                    System.out.println(o);
                                }
                                String serialComputador = v.validarConRegex("Ingrese el serial del computador a prestar: ", "^[A-Za-z0-9-]+$", "Error! Ingrese un serial válido.");
                                mPrst.prestarComputadorAIngenieria(computadores_list, documento.toString(), serialComputador);
                            } else {
                                System.out.println("No hay computadores disponibles para préstamo.");
                            }
                        } else if (opt == 2) {
                            MetodosPrestamo metodosPrestamo = new MetodosPrestamo();
                            //importar las tablets disponibles
                            ImportarArchivo importarArchivo = new ImportarArchivo();
                            LinkedList<ObjTableta_grafica> tablets_list = importarArchivo.importarArchivoTablets();
                            if (!tablets_list.isEmpty()){
                                System.out.println("TABLETS DISPONIBLES:");
                                for (ObjTableta_grafica t : tablets_list) {
                                    System.out.println(t);
                                }
                                
                                String serialTablet = v.validarConRegex("Ingrese el serial de la tablet a prestar: ", "^[A-Za-z0-9-]+$", "Error! Ingrese un serial válido.");
                                mPrst.prestarTabletAIngenieria(tablets_list, documento.toString(), serialTablet);
                            } else {
                                System.out.println("No hay tablets disponibles para préstamo.");
                            }
                        }
                    } else {
                        System.out.println("El estudiante con documento " + documento + " no está registrado en el sistema.");
                    }
                    break;
                case 2:
                    // Modificar préstamo de equipo
                    documento = v.validarentero("Ingrese el número de documento del estudiante: ", "Error, Ingrese solamente Números");
                    while(documento.toString().length() > 10){
                        System.out.println("El documento Solamente debe contener maximo 10 caracteres. \n ingrese nuevamente el Número de documento ");
                        documento = sc.nextInt();
                    }
                    
                    existe = v.verificarDocumentoEstudianteIngenieria(documento.toString());
                    if(existe){
                        System.out.println("¿Qué tipo de préstamo desea modificar?");
                        int optMod = v.validarentero("Seleccione una opción: \n[1] Préstamo de Computador \n[2] Préstamo de Tablet", "Elección Incorrecta");
                        while (optMod > 2){
                            System.out.println("Elección Incorrecta");
                            optMod = v.validarentero("Seleccione una opción: \n[1] Préstamo de Computador \n[2] Préstamo de Tablet", "Elección Incorrecta");
                        }
                        
                        if (optMod == 1) {
                            mPrst.modificarPrestamoComputador(documento.toString());
                        } else {
                            mPrst.modificarPrestamoTablet(documento.toString());
                        }
                    } else {
                        System.out.println("El estudiante con documento " + documento + " no está registrado en el sistema.");
                    }
                    break;
                case 3:
                    // Devolución de equipo
                    documento = v.validarentero("Ingrese el número de documento del estudiante: ", "Error, Ingrese solamente Números");
                    while(documento.toString().length() > 10){
                        System.out.println("El documento Solamente debe contener maximo 10 caracteres. \n ingrese nuevamente el Número de documento ");
                        documento = sc.nextInt();
                    }
                    
                    existe = v.verificarDocumentoEstudianteIngenieria(documento.toString());
                    if(existe){
                        System.out.println("¿Qué tipo de equipo desea devolver?");
                        int optDev = v.validarentero("Seleccione una opción: \n[1] Computador \n[2] Tablet", "Elección Incorrecta");
                        while (optDev > 2){
                            System.out.println("Elección Incorrecta");
                            optDev = v.validarentero("Seleccione una opción: \n[1] Computador \n[2] Tablet", "Elección Incorrecta");
                        }
                        
                        if (optDev == 1) {
                            String serialEquipo = v.validarConRegex("Ingrese el serial del computador a devolver: ", "^[A-Za-z0-9-]+$", "Error! Ingrese un serial válido.");
                            mPrst.devolverComputador(documento.toString(), serialEquipo);
                        } else {
                            String serialEquipo = v.validarConRegex("Ingrese el serial de la tablet a devolver: ", "^[A-Za-z0-9-]+$", "Error! Ingrese un serial válido.");
                            mPrst.devolverTablet(documento.toString(), serialEquipo);
                        }
                    } else {
                        System.out.println("El estudiante con documento " + documento + " no está registrado en el sistema.");
                    }
                    break;
                case 4:
                    // Buscar equipo
                    System.out.println("¿Qué desea buscar?");
                    int optBusqueda = v.validarentero("Seleccione una opción: \n[1] Equipos disponibles \n[2] Equipos prestados \n[3] Buscar por serial", "Elección Incorrecta");
                    while (optBusqueda > 3){
                        System.out.println("Elección Incorrecta");
                        optBusqueda = v.validarentero("Seleccione una opción: \n[1] Equipos disponibles \n[2] Equipos prestados \n[3] Buscar por serial", "Elección Incorrecta");
                    }
                    
                    switch(optBusqueda) {
                        case 1:
                            System.out.println("¿Qué tipo de equipo?");
                            int optTipo = v.validarentero("Seleccione una opción: \n[1] Computadores \n[2] Tablets", "Elección Incorrecta");
                            if (optTipo == 1) {
                                mPrst.mostrarComputadoresDisponibles();
                            } else {
                                mPrst.mostrarTabletsDisponibles();
                            }
                            break;
                        case 2:
                            MetodosMostrarListas mostrarListas = new MetodosMostrarListas();

                            break;
                        case 3:
                            String serialBusqueda = v.validarConRegex("Ingrese el serial a buscar: ", "^[A-Za-z0-9-]+$", "Error! Ingrese un serial válido.");
                            mPrst.buscarEquipoPorSerial(serialBusqueda);
                            break;
                    }
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
                    + "3. EDITAR\n"
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
                    String menu4_2 = v.validarConRegex("Digite una opcion: \n", "^[1-3]$",
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

                    System.out.println("------MENU EDITAR----" +
                            "\n1.EDITAR EDTUDIANTE DISEÑO\n" +
                            "2.EDITAR EDTUDIANTE INGENIERIA\n" +
                            "3.EDITAR DISPOSITIVO TABLET\n" +
                            "4.EDITAR DISPOSITIVO COMPUTADOR\n" +
                            "5.VOLVER\n");
                    String entrada = v.validarConRegex(
                            "Opcion: ", "^[1-5]$", "ERROR! INGRESE UNA OPCION DEL 1 AL 5");
                    int opcionEditar = Integer.parseInt(entrada);
                    switch (opcionEditar) {
                        case 1:
                            mEditar.editarEstudianteDis(Est_dis); //aqui parte una excepcion

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

    public static void menuEstudiantesDis(ExportarArchivo exp,LinkedList<objEst_dis> Est_dis,LinkedList<ObjTableta_grafica> Tablets,Scanner sc, Validadores v, MetodosPrestamo mPrst) {
        boolean banderamenu2 = true;
        while (banderamenu2) {
            System.out.println("\n-MENU PARA ESTUDIANTES DE DISEÑO-\n"
                    + "1. REALIZAR PRESTAMO DE EQUIPO\n"
                    + "2. MODIFICAR PRESTAMO DE EQUIPO\n"
                    + "3. DEVOLUCION EQUIPO\n"
                    + "4. BUSCAR EQUIPO\n"
                    + "5. VOLVER AL MENÚ PRINCIPAL");
            String opcionEstDis = v.validarConRegex("\nOpcion: ", "^[1-5]$", "Error! Ingrese una opcion valida");
            int c = Integer.parseInt(opcionEstDis);

            switch (c) {
                case 1:
                    mPrst.prestarTabletADiseno(Tablets, Est_dis, v, exp);;
                    break;
                // falta aqui lo que tiene que hacer
                case 2:
                    // mPrst.();
                    break;
                case 3:
                    // mPrst.();
                    break;
                case 4:
                    // mPrst.();
                    break;

                default:
                    System.out.println("Regresando al menú Principal");
                    banderamenu2 = false;
                    break;
            }

        }

    }
}