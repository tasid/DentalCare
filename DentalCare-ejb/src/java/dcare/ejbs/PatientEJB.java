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
public class PatientEJB {

    @PersistenceContext
    private EntityManager em;
    
    public void save(Patient patient) {
        if(patient.getId() != 0) {
            patient.setModifiedAt(new Date());
            em.merge(patient);
        }
        else {
            patient.setCreatedAt(new Date());
            em.persist(patient); 
        }
    }

    public Patient find(int id) {
        return em.find(Patient.class, id);
    }

    public List<Patient> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Patient.class));
        return (List<Patient>)em.createQuery(cq).getResultList();
    }
    
    public List<Patient> findAllPatients() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Patient> criQuery = builder.createQuery(Patient.class);
        
        Root<Patient> patient = criQuery.from(Patient.class);
        criQuery.select(patient);
        
        TypedQuery<Patient> query = em.createQuery(criQuery);
        return query.getResultList();
    }

    public void delete(Patient patient) {
        patient = em.merge(patient);
        em.remove(patient);
    }
}
