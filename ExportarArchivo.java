import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class ExportarArchivo {

    public void exportarArchivoComp(LinkedList<ObjComp_portatil> computador) {
        if (computador.isEmpty()) {
            System.out.println("Lista vacia por favor verificar");

        } else {
            try (FileWriter escriba = new FileWriter("listaComputadores.txt")) {
                for (ObjComp_portatil item : computador) {
                    escriba.write("Marca: " + item.getMarca() + "\n");
                    escriba.write("Precio: " + item.getPrecio() + "\n");
                    escriba.write("Procesador: " + item.getProcesador() + "\n");
                    escriba.write("Serial: " + item.getSerial() + "\n");
                    escriba.write("Sistema Operativo: " + item.getSistem_op() + "\n");
                    escriba.write("Tamaño: " + item.getTamaño() + "\n");
                    escriba.write("Disponible : " + item.isDisponible() + "\n");                    
                    escriba.write("---------------------------------------\n");

                }

                System.out.println("Registro realizado correctamente");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void exportarArchivoTablets(LinkedList<ObjTableta_grafica> Tablet) {
        if (Tablet.isEmpty()) {
            System.out.println("Lista vacia por favor verificar");

        } else {
            try (FileWriter escriba = new FileWriter("listaTablet.txt")) {
                for (ObjTableta_grafica item : Tablet) {
                    escriba.write("Marca: " + item.getMarca() + "\n");
                    escriba.write("Precio: " + item.getPrecio() + "\n");
                    escriba.write("Peso: " + item.getPeso() + "\n");
                    escriba.write("Serial: " + item.getSerial() + "\n");
                    escriba.write("Tamaño: " + item.getTamaño() + "\n");
                    escriba.write("Almacenamiento: " + item.getAlmacenamiento() + "\n");
                    escriba.write("Disponible : " + item.isDisponible() + "\n");
                    escriba.write("---------------------------------------\n");

                }

                System.out.println("Registro realizado correctamente");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void exportarArchivoEstIng(LinkedList<objEst_ing> ingenieria) {
        if (ingenieria.isEmpty()) {
            System.out.println("Lista vacia por favor verificar");

        } else {
            try (FileWriter escriba = new FileWriter("listaEstudianteIng.txt")) {
                for (objEst_ing item : ingenieria) {

                    escriba.write("Nombre: " + item.getNombre() + "\n");
                    escriba.write("Apellido: " + item.getApellido() + "\n");
                    escriba.write("Cedula: " + item.getCedula() + "\n");
                    escriba.write("Telefono: " + item.getTelefono() + "\n");
                    escriba.write("Semeste Cursado: " + item.getSemestre_cursado() + "\n");
                    escriba.write("Prom Acumulado: " + item.getProm_acum() + "\n");
                    escriba.write("Serial: " + item.getSerial() + "\n");
                    escriba.write("---------------------------------------\n");

                }

                System.out.println("Registro realizado correctamente");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void exportarArchivoEstDis(LinkedList<objEst_dis> diseño) {
        if (diseño.isEmpty()) {
            System.out.println("Lista vacia por favor verificar");

        } else {
            try (FileWriter escriba = new FileWriter("listaEstudianteDiseño.txt")) {
                for (objEst_dis item : diseño) {

                    escriba.write("Nombre: " + item.getNombre() + "\n");
                    escriba.write("Apellido: " + item.getApellido() + "\n");
                    escriba.write("Cedula: " + item.getCedula() + "\n");
                    escriba.write("Telefono: " + item.getTelefono() + "\n");
                    escriba.write("Modalidad: " + item.getModalidad() + "\n");
                    escriba.write("Cantidad de asignaturas: " + item.getCantidad_asignaturas() + "\n");
                    escriba.write("Serial : " + item.getSerial() + "\n");
                    escriba.write("---------------------------------------\n");

                }

                System.out.println("Registro realizado correctamente");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
