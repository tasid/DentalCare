/*
 * @Copyright at 99.99%
 */
package dcare.entities;

public class UtilityClass {    
    public enum UserRoleEnum {
        Administrator, 
        Doctor, 
        Employee, 
        Patient
    }
    
    public enum GenderEnum {
        Male, 
        Female, 
        Other
    }
    
    public enum MaritalStatusEnum {
        Married, 
        Single, 
        Devorced, 
        Other;
    }
    
    public enum WorkStatusEnum {
        Active, 
        Inactive, 
        Other
    }
    
    public enum AppointmentStatusEnum {
        Open,
        Cancel,
        Done
    }
    
    public enum PrescriptionStatusEnum {
        Open,
        Done
    }
}
