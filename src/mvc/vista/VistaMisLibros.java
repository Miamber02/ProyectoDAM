package mvc.vista;

import mvc.controlador.interfaz.Controlador;
import mvc.modelo.entidades.Libro;
import mvc.vista.interfaz.Vista;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class VistaMisLibros extends JFrame implements Vista {
    private Controlador controlador;


    public VistaMisLibros(ArrayList<Libro> listaLibros) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        getContentPane().setLayout(null);

        JLabel lblBookList = new JLabel("Lista Mis Libros:");
        lblBookList.setFont(new Font("Dialog", Font.BOLD, 30));
        lblBookList.setBounds(20, 10, 300, 50);
        getContentPane().add(lblBookList);

        String[] columnNames = {"ID", "Nombre"};
        Object[][] data = new Object[listaLibros.size()][columnNames.length];
        for (int i = 0; i < listaLibros.size(); i++) {
            Libro libro = listaLibros.get(i);
            data[i][0] = libro.getIdLibro();
            data[i][1] = libro.getTitulo();
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

        JButton alquilarLibro = new JButton("Devolver Libro");
        alquilarLibro.setBounds(300, 370, 160, 30);
        getContentPane().add(alquilarLibro);

        alquilarLibro.addActionListener(e -> {
            int filaSeleccionada = tablaLibros.getSelectedRow();
            if (filaSeleccionada != -1) {
                int idLibro = (int) tablaLibros.getValueAt(filaSeleccionada, 0);
                String nombreLibro = (String) tablaLibros.getValueAt(filaSeleccionada, 1);
                JOptionPane.showMessageDialog(this, "Libro devuelto - " + nombreLibro);
                controlador.devolverLibroAlquilado(idLibro);

                DefaultTableModel tableModel = (DefaultTableModel) tablaLibros.getModel();
                tableModel.removeRow(filaSeleccionada);
            } else {
                JOptionPane.showMessageDialog(this, "No ha seleccionado ningún libro");
            }
        });

        JButton btnDescargarLibro = new JButton("Descargar Libro");
        btnDescargarLibro.setBounds(130, 370, 160, 30);
        getContentPane().add(btnDescargarLibro);

        btnDescargarLibro.addActionListener(e -> {
            int filaSeleccionada = tablaLibros.getSelectedRow();
            if (filaSeleccionada != -1) {
                String nombreLibro = (String) tablaLibros.getValueAt(filaSeleccionada, 1);
                System.out.println(filaSeleccionada);
                saveFile(listaLibros.get(filaSeleccionada));
                JOptionPane.showMessageDialog(this, "Libro descargado - " + nombreLibro);
            } else {
                JOptionPane.showMessageDialog(this, "No ha seleccionado ningún libro");
            }
        });

        JButton btnVolverPerfil = new JButton("Volver al Perfil");
        btnVolverPerfil.setBounds(240, 410, 120, 30);
        getContentPane().add(btnVolverPerfil);
        btnVolverPerfil.addActionListener(e -> {
            controlador.cerrarMisLibros();
            controlador.mostrarPerfilAlumno();
        });

    }

    public static void saveFile(Libro libro) {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Seleccione la carpeta donde guardar el archivo");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getPath();
            String fileName = libro.getTitulo() + ".txt";
            String fileContent = libro.getTexto();

            try {
                FileWriter writer = new FileWriter(filePath + "/" + fileName);
                writer.write(fileContent);
                writer.close();
                System.out.println("Archivo guardado exitosamente en: " + filePath + "/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error al guardar el archivo.");
            }
        } else {
            System.out.println("Cancelado por el usuario.");
        }
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
