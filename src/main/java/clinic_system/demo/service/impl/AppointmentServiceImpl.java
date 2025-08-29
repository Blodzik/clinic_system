package clinic_system.demo.service.impl;

import clinic_system.demo.DAOS.AppointmentDAO;
import clinic_system.demo.DAOS.DoctorDAO;
import clinic_system.demo.DAOS.PatientDAO;
import clinic_system.demo.entities.Appointment;
import clinic_system.demo.entities.Doctor;
import clinic_system.demo.entities.Patient;
import clinic_system.demo.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentDAO appointmentDAO;
    private final DoctorDAO doctorDAO;
    private final PatientDAO patientDAO;

    @Autowired
    public AppointmentServiceImpl(AppointmentDAO appointmentDAO,
                                  DoctorDAO doctorDAO,
                                  PatientDAO patientDAO) {
        this.appointmentDAO = appointmentDAO;
        this.doctorDAO = doctorDAO;
        this.patientDAO = patientDAO;
    }

    @Override
    @Transactional
    public void addAppointment(Appointment appointment) {
        Doctor doctor = doctorDAO.findById(appointment.getDoctor().getId());
        Patient patient = patientDAO.findById(appointment.getPatient().getId());

        if (doctor == null || patient == null) {
            throw new RuntimeException("Invalid doctor or patient");
        }

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        appointmentDAO.addAppointment(appointment);
    }

    @Override
    public Appointment findAppointmentById(int id) {
        return appointmentDAO.findAppointmentById(id);
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
