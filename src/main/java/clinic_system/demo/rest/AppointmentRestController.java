package clinic_system.demo.rest;

import clinic_system.demo.DAOS.AppointmentDAO;
import clinic_system.demo.DAOS.DoctorDAO;
import clinic_system.demo.DAOS.PatientDAO;
import clinic_system.demo.entities.Appointment;
import clinic_system.demo.entities.Doctor;
import clinic_system.demo.entities.Patient;
import clinic_system.demo.service.AppointmentService;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentRestController {
    private AppointmentService appointmentService;

    public AppointmentRestController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/appointments")
    public List<Appointment> getAppointments() {
        return appointmentService.findAll();
    }

    @GetMapping("/appointments/{appointmentId}")
    public Appointment getAppointment(@PathVariable int appointmentId) {
        return appointmentService.findAppointmentById(appointmentId);
    }

    @PostMapping("/appointments")
    public void addAppointment(@RequestBody Appointment appointment) {
        appointment.setId(0);
        appointmentService.addAppointment(appointment);
    }
}
