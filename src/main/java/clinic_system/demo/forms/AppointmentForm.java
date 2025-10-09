package clinic_system.demo.forms;

import java.time.LocalDateTime;

public class AppointmentForm {
    private Integer id;
    private LocalDateTime time;
    private Integer patientId;
    private Integer doctorId;

    public AppointmentForm(Integer id, LocalDateTime time, Integer patientId, Integer doctorId) {
        this.id = id;
        this.time = time;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }

    public AppointmentForm() {

    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }
}
