/*
 * @Copyright at 99.99%
 */
package dcare.ejbs;

import dcare.entities.*;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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

    public void delete(Appointment appointment) {
        appointment = em.merge(appointment);
        em.remove(appointment);
    }
}
