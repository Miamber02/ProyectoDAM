package mvc.vista;

import mvc.controlador.interfaz.Controlador;
import mvc.vista.interfaz.Vista;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.*;

public class VistaCargarLibro extends JFrame implements Vista {
    Controlador controlador;

    private JTextField textFieldTitle;
    private JButton btnAttachFile;
    private JButton btnUpload;
    private JLabel lblStatus;
    private File selectedFile;

    public VistaCargarLibro(int id_profesor) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        getContentPane().setLayout(null);

        JLabel lblTitle = new JLabel("Títuo del libro:");
        lblTitle.setBounds(200, 100, 200, 22);
        getContentPane().add(lblTitle);

        textFieldTitle = new JTextField();
        textFieldTitle.setBounds(200, 130, 200, 22);
        getContentPane().add(textFieldTitle);
        textFieldTitle.setColumns(10);

        lblStatus = new JLabel("");
        lblStatus.setBounds(200, 155, 400, 16);
        getContentPane().add(lblStatus);

        btnAttachFile = new JButton("Adjuntar archivo");
        btnAttachFile.setBounds(200, 180, 200, 25);
        getContentPane().add(btnAttachFile);
        btnAttachFile.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(new JFrame());
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                lblStatus.setText("File attached: " + selectedFile.getName());
            }
        });

        btnUpload = new JButton("Cargar libro");
        btnUpload.setBounds(200, 240, 200, 25);
        getContentPane().add(btnUpload);
        btnUpload.addActionListener(e -> {
            try {
                String title = textFieldTitle.getText();
                FileInputStream fileInputStream = null;
                try {
                    fileInputStream = new FileInputStream(selectedFile);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();

                }
                controlador.cargarLibro(title, id_profesor, fileInputStream);

            } catch (Exception exc){
                JOptionPane.showMessageDialog(this, "No ha seleccionado ningún libro");
            }
        });


        JButton btnVolverPerfil = new JButton("Volver al Perfil");
        btnVolverPerfil.setBounds(200, 400, 200, 25);
        getContentPane().add(btnVolverPerfil);
        btnVolverPerfil.addActionListener(e -> {
            controlador.cerrarCargaLibros();
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
