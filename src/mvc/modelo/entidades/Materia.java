package mvc.modelo.entidades;

public class Materia {
	private int id_materia;
	private String nombre;
	private String horario;

	public Materia() {	
	}

	public Materia(int id_materia, String nombre, String horario) {
		this.id_materia = id_materia;
		this.nombre = nombre;
		this.horario = horario;
	}

	public int getId_materia() {
		return id_materia;
	}

	public void setId_materia(int id_materia) {
		this.id_materia = id_materia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	@Override
	public String toString() {
		return "Materia [codigo=" + id_materia + ", nombre=" + nombre + ", horario=" + horario + "]";
	}
}