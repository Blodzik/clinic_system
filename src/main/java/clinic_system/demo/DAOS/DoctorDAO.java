package clinic_system.demo.DAOS;

import clinic_system.demo.entities.Doctor;
import clinic_system.demo.entities.Patient;

import java.util.List;
import java.util.Optional;

public interface DoctorDAO {
    void save(Doctor doctor);
    Optional<Doctor> findById(int id);
    void deleteById(int id);
    List<Doctor> findAll();
}
