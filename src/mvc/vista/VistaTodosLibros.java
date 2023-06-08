package mvc.vista;

import mvc.controlador.interfaz.Controlador;
import mvc.modelo.entidades.Estudiante;
import mvc.modelo.entidades.Libro;
import mvc.vista.interfaz.Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class VistaTodosLibros extends JFrame implements Vista {
    private Controlador controlador;



    public VistaTodosLibros(ArrayList<Libro> listaLibros) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        getContentPane().setLayout(null);

        JLabel lblBookList = new JLabel("Lista de libros:");
        lblBookList.setFont(new Font("Dialog", Font.BOLD, 30));
        lblBookList.setBounds(20, 10, 300, 50);
        getContentPane().add(lblBookList);

        String[] columnNames = { "ID", "Nombre", "Alquilado por" };
        Object[][] data = new Object[listaLibros.size()][columnNames.length];
        for (int i = 0; i < listaLibros.size(); i++) {
            Libro libro = listaLibros.get(i);
            data[i][0] = libro.getIdLibro();
            data[i][1] = libro.getTitulo();
            data[i][2] = libro.getEstudiante().getNombre();
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable tablaLibros = new JTable(model);
        tablaLibros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tablaLibros);
        scrollPane.setBounds(90, 100, 400, 250);
        getContentPane().add(scrollPane);

        JButton btnVolverPerfil = new JButton("Volver al Perfil");
        btnVolverPerfil.setBounds(240, 410, 120, 30);
        getContentPane().add(btnVolverPerfil);
        btnVolverPerfil.addActionListener(e -> {
            controlador.cerrarTodosLibros();
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
