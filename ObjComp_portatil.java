public class ObjComp_portatil {
    private String Serial;
    private String Marca;
    private float Tamaño;
    private float Precio;
    private String Sistem_op;
    private String Procesador;
    private boolean Disponible;
    public boolean isDisponible() {
        return Disponible;
    }
    public void setDisponible(boolean disponible) {
        Disponible = disponible;
    }
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
    
}
