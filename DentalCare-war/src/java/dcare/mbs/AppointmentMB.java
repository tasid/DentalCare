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

@ManagedBean(name = "AppointmentMB")
@RequestScoped
public class AppointmentMB {

    @EJB
    private AppointmentEJB appointmentEJB;

    private Appointment appointment;
    private List<Appointment> appointmentList;

    public AppointmentMB() { }
    
    @PostConstruct
    public void init() {
        appointment = new Appointment();
        appointmentList = new ArrayList();
    }
    
    public String save() {
        try {
            //appointmentEJB.save(appointment);
            return "appointmentConfirmation";
        }
        catch(Exception ex) {            
            return "errorPage";
        }
    }
    
    public String editAppointment(int id){
        try {
            this.appointment = appointmentEJB.find(id);
            return "appointment";
        }
        catch(Exception ex) {
            return "errorPage";
        }
    }
    
    public String deleteAppointment(int id){
        try {
            this.appointment = appointmentEJB.find(id);
            appointmentEJB.delete(this.appointment);
            return "appointmentList";
        } 
        catch(Exception ex) {
            return "errorPage";
        }
    }
    
    public String makeAppointment(int id){
        try {
//            this.appointment = appointmentEJB.find(id);
            return "makeAppointment";
        }
        catch(Exception ex) {
            return "errorPage";
        }
    }
    
    public String viewProfileDetail(int id){
        try {
            this.appointment = appointmentEJB.find(id);
            return "viewAppointmentProfile";
        }
        catch(Exception ex) {
            return "errorPage";
        }
    }
    
    public List<Appointment> getAppointmentList() {
        this.appointmentList = appointmentEJB.findAllAppointments();
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}
