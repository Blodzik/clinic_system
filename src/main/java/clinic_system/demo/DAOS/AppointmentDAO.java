package clinic_system.demo.DAOS;

import clinic_system.demo.entities.Appointment;

import java.util.List;

public interface AppointmentDAO {
    void addAppointment(Appointment appointment);
    Appointment findAppointmentById(int id);
    void deleteAppointmentById(int id);
    List<Appointment> findAll();
}
