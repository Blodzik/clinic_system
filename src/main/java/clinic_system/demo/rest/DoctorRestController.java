package clinic_system.demo.rest;

import clinic_system.demo.entities.Doctor;
import clinic_system.demo.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorRestController {
    private DoctorService doctorService;

    @Autowired
    public DoctorRestController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/doctors")
    public List<Doctor> getDoctors() {
        return doctorService.findAll();
    }

    @GetMapping("/doctors/{doctorId}")
    public Doctor getDoctor(@PathVariable int doctorId) {
        return doctorService.findById(doctorId);
    }

    @PostMapping("doctors")
    public void saveDoctor(@RequestBody Doctor doctor) {
        doctor.setId(0);

        doctorService.save(doctor);
    }
}
