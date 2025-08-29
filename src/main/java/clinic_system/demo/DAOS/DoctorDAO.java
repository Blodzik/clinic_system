package clinic_system.demo.DAOS;

import clinic_system.demo.entities.Doctor;

import java.util.List;

public interface DoctorDAO {
    void save(Doctor doctor);
    Doctor findById(int id);
    void deleteById(int id);
    List<Doctor> findAll();
}
