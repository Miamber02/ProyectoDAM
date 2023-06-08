package mvc.vista;

import mvc.controlador.interfaz.Controlador;
import mvc.modelo.entidades.Estudiante;
import mvc.vista.interfaz.Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class VistaVerEstudiantes extends JFrame implements Vista {
    private Controlador controlador;



    public VistaVerEstudiantes(ArrayList<Estudiante> listaEstudiantes) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        getContentPane().setLayout(null);

        JLabel lblBookList = new JLabel("Lista de Libros:");
        lblBookList.setFont(new Font("Dialog", Font.BOLD, 30));
        lblBookList.setBounds(20, 10, 300, 50);
        getContentPane().add(lblBookList);

        String[] columnNames = { "DNI", "Nombre", "Apellidos", "Contacto" };
        Object[][] data = new Object[listaEstudiantes.size()][columnNames.length];
        for (int i = 0; i < listaEstudiantes.size(); i++) {
            Estudiante es = listaEstudiantes.get(i);
            data[i][0] = es.getDni();
            data[i][1] = es.getNombre();
            data[i][2] = es.getApellidos();
            data[i][3] = es.getEmail();
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable tablaEstudiantes = new JTable(model);
        tablaEstudiantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tablaEstudiantes);
        scrollPane.setBounds(90, 100, 400, 250);
        getContentPane().add(scrollPane);

        JButton btnVolverPerfil = new JButton("Volver al Perfil");
        btnVolverPerfil.setBounds(240, 410, 120, 30);
        getContentPane().add(btnVolverPerfil);
        btnVolverPerfil.addActionListener(e -> {
            controlador.cerrarVerEstudiantes();
            controlador.mostrarPerfilProfesor();
        });

    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    @Override
    public void mostrarVentana() {
        setVisible(true);
    }

    @Override
    public void cerrarVentana() {
        setVisible(false);
    }
}
