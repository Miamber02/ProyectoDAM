package mvc.vista;

import mvc.modelo.entidades.interfaz.Usuario;
import mvc.vista.interfaz.Vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VistaInformacionUsuario extends JFrame implements Vista {

    private JPanel contentPane;

    public VistaInformacionUsuario(Usuario usuario) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(250, 250, 300, 200);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNombre = new JLabel("Nombre: " + usuario.getNombre());
        lblNombre.setBounds(50, 20, 200, 20);
        contentPane.add(lblNombre);

        JLabel lblApellidos = new JLabel("Apellidos: " + usuario.getApellidos());
        lblApellidos.setBounds(50, 50, 200, 20);
        contentPane.add(lblApellidos);

        JLabel lblEmail = new JLabel("Email: " + usuario.getEmail());
        lblEmail.setBounds(50, 80, 200, 20);
        contentPane.add(lblEmail);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(100, 120, 80, 30);
        contentPane.add(btnCerrar);

        btnCerrar.addActionListener(e -> {
            dispose();
        });
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
