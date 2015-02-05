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
public class PatientEJB {

    @PersistenceContext
    private EntityManager em;
    
    public void save(Patient patient) {
        em.persist(patient);
    }

    public void update(Patient patient) {
        em.merge(patient);
    }

    public Patient find(int id) {
        return em.find(Patient.class, id);
    }

    public Patient findAll(int id) {
        return em.find(Patient.class, id);
    }

    public void delete(int id) {
        Patient patient = find(id);
        em.remove(patient);
    }
}
