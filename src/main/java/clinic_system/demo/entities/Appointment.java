package clinic_system.demo.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="date_time", nullable=false)
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false,
                foreignKey = @ForeignKey(name="fk_patient"))
    @com.fasterxml.jackson.annotation.JsonIgnore
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false,
                foreignKey = @ForeignKey(name="fk_doctor"))
    @com.fasterxml.jackson.annotation.JsonIgnore
    private Doctor doctor;

    public Appointment() {}

    public Appointment(LocalDateTime time, Patient patient, Doctor doctor) {
        this.time = time;
        this.patient = patient;
        this.doctor = doctor;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
