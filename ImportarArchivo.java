
    
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class ImportarArchivo{

   public LinkedList<objEst_dis> importarArchivoEstDis() {
    LinkedList<objEst_dis> listaDiseño = new LinkedList<>();
    String rutaArchivo = "listaEstudianteDiseño.txt";

    try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
        String linea;
        objEst_dis estudiante = null;

        while ((linea = br.readLine()) != null) {
            if (linea.startsWith("Nombre: ")) {
                estudiante = new objEst_dis();
                estudiante.setNombre(linea.substring(8));
            } else if (linea.startsWith("Apellido: ")) {
                if (estudiante != null) {
                    estudiante.setApellido(linea.substring(9));
                }
            } else if (linea.startsWith("Cedula: ")) {
                if (estudiante != null) {
                    estudiante.setCedula(linea.substring(8));
                }
            } else if (linea.startsWith("Telefono: ")) {
                if (estudiante != null) {
                    estudiante.setTelefono(linea.substring(10));
                }
            } else if (linea.startsWith("Modalidad: ")) {
                if (estudiante != null) {
                    estudiante.setModalidad(linea.substring(11));
                }
            } else if (linea.startsWith("Cantidad de asignaturas: ")) {
                if (estudiante != null) {
                    estudiante.setCantidad_asignaturas(Integer.parseInt(linea.substring(25)));
                }
            } else if (linea.startsWith("Serial : ")) {
                if (estudiante != null) {
                    estudiante.setSerial(linea.substring(9));
                    listaDiseño.add(estudiante);
                }
            }
        }

        System.out.println("Estudiantes de Diseño importados correctamente.");
    } catch (IOException e) {
        e.printStackTrace();
    }

    return listaDiseño;
}

public LinkedList<objEst_ing> importarArchivoEstIng() {
    LinkedList<objEst_ing> listaIngenieria = new LinkedList<>();
    String rutaArchivo = "listaEstudianteIng.txt";

    try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
        String linea;
        objEst_ing estudiante = null;

        while ((linea = br.readLine()) != null) {
            if (linea.startsWith("Nombre: ")) {
                estudiante = new objEst_ing();
                estudiante.setNombre(linea.substring(8));
            } else if (linea.startsWith("Apellido: ")) {
                if (estudiante != null) {
                    estudiante.setApellido(linea.substring(9));
                }
            } else if (linea.startsWith("Cedula: ")) {
                if (estudiante != null) {
                    estudiante.setCedula(linea.substring(8));
                }
            } else if (linea.startsWith("Telefono: ")) {
                if (estudiante != null) {
                    estudiante.setTelefono(linea.substring(10));
                }
            } else if (linea.startsWith("Semeste Cursado: ")) {
                if (estudiante != null) {
                    estudiante.setSemestre_cursado(Integer.parseInt(linea.substring(18)));
                }
            } else if (linea.startsWith("Prom Acumulado: ")) {
                if (estudiante != null) {
                    estudiante.setProm_acum(Float.parseFloat(linea.substring(16)));
                }
            } else if (linea.startsWith("Serial: ")) {
                if (estudiante != null) {
                    estudiante.setSerial(linea.substring(7));
                    listaIngenieria.add(estudiante);
                }
            }
        }

        System.out.println("Estudiantes de Ingeniería importados correctamente.");
    } catch (IOException e) {
        e.printStackTrace();
    }

    return listaIngenieria;
}

public LinkedList<ObjComp_portatil> importarArchivoComputadores() {
    LinkedList<ObjComp_portatil> listaComputadores = new LinkedList<>();
    String rutaArchivo = "listaComputadores.txt";

    try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
        String linea;
        ObjComp_portatil comp = null;

        while ((linea = br.readLine()) != null) {
            if (linea.startsWith("Marca: ")) {
                comp = new ObjComp_portatil();
                comp.setMarca(linea.substring(7).trim());
            } else if (linea.startsWith("Precio: ")) {
                comp.setPrecio(Float.parseFloat(linea.substring(8).trim()));
            } else if (linea.startsWith("Procesador: ")) {
                comp.setProcesador(linea.substring(12).trim());
            } else if (linea.startsWith("Serial: ")) {
                comp.setSerial(linea.substring(7).trim());
            } else if (linea.startsWith("Sistema Operativo: ")) {
                comp.setSistem_op(linea.substring(19).trim());
            } else if (linea.startsWith("Tamaño: ")) {
                comp.setTamaño(Float.parseFloat(linea.substring(8).trim()));
            } else if (linea.startsWith("Disponible : ")) {
                comp.setDisponible(Boolean.parseBoolean(linea.substring(12).trim()));
            } else if (linea.startsWith("---------------------------------------")) {
                if (comp != null) {
                    listaComputadores.add(comp);
                    comp = null;
                }
            }
        }

        // Por si no termina con la línea separadora
        if (comp != null) {
            listaComputadores.add(comp);
        }

        System.out.println("Computadores importados correctamente.");
    } catch (IOException e) {
        e.printStackTrace();
    }

    return listaComputadores;
}

public LinkedList<ObjTableta_grafica> importarArchivoTablets() {
    LinkedList<ObjTableta_grafica> listaTablets = new LinkedList<>();
    String rutaArchivo = "listaTablet.txt";

    try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
        String linea;
        ObjTableta_grafica tablet = null;

        while ((linea = br.readLine()) != null) {
            if (linea.startsWith("Marca: ")) {
                tablet = new ObjTableta_grafica();
                tablet.setMarca(linea.substring(7).trim());
            } else if (linea.startsWith("Precio: ")) {
                tablet.setPrecio(Float.parseFloat(linea.substring(8).trim()));
            } else if (linea.startsWith("Peso: ")) {
                tablet.setPeso(Float.parseFloat(linea.substring(6).trim()));
            } else if (linea.startsWith("Serial: ")) {
                tablet.setSerial(linea.substring(7).trim());
            } else if (linea.startsWith("Tamaño: ")) {
                tablet.setTamaño(Float.parseFloat(linea.substring(8).trim()));
            } else if (linea.startsWith("Almacenamiento: ")) {
                tablet.setAlmacenamiento(linea.substring(15).trim());
            } else if (linea.startsWith("Disponible : ")) {
                tablet.setDisponible(Boolean.parseBoolean(linea.substring(12).trim()));
            } else if (linea.startsWith("---------------------------------------")) {
                if (tablet != null) {
                    listaTablets.add(tablet);
                    tablet = null;
                }
            }
        }

        // Por si no termina con la línea separadora
        if (tablet != null) {
            listaTablets.add(tablet);
        }

        System.out.println("Tabletas gráficas importadas correctamente.");
    } catch (IOException e) {
        e.printStackTrace();
    }

    return listaTablets;
}
}
    

