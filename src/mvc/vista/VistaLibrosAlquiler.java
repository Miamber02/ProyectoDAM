package mvc.vista;

import mvc.controlador.interfaz.Controlador;
import mvc.modelo.entidades.Libro;
import mvc.vista.interfaz.Vista;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VistaLibrosAlquiler extends JFrame implements Vista {
    private Controlador controlador;

    public VistaLibrosAlquiler(int id_estudiante, ArrayList<Libro> listaLibros) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        getContentPane().setLayout(null);

        JLabel lblBookList = new JLabel("Lista de libros:");
        lblBookList.setFont(new Font("Dialog", Font.BOLD, 30));
        lblBookList.setBounds(20, 10, 300, 50);
        getContentPane().add(lblBookList);

        String[] columnNames = {"Título", "Subido por"};
        Object[][] data = new Object[listaLibros.size()][columnNames.length];
        for (int i = 0; i < listaLibros.size(); i++) {
            Libro libro = listaLibros.get(i);
            data[i][0] = libro.getTitulo();
            data[i][1] = libro.getProfesor().getNombre();
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable tablaLibros = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JScrollPane scrollPane = new JScrollPane(tablaLibros);
        scrollPane.setBounds(90, 100, 400, 250);
        getContentPane().add(scrollPane);

        JButton btnRentBook = new JButton("Alquilar Libro");
        btnRentBook.setBounds(240, 370, 120, 30);
        getContentPane().add(btnRentBook);

        btnRentBook.addActionListener(e -> {
            int filaSeleccionada = tablaLibros.getSelectedRow();
            if (filaSeleccionada != -1) {
                int idLibro = listaLibros.get(filaSeleccionada).getIdLibro();
                String nombreLibro = (String) tablaLibros.getValueAt(filaSeleccionada, 1);
                JOptionPane.showMessageDialog(this, "Libro Alquilado - " + nombreLibro);
                controlador.alquilarLibro(idLibro, id_estudiante);

                DefaultTableModel tableModel = (DefaultTableModel) tablaLibros.getModel();
                tableModel.removeRow(filaSeleccionada);
            } else {
                JOptionPane.showMessageDialog(this, "No ha seleccionado ningún libro");
            }
        });

        JButton btnVolverPerfil = new JButton("Volver al Perfil");
        btnVolverPerfil.setBounds(240, 410, 120, 30);
        getContentPane().add(btnVolverPerfil);
        btnVolverPerfil.addActionListener(e -> {
            controlador.cerrarLibrosAlquiler();
            controlador.mostrarPerfilAlumno();
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
