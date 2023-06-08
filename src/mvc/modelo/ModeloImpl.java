package mvc.modelo;

import mvc.modelo.entidades.Estudiante;
import mvc.modelo.entidades.Libro;
import mvc.modelo.entidades.Materia;
import mvc.modelo.entidades.Profesor;
import mvc.modelo.interfaz.Modelo;
import mvc.modelo.persistencia.*;

import java.io.FileInputStream;
import java.util.ArrayList;

public class ModeloImpl implements Modelo {
    private EstudianteDAO estudianteDAO;
    private LibroDAO libroDAO;
    private MateriaDAO materiaDAO;
    private ProfesorDAO profesorDAO;

    public ModeloImpl() {
        this.estudianteDAO = new EstudianteDAO();
        this.libroDAO = new LibroDAO();
        this.materiaDAO = new MateriaDAO();
        this.profesorDAO = new ProfesorDAO();
    }

    @Override
    public void crearEstudiante(Estudiante estudiante) {
    	estudianteDAO.insertEstudiante(estudiante);
    }

    @Override
    public void crearProfesor(Profesor profesor) {
        profesorDAO.insertProfesor(profesor);
    }

    @Override
    public Estudiante obtenerEstudiante(String dni) {
        return estudianteDAO.getEstudianteByDni(dni);
    }

    @Override
    public Profesor obtenerProfesor(String dni) {
        return profesorDAO.getProfesorByDni(dni);
    }

    @Override
    public void cambiarEmailProfesor(Profesor profesor) {
        profesorDAO.updateProfesor(profesor);
    }

    @Override
    public void cambiarEmailEstudiante(Estudiante estudiante) {
        estudianteDAO.updateEstudiante(estudiante);
    }

    @Override
    public ArrayList<Libro> obtenerLibrosAlquiler() {
        return libroDAO.getLibrosAlquiler();
    }

    @Override
    public void alquilarLibro(int id_libro, int id_estudiante) {
        libroDAO.updateLibro(id_libro, id_estudiante);
    }

    @Override
    public ArrayList<Libro> obtenerMisLibros(int id_estudiante) {
        return libroDAO.getMisLibros(id_estudiante);
    }

    @Override
    public ArrayList<Materia> obtenerMisAsignaturas(int id_estudiante) {
        return materiaDAO.getMisMaterias(id_estudiante);
    }

    @Override
    public Profesor obtenerInfoProfesorMateria(int idMateria) {
        int idProfesor = materiaDAO.getDniProfesorByIdMateria(idMateria);
        return profesorDAO.getProfesorById(idProfesor);
    }

    @Override
    public void devolverLibroAlquilado(int idLibro) {
        libroDAO.devolverLibro(idLibro);
    }

    @Override
    public void cargarLibro(String title, int id_profesor, FileInputStream fileInputStream) {
        libroDAO.insertLibro(title, id_profesor, fileInputStream);
    }

    @Override
    public ArrayList<Libro> obtenerTodosLibros() {
        return libroDAO.getLibros();
    }

    @Override
    public ArrayList<Estudiante> obtenerTodosEstudiantes() {
        return estudianteDAO.getEstudiantes();
    }
    
    @Override
    public boolean comprobarDniExistente(String dni) {
        return estudianteDAO.comprobarDniExistente(dni) || profesorDAO.comprobarDniExistente(dni);
    }
    
    @Override
    public boolean verificarDNI(String dni) {
    	 if (dni.length() != 9) {
             return false; // El DNI debe tener exactamente 9 caracteres
         }
         
         String numero = dni.substring(0, 8); // Obtener los 8 primeros dígitos
         String letra = dni.substring(8); // Obtener la letra
         
         try {
             int num = Integer.parseInt(numero);
             char letraCalculada = calcularLetraDNI(num); // Calcular la letra esperada
             
             return letra.equalsIgnoreCase(Character.toString(letraCalculada));
         } catch (NumberFormatException e) {
             return false; // Si los primeros 8 caracteres no son un número válido, el DNI no es válido
         }
     }
     
     private static char calcularLetraDNI(int numero) {
         String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
         int indice = numero % 23;
         
         return letras.charAt(indice);
    }
    
}
