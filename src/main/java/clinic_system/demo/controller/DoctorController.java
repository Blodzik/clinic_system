package clinic_system.demo.controller;

import clinic_system.demo.entities.Doctor;
import clinic_system.demo.exception.ResourceNotFoundException;
import clinic_system.demo.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/list")
    public String listDoctors(Model model) {
        List doctors = doctorService.findAll();
        model.addAttribute("doctors", doctors);
        return "doctors/list-doctors";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Doctor doctor = new Doctor();
        model.addAttribute("doctor", doctor);
        return "doctors/doctor-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("doctorId") int id, Model model) {
        Doctor doctor = doctorService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor Id: " + id));

        model.addAttribute("doctor", doctor);
        return "doctors/doctor-form";
    }

    @PostMapping("/save")
    public String saveDoctor(@ModelAttribute("doctor") Doctor doctor) {
        doctorService.save(doctor);
        return "redirect:/doctors/list";
    }

    @GetMapping("/delete")
    public String deleteDoctor(@RequestParam("doctorId") int id) {
        doctorService.deleteById(id);
        return "redirect:/doctors/list";
    }

}
