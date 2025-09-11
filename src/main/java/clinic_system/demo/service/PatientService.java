package clinic_system.demo.service;

import clinic_system.demo.entities.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    void save(Patient patient);
    Optional<Patient> findById(int id);
    void deleteById(int id);
    List<Patient> findAll();
}
