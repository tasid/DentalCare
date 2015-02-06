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
public class EmployeeEJB {

    @PersistenceContext
    private EntityManager em;
    
    public void save(Employee employee) {
        if(employee.getId() != 0) {
            employee.setModifiedAt(new Date());
            em.merge(employee);
        }
        else {
            employee.setCreatedAt(new Date());
            em.persist(employee); 
        }
    }

    public Employee find(int id) {
        return em.find(Employee.class, id);
    }

    public List<Employee> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Employee.class));
        return (List<Employee>)em.createQuery(cq).getResultList();
    }
    
    public List<Employee> findAllEmployees() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criQuery = builder.createQuery(Employee.class);
        
        Root<Employee> employee = criQuery.from(Employee.class);
        criQuery.select(employee);
        
        TypedQuery<Employee> query = em.createQuery(criQuery);
        return query.getResultList();
    }

    public void delete(Employee employee) {
        employee = em.merge(employee);
        em.remove(employee);
    }   
}