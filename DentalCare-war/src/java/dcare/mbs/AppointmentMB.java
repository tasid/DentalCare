/*
 * @Copyright at 99.99%
 */
package dcare.mbs;

import dcare.ejbs.*;
import dcare.entities.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

@ManagedBean(name = "AppointmentMB")
@RequestScoped
public class AppointmentMB {

    @EJB
    private AppointmentEJB appointmentEJB;
    
    @ManagedProperty(value = "#{DoctorMB}")
    private DoctorMB doctorMB;
    
    @ManagedProperty(value = "#{PatientMB}")
    private PatientMB patientMB;
    
    @ManagedProperty(value = "#{UtilityMB}")
    private UtilityMB utilityMB;

    private Appointment appointment;
    private List<Appointment> appointmentList;
    private List<String> reservedAppointmentList;

    public AppointmentMB() { }
    
    @PostConstruct
    public void init() {
        appointment = new Appointment();
        appointmentList = new ArrayList();
    }
    
    public String save() {
        try {
//            
//            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//            this.appointment.setAppointmentDate(df.format(Date.parse(this.appointment.getAppointmentDate())));
            
            Doctor selectedDoctor = doctorMB.getDoctor(appointment.getDoctorId());
            appointment.setDoctor(selectedDoctor);
            
            Patient patient = patientMB.getPatient(appointment.getPatientId()); //id should be the logged in user id
            appointment.setPatient(patient);
            
            if(appointment.getId() == 0)
                appointment.setAppointmentStatus(UtilityClass.AppointmentStatusEnum.Open.toString());

            List<String> reservedApps = getReservedAppointmentList();
            if(reservedApps.contains(this.appointment.getAppointmentTime())) {
                Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
                flash.put("invalidAppointmentTime", "Please select another date and time. Sorry, the selected Appointment Time may be reserved by another person!");
//                if(onlineAppointment)
//                    return makeAppointmentOnline(appointment.getDoctorId());
//                else 
//                    return makeAppointmentOffline(appointment.getId());
            }
            
            appointmentEJB.save(appointment);
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
    
    public String cancelAppointment(int id){
        try {
            int updateCount = appointmentEJB.updateAppointmentStatus(id, UtilityClass.AppointmentStatusEnum.Cancel);
            if(updateCount > 0)
                System.out.println("Appointment Updated : " + updateCount);
            
            return "index";
        }
        catch(Exception ex) {
            return "errorPage";
        }
    }
    
    public String appointmentDone(int id){
        try {
            int updateCount = appointmentEJB.updateAppointmentStatus(id, UtilityClass.AppointmentStatusEnum.Done);
            if(updateCount > 0)
                System.out.println("Appointment Updated : " + updateCount);
            
            return "index";
        }
        catch(Exception ex) {
            return "errorPage";
        }
    }
    
    public String makeAppointmentOffline(int id){
        try {
            this.appointment = appointmentEJB.find(id);
            if(this.appointment.getDoctor() != null)
                this.appointment.setDoctorId(this.appointment.getDoctor().getId());
    
            if(this.appointment.getPatient() != null)
                this.appointment.setPatientId(this.appointment.getPatient().getId());
            
            return "makeAppointmentOffline";
        }
        catch(Exception ex) {
            return "errorPage";
        }
    }
    
    public String makeAppointmentOnline(int id){
        try {
            this.appointment.setDoctorId(id);
            this.appointment.setPatientId(12); //this should be the logged in patient id
            
            return "makeAppointmentOnline";
        }
        catch(Exception ex) {
            return "errorPage";
        }
    }
    
    public String makeAppointment(int id){
        try {
            this.appointment.setDoctorId(id);
            this.appointment.setPatientId(12); //this should be the logged in patient id
            return "makeAppointmentOnline";
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

    public List<String> getReservedAppointmentList() {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calobj = Calendar.getInstance();
            String date = df.format(calobj.getTime());

            int doctorId = (this.appointment.getDoctorId() != 0) ? this.appointment.getDoctorId() : doctorMB.getActiveDoctorList().get(0).getId();
            if(this.appointment.getAppointmentDate() != null)
                date =  this.appointment.getAppointmentDate();

            reservedAppointmentList = appointmentEJB.findAppointmentsByDoctorAndDate(doctorId, date);
        }
        catch(Exception ex) {
            System.out.println("Error at getReservedAppointmentList() in AppointmentMB: " + ex.getMessage());
        }
        return reservedAppointmentList;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
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
