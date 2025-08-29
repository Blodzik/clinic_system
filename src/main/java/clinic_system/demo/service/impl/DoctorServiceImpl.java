package clinic_system.demo.service.impl;

import clinic_system.demo.DAOS.DoctorDAO;
import clinic_system.demo.entities.Doctor;
import clinic_system.demo.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    private DoctorDAO doctorDAO;

    @Autowired
    public DoctorServiceImpl(DoctorDAO doctorDAO) {
        this.doctorDAO = doctorDAO;
    }

    @Override
    @Transactional
    public void save(Doctor doctor) {
        doctorDAO.save(doctor);
    }

    @Override
    public Doctor findById(int id) {
        return doctorDAO.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        doctorDAO.deleteById(id);
    }

    @Override
    public List<Doctor> findAll() {
        return doctorDAO.findAll();
    }
}
