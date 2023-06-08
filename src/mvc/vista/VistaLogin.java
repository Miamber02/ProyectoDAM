package mvc.vista;

import mvc.controlador.interfaz.Controlador;
import mvc.vista.interfaz.Vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaLogin extends JFrame implements Vista {

	private JPanel contentPane;
	private JTextField dniField;
	private JPasswordField passwordField;
	private JCheckBox checkBoxProfesor;
	private Controlador controlador;

	public VistaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsername = new JLabel("DNI:");
		lblUsername.setBounds(200, 100, 80, 14);
		contentPane.add(lblUsername);

		dniField = new JTextField();
		dniField.setBounds(240, 100, 130, 20);
		contentPane.add(dniField);
		dniField.setColumns(10);

		JLabel lblPassword = new JLabel("Contraseña:");
		lblPassword.setBounds(150, 170, 80, 14);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(240, 170, 130, 20);
		contentPane.add(passwordField);

		checkBoxProfesor = new JCheckBox("Profesor");
		checkBoxProfesor.setBounds(260, 230, 97, 23);
		getContentPane().add(checkBoxProfesor);

		JLabel lblMessage = new JLabel("");
		lblMessage.setBounds(130, 280, 220, 14);
		contentPane.add(lblMessage);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(e -> {
			if (!controlador.login(dniField.getText(), new String(passwordField.getPassword()),
					checkBoxProfesor.isSelected())) {
				JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrecto.", "Error",
						JOptionPane.ERROR_MESSAGE);
			};
		});
		btnLogin.setBounds(160, 300, 100, 25);
		contentPane.add(btnLogin);

		JButton btnCreateUser = new JButton("Crear Usuario");
		btnCreateUser.addActionListener(e -> {
			controlador.cerrarLogin();
			controlador.mostrarCrearUsuario();
		});
		btnCreateUser.setBounds(280, 300, 120, 25);
		contentPane.add(btnCreateUser);
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
