package clinic_system.demo.service;

import clinic_system.demo.entities.Appointment;
import clinic_system.demo.entities.Doctor;
import clinic_system.demo.entities.Patient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    Optional<Appointment> findAppointmentById(int id);
    void deleteAppointmentById(int id);
    List<Appointment> findAll();
    void bookAppointment(int patientId, int doctorId, LocalDateTime time);
    boolean isDoctorAvailable(int doctorId, LocalDateTime time);
    public void updateAppointment(int id, int patientId, int doctorId, LocalDateTime time);
}
