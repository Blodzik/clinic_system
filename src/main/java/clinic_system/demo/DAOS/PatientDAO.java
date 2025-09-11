package clinic_system.demo.DAOS;

import clinic_system.demo.entities.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientDAO {
    void save(Patient patient);
    Optional<Patient> findById(int id);
    void deleteById(int id);
    List<Patient> findAll();
}
