/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dcare.ejbs;

import dcare.entities.Appointment;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;

/**
 *
 * @author MdTasiddiqul
 */
@Stateless
@LocalBean
public class ReminderEJB {
    
    @EJB
    private AppointmentEJB appointmentEJB;
    
    @EJB
    private EmailEJB emailEJB;
    
     private List<Appointment> appointmentList;

    @Schedule(dayOfWeek = "Mon-Fri", hour = "4", minute = "58", second = "0")
    //@Schedule( minute = "*/1", hour = "*", persistent = false)
    public void myTimer() {
        
        appointmentList = appointmentEJB.findTommorrowsAppoinment();
        
        for(Appointment appointment : appointmentList){
            System.out.println("Timer event: " + appointment.getPatient().getEmail() + new Date());
            
            //Sending Email To Patient
            SimpleDateFormat ft = new SimpleDateFormat ("d MMM, Y");
            
            String patientName = appointment.getPatient().getFirstName()+" "+appointment.getPatient().getLastName();
            String emailTo = appointment.getPatient().getEmail();
            String emailSubject = "Reminder of Tomorrows Appoinment Schedule";
            String emailBody = "<div>";
            
            emailBody +="Dear "+patientName+",<br />";
            emailBody += "Your Appoinment Information <br /><br />";
            emailBody += "Doctor : "+ appointment.getDoctor().getFirstName() + " "+appointment.getDoctor().getLastName()+ " - <br /> ";
            emailBody += "Date : "+  ft.format(appointment.getAppointmentDate()) + "<br /> ";
            emailBody += "Time : "+ appointment.getAppointmentTime() + "<br /><br /><br /> ";
            emailBody += "Best Regards <br /> Dental Care <br /> Phone: (641)222-1110 <br /> Email: care@thedentalcare.com";
            emailBody += "/div";
            
            emailEJB.sendEmail(emailTo, emailSubject, emailBody);
        }
        
        
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
