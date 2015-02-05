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
public class EmployeeEJB {

    @PersistenceContext
    private EntityManager em;
    
    public void save(Employee employee) {
        em.persist(employee);
    }
    
    public void update(Employee employee) {
        em.merge(employee);
    }

    public Employee find(int id) {
        return em.find(Employee.class, id);
    }

    public Employee findAll(int id) {
        return em.find(Employee.class, id);
    }

    public void delete(int id) {
        Employee employee = find(id);
        em.remove(employee);
    }    
}
