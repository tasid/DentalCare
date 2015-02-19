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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@ManagedBean(name = "AppointmentMB")
@RequestScoped
public class AppointmentMB {

    @EJB
    private AppointmentEJB appointmentEJB;
    
    @EJB
    private EmailEJB emailEJB;
    
    @ManagedProperty(value = "#{DoctorMB}")
    private DoctorMB doctorMB;
    
    @ManagedProperty(value = "#{PatientMB}")
    private PatientMB patientMB;
    
    @ManagedProperty(value = "#{UtilityMB}")
    private UtilityMB utilityMB;

    private Appointment appointment;
    private List<Appointment> appointmentList;
    private List<String> reservedAppointmentList;

    @Transient
    @Temporal(TemporalType.DATE)
    private Date currentDate = new Date();
    
    public AppointmentMB() { }
    
    @PostConstruct
    public void init() {
        appointment = new Appointment();
        appointmentList = new ArrayList();
    }
    
    public String save() {
        try {
            Doctor selectedDoctor = doctorMB.getDoctor(appointment.getDoctorId());
            appointment.setDoctor(selectedDoctor);
            
            Patient patient = patientMB.getPatient(appointment.getPatientId()); //id should be the logged in user id
            appointment.setPatient(patient);
            
            if(appointment.getId() == 0)
                appointment.setAppointmentStatus(UtilityClass.AppointmentStatusEnum.Open.toString());

            //validating duplicate appointment
//            List<String> reservedAppointments = getReservedAppointmentList(appointment.getDoctorId(), appointment.getAppointmentDate());
//            if(reservedAppointments.contains(this.appointment.getAppointmentTime())) {
//                Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
//                flash.put("invalidAppointmentTime", "Please select another date and time. Sorry, the selected Appointment Time may be reserved by another person!");
//            }
            
            appointmentEJB.save(appointment);
            //Sending Email To Patient
            SimpleDateFormat ft = new SimpleDateFormat ("d MMM, Y");
            
            String patientName = patient.getFirstName()+" "+patient.getLastName();
            String emailTo = patient.getEmail();
            String emailSubject = "About Appoinment Schedule - "+ ft.format(appointment.getAppointmentDate())+" "+appointment.getAppointmentTime();
            String emailBody = "<div>";
            
            emailBody +="Dear "+patientName+",<br />";
            emailBody += "Your Appoinment Information <br /><br />";
            emailBody += "Doctor : "+ selectedDoctor.getFirstName() + " "+selectedDoctor.getLastName()+ " - <br /> ";
            emailBody += "Date : "+  ft.format(appointment.getAppointmentDate()) + "<br /> ";
            emailBody += "Time : "+ appointment.getAppointmentTime() + "<br /><br /><br /> ";
            emailBody += "Best Regards <br /> Dental Care <br /> Phone: (641)222-1110 <br /> Email: care@thedentalcare.com";
            emailBody += "/div";
            
            emailEJB.sendEmail(emailTo, emailSubject, emailBody);
            
            return "appointmentConfirmation";
        }
        catch(Exception ex) {            
            return "errorPage";
        }
    }
    
    public String createAppointment(int doctorId){
        try {
            this.appointment.setDoctorId(doctorId);
            this.appointment.setPatientId(12); //logged in patient Id if the role is Patient
            this.appointment.setAppointmentDate(new Date());
            this.appointment.setAppointmentStatus(UtilityClass.AppointmentStatusEnum.Open.toString());
            return "makeAppointment";
        }
        catch(Exception ex) {
            return "errorPage";
        }
    }
    
    public String editAppointment(int appointmentId){
        try {
            this.appointment = appointmentEJB.find(appointmentId);
            if(this.appointment.getDoctor() != null)
                appointment.setDoctorId(this.appointment.getDoctor().getId());
            
            if(this.appointment.getPatient() != null)
                appointment.setPatientId(this.appointment.getPatient().getId());
                 
            return "makeAppointment";
        }
        catch(Exception ex) {
            return "errorPage";
        }
    }
    public String deleteAppointment(int appointmentId){
        try {
            this.appointment = appointmentEJB.find(appointmentId);
            appointmentEJB.delete(this.appointment);
            return "appointmentList";
        } 
        catch(Exception ex) {
            return "errorPage";
        }
    }
    
    public String cancelAppointment(int appointmentId){
        try {
            appointmentEJB.updateAppointmentStatus(appointmentId, UtilityClass.AppointmentStatusEnum.Cancel);
            return "index";
        }
        catch(Exception ex) {
            return "errorPage";
        }
    }
    
    public String appointmentDone(int appointmentId){
        try {
            appointmentEJB.updateAppointmentStatus(appointmentId, UtilityClass.AppointmentStatusEnum.Done);
            return "index";
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

    public List<String> getReservedAppointmentList(int doctorId, Date appointmentDate) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calobj = Calendar.getInstance();
            String date = df.format(calobj.getTime());

            reservedAppointmentList = appointmentEJB.findAppointmentsByDoctorAndDate(doctorId, appointmentDate);
        }
        catch(Exception ex) {
            System.out.println("Error at getReservedAppointmentList() in AppointmentMB: " + ex.getMessage());
        }
        return reservedAppointmentList;
    }

    public Date getCurrentDate() {
        return currentDate;
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

    public EmailEJB getEmailEJB() {
        return emailEJB;
    }

    public void setEmailEJB(EmailEJB emailEJB) {
        this.emailEJB = emailEJB;
    }
       
}
