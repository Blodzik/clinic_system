package clinic_system.demo.service.impl;

import clinic_system.demo.DAOS.AppointmentDAO;
import clinic_system.demo.entities.Appointment;
import clinic_system.demo.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private AppointmentDAO appointmentDAO;

    @Autowired
    public AppointmentServiceImpl(AppointmentDAO appointmentDAO) {
        this.appointmentDAO = appointmentDAO;
    }

    @Override
    @Transactional
    public void addAppointment(Appointment appointment) {
        appointmentDAO.addAppointment(appointment);
    }

    @Override
    public void findAppointmentById(int id) {
        appointmentDAO.findAppointmentById(id);
    }

    @Override
    @Transactional
    public void deleteAppointmentById(int id) {
        appointmentDAO.deleteAppointmentById(id);
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentDAO.findAll();
    }
}
