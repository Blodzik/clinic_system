package clinic_system.demo.rest;

import clinic_system.demo.entities.Patient;
import clinic_system.demo.exception.ResourceNotFoundException;
import clinic_system.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
public class PatientRestController {

    private PatientService patientService;

    @Autowired
    public PatientRestController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients")
    public List<Patient> getPatients() {
        return patientService.findAll();
    }

    @GetMapping("/patients/{patientId}")
    public Patient getPatientById(@PathVariable int patientId) {
        return patientService.findById(patientId).orElseThrow(
                () -> new ResourceNotFoundException("Patient", patientId)
        );
    }

    @PostMapping("/patients")
    public void savePatient(@RequestBody Patient patient) {
        patient.setId(0);

        patientService.save(patient);
    }

    @DeleteMapping("/patients/{patientId}")
    public void deletePatient(@PathVariable int patientId) {
        Patient patient = patientService.findById(patientId).orElseThrow(
                () -> new ResourceNotFoundException("Patient", patientId)
        );
        patientService.deleteById(patientId);
    }
}
