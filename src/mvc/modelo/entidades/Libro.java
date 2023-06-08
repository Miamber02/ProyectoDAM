package mvc.modelo.entidades;

public class Libro {
    private int idLibro;
    private String titulo;
    private Estudiante estudiante;
    private Profesor profesor;
    private String texto;


    public Libro() {
    }     

	public Estudiante getEstudiante() {
		return estudiante;
	}


	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}


	public Profesor getProfesor() {
		return profesor;
	}


	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}


	public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int id_libro) {
        this.idLibro = id_libro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}