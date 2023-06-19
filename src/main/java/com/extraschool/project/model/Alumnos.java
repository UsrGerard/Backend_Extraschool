package com.extraschool.project.model;

public class Alumnos {
	String nombre;
	String apellido;
	String email;
	String contrasena;

	public Alumnos() {
	}

	public Alumnos(String nombre, String apellido, String email, String contrasena) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.contrasena = contrasena;
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

	@Override
	public String toString() {
		return "Usuarios{" +
				"nombre='" + nombre + '\'' +
				", apellido='" + apellido + '\'' +
				", email='" + email + '\'' +
				", contrasena='" + contrasena + '\'' +
				'}';
	}
}
