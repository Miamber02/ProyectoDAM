package mvc.modelo.entidades.interfaz;

public interface Usuario {
    String dni = null;
    String nombre = null;
    String apellidos = null;
    String email = null;
    String password = null;

    String getDni();
    String getNombre();
    String getApellidos();
    String getEmail();
}
