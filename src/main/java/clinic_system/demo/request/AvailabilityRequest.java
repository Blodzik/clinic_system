package clinic_system.demo.request;

import java.time.LocalDateTime;

public class AvailabilityRequest {
    private int doctorId;
    private LocalDateTime time;

    public AvailabilityRequest() {};

    public int getDoctorId() {
        return doctorId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
