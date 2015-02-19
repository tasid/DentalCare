/*
 * @Copyright at 99.99%
 */
package dcare.mbs;

import dcare.ejbs.*;
import dcare.entities.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@ManagedBean(name = "ReportMB")
@RequestScoped
public class ReportMB {

    @EJB
    private AppointmentEJB appointmentEJB;
    
    @ManagedProperty(value = "#{DoctorMB}")
    private DoctorMB doctorMB;
    
    @ManagedProperty(value = "#{PatientMB}")
    private PatientMB patientMB;
    
    @ManagedProperty(value = "#{UtilityMB}")
    private UtilityMB utilityMB;

    private List<Appointment> appointmentList;

    @Transient
    @Temporal(TemporalType.DATE)
    private Date appointmentDate = new Date();

    @Transient
    HashMap<Integer, List<String>> doctorAppointments = new HashMap<>();
    
    @Transient
    private List<DoctorAppointments> doctorAppointmentList;
    
    public ReportMB() { }
    
    @PostConstruct
    public void init() {
        appointmentList = new ArrayList();
        doctorAppointmentList = new ArrayList();
    }    
    
    public List<Appointment> getAppointmentList() {
        this.appointmentList = appointmentEJB.findAppointmentsByDate(appointmentDate);
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public List<Appointment> getDailySchedule() {
        this.appointmentList = appointmentEJB.findAppointmentsByDate(appointmentDate); //appointment date should be considered here
        return appointmentList;
    }
    
    public List<DoctorAppointments> getDoctorAppointmentList() {
        this.appointmentList = appointmentEJB.findAppointmentsByDate(appointmentDate);
        for(Appointment apt : appointmentList) {
            Doctor dctr = apt.getDoctor();
            if(!doctorAppointments.containsKey(dctr.getId())) {
                List<String> appTimes = getAppTimes(dctr.getId(), appointmentList);
                doctorAppointments.put(dctr.getId(), appTimes);
                
                DoctorAppointments dappoint = new DoctorAppointments();
                dappoint.setDoctor(dctr);
                dappoint.setPatient(apt.getPatient());
                dappoint.setReservedAppointments(appTimes);
                doctorAppointmentList.add(dappoint);
            }
        }
        return doctorAppointmentList;
    }
    
    private List<String> getAppTimes(int doctorId, List<Appointment> appointments) {
        List<String> times = new ArrayList();
        for(Appointment apt : appointments) {
            if(apt.getDoctor().getId() == doctorId && !times.contains(apt.getAppointmentTime())) {
                times.add(apt.getAppointmentTime());
            }
        }
        return times;
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
