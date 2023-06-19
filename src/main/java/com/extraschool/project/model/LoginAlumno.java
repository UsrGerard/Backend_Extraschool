package com.extraschool.project.model;

public class LoginAlumno {
    private String email;
    private String contrasena;

    public LoginAlumno() {

    }

    // Agrega getters y setters para los campos

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
        return "Alumno{" +
                ", email='" + email + '\'' +
                ", contrasena='" + contrasena + '\'' +
                '}';
    }
}
