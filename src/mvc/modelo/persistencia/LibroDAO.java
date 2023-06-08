package mvc.modelo.persistencia;

import mvc.modelo.entidades.Estudiante;
import mvc.modelo.entidades.Libro;
import mvc.modelo.entidades.Profesor;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;

public class LibroDAO {
    private Connection connection;
    private String cadenaConexion = "jdbc:mysql://localhost:3306/proyecto_miguel";
    private String user = "root";
    private String pass = "";

    public LibroDAO() {
        crearConexion();
    }

    public void crearConexion() {
        try {
            connection = DriverManager.getConnection(cadenaConexion, user, pass);
        } catch (Exception e) {

        }
    }

    public void cerrarConexeion() {
        try {
            connection.close();
        } catch (Exception e) {

        }
    }

    public Libro getLibroById(int idLibro) throws SQLException {
        String query = "{call obtener_libro_por_id(?)}";
        Libro libro = null;
        Estudiante estudiante = null;
        
        try (CallableStatement statement = connection.prepareCall(query)) {
            statement.setInt(1, idLibro);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                libro = new Libro();
                estudiante = new Estudiante();
                libro.setIdLibro(resultSet.getInt("id"));
                libro.setTitulo(resultSet.getString("titulo"));
                estudiante.setNombre(resultSet.getString("nombre"));
                libro.setEstudiante(estudiante);
            }
        }

        return libro;
    }

    public void insertLibro(String title, int id_profesor, FileInputStream fileInputStream) {
        String query = "{call insertar_libro(?, ?, ?)}";

        try (CallableStatement statement = connection.prepareCall(query)) {
            statement.setString(1, title);
            statement.setBinaryStream(2, fileInputStream);
            statement.setInt(3, id_profesor);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateLibro(int id_libro, int id_estudiante) {
        String query = "{call actualizar_libro(?, ?)}";

        try (CallableStatement statement = connection.prepareCall(query)) {
            statement.setInt(1, id_libro);
            statement.setInt(2, id_estudiante);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void devolverLibro(int id_libro) {
        String query = "{call devolver_libro(?)}";

        try (CallableStatement statement = connection.prepareCall(query)) {
            statement.setInt(1, id_libro);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteLibro(int idLibro) throws SQLException {
        String query = "{call eliminar_libro(?)}";

        try (CallableStatement statement = connection.prepareCall(query)) {
            statement.setInt(1, idLibro);
            statement.executeUpdate();
        }
    }

    public ArrayList<Libro> getLibrosAlquiler() {
        String query = "{call obtener_libros_alquiler()}";
        ArrayList<Libro> libros = new ArrayList<>();

        try (CallableStatement statement = connection.prepareCall(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	Profesor profesor = new Profesor();
                Libro libro = new Libro();
                libro.setIdLibro(resultSet.getInt("id"));
                libro.setTitulo(resultSet.getString("titulo"));
                profesor.setNombre(resultSet.getString("nombre_profesor"));
                libro.setProfesor(profesor);
                libros.add(libro);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return libros;
    }

    public ArrayList<Libro> getMisLibros(int id_estudiante) {
        String query = "{call obtener_mis_libros(?)}";
        ArrayList<Libro> libros = new ArrayList<>();

        try (CallableStatement statement = connection.prepareCall(query)) {
            statement.setInt(1, id_estudiante);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Libro libro = new Libro();
                libro.setIdLibro(resultSet.getInt("id"));
                libro.setTitulo(resultSet.getString("titulo"));
                libro.setTexto(resultSet.getString("archivo_texto"));
                libros.add(libro);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return libros;
    }

    public ArrayList<Libro> getLibros() {
        String query = "{call obtener_libros()}";
        ArrayList<Libro> libros = new ArrayList<>();
        
        try (CallableStatement statement = connection.prepareCall(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	Estudiante estudiante = new Estudiante();
                Libro libro = new Libro();
                libro.setIdLibro(resultSet.getInt("id"));
                libro.setTitulo(resultSet.getString("titulo"));
                estudiante.setNombre(resultSet.getString("nombre_estudiante"));
                libro.setEstudiante(estudiante);
                libros.add(libro);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return libros;
    }
}
