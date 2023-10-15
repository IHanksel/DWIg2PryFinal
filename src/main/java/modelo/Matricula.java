package modelo;

import java.time.LocalDate;

public class Matricula {
    private int id;
    private String codigoAlumno;
    private int semestre; //Semestre es igual a ciclo
    private String nombreAsignatura;
    private String turno; //Turno: mañana o tarde
    private LocalDate fechaMatricula;

    public Matricula(int id, String codigoAlumno, int semestre, String nombreAsignatura, String turno, LocalDate fechaMatricula) {
        this.id = id;
        this.codigoAlumno = codigoAlumno;
        this.semestre = semestre;
        this.nombreAsignatura = nombreAsignatura;
        this.turno = turno;
        this.fechaMatricula = fechaMatricula;
    }
    
    public Matricula(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoAlumno() {
        return codigoAlumno;
    }

    public void setCodigoAlumno(String codigoAlumno) {
        this.codigoAlumno = codigoAlumno;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public LocalDate getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(LocalDate fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }
    
    
    
}
