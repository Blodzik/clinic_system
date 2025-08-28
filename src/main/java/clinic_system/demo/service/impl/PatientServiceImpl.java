package clinic_system.demo.service.impl;

import clinic_system.demo.DAOS.PatientDAO;
import clinic_system.demo.entities.Patient;
import clinic_system.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    private PatientDAO patientDAO;

    @Autowired
    public PatientServiceImpl(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    @Override
    @Transactional
    public void save(Patient patient) {
        patientDAO.save(patient);
    }

    @Override
    public void findById(int id) {
        patientDAO.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        patientDAO.deleteById(id);
    }

    @Override
    public List<Patient> findAll() {
        return patientDAO.findAll();
    }
}
