package clinic_system.demo.DAOS;

import clinic_system.demo.entities.Appointment;
import clinic_system.demo.entities.Doctor;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentDAO {
    void addAppointment(Appointment appointment);
    Appointment findAppointmentById(int id);
    void deleteAppointmentById(int id);
    List<Appointment> findAll();
    boolean existByDoctorAndTimeBetween(Doctor doctor, LocalDateTime start, LocalDateTime end);
}
