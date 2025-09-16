package clinic_system.demo.DAOS.impl;

import clinic_system.demo.DAOS.AppointmentDAO;
import clinic_system.demo.entities.Appointment;
import clinic_system.demo.entities.Doctor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    public Optional<Appointment> findAppointmentById(int id) {
        Appointment appointment = entityManager.find(Appointment.class, id);
        return Optional.ofNullable(appointment);
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

    @Override
    public boolean existByDoctorAndTimeBetween(Doctor doctor, LocalDateTime start, LocalDateTime end) {
        String jpql = """
        SELECT COUNT(a) FROM Appointment a
        WHERE a.doctor = :doctor
          AND a.time < :end
          AND a.time >= :start
    """;

        Long count = entityManager.createQuery(jpql, Long.class)
                .setParameter("doctor", doctor)
                .setParameter("start", start)
                .setParameter("end", end)
                .getSingleResult();

        return count > 0;
    }
}
