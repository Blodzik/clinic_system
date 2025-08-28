package clinic_system.demo.service;

import clinic_system.demo.entities.Patient;

import java.util.List;

public interface PatientService {
    void save(Patient patient);
    void findById(int id);
    void deleteById(int id);
    List<Patient> findAll();
}
