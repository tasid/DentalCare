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
    private List<DoctorAppointments> doctorAppointmentList = new ArrayList();

    @Transient
    private List<String> reservedTimes = new ArrayList();
    
    public ReportMB() { }
    
    @PostConstruct
    public void init() {
        appointmentList = new ArrayList();
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

    public List<String> getReservedTimes(int doctorId) {
        Map apptMap = getDoctorAppointments();
        
        if(apptMap.size() > 0)
            reservedTimes = (ArrayList<String>)apptMap.get(doctorId);
        
        return reservedTimes;
    }

    public List<DoctorAppointments> getDoctorAppointmentList() {
        HashMap dApps = getDoctorAppointments();
        Iterator it = dApps.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            DoctorAppointments dappoint = new DoctorAppointments();
            dappoint.setDoctorId((Integer)pair.getKey());
            dappoint.setReservedAppointments((ArrayList<String>)pair.getValue());
            doctorAppointmentList.add(dappoint);
        }
        return doctorAppointmentList;
    }
    
    public HashMap getDoctorAppointments() {
        this.appointmentList = appointmentEJB.findAppointmentsByDate(appointmentDate);
        for(Appointment apt : appointmentList) {
            if(!doctorAppointments.containsKey(apt.getDoctor().getId()))
                doctorAppointments.put(apt.getDoctor().getId(), getAppTimes(apt.getDoctor().getId(), appointmentList));
        }
        return doctorAppointments;
    }
    
    private List<String> getAppTimes(int doctorId, List<Appointment> appointments) {
        List<String> times = new ArrayList();
        for(Appointment apt : appointments) {
            if(apt.getDoctor().getId() == doctorId && !times.contains(apt.getAppointmentTime()))
                times.add(apt.getAppointmentTime());
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
