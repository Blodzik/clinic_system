package clinic_system.demo.DAOS;

import clinic_system.demo.entities.Patient;

import java.util.List;

public interface PatientDAO {
    void save(Patient patient);
    Patient findById(int id);
    void deleteById(int id);
    List<Patient> findAll();
}
