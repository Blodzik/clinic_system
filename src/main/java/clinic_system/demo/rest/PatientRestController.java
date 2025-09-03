package clinic_system.demo.rest;

import clinic_system.demo.entities.Patient;
import clinic_system.demo.exception.ResourceNotFoundException;
import clinic_system.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        if(patientId < 0 || patientId > patientService.findAll().size()) {
            throw new ResourceNotFoundException("Patient", patientId);
        }
        return patientService.findById(patientId);
    }

    @PostMapping("/patients")
    public void savePatient(@RequestBody Patient patient) {
        patient.setId(0);

        patientService.save(patient);
    }


}
