package clinic_system.demo.DAOS.impl;

import clinic_system.demo.DAOS.AppointmentDAO;
import clinic_system.demo.entities.Appointment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppointmentDAOImpl implements AppointmentDAO {
    private EntityManager entityManager;

    @Autowired
    public AppointmentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void addAppointment(Appointment appointment) {
        entityManager.persist(appointment);
    }

    @Override
    public void findAppointmentById(int id) {
        entityManager.find(Appointment.class, id);
    }

    @Override
    @Transactional
    public void deleteAppointmentById(int id) {
        entityManager.remove(entityManager.find(Appointment.class, id));
    }

    @Override
    public List<Appointment> findAll() {
        //create query
        TypedQuery<Appointment> theQuery = entityManager.createQuery("FROM Appointment", Appointment.class);
        //return query
        return theQuery.getResultList();
    }
}
