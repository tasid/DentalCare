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
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "PrescriptionMB")
@RequestScoped
public class PrescriptionMB {

    @EJB
    private PrescriptionEJB prescriptionEJB;

    @ManagedProperty(value = "#{DoctorMB}")
    private DoctorMB doctorMB;
    
    @ManagedProperty(value = "#{PatientMB}")
    private PatientMB patientMB;
    
    @ManagedProperty(value = "#{UtilityMB}")
    private UtilityMB utilityMB;
    
    private Prescription prescription;
    private List<Prescription> prescriptionList;

    public PrescriptionMB() { }
    
    @PostConstruct
    public void init() {
        prescription = new Prescription();
        prescriptionList = new ArrayList();
    }
    
    public String save() {
        try {
            Doctor selectedDoctor = doctorMB.getDoctor(prescription.getDoctorId());
            prescription.setDoctor(selectedDoctor);
            
            Patient patient = patientMB.getPatient(prescription.getPatientId());
            prescription.setPatient(patient);
            
            if(prescription.getId() == 0)
                prescription.setCurrentStatus(UtilityClass.PrescriptionStatusEnum.Open.toString());
            
            prescriptionEJB.save(prescription);
            return "prescriptionList";
        }
        catch(Exception ex) {            
            return "errorPage";
        }
    }
    
    public String editPrescription(int id){
        try {
            this.prescription = prescriptionEJB.find(id);
            this.prescription.setDoctorId(this.prescription.getDoctor().getId());
            this.prescription.setPatientId(this.prescription.getPatient().getId());
            
            return "prescription";
        }
        catch(Exception ex) {
            return "errorPage";
        }
    }
    
    public String generatePrescriptionPdf(int id){
        try {
            this.prescription = prescriptionEJB.find(id);
            return "prescriptionList";
        }
        catch(Exception ex) {
            return "errorPage";
        }
    }
    public String sendPrescriptionViaEmail(int id){
        try {
            this.prescription = prescriptionEJB.find(id);
            return "prescriptionList";
        }
        catch(Exception ex) {
            return "errorPage";
        }
    }
    
    public String deletePrescription(int id){
        try {
            this.prescription = prescriptionEJB.find(id);
            prescriptionEJB.delete(this.prescription);
            return "prescriptionList";
        } 
        catch(Exception ex) {
            return "errorPage";
        }
    }
    
    public String viewPrescription(int id){
        try {
            this.prescription = prescriptionEJB.find(id);
            return "viewPrescription";
        }
        catch(Exception ex) {
            return "errorPage";
        }
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public List<Prescription> getPrescriptionList() {
        this.prescriptionList = prescriptionEJB.findAllPrescriptions();
        return prescriptionList;
    }

    public void setPrescriptionList(List<Prescription> prescriptionList) {
        this.prescriptionList = prescriptionList;
    }    

    public DoctorMB getDoctorMB() {
        return doctorMB;
    }

    public void setDoctorMB(DoctorMB doctorMB) {
        this.doctorMB = doctorMB;
    }

    public PatientMB getPatientMB() {
        return patientMB;
    }

    public void setPatientMB(PatientMB patientMB) {
        this.patientMB = patientMB;
    }

    public UtilityMB getUtilityMB() {
        return utilityMB;
    }

    public void setUtilityMB(UtilityMB utilityMB) {
        this.utilityMB = utilityMB;
    }
}
