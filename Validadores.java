import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

public class Validadores {

    Scanner sc = new Scanner(System.in);

    public String validarConRegex(String mensajeEntrada, String regex, String mensajeError) {
        String entrada;
        boolean valido = false;

        do {
            System.out.print(mensajeEntrada);
            entrada = sc.nextLine().trim();
            if (entrada.isEmpty()) {
                System.out.println("Ingresa algun dato: ");
                continue;
            }

            if (entrada.matches(regex)) {
                valido = true;
            } else {
                System.out.println(mensajeError);

            }
        } while (!valido);

        return entrada;
    }

    public int validarentero(String mensaje, String mensajeError) {
        String entrada = validarConRegex(mensaje, "^[0-9]+$", mensajeError);
        return Integer.parseInt(entrada);
    }

    public float validarFloat(String mensaje, String mensajeError) {
        String entrada = validarConRegex(mensaje, "^[0-9]+(\\.[0-9]+)?$", mensajeError);
        return Float.parseFloat(entrada);
    }

    public String validarSoloNumeros(String mensaje) {
        return validarConRegex(mensaje, "^[0-9]+$", "Error! Ingrese solo valores numericos");
    }

    public String validarSoloLetras(String mensaje) {
        return validarConRegex(mensaje, "^[a-zA-Z\\s]+$", "Error! Ingrese solo caracteres alfabeticos");
    }

    public String validarNoCaracteresEspeciales(String mensaje) {
        return validarConRegex(mensaje, "^[a-zA-Z0-9]+$", "Error! No ingrese caracteres especiales\ntente nuevamente: ");
    }

    public boolean verificarDocumentoEstudianteIngenieria(String documento){
        ImportarArchivo imp = new ImportarArchivo();
        LinkedList<objEst_ing> ingenieria = imp.importarArchivoEstIng();

        for(objEst_ing e : ingenieria){
            if (e.getCedula().equalsIgnoreCase(documento)){
                System.out.println("El Estudiante existe en el sistema");
                System.out.println(e);
                return true;
            }
                System.out.println("El Usuario no existe en el sistema");
                System.out.println("Si nesesita un prestamo, es nesesario registrar el usuario");
            }
        return false;
    }
}
