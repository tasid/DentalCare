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
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@DiscriminatorValue("D")
@XmlRootElement
public class Doctor extends Person implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Lob 
    @Column(length=1000)
    private String profileDescription;
    private String degree;
    @Temporal(TemporalType.DATE)
    private Date joiningDate;
    private String workStatus;
    private String statusDescription;
    private String imageUrl; //temporary field, the image will be saved in/retrieved from database
    private String imageUrlProfileDetail;
            
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    private List<Prescription> prescriptions;
    
    @OneToOne(mappedBy = "doctor", fetch = FetchType.EAGER)
    private Appointment appointment;
    
    public Doctor() {
    }

    public Doctor(String profileDescription, String degree, Date joiningDate, String workStatus, String statusDescription) {
        this.profileDescription = profileDescription;
        this.degree = degree;
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

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrlProfileDetail() {
        return imageUrlProfileDetail;
    }

    public void setImageUrlProfileDetail(String imageUrlProfileDetail) {
        this.imageUrlProfileDetail = imageUrlProfileDetail;
    }

    @XmlTransient
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
        hash = 61 * hash + Objects.hashCode(this.profileDescription);
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
        return "Doctor{" + "id=" + id + ", profileDescription=" + profileDescription + ", joiningDate=" + joiningDate + ", workStatus=" + workStatus + ", statusDescription=" + statusDescription + '}';
    }
}
