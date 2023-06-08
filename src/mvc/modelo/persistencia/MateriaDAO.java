package mvc.modelo.persistencia;

import mvc.modelo.entidades.Materia;

import java.sql.*;
import java.util.ArrayList;

public class MateriaDAO {
    private Connection connection;
    private String cadenaConexion = "jdbc:mysql://localhost:3306/proyecto_miguel";
    private String user = "root";
    private String pass = "";

    public MateriaDAO() {
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

    public Materia getMateriaById(int idMateria) throws SQLException {
        String query = "{call obtener_materia_por_id(?)}";
        Materia materia = null;

        try (CallableStatement statement = connection.prepareCall(query)) {
            statement.setInt(1, idMateria);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                materia = new Materia();
                materia.setId_materia(resultSet.getInt("id_materia"));
                materia.setNombre(resultSet.getString("nombre"));
                materia.setHorario(resultSet.getString("horario"));
            }
        }
        return materia;
    }

    public void insertMateria(Materia materia) throws SQLException {
        String query = "{call insertar_materia(?, ?)}";

        try (CallableStatement statement = connection.prepareCall(query)) {
            statement.setString(1, materia.getNombre());
            statement.setString(2, materia.getHorario());
            statement.executeUpdate();
        }
    }

    public void updateMateria(Materia materia) throws SQLException {
        String query = "{call actualizar_materia(?, ?, ?)}";

        try (CallableStatement statement = connection.prepareCall(query)) {
            statement.setInt(1, materia.getId_materia());
            statement.setString(2, materia.getNombre());
            statement.setString(3, materia.getHorario());
            ;
            statement.executeUpdate();
        }
    }

    public void deleteMateria(int idMateria) throws SQLException {
        String query = "{call eliminar_materia(?)}";

        try (CallableStatement statement = connection.prepareCall(query)) {
            statement.setInt(1, idMateria);
            statement.executeUpdate();
        }
    }

    public ArrayList<Materia> getMisMaterias(int id_estudiante) {
        String query = "{call obtener_mis_materias(?)}";
        ArrayList<Materia> materias = new ArrayList<>();

        try (CallableStatement statement = connection.prepareCall(query)) {
            statement.setInt(1, id_estudiante);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Materia materia = new Materia();
                materia.setId_materia(resultSet.getInt("id"));
                materia.setNombre(resultSet.getString("nombre"));
                materia.setHorario(resultSet.getString("horario"));
                materias.add(materia);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return materias;
    }

    public int getDniProfesorByIdMateria(int idMateria) {
        String query = "{call obtener_dni_profesor_por_id_materia(?)}";
        int idProfesor = 0;

        try (CallableStatement statement = connection.prepareCall(query)) {
            statement.setInt(1, idMateria);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                idProfesor = resultSet.getInt("id_profesor");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return idProfesor;
    }
}
