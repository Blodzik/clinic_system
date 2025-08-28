package clinic_system.demo.service;

import clinic_system.demo.entities.Appointment;

import java.util.List;

public interface AppointmentService {
    void addAppointment(Appointment appointment);
    void findAppointmentById(int id);
    void deleteAppointmentById(int id);
    List<Appointment> findAll();
}
