package mvc.modelo.persistencia;

import mvc.modelo.entidades.Profesor;

import java.sql.*;

public class ProfesorDAO {
    private Connection connection;
    private String cadenaConexion = "jdbc:mysql://localhost:3306/proyecto_miguel";
    private String user = "root";
    private String pass = "";

    public ProfesorDAO() {
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

    public Profesor getProfesorByDni(String dni) {
        String query = "{call obtener_profesor_por_dni(?)}";
        Profesor profesor = null;

        try (CallableStatement statement = connection.prepareCall(query)) {
            statement.setString(1, dni);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                profesor = new Profesor();
                profesor.setId(resultSet.getInt("id"));
                profesor.setDni(resultSet.getString("dni"));
                profesor.setNombre(resultSet.getString("nombre"));
                profesor.setApellidos(resultSet.getString("apellidos"));
                profesor.setEmail(resultSet.getString("email"));
                profesor.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return profesor;
    }

    public Profesor getProfesorById(int id_profesor) {
        String query = "{call obtener_profesor_por_id(?)}";
        Profesor profesor = null;

        try (CallableStatement statement = connection.prepareCall(query)) {
            statement.setInt(1, id_profesor);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                profesor = new Profesor();
                profesor.setId(resultSet.getInt("id"));
                profesor.setDni(resultSet.getString("dni"));
                profesor.setNombre(resultSet.getString("nombre"));
                profesor.setApellidos(resultSet.getString("apellidos"));
                profesor.setEmail(resultSet.getString("email"));
                profesor.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return profesor;
    }
    public boolean insertProfesor(Profesor profesor) {
        String query = "{call insertar_profesor(?, ?, ?, ?, ?)}";

        try (CallableStatement statement = connection.prepareCall(query)) {
            statement.setString(1, profesor.getDni());
            statement.setString(2, profesor.getNombre());
            statement.setString(3, profesor.getApellidos());
            statement.setString(4, profesor.getEmail());
            statement.setString(5, profesor.getPassword());
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void updateProfesor(Profesor profesor) {
        String query = "{call actualizar_profesor(?, ?, ?, ?, ?)}";

        try (CallableStatement statement = connection.prepareCall(query)) {
            statement.setString(1, profesor.getDni());
            statement.setString(2, profesor.getNombre());
            statement.setString(3, profesor.getApellidos());
            statement.setString(4, profesor.getEmail());
            statement.setString(5, profesor.getPassword());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteProfesor(String dni) {
        String query = "{call eliminar_profesor(?)}";

        try (CallableStatement statement = connection.prepareCall(query)) {
            statement.setString(1, dni);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean comprobarDniExistente(String dni) {
        String query = "SELECT COUNT(*) FROM Profesor WHERE dni = ?";
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
