package clinic_system.demo.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name", nullable=false)
    private String name;

    @Column(name="specialization", nullable=false)
    private String specialization;

    @OneToMany(mappedBy="doctor", cascade=CascadeType.ALL, orphanRemoval=true)
    @com.fasterxml.jackson.annotation.JsonIgnore
    private List<Appointment> appointments = new ArrayList<>();


    public Doctor() {}

    public Doctor(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
