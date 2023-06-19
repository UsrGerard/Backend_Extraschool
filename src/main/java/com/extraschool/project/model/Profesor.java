package com.extraschool.project.model;

public class Profesor {
	private String id;
	private String nombre;
	private String apellido;
	private String email;
	private String contrasena;
	private String materia;
	private String imagen;

	public Profesor() {

	}

	public Profesor(String id, String nombre, String apellido, String email, String contrasena, String materia, String imagen) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.contrasena = contrasena;
		this.materia = materia;
		this.imagen = imagen;
	}

	// Agrega getters y setters para los campos


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getMateria() {
		return materia;
	}
		public void setMateria(String materia) {
		this.materia = materia;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return "Profesor{" +
				"id=" + id +
				", nombre='" + nombre + '\'' +
				", apellido='" + apellido + '\'' +
				", email='" + email + '\'' +
				", contrasena='" + contrasena + '\'' +
				", materia='" + materia + '\'' +
				", imagen='" + imagen + '\'' +
				'}';
	}
}