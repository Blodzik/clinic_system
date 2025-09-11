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

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentDAO appointmentDAO;
    private final DoctorDAO doctorDAO;
    private final PatientDAO patientDAO;
    private static final LocalTime START_WORK = LocalTime.of(8,0);
    private static final LocalTime END_WORK = LocalTime.of(16, 0);
    private static final Duration SLOT_DURATION = Duration.ofMinutes(30);

    @Autowired
    public AppointmentServiceImpl(AppointmentDAO appointmentDAO,
                                  DoctorDAO doctorDAO,
                                  PatientDAO patientDAO) {
        this.appointmentDAO = appointmentDAO;
        this.doctorDAO = doctorDAO;
        this.patientDAO = patientDAO;
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

    @Override
    @Transactional
    public void bookAppointment(int patientId, int doctorId, LocalDateTime time) {
        Patient patient = patientDAO.findById(patientId).orElseThrow(() -> new IllegalArgumentException("Invalid Patient ID"));
        Doctor doctor = doctorDAO.findById(doctorId).orElseThrow(() -> new IllegalArgumentException("Invalid Doctor ID"));

        Appointment appointment = new Appointment(time, patient, doctor);

        appointmentDAO.addAppointment(appointment);
    }

    @Override
    public boolean isDoctorAvailable(int doctorId, LocalDateTime time) {
        Doctor doctor = doctorDAO.findById(doctorId).orElseThrow(() -> new IllegalArgumentException("Invalid Doctor ID"));

        //check if time passes
        LocalTime startTime = time.toLocalTime();
        LocalTime endTime = startTime.plus(SLOT_DURATION);

        if(startTime.isBefore(START_WORK) || endTime.isAfter(END_WORK)) {
            return false;
        }

        //check if doctor has an appointment at this time
        LocalDateTime endDateTime = time.plus(SLOT_DURATION);
        boolean busy = appointmentDAO.existByDoctorAndTimeBetween(doctor, time, endDateTime);

        return !busy;
    }
}
