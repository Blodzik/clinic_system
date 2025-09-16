package clinic_system.demo.rest;

import clinic_system.demo.entities.Appointment;
import clinic_system.demo.exception.DoctorNotAvailableException;
import clinic_system.demo.exception.ResourceNotFoundException;
import clinic_system.demo.request.AppointmentRequest;
import clinic_system.demo.request.AvailabilityRequest;
import clinic_system.demo.service.AppointmentService;
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
        return appointmentService.findAppointmentById(appointmentId).orElseThrow(
                () -> new ResourceNotFoundException("Appointment", appointmentId)
        );
    }

    @PostMapping("/appointments")
    public void addAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        boolean available = appointmentService.isDoctorAvailable(
        appointmentRequest.getDoctorId(),
                appointmentRequest.getTime()
        );

        if (!available) {
            throw new DoctorNotAvailableException("Doctor is not available at this time");
        }

        appointmentService.bookAppointment(
                appointmentRequest.getPatientId(),
                appointmentRequest.getDoctorId(),
                appointmentRequest.getTime()
        );
    }

   @PostMapping("appointments/check-availability")
    public boolean checkAvailability(@RequestBody AvailabilityRequest availabilityRequest) {
        boolean available = appointmentService.isDoctorAvailable(
                availabilityRequest.getDoctorId(),
                availabilityRequest.getTime()
        );
        return available;
   }

   @DeleteMapping("appointments/{appointmentId}")
    public void deleteAppointment(@PathVariable int appointmentId) {
        Appointment appointment = appointmentService.findAppointmentById(appointmentId).orElseThrow(
                () -> new ResourceNotFoundException("Appointment", appointmentId)
        );
        appointmentService.deleteAppointmentById(appointmentId);
   }

}
