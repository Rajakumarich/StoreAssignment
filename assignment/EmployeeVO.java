package assignment;


/**
 *
 * @author Rajakumari Chinthoti
 */
public class EmployeeVO {
    private int empId;
    private String empName;
    private String phoneNo;
    private String cardNo;
    private boolean isAffiliate=false;
    
    public EmployeeVO(){        
    }


    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardNo() {
        return cardNo;
    }


    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setIsAffiliate(boolean isAffiliate) {
        this.isAffiliate = isAffiliate;
    }

    public boolean getIsAffiliate() {
        return isAffiliate;
    }
}
