public class objEst_dis {
    private String Cedula;
    private String Nombre;
    private String Apellido;
    private String Telefono;
    private String Modalidad;
    private int Cantidad_asignaturas;
    private String Serial;

    public objEst_dis() {
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String cedula) {
        Cedula = cedula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
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

    public String getSerial() {
        return Serial;
    }

    public void setSerial(String serial) {
        Serial = serial;
    }

    public String toString() {
        return "Cedula: " + Cedula + "\n" +
                "Nombre: " + Nombre + "\n" +
                "Apellido: " + Apellido + "\n" +
                "Telefono: " + Telefono + "\n" +
                "Modalidad: " + Modalidad + "\n" +
                "Cantidad de Asignaturas: " + Cantidad_asignaturas + "\n" +
                "Tablet Prestada: " + (Serial.equals("null")|| Serial == null ? "ninguna" : Serial);
    }

}
