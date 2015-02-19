/*
 * @Copyright at 99.99%
 */
package dcare.entities;

import java.util.ArrayList;
import java.util.List;

public class DoctorAppointments {
    
    private int doctorId;
    private Doctor doctor;
    private Patient patient;
    private List<String> reservedAppointments = new ArrayList();
    
    public DoctorAppointments() {
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<String> getReservedAppointments() {
        return reservedAppointments;
    }

    public void setReservedAppointments(List<String> reservedAppointments) {
        this.reservedAppointments = reservedAppointments;
    }    
}
