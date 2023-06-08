package mvc.vista;

import mvc.controlador.interfaz.Controlador;
import mvc.vista.interfaz.Vista;

import javax.swing.*;

public class VistaCrearUsuario extends JFrame implements Vista {
    private Controlador controlador;

    private JTextField textFieldName;
    private JTextField textFieldLastName;
    private JTextField textFieldDNI;
    private JTextField textFieldEmail;
    private JPasswordField passwordField;
    private JCheckBox checkBoxProfesor;

    public VistaCrearUsuario() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        getContentPane().setLayout(null);

        JLabel lblName = new JLabel("Nombre:");
        lblName.setBounds(180, 70, 80, 14);
        getContentPane().add(lblName);

        textFieldName = new JTextField();
        textFieldName.setBounds(240, 70, 150, 20);
        getContentPane().add(textFieldName);
        textFieldName.setColumns(10);

        JLabel lblLastName = new JLabel("Apellidos:");
        lblLastName.setBounds(170, 130, 80, 14);
        getContentPane().add(lblLastName);

        textFieldLastName = new JTextField();
        textFieldLastName.setBounds(240, 130, 150, 20);
        getContentPane().add(textFieldLastName);
        textFieldLastName.setColumns(10);

        JLabel lblDNI = new JLabel("DNI:");
        lblDNI.setBounds(200, 190, 80, 14);
        getContentPane().add(lblDNI);

        textFieldDNI = new JTextField();
        textFieldDNI.setBounds(240, 190, 150, 20);
        getContentPane().add(textFieldDNI);
        textFieldDNI.setColumns(10);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(190, 250, 80, 14);
        getContentPane().add(lblEmail);

        textFieldEmail = new JTextField();
        textFieldEmail.setBounds(240, 250, 150, 20);
        getContentPane().add(textFieldEmail);
        textFieldEmail.setColumns(10);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(160, 310, 80, 14);
        getContentPane().add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(240, 310, 150, 20);
        getContentPane().add(passwordField);

        checkBoxProfesor = new JCheckBox("Profesor");
        checkBoxProfesor.setBounds(270, 350, 97, 23);
        getContentPane().add(checkBoxProfesor);

        JButton btnCreateUser = new JButton("Crear Usuario");
        btnCreateUser.setBounds(120, 390, 120, 30);
        getContentPane().add(btnCreateUser);
        btnCreateUser.addActionListener(e -> {
        	if (controlador.pedirCrearUsuario(textFieldDNI.getText(), textFieldName.getText(), textFieldLastName.getText(), textFieldEmail.getText(), passwordField.getText(), checkBoxProfesor.isSelected())) {
                JOptionPane.showMessageDialog(this, "Usuario creado correctamente.");
                controlador.cerrarCrearUsuario();
                controlador.mostrarLogin();
            } else {
                JOptionPane.showMessageDialog(this, "Error al crear el usuario. El DNI no es válido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        );

        JButton btnGoToLogin = new JButton("Ir al login");
        btnGoToLogin.setBounds(300, 390, 120, 30);
        getContentPane().add(btnGoToLogin);
        btnGoToLogin.addActionListener(e -> {
            controlador.cerrarCrearUsuario();
            controlador.mostrarLogin();

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

