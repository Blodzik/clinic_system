package clinic_system.demo.DAOS.impl;

import clinic_system.demo.DAOS.DoctorDAO;
import clinic_system.demo.entities.Doctor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class DoctorDAOImpl implements DoctorDAO {
    private EntityManager entityManager;

    @Autowired
    public DoctorDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Doctor doctor) {
        entityManager.merge(doctor);
    }

    @Override
    public Optional<Doctor> findById(int id) {
        Doctor doctor = entityManager.find(Doctor.class, id);
        return Optional.ofNullable(doctor);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        entityManager.remove(entityManager.find(Doctor.class, id));
    }

    @Override
    public List<Doctor> findAll() {
        //create query
        TypedQuery<Doctor> theQuery = entityManager.createQuery("FROM Doctor", Doctor.class);
        //return query
        return theQuery.getResultList();
    }
}
