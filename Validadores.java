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
        return validarConRegex(mensaje, "^[a-zA-Z\\s]+$", "Ingrese solo caracteres alfabeticos");
    }

    public String validarNoCaracteresEspeciales(String mensaje) {
        return validarConRegex(mensaje, "^[a-zA-Z0-9]+$", "No ingrese caracteres especiales\nntente nuevamente: ");
    }
}
