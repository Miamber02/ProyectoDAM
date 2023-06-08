package mvc.controlador;

import mvc.controlador.interfaz.Controlador;
import mvc.modelo.entidades.Estudiante;
import mvc.modelo.entidades.Libro;
import mvc.modelo.entidades.Materia;
import mvc.modelo.entidades.Profesor;
import mvc.modelo.interfaz.Modelo;
import mvc.vista.*;

import java.io.FileInputStream;
import java.util.ArrayList;

public class ControladorImpl implements Controlador {
	private Modelo modelo;
	private VistaLogin vistaLogin;
	private VistaCrearUsuario vistaCrearUsuario;
	private VistaPerfilEstudiante vistaPerfilEstudiante;
	private VistaPerfilProfesor vistaPerfilProfesor;
	private VistaLibrosAlquiler vistaLibrosAlquiler;
	private VistaMisLibros vistaMisLibros;
	private VistaMisMaterias vistaMisMaterias;
	private VistaCargarLibro vistaCargarLibro;
	private VistaTodosLibros vistaTodosLibros;
	private VistaVerEstudiantes vistaVerEstudiantes;

	public ControladorImpl(Modelo modelo, VistaLogin vistaLogin, VistaCrearUsuario vistaCrearUsuario) {
		this.modelo = modelo;
		this.vistaLogin = vistaLogin;
		this.vistaCrearUsuario = vistaCrearUsuario;
		vistaLogin.setControlador(this);
		vistaCrearUsuario.setControlador(this);
	}

	@Override
	public void mostrarLogin() {
		vistaLogin.mostrarVentana();
	}

	@Override
	public void cerrarLogin() {
		vistaLogin.cerrarVentana();
	}

	@Override
	public void mostrarCrearUsuario() {
		vistaCrearUsuario.mostrarVentana();
	}

	@Override
	public void cerrarCrearUsuario() {
		vistaCrearUsuario.cerrarVentana();
	}

	@Override
	public void mostrarPerfilAlumno() {
		vistaPerfilEstudiante.mostrarVentana();
	}

	@Override
	public void cerrarPerfilAlumno() {
		vistaPerfilEstudiante.cerrarVentana();
	}

	@Override
	public void mostrarPerfilProfesor() {
		vistaPerfilProfesor.mostrarVentana();
	}

	@Override
	public void cerrarPerfilProfesor() {
		vistaPerfilProfesor.cerrarVentana();
	}

	@Override
	public void mostrarLibrosAlquiler(int id_estudiante) {
		vistaLibrosAlquiler = new VistaLibrosAlquiler(id_estudiante, getLibrosAlquiler());
		vistaLibrosAlquiler.setControlador(this);
		vistaLibrosAlquiler.mostrarVentana();
	}

	@Override
	public void cerrarLibrosAlquiler() {
		vistaLibrosAlquiler.cerrarVentana();
	}

	@Override
	public void mostrarMisLibros(int id_estudiante) {
		vistaMisLibros = new VistaMisLibros(getMisLibros(id_estudiante));
		vistaMisLibros.setControlador(this);
		vistaMisLibros.mostrarVentana();
	}

	@Override
	public void cerrarMisLibros() {
		vistaMisLibros.cerrarVentana();
	}

	@Override
	public void mostrarMisMaterias(int id_estudiante) {
		vistaMisMaterias = new VistaMisMaterias(getMisMaterias(id_estudiante));
		vistaMisMaterias.setControlador(this);
		vistaMisMaterias.mostrarVentana();
	}

	@Override
	public void cerrarMisAsignaturas() {
		vistaMisMaterias.cerrarVentana();
	}

	@Override
	public void mostrarCargaLibros(int id_profesor) {
		vistaCargarLibro = new VistaCargarLibro(id_profesor);
		vistaCargarLibro.setControlador(this);
		vistaCargarLibro.mostrarVentana();
	}

	@Override
	public void cerrarCargaLibros() {
		vistaCargarLibro.cerrarVentana();
	}

	@Override
	public void mostrarTodosLibros() {
		vistaTodosLibros = new VistaTodosLibros(getTodosLibros());
		vistaTodosLibros.setControlador(this);
		vistaTodosLibros.mostrarVentana();
	}

	@Override
	public void cerrarTodosLibros() {
		vistaTodosLibros.cerrarVentana();
	}

	@Override
	public void mostrarVerEstudiantes() {
		vistaVerEstudiantes = new VistaVerEstudiantes(getTodosEstudiantes());
		vistaVerEstudiantes.setControlador(this);
		vistaVerEstudiantes.mostrarVentana();
	}

	@Override
	public void cerrarVerEstudiantes() {
		vistaVerEstudiantes.cerrarVentana();
	}

	@Override
	public boolean login(String dni, String password, boolean esProfesor) {
		if (esProfesor) {
			Profesor profesor = modelo.obtenerProfesor(dni);
			if (profesor.getPassword().equals(password)) {
				cerrarLogin();
				vistaPerfilProfesor = new VistaPerfilProfesor(profesor);
				vistaPerfilProfesor.setControlador(this);
				mostrarPerfilProfesor();
				return true;
			}
		} else {
			Estudiante estudiante = modelo.obtenerEstudiante(dni);
			if (estudiante.getPassword().equals(password)) {
				cerrarLogin();
				vistaPerfilEstudiante = new VistaPerfilEstudiante(estudiante);
				vistaPerfilEstudiante.setControlador(this);
				mostrarPerfilAlumno();
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean pedirCrearUsuario(String dni, String nombre, String apellidos, String email, String password,
			boolean esProfesor) {
		if (modelo.verificarDNI(dni)) {
			if (esProfesor) {
				Profesor profesor = new Profesor(2, dni, nombre, apellidos, email, password);
				modelo.crearProfesor(profesor);
			} else {
				Estudiante estudiante = new Estudiante(2, dni, nombre, apellidos, email, password);
				modelo.crearEstudiante(estudiante);
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void cambiarEmailProfesor(Profesor profesor) {
		modelo.cambiarEmailProfesor(profesor);
	}

	@Override
	public void cambiarEmailEstudiante(Estudiante estudiante) {
		modelo.cambiarEmailEstudiante(estudiante);
	}

	@Override
	public ArrayList<Libro> getLibrosAlquiler() {
		return modelo.obtenerLibrosAlquiler();
	}

	@Override
	public void alquilarLibro(int id_libro, int id_estudiante) {
		modelo.alquilarLibro(id_libro, id_estudiante);
	}

	@Override
	public ArrayList<Libro> getMisLibros(int id_estudiante) {
		return modelo.obtenerMisLibros(id_estudiante);
	}

	@Override
	public ArrayList<Libro> getTodosLibros() {
		return modelo.obtenerTodosLibros();
	}

	@Override
	public ArrayList<Materia> getMisMaterias(int id_estudiante) {
		return modelo.obtenerMisAsignaturas(id_estudiante);
	}

	@Override
	public ArrayList<Estudiante> getTodosEstudiantes() {
		return modelo.obtenerTodosEstudiantes();
	}

	@Override
	public void mostrarInfoProfesor(int idMateria) {
		Profesor profesor = modelo.obtenerInfoProfesorMateria(idMateria);
		VistaInformacionUsuario vistaInformacionUsuario = new VistaInformacionUsuario(profesor);
		vistaInformacionUsuario.mostrarVentana();
	}

	@Override
	public void devolverLibroAlquilado(int idLibro) {
		modelo.devolverLibroAlquilado(idLibro);
	}

	@Override
	public void cargarLibro(String title, int id_profesor, FileInputStream fileInputStream) {
		modelo.cargarLibro(title, id_profesor, fileInputStream);
	}

}
