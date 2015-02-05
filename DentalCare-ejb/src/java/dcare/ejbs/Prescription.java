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
public class Prescription {

    @PersistenceContext
    private EntityManager em;
    
    public void save(Prescription prescription) {
        em.persist(prescription);
    }
    
    public void update(Prescription prescription) {
        em.merge(prescription);
    }

    public Prescription find(int id) {
        return em.find(Prescription.class, id);
    }

    public Prescription findAll(int id) {
        return em.find(Prescription.class, id);
    }

    public void delete(int id) {
        Prescription prescription = find(id);
        em.remove(prescription);
    } 
}
