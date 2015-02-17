/*
 * @Copyright at 99.99%
 */
package dcare.ejbs;

import dcare.entities.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

@Stateless
@LocalBean
public class AppointmentEJB {

    @PersistenceContext
    private EntityManager em;
    
    public void save(Appointment appointment) {
        if(appointment.getId() != 0) {
            appointment.setModifiedAt(new Date());
            em.merge(appointment);
        }
        else {
            appointment.setCreatedAt(new Date());
            em.persist(appointment); 
        }
    }

    public Appointment find(int id) {
        return em.find(Appointment.class, id);
    }

    public List<Appointment> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Appointment.class));
        return (List<Appointment>)em.createQuery(cq).getResultList();
    }
    
    public List<Appointment> findAllAppointments() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Appointment> criQuery = builder.createQuery(Appointment.class);
        
        Root<Appointment> appointment = criQuery.from(Appointment.class);
        criQuery.select(appointment);
        
        TypedQuery<Appointment> query = em.createQuery(criQuery);
        return query.getResultList();
    }
    
//    public List<String> findAppointmentsByDoctorAndDate(int doctorId, Date appointmentDate) {
//        CriteriaBuilder builder = em.getCriteriaBuilder();
//        CriteriaQuery<Appointment> criQuery = builder.createQuery(Appointment.class);
//        
//        Root<Appointment> appointment = criQuery.from(Appointment.class);
//        Join<Appointment, Doctor> doctor = appointment.join(Appointment_.doctor);
//        criQuery.where(builder.equal(doctor.get(Doctor_.id), doctorId),
//                       builder.equal(appointment.get(Appointment_.appointmentDate), appointmentDate),
//                       builder.equal(appointment.get(Appointment_.appointmentStatus), UtilityClass.AppointmentStatusEnum.Open.toString()));
//        criQuery.select(appointment);
//        
//        TypedQuery<Appointment> query = em.createQuery(criQuery);
//        List<Appointment> appointments = query.getResultList();
//        
//        List<String> reservedTimes = new ArrayList();
//        for (Appointment appnt : appointments) {
//            reservedTimes.add(appnt.getAppointmentTime());
//        }
//        return reservedTimes;
//    }

    public List<Appointment> findAppointmentsByDoctorAndDate(int doctorId, String appointmentDate) {
        StringBuilder stringBuilder = new StringBuilder("select a.* from APPOINTMENT a ")
                .append(" inner join jnd_appointment_doctor jad on a.id = jad.appointment_fk ")
                .append(" inner join PERSON p on p.id = jad.appointment_doctor_fk ")
                .append(" where p.UserType = 'D' and p.id = ?1 and a.APPOINTMENTDATE = '?2' and a.APPOINTMENTSTATUS = 'Open'");
        
        Query query = em.createNativeQuery(stringBuilder.toString(), Appointment.class);
        query.setParameter("1", doctorId);
        query.setParameter("2", appointmentDate);
        
        List<Appointment> appointments = query.getResultList();
        return query.getResultList();
    }
    
    public void delete(Appointment appointment) {
        appointment = em.merge(appointment);
        em.remove(appointment);
    }
}
