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

@ManagedBean(name = "DoctorMB")
@RequestScoped
public class DoctorMB {

    @EJB
    private DoctorEJB doctorEJB;

    private Doctor doctor;
    private List<Doctor> doctorList;
    private List<Doctor> activeDoctorList;

    public DoctorMB() { }
    
    @PostConstruct
    public void init() {
        doctor = new Doctor();
        doctorList = new ArrayList();
        activeDoctorList = new ArrayList();
    }
    
    public String save() {
        try {
            doctorEJB.save(doctor);
            return "/doctor/doctorList";
        }
        catch(Exception ex) {            
            return "errorPage";
        }
    }
    
    public String editDoctor(int id){
        try {
            this.doctor = doctorEJB.find(id);
            return "/doctor/doctor";
        }
        catch(Exception ex) {
            return "errorPage";
        }
    }
    
    public String deleteDoctor(int id){
        try {
            this.doctor = doctorEJB.find(id);
            doctorEJB.delete(this.doctor);
            return "/doctor/doctorList";
        } 
        catch(Exception ex) {
            return "errorPage";
        }
    }
    
    public String viewDoctor(int id){
        try {
            this.doctor = doctorEJB.find(id);
            return "/doctor/viewDoctor";
        }
        catch(Exception ex) {
            return "errorPage";
        }
    }
    
    public String viewProfileDetail(int id){
        try {
            this.doctor = doctorEJB.find(id);
            return "/doctor/viewDoctorProfile";
        }
        catch(Exception ex) {
            return "errorPage";
        }
    }
    
    public List<Doctor> getDoctorList() {
        this.doctorList = doctorEJB.findAllDoctors();
        return doctorList;
    }

    public void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    public List<Doctor> getActiveDoctorList() {
        this.activeDoctorList = doctorEJB.findDoctorsByStatus(UtilityClass.WorkStatusEnum.Active);
        return activeDoctorList;
    }
    
    public Doctor getDoctor(int id) {
        Doctor doctor = doctorEJB.find(id);
        return doctor;
    }
    
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
