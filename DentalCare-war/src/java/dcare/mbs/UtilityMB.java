/*
 * @Copyright at 99.99%
 */
package dcare.mbs;

import dcare.entities.UtilityClass.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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

    public PrescriptionStatusEnum[] getPrescriptionStatuses() {
        return PrescriptionStatusEnum.values();
    }
    
    public Map<String, Object> getSessionMap() {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    }
    
    public void clearSessionMap() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
    }
    
    public Object deleteSession(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(key);
    }
    
    public Object getSessionValue(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
    }

    public void setSessionValue(String key, Object value) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
    }
    
    public String convertTime(Date time){ 
        Date date = new Date(); 
        Format format = new SimpleDateFormat("hh:mm tt"); 
        return format.format(date);
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
