/*
 * @Copyright at 99.99%
 */
package dcare.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import dcare.entities.UtilityClass.*;
import java.util.Objects;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Appointment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date appointmentDate;
    private String referredBy;
    private String problemDescription;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedAt;
    private AppointmentStatusEnum appointmentStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "jnd_appointment_patient",
        joinColumns = @JoinColumn(name = "appointment_fk"),
        inverseJoinColumns = @JoinColumn(name = "appointment_patient_fk") 
    )
    private Patient patient;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "jnd_appointment_doctor",
        joinColumns = @JoinColumn(name = "appointment_fk"),
        inverseJoinColumns = @JoinColumn(name = "appointment_doctor_fk") 
    )
    private Doctor doctor;
    
    public Appointment() {
    }

    public Appointment(Date appointmentDate, String referredBy, String problemDescription, Date createdAt, Date modifiedAt, AppointmentStatusEnum appointmentStatus) {
        this.appointmentDate = appointmentDate;
        this.referredBy = referredBy;
        this.problemDescription = problemDescription;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.appointmentStatus = appointmentStatus;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getReferredBy() {
        return referredBy;
    }

    public void setReferredBy(String referredBy) {
        this.referredBy = referredBy;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public AppointmentStatusEnum getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatusEnum appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.appointmentDate);
        hash = 79 * hash + Objects.hashCode(this.referredBy);
        hash = 79 * hash + Objects.hashCode(this.problemDescription);
        hash = 79 * hash + Objects.hashCode(this.createdAt);
        hash = 79 * hash + Objects.hashCode(this.modifiedAt);
        hash = 79 * hash + Objects.hashCode(this.appointmentStatus);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appointment)) {
            return false;
        }
        Appointment other = (Appointment) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Appointment{" + "id=" + id + ", appointmentDate=" + appointmentDate + ", referredBy=" + referredBy + ", problemDescription=" + problemDescription + ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt + ", appointmentStatus=" + appointmentStatus + '}';
    }
}
