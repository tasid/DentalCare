/*
 * @Copyright at 99.99%
 */
package dcare.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Prescription implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Lob 
    @Column(length=1000)
    private String history;
    @Lob 
    @Column(length=1000)
    private String symptoms;
    @Lob 
    @Column(length=1000)
    private String tests;
    @Lob 
    @Column(length=1000)
    private String medication;
    @Lob 
    @Column(length=1000)
    private String advice;
    private String referedBy;
    private String currentStatus;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedAt;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "jnd_prescription_doctor",
        joinColumns = @JoinColumn(name = "prescription_fk"),
        inverseJoinColumns = @JoinColumn(name = "prescription_doctor_fk") 
    )
    private Doctor doctor;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "jnd_prescription_patient",
        joinColumns = @JoinColumn(name = "prescription_fk"),
        inverseJoinColumns = @JoinColumn(name = "prescription_patient_fk") 
    )
    private Patient patient;
    
    @Transient
    private int doctorId;
    
    @Transient
    private int patientId;
    
    public Prescription() {
    }

    public Prescription(String history, String symptoms, String tests, String medication, String advice, String referedBy, String currentStatus) {
        this.history = history;
        this.symptoms = symptoms;
        this.tests = tests;
        this.medication = medication;
        this.advice = advice;
        this.referedBy = referedBy;
        this.currentStatus = currentStatus;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getTests() {
        return tests;
    }

    public void setTests(String tests) {
        this.tests = tests;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getReferedBy() {
        return referedBy;
    }

    public void setReferedBy(String referedBy) {
        this.referedBy = referedBy;
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

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
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

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        hash = 47 * hash + Objects.hashCode(this.symptoms);
        hash = 47 * hash + Objects.hashCode(this.medication);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Prescription other = (Prescription) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Prescription{" + "id=" + id + ", symptoms=" + symptoms + ", medication=" + medication + '}';
    }
}
