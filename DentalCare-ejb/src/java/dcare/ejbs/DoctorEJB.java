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
public class DoctorEJB {

    @PersistenceContext
    private EntityManager em;
    
    public void save(Doctor doctor) {
        if(doctor.getId() != 0) {
            doctor.setModifiedAt(new Date());
            em.merge(doctor);
        }
        else {
            doctor.setCreatedAt(new Date());
            em.persist(doctor); 
        }
    }

    public Doctor find(int id) {
        return em.find(Doctor.class, id);
    }

    public List<Doctor> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Doctor.class));
        return (List<Doctor>)em.createQuery(cq).getResultList();
    }
    
    public List<Doctor> findAllDoctors() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Doctor> criQuery = builder.createQuery(Doctor.class);
        
        Root<Doctor> doctor = criQuery.from(Doctor.class);
        criQuery.select(doctor);
        
        TypedQuery<Doctor> query = em.createQuery(criQuery);
        return query.getResultList();
    }

    public List<Doctor> findDoctorsByStatus(UtilityClass.WorkStatusEnum workingStatus) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Doctor> criQuery = builder.createQuery(Doctor.class);
        
        Root<Doctor> doctor = criQuery.from(Doctor.class);
        criQuery.where(builder.equal(doctor.get(Doctor_.workStatus), workingStatus.toString()));
        criQuery.select(doctor);
        
        TypedQuery<Doctor> query = em.createQuery(criQuery);
        return query.getResultList();
    }
    
    public void delete(Doctor doctor) {
        doctor = em.merge(doctor);
        em.remove(doctor);
    }
}
