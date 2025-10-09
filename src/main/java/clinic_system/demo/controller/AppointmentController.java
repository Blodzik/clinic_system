package clinic_system.demo.controller;

import clinic_system.demo.entities.Appointment;
import clinic_system.demo.exception.AppointmentNotFoundException;
import clinic_system.demo.exception.DoctorNotAvailableException;
import clinic_system.demo.forms.AppointmentForm;
import clinic_system.demo.service.AppointmentService;
import clinic_system.demo.service.DoctorService;
import clinic_system.demo.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final PatientService patientService;
    private final DoctorService doctorService;

    public AppointmentController(AppointmentService appointmentService, PatientService patientService, DoctorService doctorService) {
        this.appointmentService = appointmentService;
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    @GetMapping("/list")
    public String listAppointments(Model model) {
        model.addAttribute("appointments", appointmentService.findAll());
        return "appointments/list-appointments";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        AppointmentForm appointment = new AppointmentForm();

        model.addAttribute("appointment", appointment);
        model.addAttribute("patients", patientService.findAll());
        model.addAttribute("doctors", doctorService.findAll());


        System.out.println(">>> appointment model is: " + appointment.getClass().getName());
        return "appointments/appointment-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("appointmentId") int appointmentId, Model model) {
        Appointment appointment = appointmentService.findAppointmentById(appointmentId)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found."));

        AppointmentForm appointmentForm = new AppointmentForm();
        appointmentForm.setId(appointment.getId());
        appointmentForm.setPatientId(appointment.getPatient().getId());
        appointmentForm.setDoctorId(appointment.getDoctor().getId());
        appointmentForm.setTime(appointment.getTime());

        model.addAttribute("appointment", appointmentForm);
        model.addAttribute("patients", patientService.findAll());
        model.addAttribute("doctors", doctorService.findAll());

        return "appointments/appointment-form";
    }


    @PostMapping("/save")
    public String saveAppointment(@ModelAttribute("appointment" )AppointmentForm appointmentForm) {
        int id = appointmentForm.getId();
        int patientId = appointmentForm.getPatientId();
        int doctorId = appointmentForm.getDoctorId();
        LocalDateTime time = appointmentForm.getTime();

        boolean available = appointmentService.isDoctorAvailable(doctorId, time);

        if(!available) {
            throw new DoctorNotAvailableException("Doctor is not available at this time");
        }

        if (id == 0) {
            appointmentService.bookAppointment(patientId, doctorId, time);
        } else {
            appointmentService.updateAppointment(id, patientId, doctorId, time);
        }
        return "redirect:/appointments/list";
    }

    @GetMapping("/delete")
    public String deleteAppointment(@RequestParam("appointmentId") int appointmentId) {
        appointmentService.deleteAppointmentById(appointmentId);
        return "redirect:/appointments/list";
    }

}
