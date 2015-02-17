/*
 * @Copyright at 99.99%
 */
package dcare.mbs;

import dcare.ejbs.*;
import dcare.entities.*;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "PatientMB")
@RequestScoped
public class PatientMB {

    @EJB
    private PatientEJB patientEJB;

    private Patient patient;
    private List<Patient> patientList;

    public PatientMB() { }
    
    @PostConstruct
    public void init() {
        patient = new Patient();
        patientList = new ArrayList();
    }
    
    public String save() {
        try {
            patientEJB.save(this.patient);
            return "patientList";
        }
        catch(Exception ex) {            
            return "errorPage";
        }
    }
    
    public String editPatient(int id){
        try {
            this.patient = patientEJB.find(id);
            return "patient";
        }
        catch(Exception ex) {
            return "errorPage";
        }
    }
    
    public String deletePatient(int id){
        try {
            this.patient = patientEJB.find(id);
            patientEJB.delete(this.patient);
            return "patientList";
        } 
        catch(Exception ex) {
            return "errorPage";
        }
    }
    
    public String viewPatient(int id){
        try {
            this.patient = patientEJB.find(id);
            return "viewPatient";
        }
        catch(Exception ex) {
            return "errorPage";
        }
    }
    
    public List<Patient> getPatientList() {
        this.patientList = patientEJB.findAllPatients();
        return patientList;
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }

    public Patient getPatient(int id) {
        Patient patient = patientEJB.find(id);
        return patient;
    }
    
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}