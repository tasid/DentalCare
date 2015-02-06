/*
 * @Copyright at 99.99%
 */
package dcare.mbs;

import dcare.ejbs.*;
import dcare.entities.*;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "EmployeeMB")
@RequestScoped
public class EmployeeMB {

    @EJB
    private EmployeeEJB employeeEJB;

    private Employee employee;
    private List<Employee> employeeList;

    public EmployeeMB() { }
    
    @PostConstruct
    public void init() {
        employee = new Employee();
        employeeList = new ArrayList();
    }
    
    public String save() {
        try {
            employeeEJB.save(this.employee);
            return "employeeList";
        }
        catch(Exception ex) {            
            return "errorPage";
        }
    }
    
    public String editEmployee(int id){
        try {
            this.employee = employeeEJB.find(id);
            return "employee";
        }
        catch(Exception ex) {
            return "errorPage";
        }
    }
    
    public String deleteEmployee(int id){
        try {
            this.employee = employeeEJB.find(id);
            employeeEJB.delete(this.employee);
            return "employeeList";
        } 
        catch(Exception ex) {
            return "errorPage";
        }
    }
    
    public String viewEmployee(int id){
        try {
            this.employee = employeeEJB.find(id);
            return "viewEmployee";
        }
        catch(Exception ex) {
            return "errorPage";
        }
    }
    
    public List<Employee> getEmployeeList() {
        this.employeeList = employeeEJB.findAllEmployees();
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}