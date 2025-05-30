public class objEst_dis extends EstudianteBase {
    private String Modalidad;
    private int Cantidad_asignaturas;

    public objEst_dis() {
    }

    public String getModalidad() {
        return Modalidad;
    }

    public void setModalidad(String modalidad) {
        Modalidad = modalidad;
    }

    public int getCantidad_asignaturas() {
        return Cantidad_asignaturas;
    }

    public void setCantidad_asignaturas(int cantidad_asignaturas) {
        Cantidad_asignaturas = cantidad_asignaturas;
    }

    public String toString() {
        return "Cedula: " + Cedula + "\n" +
                "Nombre: " + Nombre + "\n" +
                "Apellido: " + Apellido + "\n" +
                "Telefono: " + Telefono + "\n" +
                "Modalidad: " + Modalidad + "\n" +
                "Cantidad de Asignaturas: " + Cantidad_asignaturas + "\n" +
                "Tablet Prestada: " + (Serial == null ? "ninguna" : Serial);
    }
}
