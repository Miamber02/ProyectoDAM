package mvc.vista;

import mvc.controlador.interfaz.Controlador;
import mvc.modelo.entidades.Profesor;
import mvc.vista.interfaz.Vista;

import javax.swing.*;
import java.awt.*;

public class VistaPerfilProfesor extends JFrame implements Vista {
    private Controlador controlador;

    private JLabel lbl_Apellido;
    private JTextField txt_Apellido;
    private JLabel lbl_Nombre;
    private JTextField txt_Nombre;
    private JLabel lbl_Email;
    private JTextField txt_Email;
    private JButton btn_CambiarEmail;
    private JButton btn_LibrosAlquiler;
    private JButton btn_misLibros;
    private JButton btn_misAsignaturtas;
    private JButton btn_CerrarSesion;



    public VistaPerfilProfesor(Profesor profesor) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        getContentPane().setLayout(null);

        JLabel lbl_PerfilDeUsuario = new JLabel("Perfil del profesor");
        lbl_PerfilDeUsuario.setFont(new Font("Dialog", Font.BOLD, 30));
        lbl_PerfilDeUsuario.setBounds(20, 10, 300, 50);
        getContentPane().add(lbl_PerfilDeUsuario);

        lbl_Nombre = new JLabel("Nombre");
        lbl_Nombre.setFont(new Font("Dialog", Font.BOLD, 18));
        lbl_Nombre.setBounds(50, 60, 107, 49);
        getContentPane().add(lbl_Nombre);

        txt_Nombre = new JTextField();
        txt_Nombre.setEditable(false);

        txt_Nombre.setBounds(50, 100, 264, 21);
        getContentPane().add(txt_Nombre);
        txt_Nombre.setColumns(10);
        txt_Nombre.setText(profesor.getNombre());

        lbl_Apellido = new JLabel("Apellidos");
        lbl_Apellido.setFont(new Font("Dialog", Font.BOLD, 18));
        lbl_Apellido.setBounds(50, 160, 107, 49);
        getContentPane().add(lbl_Apellido);

        txt_Apellido = new JTextField();

        txt_Apellido.setColumns(10);
        txt_Apellido.setEditable(false);
        txt_Apellido.setBounds(50, 200, 264, 21);
        getContentPane().add(txt_Apellido);
        txt_Apellido.setText(profesor.getApellidos());


        lbl_Email = new JLabel("Email");
        lbl_Email.setFont(new Font("Dialog", Font.BOLD, 18));
        lbl_Email.setBounds(50, 260, 107, 49);
        getContentPane().add(lbl_Email);

        txt_Email = new JTextField();

        txt_Email.setColumns(10);
        txt_Email.setBounds(50, 300, 264, 21);
        getContentPane().add(txt_Email);
        txt_Email.setText(profesor.getEmail());

        btn_CambiarEmail = new JButton("Cambiar email");
        btn_CambiarEmail.setBounds(100, 350, 157, 40);
        getContentPane().add(btn_CambiarEmail);

        btn_CambiarEmail.addActionListener(e -> {
            profesor.setEmail(txt_Email.getText());
            controlador.cambiarEmailProfesor(profesor);
            JOptionPane.showMessageDialog(this, "Email actualizado");
        });

        btn_LibrosAlquiler = new JButton("Cargar Libro");
        btn_LibrosAlquiler.setBounds(380, 50, 157, 40);
        getContentPane().add(btn_LibrosAlquiler);

        btn_LibrosAlquiler.addActionListener(e -> {
            controlador.cerrarPerfilProfesor();
            controlador.mostrarCargaLibros(profesor.getId());
        });

        btn_misLibros = new JButton("Biblioteca");
        btn_misLibros.setBounds(380, 150, 157, 40);
        getContentPane().add(btn_misLibros);

        btn_misLibros.addActionListener(e -> {
            controlador.cerrarPerfilProfesor();
            controlador.mostrarTodosLibros();
        });

        btn_misAsignaturtas = new JButton("Alumnos");
        btn_misAsignaturtas.setBounds(380, 250, 157, 40);
        getContentPane().add(btn_misAsignaturtas);

        btn_misAsignaturtas.addActionListener(e -> {
            controlador.cerrarPerfilProfesor();
            controlador.mostrarVerEstudiantes();
        });

        btn_CerrarSesion = new JButton("Salir");
        btn_CerrarSesion.setBounds(380, 350, 157, 40);
        getContentPane().add(btn_CerrarSesion);

        btn_CerrarSesion.addActionListener(e -> {
        	controlador.cerrarPerfilProfesor();
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