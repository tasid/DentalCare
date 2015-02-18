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
import javax.persistence.criteria.*;

@Stateless
@LocalBean
public class PrescriptionEJB {

    @PersistenceContext
    private EntityManager em;
    
    public void save(Prescription prescription) {
        if(prescription.getId() != 0) {
            prescription.setModifiedAt(new Date());
            em.merge(prescription);
        }
        else {
            prescription.setCreatedAt(new Date());
            em.persist(prescription); 
        }
    }

    public Prescription find(int id) {
        return em.find(Prescription.class, id);
    }

    public List<Prescription> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Prescription.class));
        return (List<Prescription>)em.createQuery(cq).getResultList();
    }
    
    public List<Prescription> findPrescriptionsByDoctor(String lastName) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Prescription> criQuery = builder.createQuery(Prescription.class);
        
        Root<Prescription> prescription = criQuery.from(Prescription.class);
        //join to doctor lastName
        criQuery.select(prescription);
        
        TypedQuery<Prescription> query = em.createQuery(criQuery);
        return query.getResultList();
    }

    public List<Prescription> findPrescriptionsByPatient(String lastName) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Prescription> criQuery = builder.createQuery(Prescription.class);
        
        Root<Prescription> prescription = criQuery.from(Prescription.class);
        //join to patient lastName
        criQuery.select(prescription);
        
        TypedQuery<Prescription> query = em.createQuery(criQuery);
        return query.getResultList();
    }
    
    public void delete(Prescription prescription) {
        prescription = em.merge(prescription);
        em.remove(prescription);
    }
}
