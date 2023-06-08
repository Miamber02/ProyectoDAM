package mvc.vista;

import mvc.controlador.interfaz.Controlador;
import mvc.modelo.entidades.Materia;
import mvc.vista.interfaz.Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VistaMisMaterias extends JFrame implements Vista {
    private Controlador controlador;

    public VistaMisMaterias(ArrayList<Materia> listaMaterias) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        getContentPane().setLayout(null);

        JLabel lblBookList = new JLabel("Lista Materias:");
        lblBookList.setFont(new Font("Dialog", Font.BOLD, 30));
        lblBookList.setBounds(20, 10, 300, 50);
        getContentPane().add(lblBookList);

        String[] columnNames = { "ID", "Nombre", "Horario" };
        Object[][] data = new Object[listaMaterias.size()][columnNames.length];
        for (int i = 0; i < listaMaterias.size(); i++) {
            Materia materia = listaMaterias.get(i);
            data[i][0] = materia.getId_materia();
            data[i][1] = materia.getNombre();
            data[i][2] = materia.getHorario();
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable materiaTable = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JScrollPane scrollPane = new JScrollPane(materiaTable);
        scrollPane.setBounds(90, 100, 400, 250);
        getContentPane().add(scrollPane);

        JButton btnVerProfesor = new JButton("Datos Profesor");
        btnVerProfesor.setBounds(220, 360, 120, 30);
        getContentPane().add(btnVerProfesor);

        // Action listener para el botón "Ver Profesor"
        btnVerProfesor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = materiaTable.getSelectedRow();
                if (filaSeleccionada != -1) {
                    int idMateria = (int) materiaTable.getValueAt(filaSeleccionada, 0);

                    controlador.mostrarInfoProfesor(idMateria);
                    // Abrir ventana emergente con la información del profesor
                    //ProfesorWindow profesorWindow = new ProfesorWindow(idMateria, nombreMateria, horarioMateria);
                    //profesorWindow.show();
                } else {
                    JOptionPane.showMessageDialog(scrollPane, "No ha seleccionado ninguna materia");
                    // Agrega aquí la lógica para mostrar un mensaje de error
                }
            }
        });

        JButton btnVolverPerfil = new JButton("Volver al Perfil");
        btnVolverPerfil.setBounds(220, 410, 120, 30);
        getContentPane().add(btnVolverPerfil);
        btnVolverPerfil.addActionListener(e -> {
            controlador.cerrarMisAsignaturas();
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
