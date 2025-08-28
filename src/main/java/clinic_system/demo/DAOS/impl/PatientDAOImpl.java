package clinic_system.demo.DAOS.impl;

import clinic_system.demo.DAOS.PatientDAO;
import clinic_system.demo.entities.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PatientDAOImpl implements PatientDAO {

    private EntityManager entityManager;

    @Autowired
    public PatientDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Patient patient) {
        entityManager.persist(patient);
    }

    @Override
    public Patient findById(int id) {
        return entityManager.find(Patient.class, id);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        entityManager.remove(entityManager.find(Patient.class, id));
    }

    @Override
    public List<Patient> findAll() {
        //create query
        TypedQuery<Patient> theQuery = entityManager.createQuery("FROM Patient", Patient.class);
        //return query results
        return theQuery.getResultList();
    }

}
