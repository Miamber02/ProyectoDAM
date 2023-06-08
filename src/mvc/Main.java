package mvc;

import mvc.controlador.ControladorImpl;
import mvc.modelo.ModeloImpl;
import mvc.vista.VistaCrearUsuario;
import mvc.vista.VistaLogin;

public class Main {
    public static void main(String[] args) {
        ModeloImpl modelo = new ModeloImpl();

        VistaLogin vistaLogin = new VistaLogin();
        VistaCrearUsuario vistaCrearUsuario = new VistaCrearUsuario();

        ControladorImpl controlador = new ControladorImpl(modelo, vistaLogin, vistaCrearUsuario);

        controlador.mostrarLogin();
    }
}
