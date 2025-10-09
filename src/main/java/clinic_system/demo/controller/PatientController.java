package clinic_system.demo.controller;

import clinic_system.demo.entities.Patient;
import clinic_system.demo.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {
    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/list")
    public String listPatients(Model model) {
        List<Patient> patients = patientService.findAll();

        model.addAttribute("patients", patients);

        return "patients/list-patients";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Patient patient = new Patient();

        model.addAttribute("patient", patient);

        return "patients/patient-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("patientId") int id, Model model) {

        //get patient from the service
        Patient patient = patientService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid patient Id: " + id));


        //set the patient in the model to preopulate the form
        model.addAttribute("patient", patient);

        //send over to our form


        return "patients/patient-form";
    }

    @PostMapping("/save")
    public String savePatient(@ModelAttribute("patient") Patient patient) {
        patientService.save(patient);
        return "redirect:/patients/list";
    }

    @GetMapping("/delete")
    public String deletePatient(@RequestParam("patientId") int id) {
        patientService.deleteById(id);
        return "redirect:/patients/list";
    }
}
