/*
 * @Copyright at 99.99%
 */
package dcare.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.List;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

@Entity
@DiscriminatorValue("D")
public class Doctor extends Person implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String degree;
    @Size(max = 500)
    private String profileDescription;
    private String speciality;
    private String achievement;
    @Temporal(TemporalType.DATE)
    private Date joiningDate;
    private String workStatus;
    private String statusDescription;
            
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    private List<Prescription> prescriptions;
    
    @OneToOne(mappedBy = "doctor", fetch = FetchType.EAGER)
    private Appointment appointment;
    
    public Doctor() {
    }

    public Doctor(String degree, String profileDescription, String speciality, String achievement, Date joiningDate, String workStatus, String statusDescription) {
        this.degree = degree;
        this.profileDescription = profileDescription;
        this.speciality = speciality;
        this.achievement = achievement;
        this.joiningDate = joiningDate;
        this.workStatus = workStatus;
        this.statusDescription = statusDescription;
    }
    
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.id;
        hash = 61 * hash + Objects.hashCode(this.degree);
        hash = 61 * hash + Objects.hashCode(this.profileDescription);
        hash = 61 * hash + Objects.hashCode(this.speciality);
        hash = 61 * hash + Objects.hashCode(this.achievement);
        hash = 61 * hash + Objects.hashCode(this.joiningDate);
        hash = 61 * hash + Objects.hashCode(this.workStatus);
        hash = 61 * hash + Objects.hashCode(this.statusDescription);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Doctor)) {
            return false;
        }
        Doctor other = (Doctor) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Doctor{" + "id=" + id + ", degree=" + degree + ", profileDescription=" + profileDescription + ", speciality=" + speciality + ", achievement=" + achievement + ", joiningDate=" + joiningDate + ", workStatus=" + workStatus + ", statusDescription=" + statusDescription + '}';
    }
}
