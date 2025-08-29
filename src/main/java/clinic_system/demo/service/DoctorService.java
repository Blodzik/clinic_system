package clinic_system.demo.service;

import clinic_system.demo.entities.Doctor;

import java.util.List;

public interface DoctorService {
    void save(Doctor doctor);
    Doctor findById(int id);
    void deleteById(int id);
    List<Doctor> findAll();
}
