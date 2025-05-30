public class objEst_ing extends EstudianteBase {
    private int Semestre_cursado;
    private float Prom_acum;

    public objEst_ing() {
    }

    public int getSemestre_cursado() {
        return Semestre_cursado;
    }

    public void setSemestre_cursado(int semestre_cursado) {
        Semestre_cursado = semestre_cursado;
    }

    public float getProm_acum() {
        return Prom_acum;
    }

    public void setProm_acum(float prom_acum) {
        Prom_acum = prom_acum;
    }

    public String toString() {
        return "Cedula: " + Cedula + "\n" +
                "Nombre: " + Nombre + "\n" +
                "Apellido: " + Apellido + "\n" +
                "Telefono: " + Telefono + "\n" +
                "Semestre: " + Semestre_cursado + "\n" +
                "Promedio: " + Prom_acum + "\n" +
                "Computador Prestado: " + (Serial == null || Serial.equals("null") ? "ninguno" : Serial);
    }
}
