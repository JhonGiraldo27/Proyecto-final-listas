public class ObjComp_portatil {
    private String Serial;
    private String Marca;
    private float Tamaño;
    private float Precio;
    private String Sistem_op;
    private String Procesador;
    private boolean Disponible;

    public boolean getDisponible() {
        return Disponible;
    }

    /*public void setDisponible(boolean disponible) {
        Disponible = disponible;
    }*/

    public ObjComp_portatil() {
    }

    public String getSerial() {
        return Serial;
    }

    public void setSerial(String serial) {
        Serial = serial;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public float getTamaño() {
        return Tamaño;
    }

    public void setTamaño(float tamaño) {
        Tamaño = tamaño;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float precio) {
        Precio = precio;
    }

    public String getSistem_op() {
        return Sistem_op;
    }

    public void setSistem_op(String sistem_op) {
        Sistem_op = sistem_op;
    }

    public String getProcesador() {
        return Procesador;
    }

    public void setProcesador(String procesador) {
        Procesador = procesador;
    }

    public void setDisponible(String disponible){
        if (disponible.equalsIgnoreCase("Disponible")) {
            this.Disponible = true;
        } else if (disponible.equalsIgnoreCase("Prestado")) {
            this.Disponible = false;
        }
    }


    public String toString() {
        return "Serial: " + Serial + "\n" +
                "Marca: " + Marca + "\n" +
                "Tamaño: " + Tamaño + " pulgadas\n" +
                "Precio: $" + Precio + "\n" +
                "Sistema Operativo: " + Sistem_op + "\n" +
                "Procesador: " + Procesador + "\n" +
                "Disponible: " + (Disponible ? "Sí" : "No");
    }
}
