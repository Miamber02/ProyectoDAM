package mvc.modelo.interfaz;

import mvc.modelo.entidades.Estudiante;
import mvc.modelo.entidades.Libro;
import mvc.modelo.entidades.Materia;
import mvc.modelo.entidades.Profesor;

import java.io.FileInputStream;
import java.util.ArrayList;


public interface Modelo {
    void crearEstudiante(Estudiante estudiante);

    void crearProfesor(Profesor profesor);

    Estudiante obtenerEstudiante(String dni);

    Profesor obtenerProfesor(String dni);

    void cambiarEmailProfesor(Profesor profesor);

    void cambiarEmailEstudiante(Estudiante estudiante);

    ArrayList<Libro> obtenerLibrosAlquiler();

    void alquilarLibro(int id_libro, int dni_estudiante);

    ArrayList<Libro> obtenerMisLibros(int id_estudiante);

    ArrayList<Materia> obtenerMisAsignaturas(int id_estudiante);

    Profesor obtenerInfoProfesorMateria(int idMateria);

    void devolverLibroAlquilado(int idLibro);

    void cargarLibro(String title, int id_profesor, FileInputStream fileInputStream);

    ArrayList<Libro> obtenerTodosLibros();

    ArrayList<Estudiante> obtenerTodosEstudiantes();

	boolean comprobarDniExistente(String dni);
	
	boolean verificarDNI(String dni);
    
}
