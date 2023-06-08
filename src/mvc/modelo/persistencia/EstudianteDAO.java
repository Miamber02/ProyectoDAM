package mvc.modelo.persistencia;

import mvc.modelo.entidades.Estudiante;

import java.sql.*;
import java.util.ArrayList;

public class EstudianteDAO {
    private Connection connection;
    private String cadenaConexion = "jdbc:mysql://localhost:3306/proyecto_miguel";
    private String user = "root";
    private String pass = "";

    public EstudianteDAO() {
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

    public Estudiante getEstudianteByDni(String dni) {
        String query = "{call obtener_estudiante_por_dni(?)}";
        Estudiante estudiante = null;

        try (CallableStatement statement = connection.prepareCall(query)) {
            statement.setString(1, dni);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                estudiante = new Estudiante();
                estudiante.setId(resultSet.getInt("id"));
                estudiante.setDni(resultSet.getString("dni"));
                estudiante.setNombre(resultSet.getString("nombre"));
                estudiante.setApellidos(resultSet.getString("apellidos"));
                estudiante.setEmail(resultSet.getString("email"));
                estudiante.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return estudiante;
    }

    public boolean insertEstudiante(Estudiante estudiante) {
        String query = "{call insertar_estudiante(?, ?, ?, ?, ?)}";

        try (CallableStatement statement = connection.prepareCall(query)) {
            statement.setString(1, estudiante.getDni());
            statement.setString(2, estudiante.getNombre());
            statement.setString(3, estudiante.getApellidos());
            statement.setString(4, estudiante.getEmail());
            statement.setString(5, estudiante.getPassword());
            statement.executeUpdate();

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public void updateEstudiante(Estudiante estudiante) {
        String query = "{call actualizar_estudiante(?, ?, ?, ?, ?)}";

        try (CallableStatement statement = connection.prepareCall(query)) {
            statement.setString(1, estudiante.getDni());
            statement.setString(2, estudiante.getNombre());
            statement.setString(3, estudiante.getApellidos());
            statement.setString(4, estudiante.getEmail());
            statement.setString(5, estudiante.getPassword());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<Estudiante> getEstudiantes() {
        String query = "{call obtener_estudiantes()}";
        ArrayList<Estudiante> estudiantes = new ArrayList<>();

        try (CallableStatement statement = connection.prepareCall(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setDni(resultSet.getString("dni_estudiante"));
                estudiante.setNombre(resultSet.getString("nombre"));
                estudiante.setApellidos(resultSet.getString("apellidos"));
                estudiante.setEmail(resultSet.getString("email"));
                estudiantes.add(estudiante);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return estudiantes;
    }

    public void deleteEstudiante(String dni) throws SQLException {
        String query = "{call eliminar_estudiante(?)}";

        try (CallableStatement statement = connection.prepareCall(query)) {
            statement.setString(1, dni);
            statement.executeUpdate();
        }
    }

    public boolean comprobarDniExistente(String dni) {
        String query = "SELECT COUNT(*) FROM Estudiante WHERE dni = ?";
        boolean dniExists = false;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, dni);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                dniExists = (count > 0);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return dniExists;
    }
}
