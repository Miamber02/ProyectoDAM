package mvc.controlador.interfaz;

import mvc.modelo.entidades.Estudiante;
import mvc.modelo.entidades.Libro;
import mvc.modelo.entidades.Materia;
import mvc.modelo.entidades.Profesor;
import mvc.modelo.interfaz.Modelo;

import java.io.FileInputStream;
import java.util.ArrayList;

public interface Controlador {

    void mostrarLogin();

    void cerrarLogin();

    void mostrarCrearUsuario();

    void cerrarCrearUsuario();

    void mostrarPerfilAlumno();

    void cerrarPerfilAlumno();

    void mostrarPerfilProfesor();

    void cerrarPerfilProfesor();

    void mostrarLibrosAlquiler(int id_estudiante);

    void cerrarLibrosAlquiler();

    void mostrarMisLibros(int id_estudiante);

    void cerrarMisLibros();

    void mostrarMisMaterias(int id_estudiante);

    void cerrarMisAsignaturas();

    void mostrarCargaLibros(int id_profesor);

    void cerrarCargaLibros();

    void mostrarTodosLibros();

    void cerrarTodosLibros();

    void mostrarVerEstudiantes();

    void cerrarVerEstudiantes();

    boolean login(String dni_estudiante, String password, boolean esProfesor);
          
    boolean pedirCrearUsuario(String dni, String nombre, String apellidos, String email, String password, boolean esProfesor);

    void cambiarEmailProfesor(Profesor profesor);

    void cambiarEmailEstudiante(Estudiante estudiante);

    ArrayList<Libro> getLibrosAlquiler();

    void alquilarLibro(int id_libro, int id_estudiante);

    ArrayList<Libro> getMisLibros(int id_estudiante);

    ArrayList<Libro> getTodosLibros();

    ArrayList<Materia> getMisMaterias(int id_estudiante);

    ArrayList<Estudiante> getTodosEstudiantes();

    void mostrarInfoProfesor(int idMateria);

    void devolverLibroAlquilado(int idLibro);

    void cargarLibro(String title, int id_profesor, FileInputStream fileInputStream);

}
