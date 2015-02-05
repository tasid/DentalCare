/*
 * @Copyright at 99.99%
 */
package dcare.ejbs;

import dcare.entities.*;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class AppointmentEJB {

    @PersistenceContext
    private EntityManager em;
    
    public void save(Appointment appointment) {
        em.persist(appointment);
    }
    
    public void update(Appointment appointment) {
        em.merge(appointment);
    }

    public Appointment find(int id) {
        return em.find(Appointment.class, id);
    }

    public Appointment findAll(int id) {
        return em.find(Appointment.class, id);
    }

    public void delete(int id) {
        Appointment appointment = find(id);
        em.remove(appointment);
    } 
}
