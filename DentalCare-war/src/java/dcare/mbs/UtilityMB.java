/*
 * @Copyright at 99.99%
 */
package dcare.mbs;

import dcare.entities.UtilityClass.*;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name = "UtilityMB")
@ApplicationScoped
public class UtilityMB {

    public UtilityMB() { }
    
    public MaritalStatusEnum[] getMaritalStatuses() {
        return MaritalStatusEnum.values();
    }

    public WorkStatusEnum[] getWorkStatuses() {
        return WorkStatusEnum.values();
    }

    public AppointmentStatusEnum[] getAppointmentStatuses() {
        return AppointmentStatusEnum.values();
    }

    public GenderEnum[] getGenders() {
        return GenderEnum.values();
    }

    public UserRoleEnum[] getUserRoles() {
        return UserRoleEnum.values();
    }
    
    public List<String> fromEnum(String cname) {
        List<String> names = new ArrayList<>();
        try {
            Class c = Class.forName(cname);
            Object[] r = c.getEnumConstants();
            if (r != null) {
                for (Object o : r) {
                    names.add(o.toString());
                }
            }
        } catch (ClassNotFoundException ex) {
            ShowError(ex);
        }
        return names;
    }
    
    public static void ShowError(Exception ex) {
        FacesMessage msg=new FacesMessage(FacesMessage.SEVERITY_ERROR,ex.getMessage(),"Error Message");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
